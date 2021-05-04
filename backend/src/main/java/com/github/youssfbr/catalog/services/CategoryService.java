package com.github.youssfbr.catalog.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.youssfbr.catalog.dto.CategoryDTO;
import com.github.youssfbr.catalog.entities.Category;
import com.github.youssfbr.catalog.repositories.CategoryRepository;
import com.github.youssfbr.catalog.services.exceptions.ResourceNotFoundException;

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
	
	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		
		Category entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id " + id + " não encontrado!"));;
				
		return new CategoryDTO(entity);
	}
	
	@Transactional
	public CategoryDTO insert(CategoryDTO dto) {
		
		Category entity = new Category();
		entity.setName(dto.getName());
		entity = repository.save(entity);
		
		return new CategoryDTO(entity);
	}

	@Transactional
	public CategoryDTO update(Long id, CategoryDTO dto) {
		try {
			
			Category entity = repository.getOne(id);
			entity.setName(dto.getName());
			entity = repository.save(entity);
			
			return new CategoryDTO(entity);
		} 
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id " + id + " não encontrado!");
		}
	}
}
