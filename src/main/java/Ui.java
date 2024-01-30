import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private Scanner sc;
    public Ui() {
        this.sc = new Scanner(System.in);
    }


    public void sayHello() {
        System.out.println("Hello, I'm Ypxmm.\nNeed me do what for you?");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println("----------------------");
    }

    public void getCommands() {
        System.out.println("Take note ah, enter all time based commands are in <dd-mm-yyyy HHmm> format\n" +
                "  todo <task> - adds todo\n  deadline <task>/<by when> - adds deadline\n" +
                "  event <task>/<from when>/<to when> - adds event\n  list - lists out all tasks\n" +
                "  mark <x> - marks task x as done\n  unmark <x> - unmarks task x as undone\n" +
                "  bye - exit");
    }

    public void printList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks yet la bro");
        } else {
            System.out.println("Ok wait ah, here are your tasks:");
            int count = 1;
            for (Task t : tasks) {
                System.out.println(count + ". " + t.toString());
                count++;
            }
        }
    }

    public void addTaskMessage(Task task, TaskList tasklist) {
        System.out.println("Ok I help you add this one liao:\n" + task.toString() +
                "\nNow your list got " + tasklist.tasks.size() + ((tasklist.tasks.size() == 1) ? " task." : " tasks."));
    }

    public void deleteTaskMessage(Task task, TaskList tasklist) {
        System.out.println("Ok deleted liao:\n" + task.toString() + "\nNow your list got " +
                (tasklist.tasks.isEmpty() ? "no tasks." : tasklist.tasks.size() + " tasks left."));
    }

    public void markMessage(Task task) {
        System.out.println("Upz la, mark for you already!");
        System.out.println(task.toString());
    }

    public void unmarkMessage(Task task) {
        System.out.println("Eh wake up your idea, faster finish can or not?? Unmark for you already la!");
        System.out.println(task.toString());
    }

    public void sayGoodbye() {
        System.out.println("Oh you need zao alr? Okok see you next time!");
    }
}
