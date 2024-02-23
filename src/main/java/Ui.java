import java.util.ArrayList;

public class Ui {
    public static final String LINE = "____________________________________________________________\n";

    public static final String InputError = "Sorry, I'm not sure what you mean";

    public static String Intro() {
        return " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n" + "____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
    }

    public void printWelcome() {
        System.out.println(Intro());
    }

    public static String Outro() {
        return "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
    }

    public void printOutro() {
        System.out.println(Outro());
    }
    

    public void printList(ArrayList<Task> tasks) {
        if(tasks.size() == 0) {
            System.out.println("You have no tasks yet");
        } else {
            System.out.println("Here are the tasks in your list:");
            System.out.println(LINE);
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i).toString());
            }
            System.out.println(LINE);
        }
    }

    public void printMarkMessage(Task task) {
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done:" + "\n" + task.toString());
        System.out.println(LINE);
    }

    public void printUnmarkMessge(Task task) {
        System.out.println(LINE);
        System.out.println("OK, I've marked this task as not done yet-:" + "\n" + task.toString());
        System.out.println(LINE);
    }

    public void printAddMessage(Task task, int count) {
        System.out.println(LINE);
        System.out.println("Got it.I've added this task:");
        System.out.println(task.toString());
        if(count == 1){
            System.out.println("Now you have " + count + " task in the list.");
        } else {
            System.out.println("Now you have " + count + " tasks in the list.");
        }
        System.out.println(LINE);
    }

    public void printDeleteMessage(Task task, int count) {
        System.out.println(LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        if(count == 1){
            System.out.println("Now you have " + count + " task in the list.");
        } else {
            System.out.println("Now you have " + count + " tasks in the list.");
        }
        System.out.println(LINE);
    }

    public void printError(String message) {
        System.out.println(message);
    }

    public void printInputError(){
        System.out.println(InputError);
    }


}
