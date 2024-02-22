//package cruisey.task;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.fail;
//
//import org.junit.jupiter.api.Test;
//
//import cruisey.exception.CruiseyException;
//import cruisey.ui.Ui;
//
///**
// * JUnit tests for the {@link ToDo} class for CLI.
// */
//public class ToDoTest { //only worked for CLI
//    /**
//     * Tests the constructor for creating a ToDo task with a description.
//     * Verifies that the ToDo task is created successfully with the given description.
//     */
//
//    private Ui ui = new Ui();
//    private TaskPriority priority;
//    @Test
//    public void constructor_todoWithDescription_success() {
//        try {
//            ToDo todo = new ToDo("Test ToDo", ui, priority);
//            assertEquals("Test ToDo", todo.getDescription());
//            assertFalse(todo.checkStatus());
//        } catch (CruiseyException e) {
//            fail("Exception not expected: " + e.getMessage());
//        }
//    }
//
//    /**
//     * Tests the {@code toString} method for the string representation of a ToDo task.
//     * Verifies that the string representation matches the expected format.
//     */
//    @Test
//    public void toString_todoCheckStringRepresentation_success() { //only worked for CLI
//        try {
//            ToDo todo = new ToDo("Test ToDo", ui, priority);
//            assertEquals("Got it. I've added this task: \n [T][" + todo.getStatusIcon() + "] "
//                            + todo.getDescription(),
//                    todo.toString());
//        } catch (CruiseyException e) {
//            fail("Exception not expected: " + e.getMessage());
//        }
//    }
//
//}
