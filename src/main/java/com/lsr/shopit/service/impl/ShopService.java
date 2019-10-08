package com.lsr.shopit.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lsr.shopit.dao.ShopDetailsDAO;
import com.lsr.shopit.entities.Product;
import com.lsr.shopit.entities.Shop;
import com.lsr.shopit.entities.User;
import com.lsr.shopit.exceptions.DataNotFoundException;
import com.lsr.shopit.models.PageDetails;
import com.lsr.shopit.models.ResponseWrapper;
import com.lsr.shopit.models.requests.RegisterShopReq;
import com.lsr.shopit.models.requests.SearchShopsReq;
import com.lsr.shopit.models.requests.UpdateShopRequest;
import com.lsr.shopit.repositories.ShopRepository;
import com.lsr.shopit.repositories.ShopSpecs;
import com.lsr.shopit.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ShopService {
	@Autowired
	private ShopRepository shopRepository;

	@Autowired
	private UserRepository userRepository;

	public ResponseWrapper<Void> registerShop(RegisterShopReq registerShopReq, Long userId) {
		log.info("Inside registerShop service method");

		Optional<User> userOpt = userRepository.findById(userId);

		if (!userOpt.isPresent()) {
			throw new DataNotFoundException("User not found");
		}

		Shop shopDetailsEntity = new Shop();
		shopDetailsEntity.setName(registerShopReq.getName());
		shopDetailsEntity.setState(registerShopReq.getState());
		shopDetailsEntity.setCity(registerShopReq.getCity());
		shopDetailsEntity.setAddress(registerShopReq.getAddress());
		shopDetailsEntity.setLocation(registerShopReq.getLocation());
		shopDetailsEntity.setCategories(registerShopReq.getCategories().stream().collect(Collectors.joining(",")));
		shopDetailsEntity.setType(registerShopReq.getType().stream().collect(Collectors.joining(",")));
		shopDetailsEntity.setOfferTitle(registerShopReq.getOfferTitle());
		shopDetailsEntity.setOfferDesc(registerShopReq.getOfferDesc());
		shopDetailsEntity.setOpeningTime(registerShopReq.getOpeningTime());
		shopDetailsEntity.setClosingTime(registerShopReq.getClosingTime());
		shopDetailsEntity.setPhotoUrl(registerShopReq.getPhotoUrl());
		shopDetailsEntity.setPhone(registerShopReq.getPhone());
		shopDetailsEntity.setParkingAvail(registerShopReq.getParkingAvail());
		shopDetailsEntity.setChangingRoomAvail(registerShopReq.getChangingRoomAvail());

		shopDetailsEntity.setUser(userOpt.get());
		shopRepository.save(shopDetailsEntity);

		ResponseWrapper<Void> wrapper = new ResponseWrapper<>();
		wrapper.setSuccess(true);
		wrapper.setMessage("Success");
		return wrapper;
	}

	// private void validateTypesAndCategory(RegisterShopReq registerShopReq) {
	// List<String> typeList = registerShopReq.getType();
	// List<String> categoryList = registerShopReq.getCategory();
	//
	// // typeList.stream().map(type -> ClothType.get(type)).collect(collector)
	// }

	public ResponseWrapper<Void> updateShop(@Valid UpdateShopRequest updateShopRequest) {
		Optional<Shop> shopDetailsEntityOpt = shopRepository.findById(updateShopRequest.getId());
		if (!shopDetailsEntityOpt.isPresent()) {
			throw new DataNotFoundException("Data Not Found.");
		}
		Shop shopDetailsEntity = shopDetailsEntityOpt.get();
		if (!Objects.isNull(updateShopRequest.getName())) {
			shopDetailsEntity.setName(updateShopRequest.getName());
		}

		if (!Objects.isNull(updateShopRequest.getState())) {
			shopDetailsEntity.setState(updateShopRequest.getState());
		}

		if (!Objects.isNull(updateShopRequest.getAddress())) {
			shopDetailsEntity.setAddress(updateShopRequest.getAddress());
		}

		if (!Objects.isNull(updateShopRequest.getCity())) {
			shopDetailsEntity.setCity(updateShopRequest.getCity());
		}

		if (!Objects.isNull(updateShopRequest.getLocation())) {
			shopDetailsEntity.setLocation(updateShopRequest.getLocation());
		}

		if (!Objects.isNull(updateShopRequest.getPhotoUrl())) {
			shopDetailsEntity.setPhotoUrl(updateShopRequest.getPhotoUrl());
		}

		if (!Objects.isNull(updateShopRequest.getCategories())) {
			shopDetailsEntity
					.setCategories(updateShopRequest.getCategories().stream().collect(Collectors.joining(",")));
		}

		if (!Objects.isNull(updateShopRequest.getType())) {
			shopDetailsEntity.setType(updateShopRequest.getType().stream().collect(Collectors.joining(",")));
		}

		if (!Objects.isNull(updateShopRequest.getOpeningTime())) {
			shopDetailsEntity.setOpeningTime(updateShopRequest.getOpeningTime());
		}

		if (!Objects.isNull(updateShopRequest.getClosingTime())) {
			shopDetailsEntity.setClosingTime(updateShopRequest.getClosingTime());
		}

		if (!Objects.isNull(updateShopRequest.getClosingTime())) {
			shopDetailsEntity.setClosingTime(updateShopRequest.getClosingTime());
		}

		if (!Objects.isNull(updateShopRequest.getOfferTitle())) {
			shopDetailsEntity.setOfferTitle(updateShopRequest.getOfferTitle());
		}

		if (!Objects.isNull(updateShopRequest.getOfferDesc())) {
			shopDetailsEntity.setOfferDesc(updateShopRequest.getOfferDesc());
		}

		if (!Objects.isNull(updateShopRequest.getPhone())) {
			shopDetailsEntity.setPhone(updateShopRequest.getPhone());
		}

		if (!Objects.isNull(updateShopRequest.getParkingAvail())) {
			shopDetailsEntity.setParkingAvail(updateShopRequest.getParkingAvail());
		}

		if (!Objects.isNull(updateShopRequest.getChangingRoomAvail())) {
			shopDetailsEntity.setChangingRoomAvail(updateShopRequest.getChangingRoomAvail());
		}

		shopRepository.save(shopDetailsEntity);

		ResponseWrapper<Void> wrapper = new ResponseWrapper<>();
		wrapper.setSuccess(true);
		wrapper.setMessage("Success");
		return wrapper;
	}

	public ResponseWrapper<List<Shop>> searchShops(SearchShopsReq searchShopsReq, Pageable pageable) {
		Page<Shop> shopListPage = shopRepository.findAll(ShopSpecs.getShopsByCategory(searchShopsReq), pageable);
		ResponseWrapper<List<Shop>> wrapper = new ResponseWrapper<>();
		wrapper.setSuccess(true);
		wrapper.setData(shopListPage.getContent());
		wrapper.setMessage("Success!");
		PageDetails pageDetails = new PageDetails(shopListPage.getNumber(), shopListPage.getSize(),
				shopListPage.getTotalPages(), shopListPage.getTotalElements());
		wrapper.setPageDetails(pageDetails);
		return wrapper;
	}

	public ResponseWrapper<Shop> getShopDetails(Long id) {
		Optional<Shop> shopDetailsOpt = shopRepository.findById(id);

		ResponseWrapper<Shop> wrapper = new ResponseWrapper<>();
		if (!shopDetailsOpt.isPresent()) {
			wrapper.setData(null);
		} else {
			wrapper.setData(shopDetailsOpt.get());
		}

		wrapper.setMessage("Success");
		wrapper.setSuccess(true);
		return wrapper;
	}

	public ResponseWrapper<Void> deleteShop(Long shopId) {
		shopRepository.deleteById(shopId);
		ResponseWrapper<Void> wrapper = new ResponseWrapper<>();
		wrapper.setMessage("Shop Successfully Deleted.");
		wrapper.setSuccess(true);
		return wrapper;
	}

	public ResponseWrapper<Shop> getShopByUserId(Long shopId) {
		Optional<Shop> shopOpt = shopRepository.findByUserId(shopId);
		ResponseWrapper<Shop> wrapper = new ResponseWrapper<Shop>();
		
		if (!shopOpt.isPresent()) {
			wrapper.setData(null);
		} else {
			wrapper.setData(shopOpt.get());
		}
		wrapper.setSuccess(true);
		wrapper.setMessage("Success");
		return wrapper;
	}

}
