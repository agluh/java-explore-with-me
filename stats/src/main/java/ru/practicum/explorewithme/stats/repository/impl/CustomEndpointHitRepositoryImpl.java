package ru.practicum.explorewithme.stats.repository.impl;

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
import ru.practicum.explorewithme.stats.model.EndpointHit;
import ru.practicum.explorewithme.stats.model.ViewsStats;
import ru.practicum.explorewithme.stats.repository.CustomEndpointHitRepository;

@Repository
@AllArgsConstructor
public class CustomEndpointHitRepositoryImpl implements CustomEndpointHitRepository {

    private final EntityManager em;

    @Override
    public List<ViewsStats> findStats(LocalDateTime start, LocalDateTime end, List<String> uris,
        boolean unique) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ViewsStats> cq = cb.createQuery(ViewsStats.class);

        Root<EndpointHit> hit = cq.from(EndpointHit.class);
        List<Predicate> predicates = new ArrayList<>();

        cq.select(cb.construct(ViewsStats.class,
            hit.get("uri"),
            hit.get("app"),
            unique ? cb.countDistinct(hit.get("ip")) : cb.count(hit.get("ip"))
        ));

        cq.groupBy(hit.get("app"), hit.get("uri"), hit.get("ip"));

        predicates.add(cb.between(hit.get("timestamp"), start, end));

        if (uris != null && uris.size() > 0) {
            In<String> inClause = cb.in(hit.get("uri"));
            for (String uri : uris) {
                inClause.value(uri);
            }
            predicates.add(inClause);
        }

        cq.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getResultList();
    }
}
