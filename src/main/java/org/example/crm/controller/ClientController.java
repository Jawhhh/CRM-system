package org.example.crm.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.*;
import org.example.crm.dto.ActivityResponse;
import org.example.crm.dto.ClientRequest;
import org.example.crm.dto.ClientResponse;
import org.example.crm.service.ActivityService;
import org.example.crm.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/clients")
@Tag(name = "Клиенты", description = "Управление базой контрагентов АО «СФ Система»")
public class ClientController {

    private final ClientService clientService;
    private final ActivityService activityService;

    @Operation(summary = "Регистрация нового клиента", description = "Создает карточку контрагента с валидацией ИНН")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Клиент успешно создан"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации данных")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientResponse createClient(@Valid @RequestBody ClientRequest request) {
        return clientService.createClient(request);
    }

    @Operation(summary = "Получение списка всех клиентов", description = "Возвращает краткую информацию по всем контрагентам")
    @GetMapping
    public List<ClientResponse> getAllClients() {
        return clientService.getAllClients();
    }

    @Operation(summary = "Добавить запись о взаимодействии")
    @GetMapping("/{id}/activity")
    public List<ActivityResponse> getAllActivity(@PathVariable("id") UUID clientId) {
        return activityService.getClientHistory(clientId);
    }
}

