package duke;

import java.util.Scanner;

import duke.commands.Command;

/**
 * Duke is a chatbot that helps you keep track of your tasks.
 * It can store your tasks, mark them as done, and delete them.
 * It also supports different types of tasks, such as ToDos, Deadlines, and Events.
 * Duke stores your tasks in a file, so you can continue where you left off the next time you run it.
 * Duke has the characteristics of a typical Chinese Singaporean.
 */
public class Duke {
    static final String NAME = "jun jie";
    static final String INTRO_MSG = "hi bro, im " + NAME + "\nwhat you want me to do?";

    /**
     * Constructs a Duke chatbot.
     */
    public Duke() {
        Scanner scanner = new Scanner(System.in);

        Storage.init();
        String fileContents = Storage.read();
        TaskList taskList = new TaskList(fileContents);
        Ui ui = new Ui();

        ui.print(INTRO_MSG);
        while (true) {
            String input = scanner.nextLine();
            Command command = Parser.handleInput(input, ui, taskList);
            command.execute(taskList, ui);

            if (command.isExit()) {
                break;
            }
        }
    }

    /**
     * Entry point of the application.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        new Duke();
    }
}
