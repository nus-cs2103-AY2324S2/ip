import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Lai {
    public static void main(String[] args) {
        List<String> commands = new ArrayList<String>();

        System.out.println("Hi there, I am Lai. Your friendly fairly useless chatbot.");
        System.out.println("What can I assist you with today?");
        System.out.println("---------------------------------------------------------");

        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        String command = scanner.nextLine();
        while (!Objects.equals(command, "bye")) {
            if (!Objects.equals(command, "list")) {
                commands.add(command);
                System.out.println("---------------------------------------------------------");
                System.out.println("Added: " + command);
                System.out.println("---------------------------------------------------------");

                System.out.print("> ");
                command = scanner.nextLine();
            } else {
                System.out.println("---------------------------------------------------------");
                for (int i = 0; i < commands.size(); i++) {
                    System.out.println(String.format("%s. %s", i + 1, commands.get(i)));
                }
                System.out.println("---------------------------------------------------------");

                System.out.print("> ");
                command = scanner.nextLine();
            }
        }

        System.out.println("---------------------------------------------------------");
        System.out.println("Goodbye, we shall meet again. Hopefully.");
        System.out.println("---------------------------------------------------------");
    }
}
