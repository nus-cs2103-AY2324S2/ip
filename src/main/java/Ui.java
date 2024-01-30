import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Ui {

    private Command c;
    private Storage storage;
    private Scanner user;
    private Task task;
    private ArrayList<Task> tasklist;
    private File f;

    public Ui(Scanner user, ArrayList<Task> tasklist, File f) {
        this.user = user;
        this.tasklist = tasklist;
        this.f = f;
    }

    public void showWelcome() {
        System.out.println( "Hi babyyy! It's your EUEU!!");
        System.out.println("What are you doing today??");
    }

    public void exit() {
        System.out.println("    byeee love uu ttyl ok!");
    }

    public void readCommand() {
        Task task = new Task(user.nextLine());
        while (!task.getTask().equals("bye")) {
            if (task.getTask().equals("list")) {
                storage = new Storage(tasklist, f);
                storage.getList();
            } else {
                c = new Command(tasklist);
                c.execute(task, task.getTask());
            }
            task = new Task(user.nextLine());
//            try {
//                task = new Task(user.nextLine());
//            } catch (NoSuchElementException e) {
//                System.out.println("Say something I'm giving up on you ");
//            }
        }
        exit();
    }

}
