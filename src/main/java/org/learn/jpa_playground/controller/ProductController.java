package org.learn.jpa_playground.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.learn.jpa_playground.dto.ProductDTO;
import org.learn.jpa_playground.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/productForm")
    public String showCreateForm(Model model) {
        ProductDTO dto = new ProductDTO();
        model.addAttribute("product", dto);
        return "product/productForm";
    }

    @PostMapping
    public String processCreateForm(@ModelAttribute("product") ProductDTO dto) {

        ProductDTO productDTO = new ProductDTO();
        productDTO.getId();
        productDTO.setName(dto.getName());
        productDTO.setDescription(dto.getDescription());
        productDTO.setPrice(dto.getPrice());
        productDTO.setStockQuantity(dto.getStockQuantity());
        productDTO.setStatus(dto.getStatus());
        productDTO.setCategory(dto.getCategory());

        productService.save(dto);
        return "redirect:/";
    }

}
