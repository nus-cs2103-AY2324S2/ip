import java.util.ArrayList;
import java.util.List;

class Item {
    private static int count = 0;
    private final int id;
    private String title;
    private boolean isDone;

    public Item(String title) {
        this.id = ++count;
        this.title = title;
        this.isDone = false;
    }

    public int getId() {
        return this.id;
    }

    public void markDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return (isDone ? "[\u2713] " : "[\u2718] ") + title;
    }
}

public class Duke {
    public static void main(String[] args) {
        String logo = "\n /$$      /$$  /$$$$$$  /$$$$$$$  /$$$$$$$$ /$$     /$$\n"
                + "| $$$    /$$$ /$$__  $$| $$__  $$|__  $$__/|  $$   /$$/\n"
                + "| $$$$  /$$$$| $$  \\ $$| $$  \\ $$   | $$    \\  $$ /$$/ \n"
                + "| $$ $$/$$ $$| $$  | $$| $$$$$$$/   | $$     \\  $$$$/  \n"
                + "| $$  $$$| $$| $$  | $$| $$__  $$   | $$      \\  $$/   \n"
                + "| $$\\  $ | $$| $$  | $$| $$  \\ $$   | $$       | $$    \n"
                + "| $$ \\/  | $$|  $$$$$$/| $$  | $$   | $$       | $$    \n"
                + "|__/     |__/ \\______/ |__/  |__/   |__/       |__/    \n";
        System.out.println("\nHello I'm\n" + logo);
        System.out.println("What can I do for you?\n");

        List<Item> list = new ArrayList<Item>();
        while (true) {
            String command = System.console().readLine();
            String[] tokens = command.split(" ");
            System.out.println("\n============================================================\n");
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + ". " + list.get(i).toString());
                }
            } else if (tokens[0].equals("done")) {
                int i = Integer.parseInt(tokens[1]) - 1;
                list.get(i).markDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(list.get(i).toString());
            } else {
                Item newItem = new Item(command);
                list.add(newItem);
                System.out.println("added: " + newItem.toString());
            }
            System.out.println("\n============================================================\n");
        }
    }
}
