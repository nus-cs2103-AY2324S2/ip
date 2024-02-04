//package Duke.commands;
//
//import Duke.exceptions.DukeException;
//import Duke.util.TaskList;
//import Duke.util.UI;
//import Duke.util.Storage;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.fail;
//
//public class ToDoCommandTest {
//    @Test
//    public void ToDoCommandExecution() throws DukeException {
//        String token = "todo eat";
//        String[] words = token.split(" ", 2);
//        String res = "____________________________________________________________\n" +
//                " Got it. I've added this task:\n" +
//                "   [T][ ] eat\n" +
//                " Now you have 1 tasks in the list.\n" +
//                "____________________________________________________________";
//        assertEquals(res, new ToDoCommand(words).execute(
//                new TaskList(new ArrayList<>()),
//                new UI(),
//                new Storage("./date/duke.txt")));
//    }
//}

