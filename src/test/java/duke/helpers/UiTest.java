package duke.helpers;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UiTest {
    @Test
    public void testGetCommandList() {
        // Arrange
        String expected = "These are the available commands:\n"
                + "0. Do list commands : list commands\n"
                + "1. Add todo task : todo [your task]\n"
                + "2. Add deadline task : deadline [your task] /by [yyyy-mm-dd hh:mm]\n"
                + "3. Add event task : event [your task] /from [yyyy-mm-dd hh:mm] /to [yyyy-mm-dd hh:mm]\n"
                + "4. To list all tasks : list\n"
                + "5. To mark task done : mark [task number (integer)]\n"
                + "6. To unmark task : unmark [task number (integer)]\n"
                + "7. To delete a task : delete [task number (integer)]\n"
                + "8. To find tasks related to a date : checkdate [yyyy-mm-dd]\n"
                + "9. To find tasks related to a matching word : find [word]\n"
                + "10. To undo :undo\n"
                + "11. Close chatbot : bye\n";

        String actual = new Ui().getCommandList(); // Replace YourClassName with the actual class name

        assertEquals(expected, actual);
    }
}
