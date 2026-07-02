import java.util.List;

public interface BookRepository {
    String findBookById(int id);
    List<String> findAllBooks();
    boolean saveBook(String title);
}