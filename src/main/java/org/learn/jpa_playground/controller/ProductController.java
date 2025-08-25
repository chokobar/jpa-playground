package org.learn.jpa_playground.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.learn.jpa_playground.domain.MemberDomain;
import org.learn.jpa_playground.dto.ProductDTO;
import org.learn.jpa_playground.enums.ProductCategory;
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
            return "product/productForm";
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
        return "redirect:/product/listForm";
    }

    @GetMapping("/listForm")
    public String showProductList(
            @RequestParam(value = "category", required = false, defaultValue = "ALL") String category,
            Model model) {

        List<ProductDTO> products =
                "ALL".equalsIgnoreCase(category)
                        ? productService.findAll()
                        : productService.findAllByCategory(ProductCategory.valueOf(category));

        model.addAttribute("products", products);
        model.addAttribute("categories", ProductCategory.values());
        model.addAttribute("selectedCategory", category);
        return "product/productList";
    }

    @GetMapping("/{id}")
    public String showProductDetail(@PathVariable Long id, Model model) {
        log.info("showProductDetail");
        ProductDTO productDTO =  productService.findById(id);
        model.addAttribute("product", productDTO);
        return "product/productDetail";
    }

    @GetMapping("/edit/{id}")
    public String showProductEditForm(@PathVariable Long id, Model model) {
        log.info("showProductEditForm");
        ProductDTO productDTO =  productService.findById(id);
        model.addAttribute("product", productDTO);
        return "product/productEditForm";
    }

    @PostMapping("/edit/{id}")
    public String processEditForm(@PathVariable Long id, @ModelAttribute("product") ProductDTO dto){
        log.trace("dto : {}", dto);
        dto.setId(id);
        productService.update(dto);
        return "redirect:/product/" + id;
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        log.info("deleteProduct");
        productService.delete(id);
        return "redirect:/product/listForm";
    }
}
