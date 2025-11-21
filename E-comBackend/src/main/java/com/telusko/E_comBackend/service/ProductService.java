package com.telusko.E_comBackend.service;

import com.telusko.E_comBackend.model.Product;
import com.telusko.E_comBackend.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productrepo;

    public List<Product> getAllProducts(){
        return productrepo.findAll();
    }

    public Product getProductById(int id) {
        return productrepo.findById(id).orElse(null);//Better Convention is to set Status Code

    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        return productrepo.save(product);
    }

    public Product updateProduct(int id,Product product, MultipartFile imageFile) throws IOException {
        product.setImageData(imageFile.getBytes());
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        return productrepo.save(product);
    }

    public void deleteProduct(int id) {
        productrepo.deleteById(id);
    }

    public List<Product> searchProducts(String keyword) {
        return productrepo.searchProducts(keyword);
    }
}
