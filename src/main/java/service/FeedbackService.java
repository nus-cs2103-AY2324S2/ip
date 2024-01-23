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

        String curInput = scanner.nextLine();

        while (!curInput.equals(CommandEnum.BYE.getCommandValue())) {
            if (curInput.equals(CommandEnum.LIST.getCommandValue())) {
                this.taskService.getAllTasks();
            } else {
                this.taskService.addTask(curInput);
            }

            // this.Echo(curInput);  // No longer required?
            curInput = scanner.nextLine();
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

    // No longer required?
//     private void Echo(String input) {
//        System.out.println("----------------------------------------------");
//        System.out.println(input);
//        System.out.println("----------------------------------------------");
//     }
}
