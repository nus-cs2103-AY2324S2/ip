package service;

import type.CommandEnum;

import java.util.Scanner;

public class FeedbackService {
    private TaskService taskService = new TaskService();

    public void run() {
        this.PrintWelcome();
        this.listen();
        this.exit();
    }

    private void listen() {
        Scanner scanner = new Scanner(System.in);

        String[] curInput = scanner.nextLine().split(" ");

        // TODO: Clean up long if-else statement.. Use switch statement instead?
        while (!curInput[0].equals(CommandEnum.BYE.getCommandValue())) {
            // NOTE: LIST all tasks
            if (curInput[0].equals(CommandEnum.LIST.getCommandValue())) {
                String[] output = this.taskService.getAllTasks();

                for (String task : output) {
                    this.Echo(task);
                }
            }
            // NOTE: MARK task as completed
            else if (curInput[0].equals(CommandEnum.MARK.getCommandValue())) {
                this.Echo(this.taskService.markTaskCompleted(Integer.parseInt(curInput[1]) - 1));
            }
            // NOTE: UNMARK task as completed
            else if (curInput[0].equals(CommandEnum.UNMARK.getCommandValue())) {
                this.Echo(this.taskService.markTaskUncompleted(Integer.parseInt(curInput[1]) - 1));
            }
            // NOTE: Default - Add task
            else {
                this.Echo(this.taskService.addTask(curInput[0]));
            }

            // QUESTION: No longer required? Check if can be removed or if this functionality will still be graded
            curInput = scanner.nextLine().split(" ");
        }
    }

    private void exit() {
        System.out.println("----------------------------------------------");
        System.out.println("Goodbye! Hope to see you again!");
        System.out.println("----------------------------------------------");
    }

    private void PrintWelcome() {
        // Logo generated from : https://patorjk.com/software/taag/#p=display&f=Sub-Zero&t=OAK
        String logo =
                "______     ______     __  __    \n" +
                        "/\\  __ \\   /\\  __ \\   /\\ \\/ /    \n" +
                        "\\ \\ \\/\\ \\  \\ \\  __ \\  \\ \\  _-.    \n" +
                        " \\ \\_____\\  \\ \\_\\ \\_\\  \\ \\_\\ \\_\\  \n" +
                        "  \\/_____/   \\/_/\\/_/   \\/_/\\/_/ \n";
        System.out.println("Hello from\n" + logo);

        System.out.println("----------------------------------------------");

        System.out.println("Welcome! I'm Professor Oak");
        System.out.println("What can I do for you?");

    }

    // TODO: Allow for String[]?
     private void Echo(String input) {
        System.out.println("----------------------------------------------");
        System.out.println(input);
        System.out.println("----------------------------------------------");
     }
}
