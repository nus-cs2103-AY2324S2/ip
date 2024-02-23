import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
public class Goblin {
    static String greetings = "Hello！ I'm NetGoblin\n"
            + "What can I do for you?";
    static String bye = "Bye. Hope to see you again soon!\n";

    static ArrayList<Task> list = new ArrayList<>();

    public static void main(String[] args) {
        try {
            sayHello();
            readFile();
        }
        catch (OrkException exception) {}
        Scanner input = new Scanner(System.in);
        while (input.hasNext()){
            try {
                String inputWord = input.next();
                if (inputWord.equals("mark")) {
                    mark(input);
                } else if (inputWord.equals("unmark")) {
                    unmark(input);
                } else if (inputWord.equals("list")) {
                    showList();
                } else if (inputWord.equals("todo")) {
                    String inputLine = input.nextLine();
                    if (inputLine.isEmpty()) {
                        throw new OrkException("To do what? You dumb meat!");
                    }
                    line();
                    System.out.print("\t" + "Got it. I've added this task:\n");
                    addTodo(inputLine);
                    count();
                } else if (inputWord.equals("deadline")) {
                    String inputLine = input.nextLine();
                    if (inputLine.isEmpty()) {
                        throw new OrkException("The deadline for what?! You dumb meat!");
                    }
                    line();
                    System.out.print("\t" + "Got it. I've added this task:\n");
                    addDeadline(inputLine);
                    count();
                } else if (inputWord.equals("event")) {
                    String inputLine = input.nextLine();
                    if (inputLine.isEmpty()) {
                        throw new OrkException("You need to tell me what the event is! You dumb meat!");
                    }
                    line();
                    System.out.print("\t" + "Got it. I've added this task:\n");
                    addEvent(inputLine);
                    count();
                } else if (inputWord.equals("delete")) {
                    String inputLine = input.nextLine();
                    String[] deleteSplit = inputLine.split(" ");
                    String index= deleteSplit[1];
                    deleteList(index);
                } else if (inputWord.equals("bye")) {
                    sayBye();
                    input.close();
                    saveList();;
                    break;
                } else {
                    throw new OrkException("You think you are smart? You fresh meat!");
                }
                saveList();
            }
            catch (OrkException exception) {
                String message = exception.getMessage();
                line();
                System.out.println("\t " + message);
                line();
                input.nextLine();
            }
        }
    }
    public static void line() {
        System.out.println("--------------------------------");
    }

    public static void sayHello() {
        line();
        System.out.println(greetings);
        line();
    }

    public static void sayBye() {
        line();
        System.out.println(bye);
    }

    public static void showList() {
        line();
        System.out.println("\t" + "Read it yourself.");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + list.get(i).type()
                    + list.get(i).getStatusIcon() + list.get(i).getDescription());
        }
        line();
    }

    public static void mark(Scanner input) {
        int i = Integer.parseInt(input.next());
        list.get(i - 1).done();
        line();
        System.out.println("\t" + "Done.");
        list.get(i - 1).print();
        line();
    }

    public static void unmark(Scanner input) {
        int i = Integer.parseInt(input.next());
        list.get(i - 1).undone();
        line();
        System.out.println("\t" + "Lazy meat tastes good. CHOP CHOP");
        System.out.println("\t" + list.get(i - 1).getStatusIcon() + list.get(i - 1).getDescription());
        line();
    }

    public static void addTodo(String description) {
        ToDos todo = new ToDos(description);
        todo.print();
        list.add(todo);
    }

    public static void addDeadline(String command) {
        String[] split = command.split("/by") ;
        String description = split[0];
        String deadline = split[1];
        Deadlines deadlines = new Deadlines(description, deadline);
        deadlines.print();
        list.add(deadlines);
    }

    public static void addEvent(String command) {
        String[] split = command.split("/from") ;
        String description =split[0];
        String time = split[1];
        String[] timeSplit = time.split(" /to");
        String start = timeSplit[0];
        String end = timeSplit[1];
        Events events = new Events(description, start, end);
        events.print();
        list.add(events);
    }

    public static void count() {
        System.out.println("\t" + "Now you have " + list.size() + " tasks in the list.");
        line();
    }
    public static void deleteList(String index) {
        line();
        int indexInt = Integer.parseInt(index);
        System.out.println("\t" + "Delete task below, you happy now?");
        list.get(indexInt - 1).print();
        list.remove(indexInt - 1);
        System.out.println("\t" + "No meat, no goblins, hya hya hya");
        line();
    }

    public static void saveList() throws OrkException {
        try {
            FileWriter myWriter = new FileWriter("src/main/java/data.txt");
            for (int i = 0; i <list.size(); i++) {
                myWriter.write(list.get(i).toString() + "\n");
            }
            myWriter.close();
        } catch (IOException exception) {
            throw new OrkException("\u2539 OOPS!!! Something went wrong :" + exception.getMessage());
        }
    }

    public static void readFile() throws OrkException {
         try {
             File myObj = new File("src/main/java/data.txt");
             Scanner myReader = new Scanner(myObj);
             System.out.println("Current tasks: ");
             while (myReader.hasNextLine()) {
                 myReader.next();
                 String command = myReader.nextLine();
                 String[] removeASpace = command.split(" ", 2);
                 transformToTask(removeASpace[1]);
             }
             line();
             myReader.close();
         }
         catch (IOException e) {

         }

    }

    public static void transformToTask(String command) {
        String[] typeSplit = command.split(" ", 2);
        String type = typeSplit[0];
        if (type.equals("todo")) {
            String description = typeSplit[1];
            addTodo(description);
        } else if (type.equals("deadline")) {
            String description = typeSplit[1];
            addDeadline(description);
        } else if (type.equals("event")) {
            String description = typeSplit[1];
            addEvent(description);
        }
    }
}
