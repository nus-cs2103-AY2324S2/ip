import java.util.Scanner;

public class Mamta {

    public static String greet() {
        return "Hello! I'm Mamta\nWhat can I do for you?";
    }

    public static String exit() {
        return "Bye. Hope to see you again soon!";
    }

    public static String echo(String command) {
        if (command.equals("bye")) {
            return String.format("------------------------------------------\n%s\n------------------------------------------", Mamta.exit());
        }
        return String.format("------------------------------------------\n%s\n------------------------------------------", command);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(Mamta.greet());

        String userOutput = "";
        while (!userOutput.equals("bye")) {
            Scanner scanner = new Scanner(System.in);
            userOutput = scanner.nextLine();
            System.out.println(Mamta.echo(userOutput));
        }





    }
}
