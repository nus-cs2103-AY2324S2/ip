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
            try {
                String input = scanner.nextLine(); 
                if (input.equalsIgnoreCase("bye")) {
                    System.out.println(layer("Bye. Hope to see you again soon!"));
                    break; 
                }
                else if (input.equalsIgnoreCase("list")) {
                    if (!input.trim().equals("list")) {
                        throw new DukeException("OOPS!!! This is an invalid call of list command.");
                    } else {
                        String result = "";
                        int count = 1;
                        for (Task s : inventory) {
                            result += count + ". " + s.toString() + "\n";
                            count++;
                        }
                        System.out.println(layer(result));
                        //if marked
                    }
                } 
                else if (input.startsWith("mark")) {
                    if (input.trim().equals("mark")) {
                        throw new DukeException("OOPS!!! Invalid Command, highlight which task to mark");
                    } else {
                        int index = Integer.parseInt(input.substring(5));
                        inventory.get(index - 1).mark();
                        String temp = "Nice! I've marked this task as done: \n";
                        temp += inventory.get(index - 1).toString();
                        System.out.println(layer(temp));
                        //if unmarked
                    }
                } 
                else if (input.startsWith("unmark")) {
                    if (input.trim().equals("unmark")) {
                        throw new DukeException("OOPS!!! Invalid Command, highlight which task to unmark");
                    } else {
                        int index = Integer.parseInt(input.substring(7));
                        inventory.get(index - 1).unmark();
                        String temp = "OK, I've marked this task as not done yet: \n";
                        temp += inventory.get(index - 1).toString();
                        System.out.println(layer(temp));
                    }
                } 
                else if (input.startsWith("todo")) {
                    if (input.trim().equals("todo")) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    } else {
                        String task = input.substring(5);
                        num++;
                        inventory.add(new ToDo(task, num));
                        String temp = "Got it. I've added this task: \n";
                        temp += " " + inventory.get(inventory.size() - 1).toString();
                        temp += "\nNow you have " + num + " tasks in the list.";
                        System.out.println(layer(temp));
                    }
                }
                else if (input.startsWith("deadline")) {
                    if (input.trim().equals("deadline")) {
                        throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                    } 
                    else if (!input.contains("/")) {
                        throw new DukeException("OOPS!!! Your deadline command's format is invalid.");
                    } 
                    else if (input.substring(9).split("/").length < 2) {
                        throw new DukeException("OOPS!!! Your deadline command's format is invalid.");
                    }
                     else {
                        String[] parts = input.substring(9).split("/");
                        num++;
                        inventory.add(new Deadlines(parts[0], parts[1], num));
                        String temp = "Got it. I've added this task: \n";
                        temp += " " + inventory.get(inventory.size() - 1).toString();
                        temp += "\nNow you have " + num + " tasks in the list.";
                        System.out.println(layer(temp));
                    }
                }
                else if (input.startsWith("event")) {
                    if (input.trim().equals("event")) {
                        throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                    }
                    else if (!input.contains("/")) {
                        throw new DukeException("OOPS!!! Your event command's format is invalid.");
                    } else if (input.substring(6).split("/").length < 3) {
                        throw new DukeException("OOPS!!! Your event command's format is invalid.");
                    }  
                    else { 
                        String[] parts = input.substring(6).split("/");
                        num++;
                        inventory.add(new Events(parts[0], parts[1], parts[2], num));
                        String temp = "Got it. I've added this task: \n";
                        temp += " " + inventory.get(inventory.size() - 1).toString();
                        temp += "\nNow you have " + num + " tasks in the list.";
                        System.out.println(layer(temp));
                    }
                }
                else if (input.startsWith("delete")) {
                    if (input.trim().equals("delete")) {
                        throw new DukeException("OOPS!!! Invalid delete command");
                    } else {
                        int index = Integer.parseInt(input.substring(7));
                        String temp = "Noted. I've removed this task: \n";
                        Task t = inventory.get(index - 1);
                        inventory.remove(index-1);
                        temp += " " + t.toString();
                        num--;
                        temp += "\nNow you have " + num + " tasks in this list.";
                        System.out.println(layer(temp));
                    }

                }
                else {
                    System.out.println(layer("OOPS!!! I'm sorry, but that's an invalid command :-("));
                }
            } catch (DukeException e) {
                System.out.println(layer(e.getMessage()));
            }
        }

        scanner.close(); 
    }

    public static String layer(String s) {
        String line = "____________________________________________________________";
        return line + "\n" + s + "\n" + line; 
    }
}

