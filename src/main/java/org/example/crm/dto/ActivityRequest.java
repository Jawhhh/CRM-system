package org.example.crm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Запись об активности менеджера")
public class ActivityRequest {
    @NotNull
    private UUID clientId;

    @Schema(description = "Тип связи", example = "CALL")
    @NotBlank
    private String type; // CALL, MEETING, EMAIL

    @Schema(description = "Комментарий по итогам", example = "Договорились о встрече в четверг")
    @Size(max = 500)
    private String comment;
}
