package com.lsr.shopit.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.lsr.shopit.entities.Shop;
import com.lsr.shopit.models.requests.SearchShopsReq;

@Component
public class ShopDetailsDAO {

	@Autowired
	private JdbcTemplate template;



	public List<Shop> searchShopsByCategory(SearchShopsReq searchShopsReq) {
		boolean isWhereExist = false;
		StringBuffer sqlQuery = new StringBuffer("select * from shops");
		List<String> categoryList = searchShopsReq.getCategoryList();
		for (String category : categoryList) {
			if (!isWhereExist) {
				sqlQuery.append(" where categories like '%" + category + "%'");
				isWhereExist = true;
			} else {
				sqlQuery.append(" or categories like '%" + category + "%'");
			}
		}
		
		//List<ShopDetailsEntity> queryForList = template.queryForList(sqlQuery.toString(),ShopDetailsEntity.class);
		
		List<Map<String, Object>> queryForList2 = template.queryForList(sqlQuery.toString());
		
		return null;
	}
}
