import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String sep = "\t__________________________________________";
        System.out.println(sep);
        System.out.println("\tHello! I'm JOSEPH JOSHTUR!!!");
        System.out.println("\tWhat can I do for you?");
        System.out.println(sep);
        String command = scanner.nextLine();
        while (!command.equals("bye")) {
            System.out.println(sep);
            System.out.println("\t" + command);
            System.out.println(sep);
            command = scanner.nextLine();
        }
        System.out.println(sep);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(sep);
    }
}
