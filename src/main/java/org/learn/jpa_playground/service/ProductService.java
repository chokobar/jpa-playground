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
    public List<ProductDTO> findAllByCategory(ProductCategory category) {
        return productRepository.findByCategory(category.name())
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<ProductDTO> findAllByStatus(ProductStatus status) {
        return productRepository.findByStatus(status.name())
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<ProductDTO> findAllByCategoryAndStatus(ProductCategory category, ProductStatus status) {
        return productRepository.findByCategoryAndStatus(category.name(), status.name())
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private ProductDTO toDto(ProductDomain d) {
        ProductStatus status = safeStatus(d.getStatus());
        ProductCategory category = safeCategory(d.getCategory());

        return ProductDTO.builder()
                .id((long) d.getId())
                .name(d.getName())
                .description(d.getDescription())
                .price(d.getPrice())
                .stockQuantity(d.getStockQuantity())
                .status(status)
                .category(category)
                .createdAt(String.valueOf(d.getCreatedDate()))
                .updatedAt(String.valueOf(d.getUpdatedDate()))
                .build();
    }

    private ProductStatus safeStatus(String s) {
        if (s == null) return ProductStatus.AVAILABLE;
        try { return ProductStatus.valueOf(s); }
        catch (IllegalArgumentException e) { return ProductStatus.AVAILABLE; }
    }

    private ProductCategory safeCategory(String s) {
        if (s == null) return ProductCategory.ETC;
        try { return ProductCategory.valueOf(s); }
        catch (IllegalArgumentException e) { return ProductCategory.ETC; }
    }


    @Transactional
    public ProductDTO findById(Long id) {
        ProductDomain productDomain = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품은 존재하지 않습니다. ID:" + id));

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

    public ProductDomain update(ProductDTO productDTO) {
        ProductDomain product = productRepository.findById(productDTO.getId().intValue())
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStockQuantity(productDTO.getStockQuantity());
        product.setCategory(String.valueOf(productDTO.getCategory()));
        product.setStatus(String.valueOf(productDTO.getStatus()));
        product.setUpdatedDate(new Date());

        return productRepository.save(product);
    }

    @Transactional
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

}
