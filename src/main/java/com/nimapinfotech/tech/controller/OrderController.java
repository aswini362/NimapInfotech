package com.nimapinfotech.tech.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nimapinfotech.tech.RequestDTO.CategoryRequest;
import com.nimapinfotech.tech.RequestDTO.UpdateCategoryRequest;
import com.nimapinfotech.tech.customexception.NoCategoeyIdFound;
import com.nimapinfotech.tech.customexception.NoProductIdFound;
import com.nimapinfotech.tech.custommodels.CategoryDetails;
import com.nimapinfotech.tech.entity.Category;
import com.nimapinfotech.tech.entity.Product;
import com.nimapinfotech.tech.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/nimap/product/category")
public class OrderController {
	@Autowired
	private OrderService service;

	@PostMapping("/create")
	public ResponseEntity<String> save(@Valid @RequestBody CategoryRequest categoryRequest) {
		log.debug("*** save-Exceution started ***");
		String msg = service.save(categoryRequest);
		return new ResponseEntity<String>(msg, HttpStatus.CREATED);

	}

	@GetMapping("/fetchCategoryDetails")
	public ResponseEntity<Page<CategoryDetails>> fetchCategoryDetails(
			@RequestParam(value = "details", required = true) String productDetails, Pageable pageable) {
		Page<CategoryDetails> fetchCategoryDetails = service.fetchCategoryDetails(productDetails, pageable);
		return new ResponseEntity<Page<CategoryDetails>>(fetchCategoryDetails, HttpStatus.OK);

	}

	@PutMapping("/update")
	public ResponseEntity<String> update(@Valid @RequestBody UpdateCategoryRequest categoryRequest) {
		log.debug("*** update-Exceution started ***");
		String msg = service.update(categoryRequest);
		return new ResponseEntity<String>(msg, HttpStatus.OK);

	}

	@GetMapping("/FindAllCategory")
	public ResponseEntity<List<Category>> findAllCategory() {
		log.debug("*** getAllCategory-Exceution started ***");
		List<Category> alltheCategories = null;
		try {
			alltheCategories = service.findAllCategory();
			if (alltheCategories.isEmpty()) {
				log.info("*** getAllCategories records are not avavilabe ***");
			}
		} catch (Exception e) {
			log.error("***Exception occured while geting all the contacts***" + e.getMessage());
		}
		log.debug("*** getAllCategory-Exection ended ***");
		return new ResponseEntity<>(alltheCategories, HttpStatus.OK);
	}

	@GetMapping("/FindAllProduct")
	public ResponseEntity<List<Product>> findAllProducts() {
		log.debug("*** getAllProducts-Exceution started ***");
		List<Product> alltheProducts = null;
		try {
			alltheProducts = service.findAllProducts();
			if (alltheProducts.isEmpty()) {
				log.info("*** getAllproduct records are not avavilabe ***");
			}
		} catch (Exception e) {
			log.error("***Exception occured while geting all the contacts***" + e.getMessage());
		}
		log.debug("*** getAllProducts-Exection ended ***");
		return new ResponseEntity<>(alltheProducts, HttpStatus.OK);
	}

	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<String> deleteCategoryById(@PathVariable("id") Long id) {
		try {
			String msg = "Deleted id sucessfully";
			service.deleteByid(id);
			return new ResponseEntity<String>(msg, HttpStatus.OK);
		} catch (Exception e) {
			log.error("** Exception occured :**" + e.getMessage());
			throw new NoCategoeyIdFound("No category id found deleteing record" + id);
		}
	}

	@GetMapping("/findByCategoryId/{id}")
	public ResponseEntity<Category> findByCategoryId(@PathVariable("id") Long id) {
		log.debug("*** getCategorytId exceution started ***");
		Category findByCategoryId = service.findByCategoryId(id);
		if (findByCategoryId == null) {
			log.debug("*** No category found ***");
			throw new NoCategoeyIdFound("Invalid categoey id : " + id);
		}
		return new ResponseEntity<Category>(findByCategoryId, HttpStatus.OK);
	}

	@GetMapping("/findByProductId/{id}")
	public ResponseEntity<Product> findByProductId(@PathVariable("id") Long id) {
		log.debug("*** getCategorytId exceution started ***");
		Product findByproductId = service.findByProductId(id);
		if (findByproductId == null) {
			log.debug("*** No product found ***");
			throw new NoProductIdFound("Invalid product id : " + id);
		}
		return new ResponseEntity<Product>(findByproductId, HttpStatus.OK);
	}
	@DeleteMapping("/deleteByproduct/{id}")
	public void deleteByProductId(Long id) {
		service.deleteByProductId(id);
	}
}
