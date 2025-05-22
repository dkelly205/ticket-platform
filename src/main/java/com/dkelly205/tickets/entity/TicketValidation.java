package com.dkelly205.tickets.entity;


import com.dkelly205.tickets.enums.TicketValidationMethod;
import com.dkelly205.tickets.enums.TicketValidationStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="ticket_validations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class TicketValidation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketValidationStatus ticketValidationStatus;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketValidationMethod ticketValidationMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;


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
        TicketValidation that = (TicketValidation) o;
        return Objects.equals(id, that.id) && ticketValidationStatus == that.ticketValidationStatus && ticketValidationMethod == that.ticketValidationMethod && Objects.equals(createdDate, that.createdDate) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ticketValidationStatus, ticketValidationMethod, createdDate, updatedAt);
    }
}
