import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private static Long savedProductId;
    @Test
    @Order(1)
    void testAddProduct() throws Exception {
        Product product = new Product();
        product.setName("Laptop");
        product.setDescription("Gaming Laptop");
        product.setPrice(1299.99);
        product.setStockQuantity(10);
        product.setCategory("Electronics");
        String response = mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn().getResponse().getContentAsString();

        Product saved = objectMapper.readValue(response, Product.class);
        savedProductId = saved.getId();
    }
    @Test
    @Order(2)
    void testGetAllProducts() throws Exception {
        mockMvc.perform(get("/products"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1));
    }
    @Test
    @Order(3)
    void testGetProductById() throws Exception {
        mockMvc.perform(get("/products/" + savedProductId))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Laptop"));
    }
    @Test
    @Order(4)
    void testUpdateProduct() throws Exception {
        Product updated = new Product();
        updated.setName("Laptop Pro");
        updated.setDescription("High-end Gaming Laptop");
        updated.setPrice(1499.99);
        updated.setStockQuantity(8);
        updated.setCategory("Electronics");
        mockMvc.perform(put("/products/" + savedProductId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updated)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Laptop Pro"));
    }
    @Test
    @Order(5)
    void testDeleteProduct() throws Exception {
        mockMvc.perform(delete("/products/" + savedProductId))
            .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(get("/products/" + savedProductId))
            .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
}
