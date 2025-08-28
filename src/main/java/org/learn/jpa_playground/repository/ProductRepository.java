package org.learn.jpa_playground.repository;

import org.learn.jpa_playground.domain.ProductDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductDomain, Integer> {

    Optional<ProductDomain> findById(Long id);

    List<ProductDomain> findByCategory(String category);

    List<ProductDomain> findByStatus(String status);

    List<ProductDomain> findByCategoryAndStatus(String category, String status);
}
