package com.example.warsztatsamochodowy.service;

import com.example.warsztatsamochodowy.dto.ProductDto;
import com.example.warsztatsamochodowy.entity.Product;
import com.example.warsztatsamochodowy.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Iterable<Product> getList(){
        return productRepository.findAll();
    }

    public Optional<Product> getOne(long id){
        return productRepository.findById(id);
    }

    public List<String> validate(ProductDto dto) {
        List<String> errors = new ArrayList<>();

        if (dto.getNazwa().isBlank()) {
            errors.add("Nazwa jest wymagana.");
        }

        return errors;
    }

    public long save(ProductDto dto) {
        Product product;
        if (dto.getId() == 0) {
            product = new Product();
        } else {
            Optional<Product> opt = productRepository.findById(dto.getId());
            if (opt.isEmpty()) return 0;
            product = opt.get();
        }

        product.setNazwa(dto.getNazwa());
        product.setOpis(dto.getOpis());
        product.setCena(dto.getCena());
        product.setRodzaj(dto.getRodzaj());
        product = productRepository.save(product);

        return product.getId();
    }

    public void delete(long id){
        productRepository.deleteById(id);
    }
}
