import java.io.IOException;

public class ListCommand extends Command {
    public ListCommand() {

    }

    @Override
    public void execute(Storage s, TaskList t, Ui u)  {
        System.out.println("--------------------------");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < t.getSize(); i++) {
            System.out.println(String.valueOf(i+1) + "." + t.getTask(i).toString());
        }
        System.out.println("--------------------------");

    }

    @Override
    public boolean isExit() {
        return false;
    }
}