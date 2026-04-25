package org.example.crm.repository;

import org.example.crm.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {

    // Проверка, существует ли уже клиент с таким ИНН (нужно для валидации при создании)
    boolean existsByInn(String inn);

    // Поиск клиента по точному совпадению ИНН
    Client findByInn(String inn);

    // Умный поиск (для строки поиска): ищем совпадения без учета регистра
    List<Client> findByNameContainingIgnoreCase(String name);
}
