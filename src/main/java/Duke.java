import java.util.*; 

public class Duke {
    private static ArrayList<Task> inventory;

    public static void main(String[] args) {
        inventory = new ArrayList<>();
        String logo = " _____   _____  _    _ \n"
                    + "|  __ \\ / ____|| |  | |\n"
                    + "| |__) | (___  | |__| |\n"
                    + "|  _  / \\___ \\ |  __  |\n"
                    + "| | \\ \\ ____) || |  | |\n"
                    + "|_|  \\_|_____/ |_|  |_|\n";

        Scanner scanner = new Scanner(System.in); 

        System.out.println(layer("Hello! I'm \n" + logo + "What can I do for you?"));

        while (true) {
            String input = scanner.nextLine(); 

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break; 
            }
            else if (input.equalsIgnoreCase("list")) {
                String result = "";
                int count = 1;
                for (Task s : inventory) {
                    String box = "";
                    if (s.isMarked()) {
                        box = "[X]";
                    } else {
                        box = "[ ]";
                    }
                    result += count + ". " + box + " " + s.toString() + "\n";
                    count++;
                }
                System.out.println(layer(result));
                //if marked
            } 
            else if (input.substring(0,4).equalsIgnoreCase("mark")) {
                int index = Integer.parseInt(input.substring(5));
                inventory.get(index - 1).mark();
                String temp = "Nice! I've marked this task as done: \n";
                temp += "[X] " + inventory.get(index - 1);
                System.out.println(layer(temp));
                //if unmarked
            } 
            else if (input.substring(0, 6).equalsIgnoreCase("unmark")) {
                int index = Integer.parseInt(input.substring(7));
                inventory.get(index - 1).unmark();
                String temp = "OK, I've marked this task as not done yet: \n";
                temp += "[ ] " + inventory.get(index - 1);
                System.out.println(layer(temp));
            } 
            else {
                System.out.println(layer("added: " + input));
                inventory.add(new Task(input));
            }
        }

        scanner.close(); 
    }

    public static String layer(String s) {
        String line = "____________________________________________________________";
        return line + "\n" + s + "\n" + line; 
    }
}

