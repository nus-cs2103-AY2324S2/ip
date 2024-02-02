package baron;

import baron.Managers.TaskManager;

import java.util.Scanner;

public class Baron {
    public static void main(String[] args) {
        System.out.println("Hello, I'm Baron. What can I do for you?");
        Scanner scanner = new Scanner(System.in);

        String input;
        TaskManager taskManager = new TaskManager();
        do {
            input = scanner.nextLine();
            taskManager.handleInput(input);
        } while (!input.equals("bye"));

        scanner.close();
        System.out.println("Bye, good riddance");
    }

}
