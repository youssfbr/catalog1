package com.github.youssfbr.catalog.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.youssfbr.catalog.dto.CategoryDTO;
import com.github.youssfbr.catalog.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryResource {
	
	private CategoryService service;
	
	public CategoryResource(CategoryService service) {
		this.service = service;
	}

	@GetMapping
	ResponseEntity<List<CategoryDTO>> findAll() {		
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/{id}")
	ResponseEntity<CategoryDTO> findById(@PathVariable Long id) {		
		return ResponseEntity.ok(service.findById(id));
	}
}
