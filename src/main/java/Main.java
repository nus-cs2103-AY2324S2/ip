import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Zoe zoe = new Zoe();
        zoe.saysHi();
        zoe.readExisting();
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (!command.equals("bye")) {
            // instead of asking main to process commands that are not bye, use zoe to process them
            zoe.process(command);
            command = scanner.nextLine();
        }

        zoe.saveTasks();
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }
}
