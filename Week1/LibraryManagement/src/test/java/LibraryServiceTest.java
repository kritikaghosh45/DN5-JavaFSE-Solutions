import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LibraryServiceTest {

    private BookRepository mockRepository;
    private LibraryService libraryService;

    @BeforeEach
    public void setUp() {
        // Create a mock of BookRepository
        mockRepository = Mockito.mock(BookRepository.class);
        libraryService = new LibraryService(mockRepository);
    }

    @Test
    public void testGetBookById_Stubbing() {
        // Arrange — stub the mock to return a specific value
        when(mockRepository.findBookById(1)).thenReturn("Clean Code");

        // Act
        String result = libraryService.getBookById(1);

        // Assert
        assertEquals("Clean Code", result);
        System.out.println("Found book: " + result);
    }

    @Test
    public void testGetAllBooks_Stubbing() {
        // Arrange
        List<String> mockBooks = Arrays.asList("Clean Code", "Design Patterns", "Refactoring");
        when(mockRepository.findAllBooks()).thenReturn(mockBooks);

        // Act
        List<String> result = libraryService.getAllBooks();

        // Assert
        assertEquals(3, result.size());
        assertTrue(result.contains("Clean Code"));
        System.out.println("All books: " + result);
    }

    @Test
    public void testAddBook_Stubbing() {
        // Arrange
        when(mockRepository.saveBook("Spring Boot")).thenReturn(true);

        // Act
        boolean result = libraryService.addBook("Spring Boot");

        // Assert
        assertTrue(result);
        System.out.println("Book saved: " + result);
    }

    @Test
    public void testAddBook_NullTitle() {
        // No stub needed — null check happens before repository call
        boolean result = libraryService.addBook(null);
        assertFalse(result);
    }
}