package com.lsr.shopit.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.lsr.shopit.entities.Product;
import com.lsr.shopit.entities.Shop;

@Repository
public interface ShopRepository
		extends JpaRepository<Shop, Long>, JpaSpecificationExecutor<Shop> {
	Optional<Shop> findByUserId(Long userId);
    Optional<Product> findByIdAndUserId(Long id, Long userId);
}
