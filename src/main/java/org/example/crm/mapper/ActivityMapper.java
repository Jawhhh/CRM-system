package org.example.crm.mapper;

import org.example.crm.dto.ActivityRequest;
import org.example.crm.dto.ActivityResponse;
import org.example.crm.entity.Activity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ActivityMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client", ignore = true)
    @Mapping(source = "comment", target = "description")
    Activity toEntity(ActivityRequest request);

    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "activityDate", target = "activity_date")
    ActivityResponse toResponse(Activity activity);
}
