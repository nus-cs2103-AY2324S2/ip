//package duke;
//
//import duke.task.*;
//import duke.parser.Parser;
//import duke.ui.Ui;
//import duke.storage.Storage;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class ParserTest {
//    private Parser parser;
//    private Ui mockUi;
//    private Storage mockStorage;
//    private TaskList mockTaskList;
//    private ArrayList<Task> taskList;
//
////    taskList = new ArrayList<Task>();
//
//    @BeforeEach
//    public void setUp() {
//        // Initialize mock dependencies
//        mockUi = new Ui("TestUser");
//        mockStorage = new Storage("./data/test.txt");
//        mockTaskList = new TaskList(taskList, mockUi);
//
//        // Create the Parser object with mock dependencies
//        parser = new Parser(mockUi, mockStorage, mockTaskList);
//    }
//
//    @Test
//    public void testExecute_ByeCommand() throws IOException {
//        // Test the execute method for handling the "bye" command
//        String userInput = "bye";
//        parser.execute(userInput);
//
//        // Ensure that tasks are saved and goodbye message is sent
//        assertTrue(mockStorage.isTasksSaved());
//        assertTrue(mockUi.isGoodbyeMessageSent());
//    }
//
//    @Test
//    public void testExecute_ListCommand() throws IOException {
//        // Test the execute method for handling the "list" command
//        String userInput = "list";
//        parser.execute(userInput);
//
//        // Ensure that the task list is shown
//        assertTrue(mockUi.isTaskListShown());
//    }
//
//    // Add more test methods to cover other functionalities of the Parser class
//}
