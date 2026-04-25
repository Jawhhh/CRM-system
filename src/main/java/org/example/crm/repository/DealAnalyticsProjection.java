package org.example.crm.repository;

import org.example.crm.entity.DealStatus;

import java.math.BigDecimal;

public interface DealAnalyticsProjection {
    DealStatus getStatus(); // Наш Enum из Entity
    BigDecimal getTotalAmount();
}
