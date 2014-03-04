// Copyright 2014 University of Detroit Mercy.

package com.udm.common.adapter.jpa.repository;

import com.udm.common.AssertionConcern;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Stores the minimum required objects to perform a {@link javax.persistence.TypedQuery}.
 *
 * @author Edgar Rico (edgar.martinez.rico@gmail.com).
 * @param <CQ> the type of the {@code CriteriaQuery}.
 * @param <R> the type of the {@code Root}.
 */
public class QueryHelper<CQ, R> extends AssertionConcern {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;
    private final CriteriaQuery<CQ> criteriaQuery;
    private final Root<R> root;

    public QueryHelper(EntityManager entityManager, Class<CQ> result, Class<R> from) {
        assertArgumentNotNull(entityManager, "Entity Manager cannot be null.");
        assertArgumentNotNull(entityManager.getCriteriaBuilder(),
                "The Criteria Builder cannot be null.");
        assertArgumentNotNull(result, "The expected outcome result class cannot be null.");
        this.entityManager = entityManager;
        this.criteriaBuilder = getEntityManager().getCriteriaBuilder();
        this.criteriaQuery = criteriaBuilder.createQuery(result);
        this.root = criteriaQuery.from(from);
        assertArgumentNotNull(criteriaQuery, "The Criteria Query cannot be null.");
        assertArgumentNotNull(root, "The Root cannot be null.");
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public CriteriaBuilder getBuilder() {
        return criteriaBuilder;
    }

    public CriteriaQuery<CQ> getQuery() {
        return criteriaQuery;
    }

    public Root<R> getRoot() {
        return root;
    }

    public CQ getSingleResult() {
        return createTypedQuery().getSingleResult();
    }

    public List<CQ> getResultList() {
        return createTypedQuery().getResultList();
    }

    public List<CQ> getResultList(int firstResult, int maxResults) {
        TypedQuery<CQ> typedQuery = createTypedQuery();
        typedQuery.setFirstResult(firstResult);
        typedQuery.setMaxResults(maxResults);
        return typedQuery.getResultList();
    }

    private TypedQuery<CQ> createTypedQuery() {
        return getEntityManager().createQuery(criteriaQuery);
    }

    /**
     * Create a new count {@link com.udm.common.adapter.jpa.repository.QueryHelper}.
     *
     * @param <T>           the type that will be use to create the {@code QueryContainer}.
     * @param entityManager the instance that will be use to create the {@code CriteriaQuery}.
     * @param clase         the class that will be use to create the {@code CriteriaQuery} and the
     *                      {@code Root}.
     * @return new instance
     */
    public static final <T> QueryHelper<Long, T> newQueryCounter(EntityManager entityManager,
                                                                 Class<T> clase) {
        QueryHelper<Long, T> qc = new QueryHelper<Long, T>(entityManager, Long.class,
                clase);
        qc.getQuery().select(qc.getBuilder().countDistinct(qc.getRoot()));
        return qc;
    }

    /**
     * Creates a new instance of {@link com.udm.common.adapter.jpa.repository.QueryHelper}.
     *
     * @param <T>           the type that will be use to create the {@code QueryContainer}.
     * @param entityManager the instance that will be use to create the {@code CriteriaQuery}.
     * @param clase         the class that will be use to create the {@code CriteriaQuery} and the
     *                      {@code Root}.
     * @return new instance
     */
    public static final <T> QueryHelper<T, T> newQuery(EntityManager entityManager,
                                                       Class<T> clase) {
        return new QueryHelper<T, T>(entityManager, clase, clase);
    }
}
