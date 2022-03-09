package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.UpdateStockDto;
import com.example.demo.entity.ProductEntity;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductEntity add(ProductDto req){
        ProductEntity product = new ProductEntity();
        product.setName(req.getProductName());
        product.setPrice(req.getPrice());
        product.setStock(req.getStock());

        //todo: save to db
        return productRepository.save(product);
    }

    public List<ProductEntity> fetch(boolean isInStock){
        if (isInStock) {
            return fetchAllInStock();
        }else{
            return fetchAll();
        }
    }

    public List<ProductEntity> fetchAll(){
        return (List<ProductEntity>) productRepository.findAll();
    }

    public List<ProductEntity> fetchAllInStock(){
        return productRepository.findByStockGreaterThan(0);
    }

    public List<ProductEntity> fetchPriceUnder(long maxPrice){
        return productRepository.findByPriceLessThanEqual(maxPrice);
    }

    public ProductEntity getById(long id){
        return productRepository.findById(id).orElse(new ProductEntity());
    }

    public void delete(long id){
        productRepository.deleteById(id);
    }

    public ProductEntity updateStock(UpdateStockDto req){
        ProductEntity productEntity = getById(req.getId());

        long currentStock = productEntity.getStock() + req.getNumberOfStock();
        productEntity.setStock(currentStock);

        return productRepository.save(productEntity);
    }
}
