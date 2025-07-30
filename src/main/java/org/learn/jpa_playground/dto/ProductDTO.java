package org.learn.jpa_playground.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {

    private Long id;

    @NotBlank(message = "{name.required}")
    private String name;

    private String description;

    @NotBlank(message = "{price.required}")
    private Integer price;

    @NotBlank(message = "{stockQuantity.required}")
    private Integer stockQuantity;

    private ProductStatus status;  // ENUM 타입으로 선언

    private String category;

    private String createdAt;  // 문자열로 변환해서 반환 (포맷팅용)

    private String updatedAt;
}
