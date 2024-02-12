import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void anotherDummyTest() {
        assertEquals(4, 4);
    }

    public static void main(String[] args) {
        DukeTest test = new DukeTest();
        test.dummyTest();
        test.anotherDummyTest();
    }

}
