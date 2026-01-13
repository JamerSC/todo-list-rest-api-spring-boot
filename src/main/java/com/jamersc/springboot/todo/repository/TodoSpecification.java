package com.jamersc.springboot.todo.repository;

import com.jamersc.springboot.todo.entity.Status;
import com.jamersc.springboot.todo.entity.Todo;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class TodoSpecification {

    // ✅ Composable Specifications

    /* ===================== SEARCH ===================== */
    public static Specification<Todo> search(String search) {
        return (root, query, cb) -> {
            if (!StringUtils.hasText(search)) {
                return cb.conjunction();
            }

            String pattern = "%" + search.toLowerCase() + "%";

            return cb.or(
                    cb.like(cb.lower(root.get("title")), pattern),
                    cb.like(cb.lower(root.get("description")), pattern)
            );
        };
    }

    /* ===================== STATUS ===================== */
    public static Specification<Todo> hasStatus(Status status) {
        return (root, query, cb) ->
                status == null
                        ? cb.conjunction()
                        : cb.equal(root.get("status"), status);

    }

    /* ===================== DATE RANGE ===================== */
    public static Specification<Todo> dateRange(
            LocalDate dateFrom,
            LocalDate dateTo
    ) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (dateFrom != null) {

                OffsetDateTime start = dateFrom
                        .atStartOfDay()
                        .atOffset(ZoneOffset.UTC);

                predicates.add(cb.greaterThanOrEqualTo(
                        root.get("createdAt"), start
                ));
            }

            if (dateTo != null) {

                OffsetDateTime end = dateTo
                        .plusDays(1)
                        .atStartOfDay()
                        .atOffset(ZoneOffset.UTC);

                predicates.add(cb.lessThan(
                        root.get("createdAt"), end
                ));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
