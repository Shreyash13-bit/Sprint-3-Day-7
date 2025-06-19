import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository repository;
    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }
    @GetMapping
    public List<Product> getAll() {
        return repository.findAll();
    }
    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return repository.save(product);
    }
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updated) {
        Product existing = repository.findById(id).orElseThrow();
        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setPrice(updated.getPrice());
        existing.setStockQuantity(updated.getStockQuantity());
        existing.setCategory(updated.getCategory());
        return repository.save(existing);
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
