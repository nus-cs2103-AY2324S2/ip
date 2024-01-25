import java.util.*; 

public class Duke {
    private static ArrayList<Task> inventory;
    private static int num;

    public static void main(String[] args) {
        inventory = new ArrayList<>();
        num = 0;
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
                System.out.println(layer("Bye. Hope to see you again soon!"));
                break; 
            }
            else if (input.equalsIgnoreCase("list")) {
                String result = "";
                int count = 1;
                for (Task s : inventory) {
                    result += count + ". " + s.toString() + "\n";
                    count++;
                }
                System.out.println(layer(result));
                //if marked
            } 
            else if (input.substring(0,4).equalsIgnoreCase("mark")) {
                int index = Integer.parseInt(input.substring(5));
                inventory.get(index - 1).mark();
                String temp = "Nice! I've marked this task as done: \n";
                temp += inventory.get(index - 1).toString();
                System.out.println(layer(temp));
                //if unmarked
            } 
            else if (input.substring(0, 6).equalsIgnoreCase("unmark")) {
                int index = Integer.parseInt(input.substring(7));
                inventory.get(index - 1).unmark();
                String temp = "OK, I've marked this task as not done yet: \n";
                temp += inventory.get(index - 1).toString();
                System.out.println(layer(temp));
            } 
            else if (input.substring(0, 4).equalsIgnoreCase("todo")) {
                String task = input.substring(5);
                num++;
                inventory.add(new ToDo(task, num));
                String temp = "Got it. I've added this task: \n";
                temp += " " + inventory.get(inventory.size() - 1).toString();
                temp += "\nNow you have " + num + " tasks in the list.";
                System.out.println(layer(temp));
            }
            else if (input.substring(0, 8).equalsIgnoreCase("deadline")) {
                String[] parts = input.substring(9).split("/");
                num++;
                inventory.add(new Deadlines(parts[0], parts[1], num));
                String temp = "Got it. I've added this task: \n";
                temp += " " + inventory.get(inventory.size() - 1).toString();
                temp += "\nNow you have " + num + " tasks in the list.";
                System.out.println(layer(temp));
            }
            else if (input.substring(0, 5).equalsIgnoreCase("event")) {
                String[] parts = input.substring(6).split("/");
                num++;
                inventory.add(new Events(parts[0], parts[1], parts[2], num));
                String temp = "Got it. I've added this task: \n";
                temp += " " + inventory.get(inventory.size() - 1).toString();
                temp += "\nNow you have " + num + " tasks in the list.";
                System.out.println(layer(temp));
            }
        }

        scanner.close(); 
    }

    public static String layer(String s) {
        String line = "____________________________________________________________";
        return line + "\n" + s + "\n" + line; 
    }
}
