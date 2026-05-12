package product_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import product_management.model.Product;
import product_management.service.ProductService;

import java.util.List;
@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product product){
        return productService.updateProduct(id, product);
    }
    @DeleteMapping("products/{id}")
    public String deleteProduct(@PathVariable int id){
        boolean res=productService.deleteProduct(id);
        if(res){
            return "Delete successfully";
        }
        return "Product not found";
    }

}
