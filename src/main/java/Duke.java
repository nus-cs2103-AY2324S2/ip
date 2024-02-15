import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String botName = "Yube";
        File file = new File("./yube.txt");
        file.createNewFile();
        Ui ui = new Ui();
        Storage storage = new Storage("./yube.txt");
        TaskList taskList = new TaskList(storage.loadTasks());

        ui.showWelcomeMessage(botName);
        while (true) {
            try {
                String input = reader.readLine();
                if (input.equals("bye")) {
                    ui.showExitMessage();
                    storage.writeArrayListToFile(taskList);
                    break;
                } else if (input.equals("list")) {
                    ui.printList(taskList);
                } else if (input.contains("mark")) {
                    String[] parts = input.split(" ");
                    int index = Integer.parseInt(parts[1]);
                    Task task = taskList.getTask(index - 1);
                    if (input.contains("unmark")) {
                        task.setNotDone();
                        ui.showUnmark(task);
                    } else {
                        task.setDone();
                        ui.showMark(task);
                    }

                } else if (input.contains("todo")) {
                    Task newTask = new Todo(input.substring(5));
                    taskList.addTask(newTask);
                    ui.showRepeatFunction(newTask, taskList);
                } else if (input.contains("deadline")) {
                    String[] parts = input.substring(9).split(" /");
                    Task newTask = new Deadline(parts[0], parts[1].substring(3));
                    taskList.addTask(newTask);
                    ui.showRepeatFunction(newTask, taskList);
                } else if (input.contains("event")) {
                    String[] parts = input.substring(6).split(" /");
                    Task newTask = new Event(parts[0], parts[1].substring(5),
                            parts[2].substring(3));
                    taskList.addTask(newTask);
                    ui.showRepeatFunction(newTask, taskList);
                } else if (input.contains("delete")) {
                    String[] parts = input.split(" ");
                    int deleteIndex = Integer.parseInt(parts[1]) - 1;
                    Task deletedTask = taskList.getTask(deleteIndex);
                    taskList.removeTask(deleteIndex);
                    ui.deleteTask(deletedTask, taskList);
                } else {
                    throw new DukeException("Unable to read input");
                }
            } catch (DukeException e) {
                ui.showErrorMessage(e.toString());
            }
        }

    }
}
