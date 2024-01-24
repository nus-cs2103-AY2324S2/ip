import java.util.Scanner;
public class Dibo {
    private static final String name = "Dibo";
    public static void main(String[] args) {
        // Greeting the user
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you? \n");
        Store store = new Store(new String[]{"todo", "deadline", "event"});

        // Getting the command
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        // Responding
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                store.displayStore();
            } else if (command.contains("unmark")) {
                try {
                    int id = Integer.parseInt(command.split(" ")[1]);
                    store.unmarkTask(id);
                } catch (NumberFormatException e) {
                    System.out.println("Oh no! You have to unmark the items based on their index," +
                            "if you are not sure of the index, enter 'list' to check it out:)");
                }
            } else if (command.contains("mark")) {
                try {
                    int id = Integer.parseInt(command.split(" ")[1]);
                    store.markTask(id);
                } catch (NumberFormatException e) {
                    System.out.println("Oh no! You have to mark the items based on their index," +
                            "if you are not sure of the index, enter 'list' to check it out:)");
                }
            } else if (command.contains("delete")) {
                try {
                    int id = Integer.parseInt(command.split(" ")[1]);
                    store.deleteTask(id);
                } catch (NumberFormatException e) {
                    System.out.println("Oh no! You have to delete the items based on their index," +
                            "if you are not sure of the index, enter 'list' to check it out:)");
                }
            } else {
                try {
                    System.out.println(store.addText(command));
                } catch (DukeException d) {
                    System.out.println(d.getMessage());
                }
            }
            command = sc.nextLine();

        }

        // Exiting
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }
}
