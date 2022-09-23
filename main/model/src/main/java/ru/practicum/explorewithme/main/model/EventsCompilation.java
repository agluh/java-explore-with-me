package ru.practicum.explorewithme.main.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents a compilation of events.
 */
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Builder(setterPrefix = "with")
@AllArgsConstructor
@Entity(name = "compilations")
public class EventsCompilation {

    /* Needed for Hibernate */
    protected EventsCompilation() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String title;

    private boolean pinned;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "events_compilations",
        joinColumns = { @JoinColumn(name = "compilation_id") },
        inverseJoinColumns = { @JoinColumn(name = "event_id") }
    )
    private final Set<Event> events = new HashSet<>();

    public void addEvent(Event event) {
        events.add(event);
    }

    public void removeEvent(Event event) {
        events.remove(event);
    }
}
