package org.example.crm.repository;

import org.example.crm.entity.Deal;
import org.example.crm.entity.DealStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface DealRepository extends JpaRepository<Deal, UUID> {

    // Получить все сделки конкретного клиента
    List<Deal> findByClientId(UUID clientId);

    // Аналитика: Сумма всех сделок, сгруппированная по их статусам.
    // Spring автоматически замаппит результат в наш интерфейс-проекцию DealAnalyticsProjection
    @Query("SELECT d.status AS status, SUM(d.amount) AS totalAmount FROM Deal d GROUP BY d.status")
    List<DealAnalyticsProjection> getDealsSummaryByStatus();
}


