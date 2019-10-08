package com.lsr.shopit.controller.rest.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lsr.shopit.entities.Product;
import com.lsr.shopit.enums.ClothCategory;
import com.lsr.shopit.enums.ClothType;
import com.lsr.shopit.exceptions.DataNotFoundException;
import com.lsr.shopit.models.ResponseWrapper;
import com.lsr.shopit.models.requests.ProductFilterReq;
import com.lsr.shopit.models.response.CategoryResponse;
import com.lsr.shopit.service.impl.ProductService;

@RestController
@RequestMapping("/api/shop")
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/addProduct/{shopId}", method = RequestMethod.POST)
	public ResponseWrapper<Void> addProduct(@RequestBody Product product, @PathVariable("shopId") Long shopId) {
		return productService.addProduct(product, shopId);
	}

	@RequestMapping(value = "/deleteProduct/{productId}", method = RequestMethod.DELETE)
	public ResponseWrapper<Void> deleteProduct(@PathVariable("productId") Long productId) {
		return productService.deleteProduct(productId);
	}

	@RequestMapping(value = "/filterProducts/{shopId}", method = RequestMethod.POST)
	public ResponseWrapper<List<Product>> filterProducts(@RequestBody ProductFilterReq productFilter,
			@PathVariable("shopId") Long shopId, Pageable pageable) {
		return productService.filterProducts(productFilter, shopId, pageable);
	}

	@RequestMapping(value = "/getProductByShopId/{shopId}", method = RequestMethod.GET)
	public ResponseWrapper<List<Product>> getProductByShopId(@PathVariable("shopId") Long shopId, Pageable pageable) {
		return productService.getProductByShopId(shopId, pageable);
	}

	@RequestMapping(value = "/productDetails/{productId}", method = RequestMethod.GET)
	public ResponseWrapper<Product> getProductDetails(@PathVariable("productId") Long productId) {
		return productService.getProductDetails(productId);
	}

	@RequestMapping(value = "/getClothType", method = RequestMethod.GET)
	public ResponseWrapper<List<String>> getClothType() {
		List<String> collect = Stream.of(ClothType.values()).map(ClothType::getClothType).collect(Collectors.toList());
		ResponseWrapper<List<String>> wrapper = new ResponseWrapper<>();
		wrapper.setData(collect);
		wrapper.setMessage("Success.");
		wrapper.setSuccess(true);
		return wrapper;
	}

	@RequestMapping(value = "/getClothCategory", method = RequestMethod.GET)
	public ResponseWrapper<List<String>> getClothCategory(
			@RequestParam(name = "cat", required = false) String category) {
		List<String> collect = null;
		if (category != null) {
			collect = Stream.of(ClothCategory.values())
					.filter(clothCat -> clothCat.geClothType().getClothType().equalsIgnoreCase(category))
					.map(ClothCategory::getClothCategory).collect(Collectors.toList());
		} else {
			collect = Stream.of(ClothCategory.values()).map(ClothCategory::getClothCategory)
					.collect(Collectors.toList());
		}

		if (collect.isEmpty()) {
			throw new DataNotFoundException("No data found for category: " + category);
		}
		ResponseWrapper<List<String>> wrapper = new ResponseWrapper<>();
		wrapper.setData(collect);
		wrapper.setMessage("Success.");
		wrapper.setSuccess(true);
		return wrapper;
	}

	@RequestMapping(value = "/getAllClothCategory", method = RequestMethod.GET)
	public ResponseWrapper<List<CategoryResponse>> getAllClothCategory() {
		ClothType[] values = ClothType.values();
		
		List<CategoryResponse> categoryList = new ArrayList<>();
		for (int i = 0; i < values.length; i++) {
			String clothType = values[i].toString();
			CategoryResponse categoryResponse = new CategoryResponse();
			categoryResponse.setType(clothType);
			List<String> itemList = Stream.of(ClothCategory.values())
					.filter(clothCat -> clothCat.geClothType().getClothType().equalsIgnoreCase(clothType))
					.map(ClothCategory::getClothCategory).collect(Collectors.toList());
			categoryResponse.setItemList(itemList);
			categoryList.add(categoryResponse);
		}
		ResponseWrapper<List<CategoryResponse>> wrapper = new ResponseWrapper<>();
		wrapper.setData(categoryList);
		wrapper.setMessage("Success");
		wrapper.setSuccess(true);
		return wrapper;
	}

}
