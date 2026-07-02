import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MyClassTest {

    // Exercise 1: Basic test (already done)
    @Test
    public void testAdd() {
        MyClass obj = new MyClass();
        assertEquals(5, obj.add(2, 3));
    }

    // Exercise 3: Assertions in JUnit
    @Test
    public void testAddAssertions() {
        MyClass obj = new MyClass();

        // assertEquals - checks exact value
        assertEquals(10, obj.add(7, 3), "7 + 3 should equal 10");

        // assertNotEquals - checks values are different
        assertNotEquals(5, obj.add(1, 1), "1 + 1 should not equal 5");

        // assertTrue / assertFalse
        assertTrue(obj.add(2, 2) == 4, "2 + 2 should be 4");
        assertFalse(obj.add(2, 2) == 5, "2 + 2 should not be 5");

        // assertNull / assertNotNull
        String str = null;
        assertNull(str, "str should be null");

        String name = "Library";
        assertNotNull(name, "name should not be null");
    }

    @Test
    public void testAddNegativeNumbers() {
        MyClass obj = new MyClass();
        assertEquals(-5, obj.add(-2, -3), "Adding two negatives");
    }

    @Test
    public void testAddZero() {
        MyClass obj = new MyClass();
        assertEquals(7, obj.add(7, 0), "Adding zero should return same number");
    }
}