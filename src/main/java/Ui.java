import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Ui {

    private Command c;
    private Scanner user;
    private Task task;
    private TaskList tasklist;


    public Ui(Scanner user, TaskList tasklist) {
        this.user = user;
        this.tasklist = tasklist;
    }



    public void showWelcome() {
        System.out.println( "Hi babyyy! It's your EUEU!!");
        System.out.println("What are you doing today??");
    }

    public void exit() {
        System.out.println("    byeee love uu ttyl ok!");
    }

    public void readCommand() {
        Parser parse = new Parser(tasklist);
        Task task = new Task(user.nextLine());
        while (!task.getTask().equals("bye")) {
            parse.parsing(task.getTask());
            task = new Task(user.nextLine());
        }
        exit();
    }



}
//            try {
//                task = new Task(user.nextLine());
//            } catch (NoSuchElementException e) {
//                System.out.println("Say something I'm giving up on you ");
//            }