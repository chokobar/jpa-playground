package org.learn.jpa_playground.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.learn.jpa_playground.domain.MemberDomain;
import org.learn.jpa_playground.dto.ProductDTO;
import org.learn.jpa_playground.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/productForm")
    public String showCreateForm(@SessionAttribute(value = "member", required = false) MemberDomain member , Model model) {
        if (member == null || !member.getUserRole().equals("ADMIN")) {
            return "redirect:/access-denied";
        }
        ProductDTO dto = new ProductDTO();
        model.addAttribute("product", dto);
        return "product/productForm";
    }

    @PostMapping("/save")
    public String processCreateForm(@Validated @ModelAttribute("product") ProductDTO dto,
                                    BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "product/productForm"; // 다시 폼으로
        }

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

    @GetMapping("/listForm")
    public String showProductList(Model model) {
        log.info("showProductList");
        List<ProductDTO> product = productService.findAll();
        model.addAttribute("products", product);
        return "product/productList";
    }

    @GetMapping("/{id}")
    public String showProductDetail(@PathVariable Long id, Model model) {
        log.info("showProductDetail");
        ProductDTO productDTO =  productService.findById(id);
        model.addAttribute("product", productDTO);
        return "product/productDetail";
    }
}
