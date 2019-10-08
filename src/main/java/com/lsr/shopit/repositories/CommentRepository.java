package com.lsr.shopit.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.google.common.base.Optional;
import com.lsr.shopit.entities.Comments;
import com.lsr.shopit.entities.Product;
import com.lsr.shopit.entities.Shop;

public interface CommentRepository  extends JpaRepository<Comments, Long>, JpaSpecificationExecutor<Comments> {
	List<Comments> findByShopId(Long shopId);
}
