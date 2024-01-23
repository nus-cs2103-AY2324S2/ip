import java.util.ArrayList;
import java.util.List;

class Item {
    Integer id;
    String title;

    public Item(String title) {
        this.title = title;
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
            if (command.equals("bye")) {
                System.out.println("\n============================================================");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("\n============================================================\n");
                break;
            } else if (command.equals("list")) {
                System.out.println("\n============================================================\n");
                for (Item s : list) {
                    System.out.println(s.title);
                }
                System.out.println("\n============================================================\n");
            } else {
                System.out.println("\n============================================================\n");
                Item newItem = new Item(command);
                list.add(newItem);
                System.out.println("added: " + newItem.title);
                System.out.println("\n============================================================\n");
            }
        }
    }
}
