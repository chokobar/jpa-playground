package org.learn.jpa_playground.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.learn.jpa_playground.domain.ProductDomain;
import org.learn.jpa_playground.enums.ProductCategory;
import org.learn.jpa_playground.dto.ProductDTO;
import org.learn.jpa_playground.enums.ProductStatus;
import org.learn.jpa_playground.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Date;
import java.util.stream.Collectors;

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
                .category(String.valueOf(productDTO.getCategory()))
                .createdDate(new Date()) // 혹은 LocalDateTime.now() → Date로 변환
                .build();

        return productRepository.save(productDomain);
    }

    @Transactional
    public List <ProductDTO> findAll() {
        return productRepository.findAll()
                .stream()
                .map(productDomain -> ProductDTO.builder()
                        .id((long)productDomain.getId())
                        .name(productDomain.getName())
                        .description(productDomain.getDescription())
                        .price(productDomain.getPrice())
                        .stockQuantity(productDomain.getStockQuantity())
                        .status(ProductStatus.valueOf(productDomain.getStatus()))
                        .category(ProductCategory.valueOf(productDomain.getCategory()))
                        .createdAt(String.valueOf(productDomain.getCreatedDate()))
                        .updatedAt(String.valueOf(productDomain.getUpdatedDate()))
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional
    public ProductDTO findById(Long id) {
        ProductDomain productDomain = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. ID:" + id));

        return ProductDTO.builder()
                .id((long) productDomain.getId())
                .name(productDomain.getName())
                .description(productDomain.getDescription())
                .price(productDomain.getPrice())
                .stockQuantity(productDomain.getStockQuantity())
                .status(ProductStatus.valueOf(productDomain.getStatus()))
                .category(ProductCategory.valueOf(productDomain.getCategory()))
                .createdAt(String.valueOf(productDomain.getCreatedDate()))
                .updatedAt(String.valueOf(productDomain.getUpdatedDate()))
                .build();
    }

    @Transactional
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

}
