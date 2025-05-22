package com.dkelly205.tickets.entity;


import com.dkelly205.tickets.enums.EventStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="events")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private LocalDateTime startDate;

    @Column(nullable=false)
    private LocalDateTime endDate;

    @Column(nullable=false)
    private String venue;

    @Column(nullable=false)
    private LocalDateTime salesOpen;

    @Column(nullable=false)
    private LocalDateTime salesClosed;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "organiser_id")
    private User organiser;

    @ManyToMany(mappedBy = "attendingEvents")
    private List<User> attendees = new ArrayList<>();

    @ManyToMany(mappedBy = "staffingEvents")
    private List<User> staff = new ArrayList<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<TicketType> ticketTypes = new ArrayList<>();

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id) && Objects.equals(name, event.name) && Objects.equals(startDate, event.startDate) && Objects.equals(endDate, event.endDate) && Objects.equals(venue, event.venue) && Objects.equals(salesOpen, event.salesOpen) && Objects.equals(salesClosed, event.salesClosed) && eventStatus == event.eventStatus && Objects.equals(createdDate, event.createdDate) && Objects.equals(updatedAt, event.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, startDate, endDate, venue, salesOpen, salesClosed, eventStatus, createdDate, updatedAt);
    }
}
