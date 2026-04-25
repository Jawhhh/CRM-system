package org.example.crm.mapper;

import org.example.crm.dto.ClientRequest;
import org.example.crm.dto.ClientResponse;
import org.example.crm.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientMapper {

    // Из Request в Entity (id и createdAt игнорируются, т.к. генерятся БД)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deals", ignore = true)
    @Mapping(target = "activities", ignore = true)
    Client toEntity(ClientRequest request);

    // Из Entity в Response
    ClientResponse toResponse(Client client);
}
