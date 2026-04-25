package org.example.crm.service;

import lombok.RequiredArgsConstructor;
import org.example.crm.dto.ClientRequest;
import org.example.crm.dto.ClientResponse;
import org.example.crm.entity.Client;
import org.example.crm.exception.BusinessException;
import org.example.crm.mapper.ClientMapper;
import org.example.crm.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Transactional
    public ClientResponse createClient(ClientRequest request) {
        // Проверка бизнес-логики: уникальность ИНН
        if (clientRepository.existsByInn(request.getInn())) {
            throw new BusinessException("Контрагент с ИНН " + request.getInn() + " уже зарегистрирован в системе");
        }

        Client client = clientMapper.toEntity(request);
        client.setCreatedAt(ZonedDateTime.now());
        Client savedClient = clientRepository.save(client);
        return clientMapper.toResponse(savedClient);
    }

    public List<ClientResponse> getAllClients() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toResponse)
                .collect(Collectors.toList());
    }

    public Client findEntityById(UUID id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Клиент с ID " + id + " не найден"));
    }
}
