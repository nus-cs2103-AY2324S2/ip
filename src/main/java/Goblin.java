import java.util.Scanner;
import java.util.ArrayList;
public class Goblin {
    static String greetings = "Hello！ I'm NetGoblin\n"
            + "What can I do for you?";
    static String bye = "Bye. Hope to see you agian soon!\n";

    static ArrayList<Task> list = new ArrayList<>();

    public static void main(String[] args) {
        sayHello();
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
                    if (inputLine.equals("")) {
                        throw new OrkException("To do what? You dumb meat!");
                    }
                    line();
                    System.out.print("\t" + "Got it. I've added this task:\n");
                    String description = inputWord + inputLine;
                    addTodo(description);
                    count();
                } else if (inputWord.equals("deadline")) {
                    String inputLine = input.nextLine();
                    if (inputLine.equals("")) {
                        throw new OrkException("The deadline for what?! You dumb meat!");
                    }
                    line();
                    System.out.print("\t" + "Got it. I've added this task:\n");
                    String command = inputWord + inputLine;
                    addDeadline(command);
                    count();
                } else if (inputWord.equals("event")) {
                    String inputLine = input.nextLine();
                    if (inputLine.equals("")) {
                        throw new OrkException("You need to tell me what the event is! You dumb meat!");
                    }
                    System.out.print("\t" + "Got it. I've added this task:\n");
                    String command = inputWord + inputLine;
                    addEvent(command);
                    count();
                } else if (inputWord.equals("delete")) {
                    String inputLine = input.nextLine();
                    String[] deleteSplit = inputLine.split(" ");
                    String index= deleteSplit[1];
                    deleteList(index);
                } else if (inputWord.equals("bye")) {
                    sayBye();
                    input.close();
                    break;
                } else {
                    throw new OrkException("You think you are smart? You fresh meat!");
                }
            }
            catch (OrkException exception) {
                String message = exception.getMessage();
                line();
                System.out.println("\t " + message);
                line();
                String a = input.nextLine();
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
            System.out.println("\t" + (i + 1) + "." + list.get(i).getStatusIcon() + list.get(i).getDescription());
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
        ToDos temptask = new ToDos(description);
        temptask.print();
        list.add(temptask);
    }

    public static void addDeadline(String command) {
        String[] desplit = command.split("/by") ;
        String description = desplit[0];
        String deadline = desplit[1];
        Deadlines temptask = new Deadlines(description, deadline);
        temptask.print();
        list.add(temptask);
    }

    public static void addEvent(String command) {
        String[] desplit = command.split("/from") ;
        String description = desplit[0];
        String time = desplit[1];
        String[] timeSplit = time.split(" /to");
        String start = timeSplit[0];
        String end = timeSplit[1];
        Events temptask = new Events(description, start, end);
        temptask.print();
        list.add(temptask);
    }

    public static void count() {
        System.out.println("\t" + "Now you have " + list.size() + " tasks in the list.");
        line();
    }
    public static void deleteList(String index) {
        line();
        int indexi = Integer.parseInt(index);
        System.out.println("\t" + "Delete your task already, you happy now?");
        list.get(indexi - 1).print();
        list.remove(indexi - 1);
        System.out.println("\t" + "No meat, no goblins, hayahyahya");
        line();
    }
}
