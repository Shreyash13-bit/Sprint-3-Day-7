import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class UserTest {
    @Test
    public void testUserFields() {
        User user = new User("Shreyash", "shreyash@gmail.com", 22);

        assertAll("User properties",
            () -> assertEquals("Shreyash", user.getName()),
            () -> assertEquals("shreyash@gmail.com", user.getEmail()),
            () -> assertEquals(22, user.getAge()),
            () -> assertNotNull(user.getEmail()),
            () -> assertTrue(user.getAge() > 18)
        );
    }
    @Test
    public void testValidateAgeThrowsException() {
        UserService userService = new UserService();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.validateAge(16);
        });
        assertEquals("Underage", exception.getMessage());
    }
}
