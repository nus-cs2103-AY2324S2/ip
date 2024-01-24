import java.util.ArrayList;
import java.util.Scanner;

public class Drake {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println(" What's up everyone. I'm Drake.");
        System.out.println(" How can I help?");
        System.out.println("____________________________________________________________");
        
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        while (true) {
            String input = scanner.nextLine().trim();
            try {
                if (input.equals("bye")) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" See you later, alligator! ");
                    System.out.println("____________________________________________________________");
                    break;
                }
                else if (input.equals("list")) {
                    System.out.println("____________________________________________________________");
                    System.out.println("You asked for the tasks in your list? Here:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                    System.out.println("____________________________________________________________");
                }
                else if (input.split(" ")[0].equals("mark")) {
                    Task markedTask = tasks.get(Integer.parseInt(input.split(" ")[1]) - 1);
                    markedTask.isDone = true;

                    System.out.println("____________________________________________________________");
                    System.out.println("Cool. I now declare this task marked as, done:");
                    System.out.println(markedTask);
                    System.out.println("____________________________________________________________");
                }

                else if (input.split(" ")[0].equals("unmark")) {
                    Task markedTask = tasks.get(Integer.parseInt(input.split(" ")[1]) - 1);
                    markedTask.isDone = false;

                    System.out.println("____________________________________________________________");
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(markedTask);
                    System.out.println("____________________________________________________________");
                }

                else if (input.split(" ")[0].equals("todo")) {
                    String todo = isSubstringValid(input, 5);
                    Todo newTodo = new Todo(todo);
                    tasks.add(newTodo);
                    
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTodo);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");                
                    System.out.println("____________________________________________________________");

                }

                else if (input.split(" ")[0].equals("deadline")) {
                    String[] parts = input.substring(9).split("/");
                    Deadline newDeadline = new Deadline(parts[0], parts[1].substring(3));
                    tasks.add(newDeadline);
                    
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newDeadline);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");                
                    System.out.println("____________________________________________________________");
                }

                else if (input.split(" ")[0].equals("event")) {
                    String[] parts = input.substring(6).split("/");
                    String title = parts[0];
                    String from = "";
                    String to = "";
                
                    for (int i = 1; i < parts.length; i++) {
                        if (parts[i].split(" ")[0].equals("from")) {
                            from = parts[i].substring(5);
                        }
                        if (parts[i].split(" ")[0].equals("to")) {
                            to = parts[i].substring(3);
                        }
                    }
                    
                    Event newEvent = new Event(title, from, to);
                    tasks.add(newEvent);
                    
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newEvent);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");                
                    System.out.println("____________________________________________________________");
                }

                else if (input.split(" ")[0].equals("delete")) {
                    Task toDelete = tasks.get(Integer.parseInt(input.split(" ")[1]) - 1);
                    tasks.remove(toDelete);
                    System.out.println("____________________________________________________________");
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(toDelete);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");                
                    System.out.println("____________________________________________________________");                    
                }

                else {
                    notValidCommand();
                }
            } catch (NotValidCommand e) {
                System.out.println("____________________________________________________________");
                System.out.println(e.getMessage());
                System.out.println("____________________________________________________________");
            } catch (TodoLeftBlank e) {
                System.out.println("____________________________________________________________");
                System.out.println(e.getMessage());
                System.out.println("____________________________________________________________");
            }
        }
        scanner.close();
    }

    public static String isSubstringValid(String input, int num) throws TodoLeftBlank {
        try {
            return input.substring(num);
        } catch(StringIndexOutOfBoundsException e) {
            throw new TodoLeftBlank("Looks like you left the description of the todo empty. This isn't allowed!");
        }
 
    }

    public static void notValidCommand() throws NotValidCommand {
        throw new NotValidCommand("That's not a valid command!");
    }

}
