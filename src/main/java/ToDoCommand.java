import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToDoCommand extends Command{

    private final String desc;
    public ToDoCommand(String desc) {
        this.desc = desc;
    }

    public void execute(TaskList tasks, UI ui, Storage storage) {
        Task newTask = new ToDo(this.desc);
        tasks.add(newTask);
        ui.printAddTask(newTask, tasks.size());
    }
}
