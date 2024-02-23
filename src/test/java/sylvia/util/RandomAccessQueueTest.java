package sylvia.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RandomAccessQueueTest {
    private RandomAccessQueue<Integer> queue;

    @BeforeEach
    public void setUp() {
        queue = new RandomAccessQueue<>();
    }

    @Test
    public void addLast_singleElement_success() {
        // Arrange
        int element = 42;

        // Act
        queue.addLast(element);

        // Assert
        assertEquals(element, queue.get(0));
        assertEquals(1, queue.size());
    }

    @Test
    public void addLast_multipleElements_success() {
        // Arrange
        int element1 = 42;
        int element2 = 24;

        // Act
        queue.addLast(element1);
        queue.addLast(element2);

        // Assert
        assertEquals(element1, queue.get(0));
        assertEquals(element2, queue.get(1));
        assertEquals(2, queue.size());
    }

    @Test
    public void addLast_fullQueue_oldestElementRemoved() {
        // Arrange
        int element1 = 42;
        int element2 = 24;
        int element3 = 12;

        RandomAccessQueue<Integer> queueCus = new RandomAccessQueue<>(2);

        // Act
        queueCus.addLast(element1);
        queueCus.addLast(element2);
        queueCus.addLast(element3);

        // Assert
        assertEquals(element2, queueCus.get(0));
        assertEquals(element3, queueCus.get(1));
        assertEquals(2, queueCus.size());
    }

    @Test
    public void pollFirst_emptyQueue_returnsNull() {
        // Act
        Integer result = queue.pollFirst();

        // Assert
        assertEquals(null, result);
    }

    @Test
    public void pollFirst_singleElement_success() {
        // Arrange
        int element = 42;
        queue.addLast(element);

        // Act
        Integer result = queue.pollFirst();

        // Assert
        assertEquals(element, result);
        assertEquals(0, queue.size());
    }

    @Test
    public void get_invalidIndex_throwsIndexOutOfBoundsException() {
        // Arrange
        int index = 0;

        // Act & Assert
        assertThrows(IndexOutOfBoundsException.class, () -> queue.get(index));
    }

    @Test
    public void get_validIndex_success() {
        // Arrange
        int element1 = 42;
        int element2 = 24;
        queue.addLast(element1);
        queue.addLast(element2);

        // Act
        int result = queue.get(1);

        // Assert
        assertEquals(element2, result);
    }

    @Test
    public void size_emptyQueue_returnsZero() {
        // Act
        int result = queue.size();

        // Assert
        assertEquals(0, result);
    }

    @Test
    public void size_nonEmptyQueue_returnsCorrectSize() {
        // Arrange
        int element1 = 42;
        int element2 = 24;
        queue.addLast(element1);
        queue.addLast(element2);

        // Act
        int result = queue.size();

        // Assert
        assertEquals(2, result);
    }

    @Test
    public void traverseUp_emptyQueue_returnsNull() {
        // Act
        Integer result = queue.traverseUp();

        // Assert
        assertEquals(null, result);
    }

    @Test
    public void traverseUp_singleElement_returnsElement() {
        // Arrange
        int element = 42;
        queue.addLast(element);

        // Act
        Integer result = queue.traverseUp();

        // Assert
        assertEquals(element, result);
    }

    @Test
    public void traverseUp_multipleElements_success() {
        // Arrange
        int element1 = 42;
        int element2 = 24;
        queue.addLast(element1);
        queue.addLast(element2);

        // Act
        Integer result1 = queue.traverseUp();
        Integer result2 = queue.traverseUp();

        // Assert
        assertEquals(element2, result1);
        assertEquals(element1, result2);
    }

    @Test
    public void traverseDown_emptyQueue_returnsNull() {
        // Act
        Integer result = queue.traverseDown();

        // Assert
        assertEquals(null, result);
    }

    @Test
    public void traverseDown_singleElement_returnsNull() {
        // Arrange
        int element = 42;
        queue.addLast(element);

        // Act
        Integer result = queue.traverseDown();

        // Assert
        assertEquals(null, result);
    }

    @Test
    public void traverseDown_multipleElements_success() {
        // Arrange
        int element1 = 42;
        int element2 = 24;
        queue.addLast(element1);
        queue.addLast(element2);

        // Act
        queue.traverseUp();
        queue.traverseUp();
        Integer result1 = queue.traverseDown();
        Integer result2 = queue.traverseDown();

        // Assert
        assertEquals(element2, result1);
        assertEquals(null, result2);
    }
}
