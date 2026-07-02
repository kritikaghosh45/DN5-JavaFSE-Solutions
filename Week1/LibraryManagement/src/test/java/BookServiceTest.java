import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookServiceTest {

    private BookService bookService;

    // Setup: runs before EACH test method
    @BeforeEach
    public void setUp() {
        bookService = new BookService();
        bookService.addBook("Java Programming");
        bookService.addBook("Design Patterns");
        System.out.println("Setup: BookService initialized with 2 books");
    }

    // Teardown: runs after EACH test method
    @AfterEach
    public void tearDown() {
        bookService = null;
        System.out.println("Teardown: BookService cleared");
    }

    @Test
    public void testAddBook() {
        // Arrange
        String newBook = "Spring Boot in Action";

        // Act
        bookService.addBook(newBook);

        // Assert
        assertTrue(bookService.isAvailable(newBook));
        assertEquals(3, bookService.getTotalBooks());
    }

    @Test
    public void testRemoveBook() {
        // Arrange
        String bookToRemove = "Design Patterns";

        // Act
        boolean result = bookService.removeBook(bookToRemove);

        // Assert
        assertTrue(result);
        assertFalse(bookService.isAvailable(bookToRemove));
        assertEquals(1, bookService.getTotalBooks());
    }

    @Test
    public void testIsAvailable() {
        // Arrange — already done in @BeforeEach

        // Act & Assert
        assertTrue(bookService.isAvailable("Java Programming"));
        assertFalse(bookService.isAvailable("Unknown Book"));
    }

    @Test
    public void testRemoveNonExistentBook() {
        // Arrange
        String nonExistent = "Fake Book";

        // Act
        boolean result = bookService.removeBook(nonExistent);

        // Assert
        assertFalse(result);
        assertEquals(2, bookService.getTotalBooks());
    }
}