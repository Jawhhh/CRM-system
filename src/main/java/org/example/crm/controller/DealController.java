package org.example.crm.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.crm.dto.DealRequest;
import org.example.crm.dto.DealResponse;
import org.example.crm.service.DealService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/deals")
@Tag(name = "Сделки", description = "Управление воронкой продаж и финансовыми показателями")
public class DealController {

    private final DealService dealService;

    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создание новой сделки", description = "Инициализирует сделку для конкретного клиента")
    @PostMapping
    public DealResponse createDeal(@Valid @RequestBody DealRequest request) {
        return dealService.createDeal(request);
    }

    @Operation(summary = "Обновление статуса сделки", description = "Переводит сделку на следующий этап воронки")
    @PatchMapping("/{id}/status")
    public void updateStatus(
            @Parameter(description = "ID сделки (UUID)") @PathVariable UUID id,
            @Parameter(description = "Новый статус") @RequestParam String status) {
        dealService.updateStatus(id, status);
    }

    @Operation(summary = "Аналитическая сводка", description = "Возвращает сумму сделок по этапам")
    @GetMapping("/analytics/summary")
    public Map<String, Double> getAnalytics() {
        return dealService.getAnalyticsSummary();
    }
}

