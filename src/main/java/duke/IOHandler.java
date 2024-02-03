package duke;

import duke.task.*;
import java.util.Scanner;

public class IOHandler {

    private final Scanner sc = new Scanner(System.in);

    public IOHandler()  {
    }

    public void welcomeMessage() {
        divider();
        System.out.println("Hello! I'm Shaunbot");
        System.out.println("What can I do for you?");
        divider();
    }

    public void divider() {
        System.out.println("____________________________________________________________");
    }

    public String typeMessage() {
        return sc.nextLine();
    }


    public void display(TaskList taskList) {
        int count = 0;
        int serial = 1;
        divider();
        while (count < taskList.size()) {
            System.out.println(serial + "." + taskList.get(count));
            count++;
            serial++;
        }
        divider();
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon !");
        divider();
    }

    public void echoAdd(Task t, TaskList taskList) {
        divider();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t);
        taskList.size();
        divider();
    }

}