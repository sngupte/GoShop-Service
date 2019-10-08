package com.lsr.shopit.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lsr.shopit.entities.Product;
import com.lsr.shopit.entities.Shop;
import com.lsr.shopit.exceptions.DataNotFoundException;
import com.lsr.shopit.models.PageDetails;
import com.lsr.shopit.models.ResponseWrapper;
import com.lsr.shopit.models.requests.ProductFilterReq;
import com.lsr.shopit.repositories.ProductRepository;
import com.lsr.shopit.repositories.ProductSpecs;
import com.lsr.shopit.repositories.ShopRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private ShopRepository shopRepo;

	public ResponseWrapper<Void> addProduct(Product product, Long shopId) {
		Optional<Shop> shopOpt = shopRepo.findById(shopId);
		if (shopOpt.isPresent()) {
			Shop shop = shopOpt.get();
			product.setShop(shop);
			productRepo.save(product);
		} else {
			throw new DataNotFoundException("Invalid Shop Id ");
		}

		ResponseWrapper<Void> wrapper = new ResponseWrapper<>();
		wrapper.setMessage("Success");
		wrapper.setSuccess(true);
		return wrapper;
	}

	public ResponseWrapper<List<Product>> getProductByShopId(Long shopId, Pageable pageable) {
		Page<Product> findByShopId = productRepo.findByShopId(shopId, pageable);
		ResponseWrapper<List<Product>> wrapper = new ResponseWrapper<List<Product>>();
		wrapper.setData(findByShopId.getContent());
		PageDetails pageDetails = new PageDetails(findByShopId.getNumber(), findByShopId.getSize(),
				findByShopId.getTotalPages(), findByShopId.getTotalElements());
		wrapper.setPageDetails(pageDetails);
		wrapper.setSuccess(true);
		wrapper.setMessage("Success");
		return wrapper;
	}

	public ResponseWrapper<List<Product>> filterProducts(ProductFilterReq productFilter, Long shopId,
			Pageable pageable) {
		Page<Product> productPage = productRepo.findAll(ProductSpecs.getFilterProductSpecs(shopId, productFilter),
				pageable);
		ResponseWrapper<List<Product>> wrapper = new ResponseWrapper<List<Product>>();
		wrapper.setData(productPage.getContent());
		PageDetails pageDetails = new PageDetails(productPage.getNumber(), productPage.getSize(),
				productPage.getTotalPages(), productPage.getTotalElements());
		wrapper.setPageDetails(pageDetails);
		wrapper.setSuccess(true);
		wrapper.setMessage("Success");
		return wrapper;
	}

	public ResponseWrapper<Void> deleteProduct(Long productId) {
		productRepo.deleteById(productId);
		ResponseWrapper<Void> wrapper = new ResponseWrapper<>();
		wrapper.setMessage("Product Successfully Deleted");
		wrapper.setSuccess(true);
		return wrapper;
	}

	public ResponseWrapper<Product> getProductDetails(Long productId) {
		Optional<Product> productOpt = productRepo.findById(productId);
		if (!productOpt.isPresent()) {
			throw new DataNotFoundException("Product not found");
		}
		ResponseWrapper<Product> wrapper = new ResponseWrapper<>();
		wrapper.setData(productOpt.get());
		wrapper.setMessage("Success");
		wrapper.setSuccess(true);
		return wrapper;
	}

}
