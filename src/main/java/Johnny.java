import java.util.Scanner;

public class Johnny {

    public static void main(String[] args) {
        System.out.println("Johnny here. What do you want?\n");
        Johnny.takeCommands();
        System.out.println("Bye. I'm going back to sleep.");
    }

    public static void takeCommands() {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            String command = scanner.nextLine();

            if(command.equals("bye")) {
                break;
            }

            System.out.println(command + "\n");
        }

        scanner.close();
    }

}
