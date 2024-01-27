import java.io.IOException;

public class ExitCommand extends Command {

    public ExitCommand() {
        super(true);
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.saveTodoData(tasks, ui);
        } catch (IOException e) {
            System.out.println("Data not saved: " + e.getMessage());
        }
    }
}
