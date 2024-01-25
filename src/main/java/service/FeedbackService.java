package service;

import model.Feedback;
import type.CommandEnum;

import java.util.Arrays;
import java.util.stream.Collectors;

public class FeedbackService {
    private TaskService taskService = new TaskService();

    public Feedback run(String userInput) {
        String[] cur = userInput.split(" ");
        CommandEnum curCommand = CommandEnum.getCommandEnum(cur[0]);
        Feedback feedback = null;

        int taskId = -1;

        // TODO: Exception Handling for incorrect input
        switch (curCommand) {
            case BYE:
                feedback = new Feedback(true, this.getExitMessage());
                break;
            case LIST:
                feedback = new Feedback(false, this.taskService.getAllTasks());
                break;
            case MARK:
                taskId = Integer.parseInt(cur[1]) - 1;
                feedback = new Feedback(false, this.taskService.markTaskCompleted(taskId));
                break;
            case UNMARK:
                taskId = Integer.parseInt(cur[1]) - 1;
                feedback = new Feedback(false, this.taskService.markTaskUncompleted(taskId));
                break;
            case TODO:
                String taskName = this.parseTodoInput(cur);
                feedback = new Feedback(false, this.taskService.addTodo(taskName));
                break;
            case DEADLINE:
                String[] deadLineValues = this.parseDeadlineInput(cur);
                feedback = new Feedback(false, this.taskService.addDeadline(deadLineValues[0], deadLineValues[1]));
                break;
            case EVENT:
                String[] eventValues = this.parseEventInput(cur);
                feedback = new Feedback(false, this.taskService.addEvent(eventValues[0], eventValues[1], eventValues[2]));
                break;
            default:
                // TODO: Throw exception
                break;
        }

        return feedback;
    }

    public String getWelcomeMessage() {
        // Logo generated from : https://patorjk.com/software/taag/#p=display&f=Sub-Zero&t=OAK
        String logo =
                " ______     ______     __  __    \n" +
                        "/\\  __ \\   /\\  __ \\   /\\ \\/ /    \n" +
                        "\\ \\ \\/\\ \\  \\ \\  __ \\  \\ \\  _-.    \n" +
                        " \\ \\_____\\  \\ \\_\\ \\_\\  \\ \\_\\ \\_\\  \n" +
                        "  \\/_____/   \\/_/\\/_/   \\/_/\\/_/ \n";

        return "Hello from\n" + logo + "\n" +
                "----------------------------------------------\n" +
                "Welcome! I'm Professor Oak\n" +
                "What can I do for you?";

    }

    private String getExitMessage() {
        return "Goodbye! Hope to see you again!";

    }

    private String parseTodoInput(String[] input) {
        // Sample Todo Input: todo borrow book

        // @@author SherisseTJW-reused
        // Reused from https://stackoverflow.com/a/34440330
        // with minor modifications
        return Arrays.stream(input).skip(1).map(String::trim).collect(Collectors.joining(" "));
    }

    private String[] parseDeadlineInput(String[] input) {
        // Sample Deadline Input: deadline return book /by Sunday

        // @@author SherisseTJW-reused
        // Reused from https://stackoverflow.com/a/34440330
        String fullInput = Arrays.stream(input).skip(1).collect(Collectors.joining(" "));

        String[] temp = fullInput.split("/by");

        return new String[] { temp[0].strip(), temp[1].strip() };
    }

    private String[] parseEventInput(String[] input) {
        // Sample Event Input: event project meeting /from Mon 2pm /to 4pm

        // @@author SherisseTJW-reused
        // Reused from https://stackoverflow.com/a/34440330
        String fullInput = Arrays.stream(input).skip(1).collect(Collectors.joining(" "));

        String[] temp = fullInput.split("/from");
        String[] datetimes = temp[1].split("/to");

        return new String[] { temp[0].strip(), datetimes[0].strip(), datetimes[1].strip() };
    }

    // NOTE: REMOVE THIS if not going to be graded since add tasks exists
//    private void Echo(String input) {
//        System.out.println("----------------------------------------------");
//        System.out.println(input);
//        System.out.println("----------------------------------------------");
//    }
}