package bmo.util;

import bmo.command.Command;
import bmo.command.DoneCommand;
import bmo.task.Task;
import bmo.ui.Ui;

import java.util.ArrayList;

/**
 * TaskList class to store and manage the list of tasks.
 */
public class TaskList extends ArrayList<Task> {
    private ArrayList<Task> taskLog;

    /**
     * Constructor for TaskList.
     *
     * @param ui Ui object to handle user interface.
     */
    public TaskList(Ui ui) {
        this.taskLog = new ArrayList<>();
        ui.printEmptyStorage();
    }

    /**
     * Constructor for TaskList.
     *
     * @param content String containing the saved data.
     * @param ui      Ui object to handle user interface.
     * @param storage Storage object to handle file storage.
     */
    public TaskList(String content, Ui ui, Storage storage) {
        taskLog = new ArrayList<>();

        assert ui != null : "UI should not be null";
        assert storage != null : "Storage should not be null";

        if (content.isBlank()) {
            ui.printEmptyStorage();
            return;
        }

        String[] lines = content.split("\n");
        Integer indexCounter = 1;

        for (String line : lines) {
            assert line != null : "Line should not be null";
            System.out.println(line);
            String[] info = line.split("\\|");
            assert info.length >= 3 : "Info array should have at least 3 elements";

            String taskType = info[0].trim();
            boolean isDone = info[1].trim().equals("1");
            String taskDescription = info[2].trim();

            try {
                Command c = null;
                switch (taskType) {
                    case "T":
                        c = Parser.parse("todo " + taskDescription);
                        break;
                    case "D":
                        String taskDueDate = info[3].trim();
                        c = Parser.parse("due " + taskDescription + " /by " + taskDueDate);
                        break;
                    case "E":
                        String taskStart = info[3].trim();
                        String taskEnd = info[4].trim();
                        c = Parser.parse("event " + taskDescription + " /from " + taskStart + " /to " + taskEnd);
                        break;
                    default:
                        ui.printErrInvalidCommand();
                        break;
                }

                if (c != null) {
                    c.execute(this, ui, storage);
                    if (isDone) {
                        Command done = new DoneCommand(indexCounter.toString());
                        done.execute(this, ui, storage);
                    }
                }
            } catch (Exception e) {
                ui.printErrInvalidTask();
            }

            indexCounter++;
        }
    }

    /**
     * Converts tasks in TaskList into a string to be saved as text data.
     */
    public String toSaveData() {
        assert this != null : "TaskList instance should not be null";
        StringBuilder taskContents = new StringBuilder();
        for (Task task : this) {
            taskContents.append(task.toSaveData());
        }
        return taskContents.toString();
    }
}
