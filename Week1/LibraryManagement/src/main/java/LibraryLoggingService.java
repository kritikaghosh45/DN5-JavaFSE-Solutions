import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LibraryLoggingService {

    private static final Logger logger = LoggerFactory.getLogger(LibraryLoggingService.class);

    public String findBook(String title) {
        logger.info("Searching for book: {}", title);

        if (title == null || title.isEmpty()) {
            logger.error("Book title cannot be null or empty");
            return null;
        }

        if (title.equals("Restricted")) {
            logger.warn("Attempt to access restricted book: {}", title);
            return null;
        }

        logger.debug("Book found successfully: {}", title);
        return "Found: " + title;
    }

    public boolean deleteBook(int bookId) {
        logger.info("Attempting to delete book with ID: {}", bookId);

        if (bookId <= 0) {
            logger.error("Invalid book ID: {}. ID must be positive.", bookId);
            return false;
        }

        logger.info("Book with ID {} deleted successfully", bookId);
        return true;
    }
}