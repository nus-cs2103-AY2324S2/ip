package processor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProcessorTest {
    private TaskList taskList;
    private Ui chatbotUi;
    private Processor processor;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        chatbotUi = new Ui();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        processor = new Processor(taskList, chatbotUi);
    }

    @Test
    public void testUserInputProcessMarkUnmark_markTask_success() throws IOException {
        // Add a task to the task list
        Task task = new Task("Sample Task", false);
        taskList.addTask(task);

        // Mark the task
        processor.userInputProcessMarkUnmark("mark 1");

        // Verify that the task is marked
        assertEquals(true, task.isDone());

        // Verify the output message
        String expectedOutput = (chatbotUi.divider()
            + "Nice! I've marked this task as done:\n"
            + "[T][X] Sample Task\n"
            + chatbotUi.divider()).trim().replace("\r\n", "\n");
    
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r\n", "\n"));
    }

//     @Test
//     public void testUserInputProcessMarkUnmark_unmarkTask_success() throws IOException {
//     // Add a task to the task list
//     Task task = new Task("Sample Task", true);
//     taskList.addTask(task);

//     // Unmark the task
//     processor.userInputProcessMarkUnmark("unmark 1");

//     // Verify that the task is unmarked
//     assertEquals(false, task.isDone());

//     // Verify the output message
//     String expectedOutput = "OK, I've marked this task as not done yet:\n"
//             + "[T][ ] Sample Task\n";
//     assertTrue(outputStream.toString().contains(expectedOutput));
// }

//     @Test
//     public void testUserInputAddTask_todoTask_success() throws IOException {
//         // Add a todo task
//         processor.userInputAddTask("todo Sample Todo");

//         // Verify that the task is added to the task list
//         assertEquals(1, taskList.size());
//         Task task = taskList.getTaskAtIndex(0);
//         assertEquals("[T][ ] Sample Todo", task.toString());

//         // Verify the output message
//         String expectedOutput = chatbotUi.divider()
//                 + "Got it. I've added this task:\n"
//                 + "[T][ ] Sample Todo\n"
//                 + "Now you have 1 tasks in the list.\n"
//                 + chatbotUi.divider();
//         assertEquals(expectedOutput.trim().replace("\r\n", "\n"), outputStream.toString().trim().replace("\r\n", "\n"));
//     }


}