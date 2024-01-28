import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

public class Drake {
    private static final String FILE_PATH = "./list.dat";

    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println(" What's up everyone. I'm Drake.");
        System.out.println(" How can I help?");
        System.out.println("____________________________________________________________");
        
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = loadList();
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
                        saveList(tasks);
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

    private static void saveList(ArrayList<Task> tasks) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            System.out.println("Error saving list!: " + e.getMessage());
        }
    }

    private static ArrayList<Task> loadList() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                return (ArrayList<Task>) ois.readObject();
            } catch (IOException e) {
                return new ArrayList<>();
            } catch (ClassNotFoundException e) {
                System.out.println("Class not found!: " + e.getMessage());
            }
        }
        return new ArrayList<>();
    }

    

}