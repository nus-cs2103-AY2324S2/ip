package gronk;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserInterfaceTest {
    @Test
    public void returnAllTasksTest(){
        Storage storage = new Storage("testTasks.txt");
        storage.loadFromFile();
        Parser parser = new Parser(storage.returnTasks());
        UserInterface userInterface = new UserInterface();
        String expectedMessage = "\t1. [T] [ ] gaming\n" +
                "\t2. [D] [ ] swim (by: Jan 1 2025)\n" +
                "\t3. [T] [X] game\n" +
                "\t4. [T] [X] fight";
        assertEquals(userInterface.returnAllTasks(storage.returnTasks()), expectedMessage);
    }

    @Test
    public void returnEmptyTasksTest(){
        Storage storage = new Storage("testTasks2.txt");
        storage.loadFromFile();
        Parser parser = new Parser(storage.returnTasks());
        UserInterface userInterface = new UserInterface();
        String expectedMessage = "";
        assertEquals(userInterface.returnAllTasks(storage.returnTasks()), expectedMessage);
    }
}