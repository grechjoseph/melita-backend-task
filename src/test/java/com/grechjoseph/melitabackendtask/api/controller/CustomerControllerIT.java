package com.grechjoseph.melitabackendtask.api.controller;

import com.grechjoseph.melitabackendtask.api.dto.ApiCustomer;
import com.grechjoseph.melitabackendtask.api.dto.ApiExtendedCustomer;
import com.grechjoseph.melitabackendtask.client.melita.client.MelitaCustomerClient;
import com.grechjoseph.melitabackendtask.client.melita.dto.CustomerClientIdsCollection;
import com.grechjoseph.melitabackendtask.client.melita.dto.CustomerClientStatusType;
import com.grechjoseph.melitabackendtask.client.melita.dto.response.CustomerClientListResponse;
import com.grechjoseph.melitabackendtask.client.melita.dto.response.CustomerClientResponse;
import com.grechjoseph.melitabackendtask.domain.data.Customer;
import com.grechjoseph.melitabackendtask.domain.enums.CustomerStatus;
import com.grechjoseph.melitabackendtask.domain.enums.CustomerType;
import com.grechjoseph.melitabackendtask.mapping.ModelMapper;
import com.grechjoseph.melitabackendtask.service.impl.MelitaCustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static com.grechjoseph.melitabackendtask.domain.enums.CustomerStatus.ACTIVE;
import static com.grechjoseph.melitabackendtask.domain.enums.CustomerType.REGULAR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Integration Tests for {@link CustomerController}.
 */
@SpringBootTest
public class CustomerControllerIT {

    public static final String CUSTOMER_NAME = "Tester";
    public static final String CUSTOMER_SURNAME = "Of Stuff";
    public static final CustomerType CUSTOMER_TYPE = REGULAR;
    public static final CustomerStatus CUSTOMER_STATUS = ACTIVE;
    public static final String SUCCESS = "SUCCESS";
    public static final int SEQUENCE_ID = 0;
    public static final String CUSTOMER_ID = UUID.randomUUID().toString();

    @Mock
    private MelitaCustomerClient mockMelitaCustomerClient;

    @Autowired
    private ModelMapper modelMapper;

    private MelitaCustomerServiceImpl melitaCustomerService;
    private CustomerController customerController;

    private ApiCustomer apiCustomer;

    @BeforeEach
    public void init() {
        melitaCustomerService = new MelitaCustomerServiceImpl(mockMelitaCustomerClient, modelMapper);
        customerController = new CustomerController(melitaCustomerService, modelMapper);
        apiCustomer = new ApiCustomer(CUSTOMER_NAME, CUSTOMER_SURNAME, CUSTOMER_TYPE, CUSTOMER_STATUS);

        initMocks();
    }

    @Test
    public void createCustomer_validCustomer_shouldReturnCreatedCustomer() {
        final ApiCustomer result = customerController.createCustomer(apiCustomer);
        assertThat(result).isEqualTo(apiCustomer);
        verify(mockMelitaCustomerClient).createCustomer(modelMapper.map(apiCustomer, Customer.class));
    }

    @Test
    public void getCustomerById_existingCustomer_shouldReturnCustomer() {
        final ApiCustomer result = customerController.getCustomerById(CUSTOMER_ID);
        assertThat(result).isEqualTo(apiCustomer);
        verify(mockMelitaCustomerClient).getCustomerById(CUSTOMER_ID);
    }

    @Test
    public void getCustomers_shouldResolveIdsToCustomerObjects() {
        final List<ApiExtendedCustomer> result = customerController.getAllCustomers();
        final ApiExtendedCustomer extendedCustomer = new ApiExtendedCustomer(CUSTOMER_ID,
                CUSTOMER_NAME,
                CUSTOMER_SURNAME,
                CUSTOMER_TYPE,
                CUSTOMER_STATUS);
        assertThat(result).isEqualTo(List.of(extendedCustomer));
        verify(mockMelitaCustomerClient).getCustomerById(CUSTOMER_ID);
        verify(mockMelitaCustomerClient).getCustomers();
    }

    private void initMocks() {
        final CustomerClientResponse customerClientResponse = new CustomerClientResponse(SEQUENCE_ID, apiCustomer, SUCCESS);
        final CustomerClientIdsCollection customerClientIdsCollection = new CustomerClientIdsCollection();
        customerClientIdsCollection.setCustomerIds(Set.copyOf(List.of(CUSTOMER_ID)));
        final CustomerClientListResponse customerClientListResponse = new CustomerClientListResponse(SEQUENCE_ID,
                customerClientIdsCollection,
                CustomerClientStatusType.SUCCESS);

        when(mockMelitaCustomerClient.createCustomer(modelMapper.map(apiCustomer, Customer.class))).thenReturn(customerClientResponse);
        when(mockMelitaCustomerClient.getCustomerById(CUSTOMER_ID)).thenReturn(customerClientResponse);
        when(mockMelitaCustomerClient.getCustomers()).thenReturn(customerClientListResponse);
    }


}
