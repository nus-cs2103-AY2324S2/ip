import java.util.ArrayList;
import java.util.Scanner;

enum Command {
    BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, INVALID;
    
    // Method to get the appropriate enum value from a string input
    public static Command fromString(String commandString) {
        switch (commandString.toLowerCase()) {
            case "bye":
                return BYE;
            case "list":
                return LIST;
            case "mark":
                return MARK;
            case "unmark":
                return UNMARK;
            case "todo":
                return TODO;
            case "deadline":
                return DEADLINE;
            case "event":
                return EVENT;
            case "delete":
                return DELETE;
            default:
                return INVALID;
        }
    }
}

// ... (rest of your classes)

public class Drake {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println(" What's up everyone. I'm Drake.");
        System.out.println(" How can I help?");
        System.out.println("____________________________________________________________");
        
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        Boolean running = true;
        
        while (running) {
            String input = scanner.nextLine().trim();
            String[] words = input.split(" ", 2);
            String commandWord = words[0];
            Command command = Command.fromString(commandWord);

            try {
                switch (command) {
                    case BYE:
                        System.out.println("____________________________________________________________");
                        System.out.println(" See you later, alligator! ");
                        System.out.println("____________________________________________________________");
                        running = false;
                        break;
                    case LIST:
                        System.out.println("____________________________________________________________");
                        System.out.println("You asked for the tasks in your list? Here:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + ". " + tasks.get(i));
                        }
                        System.out.println("____________________________________________________________");
                        break;
                    case MARK:
                        Task markedTask = tasks.get(Integer.parseInt(input.split(" ")[1]) - 1);
                        markedTask.isDone = true;

                        System.out.println("____________________________________________________________");
                        System.out.println("Cool. I now declare this task marked as, done:");
                        System.out.println(markedTask);
                        System.out.println("____________________________________________________________");
                        break;
                    case UNMARK:
                        Task unmarkedTask = tasks.get(Integer.parseInt(input.split(" ")[1]) - 1);
                        unmarkedTask.isDone = false;

                        System.out.println("____________________________________________________________");
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(unmarkedTask);
                        System.out.println("____________________________________________________________");
                        break;
                    case TODO:
                        String todo = isSubstringValid(input, 5);
                        Todo newTodo = new Todo(todo);
                        tasks.add(newTodo);
                        
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println(newTodo);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");                
                        System.out.println("____________________________________________________________");
                            break;
                    case DEADLINE:
                        String[] parts = input.substring(9).split("/");
                        Deadline newDeadline = new Deadline(parts[0], parts[1].substring(3));
                        tasks.add(newDeadline);
                        
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println(newDeadline);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");                
                        System.out.println("____________________________________________________________");
                        break;
                    case EVENT:
                        parts = input.substring(6).split("/");
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
                        break;
                    case DELETE:
                        Task toDelete = tasks.get(Integer.parseInt(input.split(" ")[1]) - 1);
                        tasks.remove(toDelete);
                        System.out.println("____________________________________________________________");
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(toDelete);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");                
                        System.out.println("____________________________________________________________");   
                        break;
                    case INVALID:
                        notValidCommand();
                        break;
                    // ... handle other cases
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