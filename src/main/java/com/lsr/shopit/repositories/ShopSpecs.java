package com.lsr.shopit.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.lsr.shopit.entities.Shop;
import com.lsr.shopit.models.requests.SearchShopsReq;

public class ShopSpecs {
	public static Specification<Shop> getShopsByCategory(SearchShopsReq searchShopsReq) {

		if (StringUtils.hasText(searchShopsReq.getLocation()) && !searchShopsReq.getCategoryList().isEmpty()) {
			return Specification.where(getLocationSpecification(searchShopsReq.getLocation()))
					.and(getCategorySpecification(searchShopsReq.getCategoryList()))
					.and(getTypeSpecification(searchShopsReq.getTypeList()));
		}

		return null;
	}

	private static Specification<Shop> getLocationSpecification(String location) {
		return (root, query, criteriaBuilder) -> {
			Predicate locationPredicate = criteriaBuilder.equal(root.get("location"), location);
			return locationPredicate;
		};
	}

	private static Specification<Shop> getCategorySpecification(List<String> categoryList) {
		List<Predicate> predicates = new ArrayList<>();
		return (root, query, criteriaBuilder) -> {
			for (String category : categoryList) {
				predicates.add(criteriaBuilder.like(root.get("categories"), "%" + category + "%"));
			}
			return criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));
		};
	}

	private static Specification<Shop> getTypeSpecification(List<String> typeList) {
		List<Predicate> predicates = new ArrayList<>();
		return (root, query, criteriaBuilder) -> {
			for (String type : typeList) {
				predicates.add(criteriaBuilder.like(root.get("type"), "%" + type + "%"));
			}
			return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}
}
