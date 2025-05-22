package com.dkelly205.tickets.entity;

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
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @Column(name="id", nullable = false, updatable = false)
    private Long id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false, unique = true)
    private String email;

    @OneToMany(mappedBy = "organiser", cascade = CascadeType.ALL)
    private List<Event> organisedEvents = new ArrayList<>();

    @ManyToMany
    @JoinTable(
          name = "user_attending_events",
          joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="event_id")
    )
    private List<Event> attendingEvents = new ArrayList<>();


    @ManyToMany
    @JoinTable(
            name = "user_staffing_events",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="event_id")
    )
    private List<Event> staffingEvents = new ArrayList<>();

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(createdDate, user.createdDate) && Objects.equals(updatedAt, user.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, createdDate, updatedAt);
    }
}
