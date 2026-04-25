package org.example.crm.service;

import lombok.RequiredArgsConstructor;
import org.example.crm.dto.DealRequest;
import org.example.crm.dto.DealResponse;
import org.example.crm.entity.Client;
import org.example.crm.entity.Deal;
import org.example.crm.entity.DealStatus;
import org.example.crm.exception.BusinessException;
import org.example.crm.mapper.DealMapper;
import org.example.crm.repository.DealRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DealService {
    private final DealRepository dealRepository;
    private final ClientService clientService; // Внедряем сервис клиента для проверки существования
    private final DealMapper dealMapper;

    @Transactional
    public DealResponse createDeal(DealRequest request) {
        // 1. Проверяем, существует ли клиент
        Client client = clientService.findEntityById(request.getClientId());

        // 2. Маппим и устанавливаем связь
        Deal deal = dealMapper.toEntity(request);
        deal.setClient(client);
        deal.setCreatedAt(ZonedDateTime.now());

        // Устанавливаем начальный статус, если не передан
        if (deal.getStatus() == null) deal.setStatus(DealStatus.NEW);

        Deal savedDeal = dealRepository.save(deal);
        return dealMapper.toResponse(savedDeal);
    }

    @Transactional
    public void updateStatus(UUID dealId, String newStatus) {
        Deal deal = dealRepository.findById(dealId)
                .orElseThrow(() -> new BusinessException("Сделка не найдена"));
        try {
            DealStatus dealStatus = DealStatus.valueOf(newStatus);
            deal.setStatus(dealStatus);
            deal.setUpdatedAt(ZonedDateTime.now());
        } catch (Exception e) {
            throw new BusinessException("Переданного статуса не существует");
        }
    }

    public Map<String, Double> getAnalyticsSummary() {
        // Получаем проекции из репозитория и превращаем в удобный Map для фронтенда
        return dealRepository.getDealsSummaryByStatus().stream()
                .collect(Collectors.toMap(
                        p -> p.getStatus().name(),
                        p -> p.getTotalAmount().doubleValue()
                ));
    }
}
