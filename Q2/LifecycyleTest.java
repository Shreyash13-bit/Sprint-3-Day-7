import org.junit.jupiter.api.*;
public class LifecycleTest {
    @BeforeAll
    static void beforeAll() {
        System.out.println("@BeforeAll - runs once before all tests");
    }
    @BeforeEach
    void beforeEach() {
        System.out.println("@BeforeEach - runs before each test");
    }
    @Test
    void testOne() {
        System.out.println("Test 1 - executing testOne");
    }
    @Test
    void testTwo() {
        System.out.println("Test 2 - executing testTwo");
    }
    @AfterEach
    void afterEach() {
        System.out.println("@AfterEach - runs after each test");
    }
    @AfterAll
    static void afterAll() {
        System.out.println("@AfterAll - runs once after all tests");
    }
}
