package org.example.crm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Запрос на создание/обновление клиента")
public class ClientRequest {
    @Schema(description = "Наименование организации", example = "ООО 'Технострой'")
    @NotBlank(message = "Название не может быть пустым")
    public String name;

    @Schema(description = "ИНН организации (10 или 12 цифр)", example = "7701234567")
    @Pattern(regexp = "^(20[0-9]{9}|[0-9]{12})$", message = "Некорректный формат ИНН")
    public String inn;

    @Schema(description = "Контактный Email", example = "info@techno.ru")
    @Email(message = "Некорректный формат email")
    public String email;

    @Pattern(regexp = "^\\d{10}$", message = "Номер телефона должен состоять из 10 цифр")
    private String phone;

    @NotBlank(message = "Название не может быть пустым")
    private String address;
}
