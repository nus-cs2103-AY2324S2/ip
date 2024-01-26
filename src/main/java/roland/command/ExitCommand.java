package roland.command;

import roland.Storage;
import roland.task.Task;
import roland.TaskList;
import roland.Ui;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ExitCommand extends Command {

    public ExitCommand() {

    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        serializeArrayList(tasks.getList(), storage.getFilePath());
        System.out.println(ui.getBot() + "Bye. Hope to see you again soon!");

    }

    public boolean isExit() {
        return true;
    }

    private static void serializeArrayList(ArrayList<Task> list, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
