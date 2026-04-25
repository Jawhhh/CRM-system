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
@Schema(description = "Клиент")
public class ClientResponse {
    private UUID id;

    @Schema(description = "Наименование организации", example = "ООО 'Технострой'")
    public String name;

    @Schema(description = "ИНН организации (10 или 12 цифр)", example = "7701234567")
    public String inn;

    @Schema(description = "Контактный Email", example = "info@techno.ru")
    public String email;

    private String phone;

    private String address;

    private ZonedDateTime createdAt;
}
