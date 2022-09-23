package ru.practicum.explorewithme.main.model;

import java.time.LocalDateTime;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents an event in a time.
 */
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Builder(setterPrefix = "with")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "events")
public class Event {

    /* Needed for Hibernate */
    protected Event() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String title;

    private String annotation;

    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private EventCategory category;

    @Column(name = "event_date")
    private LocalDateTime eventDate;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "lat", column = @Column(name = "location_lat")),
        @AttributeOverride(name = "lng", column = @Column(name = "location_lng")),
    })
    private Location location;

    private boolean paid;

    @Column(name = "participant_limit")
    private int participantLimit;

    @Column(name = "confirmed_requests")
    private int confirmedRequests;

    @Column(name = "request_moderation")
    private boolean requestModeration;

    @ManyToOne
    @JoinColumn(name = "initiator_id")
    private User initiator;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EventState state = EventState.PENDING;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "published_on")
    private LocalDateTime publishedOn;

    private long views;

    public void increaseViews() {
        views++;
    }
}
