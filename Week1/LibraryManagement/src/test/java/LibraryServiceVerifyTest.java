import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LibraryServiceVerifyTest {

    private BookRepository mockRepository;
    private LibraryService libraryService;

    @BeforeEach
    public void setUp() {
        mockRepository = Mockito.mock(BookRepository.class);
        libraryService = new LibraryService(mockRepository);
    }

    @Test
    public void testGetBookById_VerifyInteraction() {
        // Arrange
        when(mockRepository.findBookById(1)).thenReturn("Clean Code");

        // Act
        libraryService.getBookById(1);

        // Verify — was findBookById called exactly once with argument 1?
        verify(mockRepository, times(1)).findBookById(1);
        System.out.println("Verified: findBookById(1) was called exactly once");
    }

    @Test
    public void testAddBook_VerifyInteraction() {
        // Arrange
        when(mockRepository.saveBook("Effective Java")).thenReturn(true);

        // Act
        libraryService.addBook("Effective Java");

        // Verify — saveBook was called with the right argument
        verify(mockRepository).saveBook("Effective Java");
        System.out.println("Verified: saveBook called with 'Effective Java'");
    }

    @Test
    public void testAddBook_NullTitle_VerifyNoInteraction() {
        // Act
        libraryService.addBook(null);

        // Verify — saveBook should NEVER be called when title is null
        verify(mockRepository, never()).saveBook(anyString());
        System.out.println("Verified: saveBook was never called for null title");
    }

    @Test
    public void testGetBookById_CalledMultipleTimes() {
        // Arrange
        when(mockRepository.findBookById(anyInt())).thenReturn("Some Book");

        // Act — call twice
        libraryService.getBookById(1);
        libraryService.getBookById(2);

        // Verify — findBookById was called exactly 2 times total
        verify(mockRepository, times(2)).findBookById(anyInt());
        System.out.println("Verified: findBookById was called 2 times");
    }
}