package com.assignment.util;

import com.assignment.entity.ShopperPersonalizedData;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import jakarta.persistence.criteria.Predicate;


public class ShopperPersonalizedDataSpecs {

    public static Specification<ShopperPersonalizedData> filterProducts(String shopperId, String category, String brand) {
        return (root, query, cb) -> {
            ArrayList<Predicate> predicates = new ArrayList<>();

            if (!StringUtils.isEmpty(shopperId)) {
                predicates.add(cb.equal(root.get("shopperId"), shopperId));
            }

            if (!StringUtils.isEmpty(category)) {
                predicates.add(cb.equal(root.get("productDetails").get("category"), category));
            }

            if (!StringUtils.isEmpty(brand)) {
                predicates.add(cb.equal(root.get("productDetails").get("brand"), brand));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}

