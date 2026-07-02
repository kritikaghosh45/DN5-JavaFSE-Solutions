import java.util.ArrayList;
import java.util.List;

public class BookService {
    private List<String> books = new ArrayList<>();

    public void addBook(String title) {
        books.add(title);
    }

    public boolean removeBook(String title) {
        return books.remove(title);
    }

    public boolean isAvailable(String title) {
        return books.contains(title);
    }

    public int getTotalBooks() {
        return books.size();
    }
}