package com.lsr.shopit.controller.rest.v1;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lsr.shopit.entities.Shop;
import com.lsr.shopit.models.ResponseWrapper;
import com.lsr.shopit.models.requests.RegisterShopReq;
import com.lsr.shopit.models.requests.SearchShopsReq;
import com.lsr.shopit.models.requests.UpdateShopRequest;
import com.lsr.shopit.models.response.ShopDetailResp;
import com.lsr.shopit.service.impl.ShopService;

@RestController
@RequestMapping("/api/shop")
public class ShopController {

	@Autowired
	private ShopService shopRegistrationService;

	@RequestMapping(value = "/registerShop/{userId}", method = RequestMethod.POST)
	public ResponseWrapper<Void> registerShop(@RequestBody @Valid RegisterShopReq registerShopReq,
			@PathVariable("userId") Long userId) {
		return shopRegistrationService.registerShop(registerShopReq, userId);
	}

	@RequestMapping(value = "/deleteShop/{shopId}", method = RequestMethod.POST)
	public ResponseWrapper<Void> deleteShop(@PathVariable("shopId") Long shopId) {
		return shopRegistrationService.deleteShop(shopId);
	}

	@RequestMapping(value = "/shopDetails", method = RequestMethod.GET)
	public ResponseWrapper<Shop> getShopDetails(@RequestParam("id") Long id) {
		return shopRegistrationService.getShopDetails(id);
	}

	@RequestMapping(value = "/shopDetailsByUserId", method = RequestMethod.GET)
	public ResponseWrapper<Shop> getShopDetailsByUserId(@RequestParam("userId") Long id) {
		return shopRegistrationService.getShopByUserId(id);
	}

	@RequestMapping(value = "/updateShop", method = RequestMethod.PUT)
	public ResponseWrapper<Void> updateShop(@RequestBody @Valid UpdateShopRequest updateShopRequest) {
		return shopRegistrationService.updateShop(updateShopRequest);
	}

	@RequestMapping(value = "/searchShop", method = RequestMethod.POST)
	public ResponseWrapper<List<Shop>> searchShops(@RequestBody SearchShopsReq searchShopsReq, Pageable pageable) {
		return shopRegistrationService.searchShops(searchShopsReq, pageable);
	}

}
