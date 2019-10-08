package com.lsr.shopit.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProductsDAO {
	@Autowired
	private JdbcTemplate template;
}
