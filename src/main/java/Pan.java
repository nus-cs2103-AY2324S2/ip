import java.util.Scanner;

public class Pan {
    public static void main(String[] args) {
        hello();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("");
            String instruction = scanner.nextLine();
            System.out.println("");
            if (!instruction.equals("bye")) {
                echo(instruction);
                continue;
            } else {
                bye();
                break;
            }
        }
        scanner.close();
    }

    public static void hello() {
        System.out.println("Hello! I'm Pan\nWhat can I do for you?\n");
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public static void echo(String instruction) {
        System.out.println(instruction + "\n");
    }
}
