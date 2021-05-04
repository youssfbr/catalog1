package com.github.youssfbr.catalog.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.youssfbr.catalog.dto.CategoryDTO;
import com.github.youssfbr.catalog.entities.Category;
import com.github.youssfbr.catalog.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	private CategoryRepository repository;
	
	@Autowired
	public CategoryService(CategoryRepository repository) {
		this.repository = repository;	
	}

	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll() {
		List<Category> list = repository.findAll();
						
		return list.stream().map(cat -> new CategoryDTO(cat)).collect(Collectors.toList());				
	}
}
