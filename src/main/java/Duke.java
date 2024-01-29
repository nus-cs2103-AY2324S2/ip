import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello it's a-me! Mario!\nWhat-a can I do fo' ya!");
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String command = scanner.next();
            System.out.println(command + "-o!");
            if (command.equals("bye")) {
                break;
            }
        }
    }
}
