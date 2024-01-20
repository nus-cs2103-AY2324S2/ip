import java.util.ArrayList;
import java.util.Scanner;

public class Johnny {

    private static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Johnny here. What do you want?\n");
        Johnny.takeCommands();
        System.out.println("Bye. I'm going back to sleep.");
    }

    public static void takeCommands() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String command = scanner.nextLine();

            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                for (int i = 0; i < Johnny.list.size(); i++) {
                    System.out.println(i + 1 + ". " + list.get(i));
                }
                System.out.println();
            } else {
                Johnny.list.add(command);
                System.out.println("added: " + command + "\n");
            }
        }

        scanner.close();
    }

}
