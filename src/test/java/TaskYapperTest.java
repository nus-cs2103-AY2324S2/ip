import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
public class TaskYapperTest {
    @Test
    public void dummyTest() {
        TaskYapper yapper = new TaskYapper("./data/taskyapper.txt");
        String message = yapper.getResponse("abc");
        assertEquals(message, "Error: What's YAPpening??!! Please yap your instruction more clearly");
    }

    public static void main(String[] args) {
        TaskYapperTest test = new TaskYapperTest();
        test.dummyTest();
    }

}
