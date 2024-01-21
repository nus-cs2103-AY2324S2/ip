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
            CommandType command = CommandType.valueOf(Parser.getCommandType(input));
            switch (command) {
                case BYE:
                    printExitMessage();
                    return;
                case LIST:
                    printTaskListMessage();
                    break;
                case MARK:
                    markTaskAsDoneAndPrintMessage(input);
                    break;
                case UNMARK:
                    markTaskAsUndoneAndPrintMessage(input);
                    break;
                case TODO:
                    addTodoAndPrintMessage(input);
                    break;
                case DEADLINE:
                    addDeadlineAndPrintMessage(input);
                    break;
                case EVENT:
                    addEventAndPrintMessage(input);
                    break;
                default:
                    // TODO: handle invalid command
            }
        }
    }

    private void printTaskListMessage() {
        MessageBox taskListMessage = new MessageBox(taskList.toString());
        taskListMessage.print();
    }

    private void addTodoAndPrintMessage(String input) {
        MessageBox taskAddedMessage = new MessageBox(
                taskList.addTodo(
                        Parser.getTodoDescription(input)));
        taskAddedMessage.print();
    }

    private void addDeadlineAndPrintMessage(String input) {
        MessageBox taskAddedMessage = new MessageBox(
                taskList.addDeadline(
                        Parser.getDeadlineDescription(input),
                        Parser.getDeadlineDueDate(input)));
        taskAddedMessage.print();
    }

    private void addEventAndPrintMessage(String input) {
        MessageBox taskAddedMessage = new MessageBox(
                taskList.addEvent(
                        Parser.getEventDescription(input),
                        Parser.getEventStart(input),
                        Parser.getEventEnd(input)));
        taskAddedMessage.print();
    }

    private void markTaskAsDoneAndPrintMessage(String input) {
        MessageBox taskDoneMessage = new MessageBox(
                taskList.markTaskAsDone(
                        Parser.getTaskNumber(input)));
        taskDoneMessage.print();
    }

    private void markTaskAsUndoneAndPrintMessage(String input) {
        MessageBox taskUndoneMessage = new MessageBox(
                taskList.markTaskAsUndone(
                        Parser.getTaskNumber(input)));
        taskUndoneMessage.print();
    }
}
