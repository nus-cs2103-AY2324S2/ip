import java.time.LocalDate;
import java.util.Scanner;

public class Ui {
    Scanner scanner;
    Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        String starter = "GrumbleBug:"
                + "_______________________________________\n"
                + "Oh hey, adventurer.\n"
                + "I'm GrumbleBug.\n";
        System.out.println(starter);
    }

    public void bye() {
        String bye = "GrumbleBug:"
                + "_______________________________________\n"
                + "Okay, byebye.\n";
        System.out.println(bye);
    }

    public void list(TaskList taskList) {
        String reply = "GrumbleBug:"
                + "_______________________________________\n"
                + "You have these things in your list...";
        System.out.println(reply);
        taskList.printList();
    }

    public void mark(TaskList taskList) {
        String reply = "GrumbleBug:"
                + "_______________________________________\n"
                + "Which task number? Give me the NUMBER.\n";
        System.out.println(reply);
        int i = 0;
        try {
            i = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Not a valid input...");
        }
        Task t = taskList.get(i);
        t.isDone = true;
    }
    public void unmark(TaskList taskList) {
        String reply = "GrumbleBug:"
                + "_______________________________________\n"
                + "Which task number? Give me the NUMBER.\n";
        System.out.println(reply);
        taskList.printList();
        int i = 0;
        try {
            i = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Not a valid input...\n");
        }
        Task t = taskList.get(i);
        t.isDone = false;
    }

    public void addTodo(TaskList taskList) {

        String reply = "GrumbleBug:"
                + "_______________________________________\n"
                + "What do you want TODO..?\n";
        System.out.println(reply);
        String todo = scanner.nextLine();

        Task t = new Task(false, todo);
        taskList.add(t);

        String reply2 = "GrumbleBug:"
                + "_______________________________________\n"
                + "ok lol\n";
        System.out.println(reply2);
    }
    public void addDeadline(TaskList taskList, Parser parser) {

        String reply = "GrumbleBug:"
                + "_______________________________________\n"
                + "What is the thing with the deadline..?\n";
        System.out.println(reply);
        String todo = scanner.nextLine();

        String reply2 = "GrumbleBug:"
                + "_______________________________________\n"
                + "ok and whens the deadline... in yyyy-mm-dd format.\n";
        System.out.println(reply2);
        LocalDate deadline = parser.parse(scanner.nextLine());

        Task t = new Task(false, todo, deadline);
        taskList.add(t);
    }
    public void addEvent(TaskList taskList, Parser parser) {

        String reply = "GrumbleBug:"
                + "_______________________________________\n"
                + "Event name. :||\n";
        System.out.println(reply);
        String todo = scanner.nextLine();

        String reply2 = "GrumbleBug:"
                + "_______________________________________\n"
                + "Start date in yyyy-mm-dd format.\n";
        System.out.println(reply2);
        LocalDate st = parser.parse(scanner.nextLine());
        String reply3 = "GrumbleBug:"
                + "_______________________________________\n"
                + "End date in yyyy-mm-dd format.\n";
        System.out.println(reply3);
        LocalDate end = parser.parse(scanner.nextLine());

        Task t = new Task(false, todo, st, end);
        taskList.add(t);
    }

    public void delete(TaskList taskList) {
        String reply2 = "GrumbleBug:"
                + "_______________________________________\n"
                + "WHich task? Give me its number:\n";
        System.out.println(reply2);
        taskList.printList();

        int i = 0;
        try {
            i = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Not a valid input...\n");
        }
        taskList.delete(i);
    }

    public void badInput() {
        System.out.println("I don't understand what you just said bruh \n");
    }

}
