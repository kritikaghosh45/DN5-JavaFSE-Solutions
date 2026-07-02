import java.util.List;

public class LibraryService {
    private BookRepository bookRepository;

    public LibraryService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public String getBookById(int id) {
        return bookRepository.findBookById(id);
    }

    public List<String> getAllBooks() {
        return bookRepository.findAllBooks();
    }

    public boolean addBook(String title) {
        if (title == null || title.isEmpty()) {
            return false;
        }
        return bookRepository.saveBook(title);
    }
}