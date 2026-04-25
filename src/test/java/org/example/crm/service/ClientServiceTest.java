package org.example.crm.service;

import org.example.crm.dto.ClientRequest;
import org.example.crm.entity.Client;
import org.example.crm.exception.BusinessException;
import org.example.crm.mapper.ClientMapper;
import org.example.crm.repository.ClientRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;
    @Mock
    private ClientMapper clientMapper;

    @InjectMocks
    private ClientService clientService;

    @Test
    @DisplayName("Успешное создание клиента")
    void createClient_Success() {
        // Given
        ClientRequest request = new ClientRequest();
        request.setInn("1234567890");

        when(clientRepository.existsByInn(anyString())).thenReturn(false);
        when(clientMapper.toEntity(any())).thenReturn(new Client());
        when(clientRepository.save(any())).thenReturn(new Client());

        // When
        clientService.createClient(request);

        // Then
        verify(clientRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Ошибка при дублировании ИНН")
    void createClient_ThrowsException_WhenInnExists() {
        // Given
        ClientRequest request = new ClientRequest();
        request.setInn("1234567890");
        when(clientRepository.existsByInn("1234567890")).thenReturn(true);

        // When & Then
        assertThrows(BusinessException.class, () -> clientService.createClient(request));
    }
}