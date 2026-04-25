package org.example.crm.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.crm.dto.ActivityRequest;
import org.example.crm.dto.ActivityResponse;
import org.example.crm.service.ActivityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/activities")
@Tag(name = "Активности", description = "Логгирование взаимодействий с клиентами (звонки, встречи)")
public class ActivityController {

    private final ActivityService activityService;

    @Operation(summary = "Добавить запись о взаимодействии")
    @PostMapping
    public void logActivity(@Valid @RequestBody ActivityRequest request) {
        activityService.logActivity(request);
    }
}

