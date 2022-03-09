package com.example.demo.controller;

import com.example.demo.dto.CommonResponse;
import com.example.demo.dto.ProductDto;
import com.example.demo.dto.UpdateStockDto;
import com.example.demo.entity.ProductEntity;
import com.example.demo.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public List<ProductEntity> getProducts(
            @RequestParam(value = "inStock", defaultValue = "0") boolean isInStock,
            @RequestParam(value = "maxPrice", defaultValue = "0") long maxPrice
    ) {
        //TODO: Add code to get all product list here
        if (maxPrice == 0){
            return productService.fetch(isInStock);
        } else {
            return productService.fetchPriceUnder(maxPrice);
        }
    }

    @GetMapping("{id}")
    public ProductEntity getProduct(@PathVariable("id") String id) {
        //TODO: Add code to get product here
        return productService.getById(Long.parseLong(id));
//        return new CommonResponse("Dummy Product");
    }

    @PostMapping("")
    public ProductEntity addProduct(@RequestBody ProductDto productDto)  {
        return productService.add(productDto);
    }

    @PutMapping("/stock")
    public ProductEntity updateStock(@RequestBody UpdateStockDto req) {
        //TODO: Add code to post here
        return productService.updateStock(req);
    }

    @DeleteMapping("{id}")
    public CommonResponse deleteProduct(@PathVariable("id") String id) {
        //TODO: Add code to get product list here
        productService.delete(Long.parseLong(id));
        return new CommonResponse("Successfully delete product");
    }
}
