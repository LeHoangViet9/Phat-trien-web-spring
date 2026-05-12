package product_management.service;

import org.springframework.stereotype.Service;
import product_management.model.Product;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductService {
    private List<Product> products = new ArrayList<>();

    public ProductService() {

        products.add(new Product(1, "Laptop", 1500));
        products.add(new Product(2, "Mouse", 20));
        products.add(new Product(3, "Keyboard", 50));

    }

    public List<Product> getAllProducts() {
        return products;
    }
    public Product addProduct(Product product) {
        products.add(product);
        return product;
    }
    public Product updateProduct(int id,Product newProduct){
        for(Product product:products){
            if(product.getId()==id){
                product.setName(newProduct.getName());
                product.setPrice(newProduct.getPrice());
                return product;
            }
        }
        return null;

    }
    public boolean deleteProduct(int id){
        for(Product product:products){
            if(product.getId()==id){
                products.remove(product);
                return true;
            }
        }
        return false;
    }

}
