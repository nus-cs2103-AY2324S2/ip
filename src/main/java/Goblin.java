import java.util.Scanner;
import java.util.ArrayList;
public class Goblin {
    static String greetings = "Hello！ I'm NetGoblin\n"
            + "What can I do for you?\n";
    static String bye = "Bye. Hope to see you agian soon!\n";

    static ArrayList<Task> list = new ArrayList<>();

    public static void main(String[] args) {
        sayHello();
        Scanner input = new Scanner(System.in);
        while (input.hasNext()){
            String inputWord = input.next();
            if (inputWord.equals("mark")) {
                mark(input);
            } else if (inputWord.equals("unmark")) {
                unmark(input);
            } else if (inputWord.equals("list")) {
                showList();
            } else if (inputWord.equals("todo")) {
                line();
                System.out.print("\t" + "Got it. I've added this task:\n");
                String inputLine = input.nextLine();
                String description = inputWord + inputLine;
                addTodo(description);
                count();
            } else if (inputWord.equals("deadline")) {
                line();
                System.out.print("\t" + "Got it. I've added this task:\n");
                String inputLine = input.nextLine();
                String command = inputWord + inputLine;
                addDeadline(command);
                count();
            } else if (inputWord.equals("event")) {
                System.out.print("\t" + "Got it. I've added this task:\n");
                String inputLine = input.nextLine();
                String command = inputWord + inputLine;
                addEvent(command);
                count();
            } else if (inputWord.equals("bye")) {
                sayBye();
                input.close();
                break;
            } /*else {

                Task temptask = new Task(inputWord + inputLine);
                line();
                System.out.println("added: " + inputWord + inputLine);
                line();
                list.add(temptask);
            }*/
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
        System.out.println("\t" + "Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + list.get(i).getStatusIcon() + list.get(i).getDescription());
        }
        line();
    }

    public static void mark(Scanner input) {
        int i = Integer.parseInt(input.next());
        list.get(i - 1).done();
        line();
        System.out.println("\t" + "Nice! I've marked this task as done:");
        list.get(i - 1).print();
        line();
    }

    public static void unmark(Scanner input) {
        int i = Integer.parseInt(input.next());
        list.get(i - 1).undone();
        line();
        System.out.println("\t" + "Nice! I've marked this task as done:");
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
}
