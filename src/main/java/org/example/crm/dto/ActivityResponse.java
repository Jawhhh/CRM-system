package org.example.crm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Запись об активности менеджера")
public class ActivityResponse {
    private UUID id;

    private UUID clientId;

    @Schema(description = "Тип связи", example = "CALL")
    private String type; // CALL, MEETING, EMAIL

    @Schema(description = "Комментарий по итогам", example = "Договорились о встрече в четверг")
    private String description;

    private ZonedDateTime activity_date;
}
