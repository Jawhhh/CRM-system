package org.example.crm.mapper;

import org.example.crm.dto.DealRequest;
import org.example.crm.dto.DealResponse;
import org.example.crm.entity.Deal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DealMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client", ignore = true) // Сеттим клиента в сервисе через репозиторий
    Deal toEntity(DealRequest request);

    @Mapping(source = "client.id", target = "clientId")
    DealResponse toResponse(Deal deal);
}