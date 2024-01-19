import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello, I'm Baron. What can I do for you?");
        Scanner scanner = new Scanner(System.in);

        String input;
        do {
            input = scanner.nextLine();
            System.out.println(input);
            System.out.println("---");
        } while (!input.equals("bye"));
        
        scanner.close();
        System.out.println("Bye, good riddance");
    }
}
