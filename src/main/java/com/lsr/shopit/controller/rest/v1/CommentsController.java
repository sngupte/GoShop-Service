package com.lsr.shopit.controller.rest.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lsr.shopit.entities.Comments;
import com.lsr.shopit.entities.Product;
import com.lsr.shopit.models.ResponseWrapper;
import com.lsr.shopit.service.impl.CommentsService;

@RestController
@RequestMapping("/api/shop")
public class CommentsController {
	
	@Autowired
	private CommentsService commentsService;
	
	@RequestMapping(value="/addComment/{shopId}", method = RequestMethod.POST)
	public ResponseWrapper<Void> addProduct(@RequestBody Comments comment, @PathVariable("shopId") Long shopId) {
		return commentsService.addComment(comment, shopId);
	}
	
	@RequestMapping(value = "/getCommentByShopId/{shopId}", method = RequestMethod.GET)
	public ResponseWrapper<List<Comments>> getProductByShopId(@PathVariable("shopId") Long shopId) {
		return commentsService.getCommentByShopId(shopId);
	}
}
