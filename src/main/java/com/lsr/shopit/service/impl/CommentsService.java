package com.lsr.shopit.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lsr.shopit.entities.Comments;
import com.lsr.shopit.entities.Shop;
import com.lsr.shopit.exceptions.DataNotFoundException;
import com.lsr.shopit.models.ResponseWrapper;
import com.lsr.shopit.repositories.CommentRepository;
import com.lsr.shopit.repositories.ShopRepository;

@Service
public class CommentsService {
	
	@Autowired
	private CommentRepository commentRepo;
	
	@Autowired
	private ShopRepository shopRepo;
	
	public ResponseWrapper<Void> addComment(Comments comment, long shopId){
		Optional<Shop> shopOpt = shopRepo.findById(shopId);
		if (shopOpt.isPresent()) {
			Shop shop = shopOpt.get();
			shop.addRatings(comment.getRatings());
			shopRepo.save(shop);
			comment.setShop(shop);
			commentRepo.save(comment);
		} else {
			throw new DataNotFoundException("Invalid Shop Id ");
		}
		ResponseWrapper<Void> wrapper = new ResponseWrapper<>();
		wrapper.setMessage("Success");
		wrapper.setSuccess(true);
		return wrapper;
	}

	public ResponseWrapper<List<Comments>> getCommentByShopId(Long shopId){
		List<Comments> commentList= commentRepo.findByShopId(shopId);
		ResponseWrapper<List<Comments>> wrapper = new ResponseWrapper<List<Comments>>();
		wrapper.setData(commentList);
		wrapper.setSuccess(true);
		wrapper.setMessage("Success");
		return wrapper;

	}
	
}
