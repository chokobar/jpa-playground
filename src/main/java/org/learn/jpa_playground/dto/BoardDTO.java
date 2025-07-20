package org.learn.jpa_playground.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDTO {

    private Long id;

    @NotBlank(message = "{title.required}")
    private String title;

    @NotBlank(message = "{content.required}")
    private String content;

    private String writer;

    private String createdDate;

    private String updatedDate;

    private Integer viewCount;
}
