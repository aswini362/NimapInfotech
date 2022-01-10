package com.nimapinfotech.tech.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nimapinfotech.tech.custommodels.CategoryDetails;
import com.nimapinfotech.tech.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	@Query(value = "SELECT category.id, category.description, category.name,product.product_name FROM  product JOIN category ON category.id=product.cp_fk where product.product_name=?1", nativeQuery = true)
	public Page<CategoryDetails> fetchCategoryDetails(String productDetails, Pageable pageable);

	@Modifying
	@Query(value = "delete from category where id= :id",nativeQuery = true)
	void deleteByCategoryId(@Param("id") Long id);
}
