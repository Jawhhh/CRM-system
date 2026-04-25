package org.example.crm.it;

import org.example.crm.repository.DealRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DealRepositoryTest {

    @Autowired
    private DealRepository dealRepository;

    @Test
    @DisplayName("Проверка корректности работы аналитического запроса")
    void testAnalyticsQuery() {
        // Тут можно добавить пару тестовых сделок в H2 базу
        var summary = dealRepository.getDealsSummaryByStatus();

        assertNotNull(summary);
        // Проверяем, что запрос выполняется без ошибок
    }
}
