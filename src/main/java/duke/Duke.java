package duke;

import java.util.ArrayList;
import java.util.Scanner;
import duke.Command.Command;
import duke.Storage;
import duke.Task.Task;
import duke.TaskList;
import duke.Ui;

class Duke {
    private TaskList taskList;

    Duke() {
        ArrayList<Task> tasks = Storage.loadTasksFromFile();
        taskList = new TaskList(tasks);
    }

    void run() {
        Ui.printLogo();
        Ui.printWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        String command = Ui.getUserCommand(scanner);

        while (!command.equals("bye")) {
            try {
                Command cmd = Parser.parseCommand(command);
                Ui.printMessage(cmd.execute(taskList, command));
            } catch (DukeException e) {
                Ui.printMessage("OOPS!!! " + e.getMessage());
            }
            command = Ui.getUserCommand(scanner);
        }

        Storage.saveTasksToFile(taskList.getTasks());
        Ui.printGoodbyeMessage();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}