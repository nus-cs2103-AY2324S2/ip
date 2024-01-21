package messages;

import tasks.TaskList;
import java.util.Scanner;

public class Responses {
    private TaskList taskList = new TaskList();


    // String constants
    private static final String BANTER_LOGO = ".______        ___      .__   __. .___________. _______ .______      \n" +
            "|   _  \\      /   \\     |  \\ |  | |           ||   ____||   _  \\     \n" +
            "|  |_)  |    /  ^  \\    |   \\|  | `---|  |----`|  |__   |  |_)  |    \n" +
            "|   _  <    /  /_\\  \\   |  . `  |     |  |     |   __|  |      /     \n" +
            "|  |_)  |  /  _____  \\  |  |\\   |     |  |     |  |____ |  |\\  \\----.\n" +
            "|______/  /__/     \\__\\ |__| \\__|     |__|     |_______|| _| `._____|\n" +
            "                                                                     \n";

    private static final String GREET_MESSAGE_BODY = "Hello! I'm Banter\n" +
            "What can I do for you?";

    private static final String EXIT_MESSAGE_BODY = "Bye. Hope to see you again soon!";


    // Messages
    private static final MessageBox GREET_MESSAGE = new MessageBox(GREET_MESSAGE_BODY);

    private static final MessageBox EXIT_MESSAGE = new MessageBox(EXIT_MESSAGE_BODY);


    // Methods
    public void printGreetMessage() {
        System.out.println(BANTER_LOGO);
        GREET_MESSAGE.print();
    }

    private void printExitMessage() {
        EXIT_MESSAGE.print();
    }

    public void respondUntilExit() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            CommandTypes command = CommandTypes.getCommandType(input);
            switch (command) {
                case EXIT:
                    printExitMessage();
                    return;
                case LIST:
                    printTaskList();
                    break;
                case MARK:
                    taskMarkedAsDone(input);
                    break;
                case UNMARK:
                    taskMarkedAsUndone(input);
                    break;
                default:
                    addTask(input);
            }
        }
    }

    private void printTaskList() {
        MessageBox taskListMessage = new MessageBox(taskList.toString());
        taskListMessage.print();
    }

    private void addTask(String taskDescription) {
        taskList.addTask(taskDescription);
        MessageBox taskAddedMessage = new MessageBox("added: " + taskDescription);
        taskAddedMessage.print();
    }

    private void taskMarkedAsDone(String input) {
        int taskNumber = Integer.parseInt(Parser.getArgument(input));
        MessageBox taskDoneMessage = new MessageBox(taskList.markTaskAsDone(taskNumber));
        taskDoneMessage.print();
    }

    private void taskMarkedAsUndone(String input) {
        int taskNumber = Integer.parseInt(Parser.getArgument(input));
        MessageBox taskUndoneMessage = new MessageBox(taskList.markTaskAsUndone(taskNumber));
        taskUndoneMessage.print();
    }
}
