import java.util.List;
import java.util.ArrayList;
import task.taskList;
import task.Task;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm KAI\n" + "What can i do for you?\n");
        taskList tasklist = new taskList();
        Parsing.inputParsing(tasklist);


    }
}
