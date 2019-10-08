package com.lsr.shopit.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.lsr.shopit.entities.Product;
import com.lsr.shopit.models.requests.ProductFilterReq;

public class ProductSpecs {

	public static Specification<Product> getFilterProductSpecs(Long shopId, ProductFilterReq productFilter) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> allPredicatesList = new ArrayList<>();
			List<Predicate> catPredicatesList = new ArrayList<>();
			List<Predicate> sizePredicatesList = new ArrayList<>();
			List<Predicate> typePredicatesList = new ArrayList<>();
			
			allPredicatesList.add(criteriaBuilder.equal(root.get("shop").get("id"), shopId));
			
			if ((productFilter.getMinPrice() != null) && (productFilter.getMaxPrice() != null)) {
				allPredicatesList.add(criteriaBuilder.between(root.get("originalPrice"), productFilter.getMinPrice(), productFilter.getMaxPrice()));
			}
			
			if ((productFilter.getClothCategoryList() != null) && (!productFilter.getClothCategoryList().isEmpty())) {
				for (String category : productFilter.getClothCategoryList()) {
					catPredicatesList.add(criteriaBuilder.equal(root.get("category"), category));
				}
				allPredicatesList.add(criteriaBuilder.or(catPredicatesList.toArray(new Predicate[catPredicatesList.size()])));
			}
			
			if ((productFilter.getClothTypeList() != null) && (!productFilter.getClothTypeList().isEmpty())) {
				for (String type : productFilter.getClothTypeList()) {
					typePredicatesList.add(criteriaBuilder.equal(root.get("type"), type));
				}
				allPredicatesList.add(criteriaBuilder.or(typePredicatesList.toArray(new Predicate[typePredicatesList.size()])));
			}
			
			if ((productFilter.getSizesList() != null) && (!productFilter.getSizesList().isEmpty())) {
				for (String size : productFilter.getSizesList()) {
					sizePredicatesList.add(criteriaBuilder.like(root.get("sizes"), "%" + size + "%"));
				}
				allPredicatesList.add(criteriaBuilder.or(sizePredicatesList.toArray(new Predicate[sizePredicatesList.size()])));
			}
			
			return criteriaBuilder.and(allPredicatesList.toArray(new Predicate[allPredicatesList.size()]));
		};
	}

}
