//package duke.task;
//
//import duke.exception.DukeException;
//
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * JUnit tests for the {@link ToDo} class.
// */
//public class ToDoTest {
//
//    /**
//     * Tests the constructor for creating a ToDo task with a description.
//     * Verifies that the ToDo task is created successfully with the given description.
//     */
//    @Test
//    public void constructor_todoWithDescription_success() {
//        try {
//            ToDo todo = new ToDo("Test ToDo");
//            assertEquals("Test ToDo", todo.getDescription());
//            assertFalse(todo.checkStatus());
//        } catch (DukeException e) {
//            fail("Exception not expected: " + e.getMessage());
//        }
//    }
//
//    /**
//     * Tests the {@code toString} method for the string representation of a ToDo task.
//     * Verifies that the string representation matches the expected format.
//     */
//    @Test
//    public void toString_todoCheckStringRepresentation_success() {
//        try {
//            ToDo todo = new ToDo("Test ToDo");
//            assertEquals("Got it. I've added this task: \n [T][" + todo.getStatusIcon() + "] "
//             + todo.getDescription(),
//                    todo.toString());
//        } catch (DukeException e) {
//            fail("Exception not expected: " + e.getMessage());
//        }
//    }
//
//}
