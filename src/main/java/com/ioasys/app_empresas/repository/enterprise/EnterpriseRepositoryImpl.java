package com.ioasys.app_empresas.repository.enterprise;

import com.ioasys.app_empresas.model.Enterprise;
import com.ioasys.app_empresas.repository.filter.EnterpriseFilter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class EnterpriseRepositoryImpl implements EnterpriseRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Enterprise> filter(EnterpriseFilter filter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Enterprise> criteriaQuery = builder.createQuery(Enterprise.class);
        Root<Enterprise> root = criteriaQuery.from(Enterprise.class);
        root.fetch("enterpriseType", JoinType.INNER);
        Predicate[] predicates = createRestiction(filter, builder, root);

        criteriaQuery.where(predicates);
        TypedQuery<Enterprise> query = manager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    private Predicate[] createRestiction(EnterpriseFilter filter, CriteriaBuilder builder, Root<Enterprise> root) {
        List<Predicate> predicates = new ArrayList<>();
        if(filter.getName() != null) {
            predicates.add(builder.equal(root.get("name"), filter.getName()));
        }
        if (filter.getTypeCode() != null) {
            predicates.add(builder.equal(root.get("enterpriseType").get("code"), filter.getTypeCode()));
        }
        return  predicates.toArray(new Predicate[predicates.size()]);
     }
}
