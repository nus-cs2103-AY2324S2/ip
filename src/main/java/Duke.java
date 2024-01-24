import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    private static ArrayList<String> taskList = new ArrayList<>();
    public static void main(String[] args) {
        greet();
        handleInput();
        exit();
    }

    public static void greet() {
        String logo = "   _____ __  ______  ____  ___    _   __\n"
                    + "  / ___// / / / __ \\/ __ \\/   |  / | / /\n"
                    + "  \\__ \\/ /_/ / / / / / / / /| | /  |/ / \n"
                    + " ___/ / __  / /_/ / /_/ / ___ |/ /|  /  \n"
                    + "/____/_/ /_/\\____/_____/_/  |_/_/ |_/   \n";
        System.out.println(logo);
        System.out.println("Greetings, human.");
        System.out.println("What can I do for you?");
    }
    public static void handleInput() {
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.print("> ");
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                break;
            } else if (input.equalsIgnoreCase("list")) {
                for (int i = 1; i < taskList.size(); i++) {
                    System.out.print(i + ". ");
                    System.out.println(taskList.get(i-1));
                }
            } else {
                taskList.add(input);
                System.out.println("added: " + input);
            }
        }
        sc.close();
    }
    public static void exit() {
        System.out.println("Goodbye.");
    }
}
