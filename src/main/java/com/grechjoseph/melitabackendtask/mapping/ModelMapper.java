package com.grechjoseph.melitabackendtask.mapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grechjoseph.melitabackendtask.api.dto.ApiCustomer;
import com.grechjoseph.melitabackendtask.api.dto.ApiExtendedCustomer;
import com.grechjoseph.melitabackendtask.domain.data.Customer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * {@link ConfigurableMapper} responsible of transforming Api Objects into Entity objects.
 */
@Primary
@Component
@RequiredArgsConstructor
public class ModelMapper extends ConfigurableMapper {

    private final ObjectMapper objectMapper;

    @Getter
    private MapperFactory mapperFactory;

    /**
     * Configure method for mapping Entity objects to Api objects.
     * @param factory Factory to use.
     */
    public void configure(MapperFactory factory) {
        super.configure(factory);
        this.mapperFactory = factory;
        registerClassMaps();
    }

    private void registerClassMaps() {
        mapperFactory.registerClassMap(mapperFactory.classMap(ApiCustomer.class, Customer.class).exclude("id")
                .byDefault()
                .toClassMap());

        mapperFactory.registerClassMap(mapperFactory.classMap(Customer.class, ApiExtendedCustomer.class)
                .byDefault()
                .toClassMap());
    }

}
