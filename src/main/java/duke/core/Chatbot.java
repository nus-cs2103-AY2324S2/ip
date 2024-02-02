package duke.core;

import duke.task.TaskList;
import duke.ui.Ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Chatbot {

    private final String name;
    private TaskList tasklist;

    public Chatbot(String name) {
        this.name = name;
        this.tasklist = new TaskList();
    }

    /**
     * Activates the chatbot to interact with different user commands
     *
     * @param in User input commands.
     * @param out Chatbot replies.
     */
    public void startChat(InputStream in, PrintStream out) {
        Scanner scanner = new Scanner(in);

        Ui.setOutputStream(out);
        Ui.greet(name);

        boolean isChatting = true;
        while (isChatting) {
            if (scanner.hasNextLine()) {  // Make sure there's another line to read
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
                } else if (input.contains("find")) {
                    String[] parts = input.split("\\s+", 2);
                    Ui.find(tasklist, parts[1]);
                } else {
                    Ui.addTask(tasklist, input);
                }
            }
        }
        scanner.close();
    }
}
