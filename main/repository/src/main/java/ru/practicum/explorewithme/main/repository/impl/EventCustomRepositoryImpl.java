package ru.practicum.explorewithme.main.repository.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.practicum.explorewithme.main.model.Event;
import ru.practicum.explorewithme.main.model.EventState;
import ru.practicum.explorewithme.main.repository.EventCustomRepository;

@Repository
@AllArgsConstructor
public class EventCustomRepositoryImpl implements EventCustomRepository {

    private final EntityManager em;

    @Override
    public List<Event> searchForEvents(
            String text,
            List<Long> categories,
            Boolean paid,
            LocalDateTime rangeStart,
            LocalDateTime rangeEnd,
            Boolean onlyAvailable,
            String sort,
            int from,
            int size
    ) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Event> cq = cb.createQuery(Event.class);

        Root<Event> event = cq.from(Event.class);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.equal(event.get("state"), EventState.PUBLISHED));

        if (text != null) {
            String search = text.toUpperCase();
            predicates.add(
                cb.or(
                    cb.like(cb.upper(event.get("annotation")), "%" + search + "%"),
                    cb.like(cb.upper(event.get("description")), "%" + search + "%")
                )
            );
        }

        if (categories != null && categories.size() > 0) {
            In<Long> inClause = cb.in(event.get("category"));
            for (long catId : categories) {
                inClause.value(catId);
            }
            predicates.add(inClause);
        }

        if (paid != null) {
            predicates.add(cb.equal(event.get("paid"), paid));
        }

        if (onlyAvailable != null && onlyAvailable) {
            predicates.add(
                cb.or(
                    cb.le(event.get("confirmedRequests"), event.get("participantLimit")),
                    cb.le(event.get("participantLimit"), 0)
                )
            );
        }

        if (sort.equals("EVENT_DATE")) {
            cq.orderBy(cb.desc(event.get("eventDate")));
        } else if (sort.equals("VIEWS")) {
            cq.orderBy(cb.desc(event.get("views")));
        }

        if (rangeStart == null && rangeEnd == null) {
            predicates.add(cb.greaterThan(event.get("eventDate"), LocalDateTime.now()));
        }

        if (rangeStart != null) {
            predicates.add(cb.greaterThan(event.get("eventDate"), rangeStart));
        }

        if (rangeEnd != null) {
            predicates.add(cb.lessThan(event.get("eventDate"), rangeEnd));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq)
            .setFirstResult(from)
            .setMaxResults(size)
            .getResultList();
    }

    @Override
    public List<Event> searchForEvents(
        List<Long> users,
        List<EventState> states,
        List<Long> categories,
        LocalDateTime rangeStart,
        LocalDateTime rangeEnd,
        int from,
        int size
    ) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Event> cq = cb.createQuery(Event.class);

        Root<Event> event = cq.from(Event.class);
        List<Predicate> predicates = new ArrayList<>();

        if (users != null && users.size() > 0) {
            In<Long> inClause = cb.in(event.get("initiator"));
            for (long user : users) {
                inClause.value(user);
            }
            predicates.add(inClause);
        }

        if (states != null && states.size() > 0) {
            In<EventState> inClause = cb.in(event.get("state"));
            for (EventState state : states) {
                inClause.value(state);
            }
            predicates.add(inClause);
        }

        if (categories != null && categories.size() > 0) {
            In<Long> inClause = cb.in(event.get("category"));
            for (long catId : categories) {
                inClause.value(catId);
            }
            predicates.add(inClause);
        }

        if (rangeStart == null && rangeEnd == null) {
            predicates.add(cb.greaterThan(event.get("eventDate"), LocalDateTime.now()));
        }

        if (rangeStart != null) {
            predicates.add(cb.greaterThan(event.get("eventDate"), rangeStart));
        }

        if (rangeEnd != null) {
            predicates.add(cb.lessThan(event.get("eventDate"), rangeEnd));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq)
            .setFirstResult(from)
            .setMaxResults(size)
            .getResultList();
    }
}
