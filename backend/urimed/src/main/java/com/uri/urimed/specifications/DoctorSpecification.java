package com.uri.urimed.specifications;

import com.uri.urimed.model.Doctor;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class DoctorSpecification implements Specification<Doctor> {

    private String key;
    private String operation;
    private Object value;

    public DoctorSpecification(String key, String operation, Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    @Override
    public Predicate toPredicate(Root<Doctor> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (operation == null) {
            throw new IllegalArgumentException("Operation must not be null");
        }
        if (operation.equalsIgnoreCase(":")) {
            return criteriaBuilder.like(root.get(key), "%" + value + "%");
        } else if (operation.equalsIgnoreCase("=")) {
            return criteriaBuilder.equal(root.get(key), value);
        }
        return null;
    }
}