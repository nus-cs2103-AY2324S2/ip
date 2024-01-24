package service;

import model.Feedback;
import type.CommandEnum;

public class FeedbackService {
    private TaskService taskService = new TaskService();

    public Feedback run(String userInput) {
        String[] cur = userInput.split(" ");
        CommandEnum curCommand = CommandEnum.getCommandEnum(cur[0]);
        Feedback feedback = null;
        int taskId = -1;

        // TODO: Exception Handling for incorrect input
        switch (curCommand) {
            case CommandEnum.BYE:
                feedback = new Feedback(true, this.getExitMessage());
                break;
            case CommandEnum.LIST:
                feedback = new Feedback(false, this.taskService.getAllTasks());
                break;
            case CommandEnum.MARK:
                taskId = Integer.parseInt(cur[1]) - 1;
                feedback = new Feedback(false, this.taskService.markTaskCompleted(taskId));
                break;
            case CommandEnum.UNMARK:
                taskId = Integer.parseInt(cur[1]) - 1;
                feedback = new Feedback(false, this.taskService.markTaskUncompleted(taskId));
                break;
            default:
                feedback = new Feedback(false, this.taskService.addTask(userInput));
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
        return "----------------------------------------------\n" +
                "Goodbye! Hope to see you again!\n" +
                "----------------------------------------------";
    }

    // TODO: REMOVE THIS if not going to be graded since add tasks exists
//    private void Echo(String input) {
//        System.out.println("----------------------------------------------");
//        System.out.println(input);
//        System.out.println("----------------------------------------------");
//    }
}