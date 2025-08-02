package org.learn.jpa_playground.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.learn.jpa_playground.domain.ProductDomain;
import org.learn.jpa_playground.dto.ProductDTO;
import org.learn.jpa_playground.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public ProductDomain save(ProductDTO productDTO) {
        ProductDomain productDomain = ProductDomain.builder()
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .stockQuantity(productDTO.getStockQuantity())
                .status(String.valueOf(productDTO.getStatus()))
                .category(productDTO.getCategory())
                .createdDate(new Date()) // 혹은 LocalDateTime.now() → Date로 변환
                .build();

        return productRepository.save(productDomain);
    }

}
