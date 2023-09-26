package org.example;

import org.example.data.*;
import javax.persistence.criteria.*;
import javax.persistence.EntityManager;
import java.util.List;

public class CriteriaAPI {
    public static List<Object[]> getNonResidentsInfo(EntityManager entityManager) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = criteriaBuilder.createQuery(Object[].class);
        Root<Tenant> tenantRoot = query.from(Tenant.class);

        Subquery<Long> subquery = createSubquere(criteriaBuilder, query, tenantRoot);

        query.multiselect(
                tenantRoot.join("member").get("name"),
                tenantRoot.get("autoAllow"),
                tenantRoot.join("member").join("memberRole").get("role"),
                tenantRoot.join("flat").get("number"),
                tenantRoot.join("flat").get("level"),
                tenantRoot.join("flat").get("square"),
                tenantRoot.join("flat").get("building").get("address")
        );

        Predicate condition = createCondition(criteriaBuilder, tenantRoot, subquery);

        query.where(condition);

        List<Object[]> result = entityManager.createQuery(query).getResultList();
        return result;
    }

    private static Subquery<Long> createSubquere(CriteriaBuilder criteriaBuilder, CriteriaQuery<Object[]> query, Root<Tenant> tenantRoot) {
        Subquery<Long> subquery = query.subquery(Long.class);
        
        Root<Tenant> subqueryTenantRoot = subquery.from(Tenant.class);

        Join<Tenant, Flat> subqueryFlatJoin = subqueryTenantRoot.join("flat");
        subquery.select(criteriaBuilder.count(subqueryFlatJoin.get("id")))
                .where(criteriaBuilder.equal(subqueryTenantRoot.get("id"), tenantRoot.join("member").get("id")));
        return subquery;
    }

    private static Predicate createCondition(CriteriaBuilder criteriaBuilder, Root<Tenant> tenantRoot, Subquery<Long> subquery) {
        return criteriaBuilder.and(
                criteriaBuilder.notEqual(tenantRoot.get("autoAllow"), (byte) 1),
                criteriaBuilder.lessThan(subquery, 2L));
    }
}
