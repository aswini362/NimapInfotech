package com.nimapinfotech.tech.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.nimapinfotech.tech.RequestDTO.CategoryRequest;
import com.nimapinfotech.tech.RequestDTO.ProductRequest;
import com.nimapinfotech.tech.RequestDTO.UpdateCategoryRequest;
import com.nimapinfotech.tech.customexception.NoCategoeyIdFound;
import com.nimapinfotech.tech.customexception.NoProductIdFound;
import com.nimapinfotech.tech.custommodels.CategoryDetails;
import com.nimapinfotech.tech.entity.Category;
import com.nimapinfotech.tech.entity.Product;
import com.nimapinfotech.tech.repo.CategoryRepository;
import com.nimapinfotech.tech.repo.ProductRepository;
import com.nimapinfotech.tech.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OrderServiceImpl implements OrderService {
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;

	@Override
	public String save(CategoryRequest categoryRequest) {
		log.info("addCategory(-) method execution start");
		log.debug("CategoryModel converted to CategoryEntity");
		List<Product> products = new ArrayList<Product>();
		Category category = new Category();
		category.setDescription(categoryRequest.getDescription());
		category.setName(categoryRequest.getName());

		for (ProductRequest productRequest : categoryRequest.getProduct()) {
			Product product = new Product();
			product.setPrice(productRequest.getPrice());
			product.setProductName(productRequest.getProductName());
			product.setQty(productRequest.getQty());
			log.info("Entity saved start");
			productRepository.save(product);
			System.out.println(product);
			products.add(product);
		}
		category.setProducts(products);
		log.info("Entity saved start");
		categoryRepository.save(category);
		System.out.println(category);
		log.info("addCategory(-) method execution end and added category return");
		return "Saved SucessFully";
	}

	@Override
	public Page<CategoryDetails> fetchCategoryDetails(String productDetails, Pageable pageable) {
		pageable = PageRequest.of(0, 10);
		return categoryRepository.fetchCategoryDetails(productDetails, pageable);
	}

	@Override
	public String update(UpdateCategoryRequest categoryRequest) {
		List<Product> products = new ArrayList<Product>();
		Category category = new Category();
		category = categoryRepository.findById(categoryRequest.getId()).get();
		category.setDescription(categoryRequest.getDescription());
		category.setName(categoryRequest.getName());

		for (ProductRequest productRequest : categoryRequest.getProduct()) {
			Product product = new Product();
			product = productRepository.findById(productRequest.getId()).get();
			product.setPrice(productRequest.getPrice());
			product.setProductName(productRequest.getProductName());
			product.setQty(productRequest.getQty());
			productRepository.save(product);
			System.out.println(product);
			products.add(product);
		}
		category.setProducts(products);
		categoryRepository.save(category);
		System.out.println(category);
		return "update SucessFully";
	}

	@Override
	public List<Category> findAllCategory() {
		log.info("fetchAllCategories() service method execution start");
		log.debug("Invoke repo class method to fetch categories");
		return categoryRepository.findAll();
	}

	@Override
	public List<Product> findAllProducts() {
		log.info("fetchAllProduct() service method execution start");
		log.debug("Invoke repo class method to fetch product");
		return productRepository.findAll();
	}

	@Override
	@Transactional
	public boolean deleteByid(Long id) {
		log.info("deleteByid service method execution start");
		try {
			log.debug("Invoke repo class method to delete category");
			categoryRepository.deleteByCategoryId(id);
			return true;
		} catch (Exception e) {
			log.error("** Exception occured :**" + e.getMessage());
			throw new NoCategoeyIdFound("No category id found deleteing record" + id);
		}
	}

	@Override
	public Category findByCategoryId(Long id) throws NoCategoeyIdFound {
		log.info("findByCategoryId service method execution start");
		Optional<Category> findByCategoryId = categoryRepository.findById(id);
		if (findByCategoryId.isPresent()) {
			log.debug("Invoke repo class method to fetch category");
			return findByCategoryId.get();
		} else {
			throw new NoCategoeyIdFound("Invalid categoey id : " + id);
		}

	}

	@Override
	public Product findByProductId(Long id) {
		log.info("findByProductId service method execution start");
		Optional<Product> findByproductId = productRepository.findById(id);
		if (findByproductId.isPresent()) {
			log.debug("Invoke repo class method to fetch product");
			return findByproductId.get();
		} else {
			throw new NoProductIdFound("No product id found for this record" + id);
		}
	}

	@Override
	@Transactional
	public void deleteByProductId(Long id) {
		productRepository.deleteById(id);
		
	}

}
