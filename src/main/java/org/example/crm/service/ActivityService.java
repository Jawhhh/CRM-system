package org.example.crm.service;

import lombok.RequiredArgsConstructor;
import org.example.crm.dto.ActivityRequest;
import org.example.crm.dto.ActivityResponse;
import org.example.crm.entity.Activity;
import org.example.crm.entity.Client;
import org.example.crm.mapper.ActivityMapper;
import org.example.crm.repository.ActivityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final ClientService clientService;
    private final ActivityMapper activityMapper;

    @Transactional
    public ActivityResponse logActivity(ActivityRequest request) {
        Client client = clientService.findEntityById(request.getClientId());

        Activity activity = activityMapper.toEntity(request);
        activity.setActivityDate(ZonedDateTime.now());
        activity.setClient(client);

        Activity savedActivity = activityRepository.save(activity);
        return activityMapper.toResponse(savedActivity);
    }

    public List<ActivityResponse> getClientHistory(UUID clientId) {
        return activityRepository.findByClientIdOrderByActivityDateDesc(clientId).stream()
                .map(activityMapper::toResponse)
                .collect(Collectors.toList());
    }
}
