package com.grechjoseph.melitabackendtask.service.impl;

import com.grechjoseph.melitabackendtask.api.dto.ApiCustomer;
import com.grechjoseph.melitabackendtask.client.melita.client.MelitaCustomerClient;
import com.grechjoseph.melitabackendtask.client.melita.dto.CustomerClientIdsCollection;
import com.grechjoseph.melitabackendtask.client.melita.dto.response.CustomerClientListResponse;
import com.grechjoseph.melitabackendtask.client.melita.dto.response.CustomerClientResponse;
import com.grechjoseph.melitabackendtask.domain.data.Customer;
import com.grechjoseph.melitabackendtask.domain.enums.CustomerStatus;
import com.grechjoseph.melitabackendtask.domain.enums.CustomerType;
import com.grechjoseph.melitabackendtask.domain.exception.BaseException;
import com.grechjoseph.melitabackendtask.mapping.ModelMapper;
import com.grechjoseph.melitabackendtask.utils.CustomFeignException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static com.grechjoseph.melitabackendtask.client.melita.dto.CustomerClientResponseCode.SUCCESS;
import static com.grechjoseph.melitabackendtask.domain.enums.CustomerStatus.ACTIVE;
import static com.grechjoseph.melitabackendtask.domain.enums.CustomerType.REGULAR;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Unit Tests for {@link MelitaCustomerServiceImpl}.
 */
@SpringBootTest
public class MelitaCustomerServiceImplTest {

    public static final String CUSTOMER_NAME = "Tester";
    public static final String CUSTOMER_SURNAME = "Of Stuff";
    public static final CustomerType CUSTOMER_TYPE = REGULAR;
    public static final CustomerStatus CUSTOMER_STATUS = ACTIVE;
    public static final int SEQUENCE_ID = 0;
    public static final String CUSTOMER_ID = UUID.randomUUID().toString();

    @Mock
    private MelitaCustomerClient mockMelitaCustomerClient;

    @Autowired
    private ModelMapper modelMapper;

    private MelitaCustomerServiceImpl melitaCustomerService;

    private Customer customer;

    @BeforeEach
    public void init() {
        melitaCustomerService = new MelitaCustomerServiceImpl(mockMelitaCustomerClient, modelMapper);
        customer = new Customer();
        customer.setName(CUSTOMER_NAME);
        customer.setSurname(CUSTOMER_SURNAME);
        customer.setType(CUSTOMER_TYPE);
        customer.setStatus(CUSTOMER_STATUS);

        initMocks();
    }

    @Test
    public void createCustomer_validCustomer_shouldNotThrowException() {
        assertThatCode(() -> melitaCustomerService.createCustomer(customer)).doesNotThrowAnyException();
        verify(mockMelitaCustomerClient).createCustomer(customer);
    }

    @Test
    public void getCustomerById_existingCustomer_shouldReturnCustomer() {
        assertThatCode(() -> melitaCustomerService.getCustomerById(CUSTOMER_ID)).doesNotThrowAnyException();
        verify(mockMelitaCustomerClient).getCustomerById(CUSTOMER_ID);
    }

    @Test
    public void getCustomerById_nonExistingCustomer_shouldThrowException() {
        when(mockMelitaCustomerClient.getCustomerById(any(String.class))).thenThrow(new CustomFeignException(NOT_FOUND.value(), "Customer could not be found"));
        assertThatThrownBy(() -> melitaCustomerService.getCustomerById(CUSTOMER_ID)).isInstanceOf(BaseException.class);
        verify(mockMelitaCustomerClient).getCustomerById(any(String.class));
    }

    @Test
    public void getCustomers_shouldReturnObjectWithListOfIds() {
        assertThatCode(() -> melitaCustomerService.getAllCustomers()).doesNotThrowAnyException();
        verify(mockMelitaCustomerClient).getCustomers();
    }

    private void initMocks() {
        final ApiCustomer apiCustomer = new ApiCustomer(customer.getName(),
                customer.getSurname(),
                customer.getType(),
                customer.getStatus());
        final CustomerClientResponse customerClientResponse = new CustomerClientResponse(SEQUENCE_ID, apiCustomer, SUCCESS);
        final CustomerClientIdsCollection customerClientIdsCollection = new CustomerClientIdsCollection();
        customerClientIdsCollection.setCustomerIds(Set.copyOf(List.of(CUSTOMER_ID)));
        final CustomerClientListResponse customerClientListResponse = new CustomerClientListResponse(SEQUENCE_ID,
                customerClientIdsCollection,
                SUCCESS);

        when(mockMelitaCustomerClient.createCustomer(customer)).thenReturn(customerClientResponse);
        when(mockMelitaCustomerClient.getCustomerById(any(String.class))).thenReturn(customerClientResponse);
        when(mockMelitaCustomerClient.getCustomers()).thenReturn(customerClientListResponse);
    }
}
