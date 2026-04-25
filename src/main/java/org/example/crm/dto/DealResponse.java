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
@Schema(description = "Модель сделки")
public class DealResponse {
    @Schema(description = "ID клиента из базы данных")
    private UUID id;

    @Schema(description = "ID клиента из базы данных")
    private UUID clientId;

    @Schema(description = "Сумма сделки в рублях", example = "150000.00")
    private Double amount;

    @Schema(description = "Текущий статус", allowableValues = {"NEW", "NEGOTIATION", "PAID", "CLOSED"})
    private String status;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;
}
