public class LibraryLoggingMain {
    public static void main(String[] args) {
        LibraryLoggingService service = new LibraryLoggingService();

        // Test normal search
        System.out.println(service.findBook("Clean Code"));

        // Test null input - triggers ERROR log
        System.out.println(service.findBook(null));

        // Test restricted book - triggers WARN log
        System.out.println(service.findBook("Restricted"));

        // Test valid delete
        System.out.println(service.deleteBook(5));

        // Test invalid delete - triggers ERROR log
        System.out.println(service.deleteBook(-1));
    }
}