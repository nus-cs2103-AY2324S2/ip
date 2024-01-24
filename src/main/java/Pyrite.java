import java.util.Scanner;

public class Pyrite {
    String name = "Pyrite";
    String horizontal_line = "\t____________________________________________________________";
    String greeting = "\tHello! I'm " + name + "\n"
            + "\tWhat can I do for you?";
    String farewell = "\tBye. Hope to see you again soon!";
    Task[] list = new Task[100];
    int list_count = 0;
    private void printList(Task[] list, int list_count) {
        System.out.println("\t" + "Here are the tasks in your list:");
        for (int i = 0; i < list_count; i++) {
            System.out.println("\t" + Integer.toString(i + 1) + ". " + list[i].getFullStatusString());
        }
    }
    public void begin() {
        System.out.println(this.horizontal_line);
        System.out.println(this.greeting);
        System.out.println(this.horizontal_line);
        Scanner scanner = new Scanner(System.in);
        String input;
        // Solution below inspired by https://stackoverflow.com/questions/31690570/java-scanner-command-system
        while (true) {
            System.out.println();
            input = scanner.nextLine();
            System.out.println(this.horizontal_line);
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                printList(this.list, this.list_count);
            } else {
                // Commands with parameters
                String parameters[] = input.split(" ");
                if (parameters[0].equals("mark")) {
                    int id = Integer.parseInt(parameters[1]) - 1;
                    list[id].setDone(true);
                    System.out.println("\t"
                            + "Nice! I've marked this task as done:\n"
                            + "\t"
                            + list[id].getFullStatusString());
                } else if (parameters[0].equals("unmark")) {
                    int id = Integer.parseInt(parameters[1]) - 1;
                    list[id].setDone(false);
                    System.out.println("\t"
                            + "OK, I've marked this task as not done yet:\n"
                            + "\t"
                            + list[id].getFullStatusString());
                } else {
                    // Normal list item, add to list
                    this.list[this.list_count] = new Task(input);
                    this.list_count++;
                    System.out.println("\t" + "added: " + input);
                }
            }
            System.out.println(this.horizontal_line);
        }

        System.out.println(this.horizontal_line);
        System.out.println(this.farewell);
        System.out.println(this.horizontal_line);
    }
}