import java.util.*;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        intro();
        echo(sc);
        exit();
    }
    private static void intro() {
        String logo = "        _  _        \n  __ _ | || | _   _ \n / _` || || || | | |\n| (_| || || || |_| |\n \\__,_||_||_| \\__, |\n              |___/ \n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Ally \n" + "What can I do for you?");
    }
    private static void echo(Scanner sc) {
        String task = sc.nextLine();
        while (!task.equals("bye")) {
            System.out.println(task);
            task = sc.nextLine();
        }
    }
    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

}
