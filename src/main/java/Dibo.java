import java.util.Scanner;
public class Dibo {
    private static String name = "Dibo";
    public static void main(String[] args) {
        // Greeting the user
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you? \n");
        Store store = new Store();

        // Getting the command
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        // Responding
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                store.displayStore();
            } else if (command.contains("unmark")) {
                int id = Integer.parseInt(command.split(" ")[1]);
                store.unmarkTask(id);
            } else if (command.contains("mark")) {
                int id = Integer.parseInt(command.split(" ")[1]);
                store.markTask(id);
            } else {
                System.out.println(store.addText(command));
            }
            command = sc.nextLine();

        }

        // Exiting
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }
}
