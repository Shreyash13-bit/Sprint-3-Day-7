import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    private static Long savedId;
    @Test
    @Order(1)
    void addBookTest() throws Exception {
        Book book = new Book();
        book.setTitle("1984");
        book.setAuthor("George Orwell");
        book.setPublishedYear(1949);
        String response = mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(book)))
            .andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();
        Book saved = mapper.readValue(response, Book.class);
        savedId = saved.getId();
    }
    @Test
    @Order(2)
    void getAllBooksTest() throws Exception {
        mockMvc.perform(get("/books"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(1));
    }
    @Test
    @Order(3)
    void getBookByIdTest() throws Exception {
        mockMvc.perform(get("/books/" + savedId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title").value("1984"));
    }
    @Test
    @Order(4)
    void updateBookTest() throws Exception {
        Book updated = new Book();
        updated.setTitle("Animal Farm");
        updated.setAuthor("George Orwell");
        updated.setPublishedYear(1945);
        mockMvc.perform(put("/books/" + savedId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(updated)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title").value("Animal Farm"));
    }
    @Test
    @Order(5)
    void deleteBookTest() throws Exception {
        mockMvc.perform(delete("/books/" + savedId))
            .andExpect(status().isOk());
        mockMvc.perform(get("/books/" + savedId))
            .andExpect(status().is4xxClientError());
    }
}
