package org.learn.jpa_playground.repository;

import org.learn.jpa_playground.domain.ProductDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductDomain, Integer> {

}
