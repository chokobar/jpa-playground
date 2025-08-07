package org.learn.jpa_playground.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "{description.required}")
    private String description;

    @NotNull(message = "{price.required}")
    @Min(value = 0, message = "가격은 0원 이상이어야 합니다.")
    private Integer price;

    @NotNull(message = "{stockQuantity.required}")
    @Min(value = 0, message = "재고 수량은 0 이상이어야 합니다.")
    private Integer stockQuantity;

    private ProductStatus status;  // ENUM 타입으로 선언

    private ProductCategory category;    // ENUM 타입으로 선언

    private String createdAt;  // 문자열로 변환해서 반환 (포맷팅용)

    private String updatedAt;
}
