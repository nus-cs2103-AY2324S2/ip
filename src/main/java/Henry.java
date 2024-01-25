import java.util.Scanner;
public class Henry {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] items = new Task[100];
        int numOfItems = 0;

        String logo = "  _    _                       \n" +
                " | |  | |                      \n" +
                " | |__| | ___ _ __  _ __ _   _ \n" +
                " |  __  |/ _ \\ '_ \\| '__| | | |\n" +
                " | |  | |  __/ | | | |  | |_| |\n" +
                " |_|  |_|\\___|_| |_|_|   \\__, |\n" +
                "                          __/ |\n" +
                "                         |___/ \n";
        String greetMessage = "Hello! I'm Henry\nWhat can I do for you?";
        String exitMessage = "See you again bro!";

        System.out.println(logo);
        System.out.println(greetMessage);
        System.out.println();

        while (true) {
            String currentMessage = scanner.nextLine();
            if (currentMessage.equals("bye")) {
                System.out.println(exitMessage);
                break;
            } else if (currentMessage.equals("list")) {
                for (int i = 0; i < numOfItems; i = i + 1) {
                    System.out.printf("%d. %s\n", i + 1, items[i]);
                }
                System.out.println();
            } else if (currentMessage.startsWith("mark")) {
                int index = Integer.parseInt(currentMessage.split(" ")[1]) - 1;
                items[index].markAsDone();
                System.out.println("This task is marked as done XD");
                System.out.println(items[index]);
                System.out.println();
            } else if (currentMessage.startsWith("unmark")) {
                int index = Integer.parseInt(currentMessage.split(" ")[1]) - 1;
                items[index].unmarkAsDone();
                System.out.println("This task is marked as undone :(");
                System.out.println(items[index]);
                System.out.println();
            } else {
                System.out.println("added: " + currentMessage);
                System.out.println();
                items[numOfItems] = new Task(currentMessage);
                numOfItems = numOfItems + 1;
            }
        }
    }
}