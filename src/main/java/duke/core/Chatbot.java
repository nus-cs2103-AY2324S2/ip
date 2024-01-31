package duke.core;
import duke.task.TaskList;
import duke.ui.Ui;
import java.util.Scanner;

public class Chatbot {

    private final String name;
    private TaskList tasklist; // Hold the list of tasks

    public Chatbot(String name) {
        this.name = name;
        this.tasklist = new TaskList();
    }

    public void startChat() {
        Scanner scanner = new Scanner(System.in);

        Ui.greet(name);

        boolean isChatting = true;
        while (isChatting) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                Ui.bye(tasklist);
                isChatting = false;
            } else if (input.contains("list")) {
                Ui.listTasks(tasklist);
            } else if (input.contains("mark")) {
                String[] parts = input.split("\\s+", 2);
                Ui.mark(tasklist, (parts[1]), !parts[0].contains("un"));
            } else if (input.contains("delete")) {
                String[] parts = input.split("\\s+", 2);
                Ui.delete(tasklist, parts[1]);
            } else {
                Ui.addTask(tasklist, input);
            }
        }
        scanner.close();
    }
}
