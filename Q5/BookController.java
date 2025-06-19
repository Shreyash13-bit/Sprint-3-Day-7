import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@RequestMapping("/books")
public class BookController {
    private final BookRepository repository;
    public BookController(BookRepository repository) {
        this.repository = repository;
    }
    @GetMapping
    public List<Book> getAllBooks() {
        return repository.findAll();
    }
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }
    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return repository.save(book);
    }
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book updated) {
        Book book = repository.findById(id).orElseThrow();
        book.setTitle(updated.getTitle());
        book.setAuthor(updated.getAuthor());
        book.setPublishedYear(updated.getPublishedYear());
        return repository.save(book);
    }
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
