package arona;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddTaskTest {
    @Test
    public void addTaskTest_success() {
        try {
            Ui ui = new Ui();
            TaskList tasklist = new TaskList(ui);
            Storage storage = new Storage();
            String fullCommandOne = "todo food";
            String fullCommandTwo = "deadline movie /by 2020-12-31";
            String fullCommandThree = "event run /from 2020-10-20 /to 2021-12-20";

            new AddTask(fullCommandOne).execute(tasklist, ui, storage);
            new AddTask(fullCommandTwo).execute(tasklist, ui, storage);
            new AddTask(fullCommandThree).execute(tasklist, ui, storage);

            assertEquals("[T][ ] food", tasklist.getTasks().get(0).toString());
            assertEquals("[D][ ] movie (by: 31/12/2020)", tasklist.getTasks().get(1).toString());
            assertEquals("[E][ ] run (from: 20/10/2020 to: 20/12/2021)", tasklist.getTasks().get(2).toString());
        } catch (FileException e) {

        } catch (TaskException e) {

        }
    }
}