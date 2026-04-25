package org.example.crm.repository;

import org.example.crm.entity.Activity;
import org.example.crm.entity.ActivityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, UUID> {

    // Получить всю историю клиента, отсортированную по дате убывания (Сначала новые)
    List<Activity> findByClientIdOrderByActivityDateDesc(UUID clientId);

    // Получить все активности определенного типа (например, только 'MEETING' - встречи)
    // для конкретного клиента
    List<Activity> findByClientIdAndTypeOrderByActivityDateDesc(UUID clientId, ActivityType type);
}
