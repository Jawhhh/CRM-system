package org.example.crm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Модель сделки")
public class DealRequest {
    @Schema(description = "ID клиента из базы данных")
    @NotNull
    private UUID clientId;

    @Schema(description = "Сумма сделки в рублях", example = "150000.00")
    @Positive(message = "Сумма должна быть больше нуля")
    private Double amount;

    @Schema(description = "Текущий статус", allowableValues = {"NEW", "NEGOTIATION", "PAID", "CLOSED"})
    private String status;
}
