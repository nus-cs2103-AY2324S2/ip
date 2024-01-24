import java.util.Objects;
import java.util.Scanner;

public class Lai {
    public static void main(String[] args) {
        System.out.println("Hi there, I am Lai. Your friendly fairly useless chatbot.");
        System.out.println("What can I assist you with today?");
        System.out.println("---------------------------------------------------------");

        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        String command = scanner.nextLine();
        while (!Objects.equals(command, "bye")) {
            System.out.println("---------------------------------------------------------");
            System.out.println(command);
            System.out.println("---------------------------------------------------------");

            System.out.print("> ");
            command = scanner.nextLine();
        }

        System.out.println("---------------------------------------------------------");
        System.out.println("Goodbye, we shall meet again. Hopefully.");
        System.out.println("---------------------------------------------------------");
    }
}
