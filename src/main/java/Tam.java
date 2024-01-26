import java.util.Scanner;
public class Tam {
    static String dividerText = "____________________________________________________________\n";
    static Scanner scannerObj = new Scanner(System.in);
    public static void main(String[] args) {
        Tam.greet();
        int status = readCommand();
        while (status == 0) {
            status = readCommand();
        }
        Tam.exit();
    }

    public static void greet() {
        String greetText = "Hello! I'm Tam the Task Manager!\nWhat can I do for you?\n";
        System.out.print(dividerText);
        System.out.print(greetText);
        System.out.print(dividerText);
    }

    public static void exit() {
        String exitText = "Bye. Hope to see you again soon!\n";
        System.out.print(exitText);
        System.out.print(dividerText);
    }

    public static int readCommand() {
        String input = scannerObj.nextLine();
        if (input.toLowerCase().equals("bye")) {
            System.out.print(dividerText);
            return 1;
        }
        else {
            System.out.print(dividerText);
            System.out.println(input);
            System.out.print(dividerText);
            return 0;
        }
    }
}
