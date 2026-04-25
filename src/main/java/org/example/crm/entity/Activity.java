package org.example.crm.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "activities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // Связь с таблицей clients
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ActivityType type; // enum: CALL, MEETING, EMAIL

    @Column(nullable = false, length = 5000)
    private String description;

    @CreationTimestamp
    @Column(name = "activity_date", updatable = false)
    private ZonedDateTime activityDate;
}


