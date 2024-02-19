package duke.ui;

import duke.command.CommandEnum;
import duke.task.Task;
import duke.task.TaskList;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Scanner;

/**
 * Text UI of the application
 */
public class Ui {

    private static final String BORDER_DIVIDER = "================================";
    private static final String DIVIDER = "--------------------------------";

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void showWelcome() {
        System.out.println("================================ \n" +
                "Hello I'm Axolotl! \n" +
                "What can I do for you? \n" +
                "================================ \n");
    }
    public String readCommand() {
        String input = in.nextLine();

        return input;
    }

    public static void printTaskMarked(int taskNo, Task t) {
        System.out.println("-------------------------------- \n" +
                "Nice! I've marked task " + (taskNo + 1) + " as done: \n" +
                t.toString() + "\n" +
                "-------------------------------- \n");
    }

    public static void printTaskUnmarked(int taskNo, Task t) {
        System.out.println("-------------------------------- \n" +
                "Sure, I've marked task " + (taskNo + 1) + " as not done yet: \n" +
                t.toString() + "\n" +
                "-------------------------------- \n");
    }

    public static void printTaskRemoved(Task t, ArrayList<Task> list) {
        System.out.println("-------------------------------- \n" +
                "Okay, I will delete this task: \n" +
                t.toString() + "\n" +
                "You now have " + list.size() + " in the list. \n" +
                "-------------------------------- \n");
    }

    public static void printTaskAdded(Task t, ArrayList<Task> list) {
        System.out.println("-------------------------------- \n" +
                "Sure, I've added this task: \n" +
                t.toString() + "\n" +
                "Now you have " + list.size() + " task(s) in the list. \n" +
                "-------------------------------- \n");
    }

    public static void printTasks(ArrayList<Task> list) {
        System.out.println( "-------------------------------- \n" +
                "Here are the tasks in your list:");
        if (list == null || list.isEmpty()) {
            System.out.println("----You have no tasks yet.----");
        }
        else {
            for (int i = 0; i < list.size(); i++) {
                Task iTask = list.get(i);
                System.out.println((i + 1) + ". " + iTask.toString());
            }
        }
        System.out.println( "-------------------------------- \n");
    }

    public static void printMatchingTasks(ArrayList<Task> list) {
        System.out.println( "-------------------------------- \n" +
                "Here are the matching tasks in your list:");
        if (list == null | list.isEmpty()) {
            System.out.println("----You have no such tasks.----");
        }
        else {
            for (int i = 0; i < list.size(); i++) {
                Task iTask = list.get(i);
                System.out.println((i + 1) + ". " + iTask.toString());
            }
        }
        System.out.println( "-------------------------------- \n");
    }



    public static void showLoadingError() {
        System.out.println("-------------------------------- \n" +
                "Oops, error in uploading saved tasks. Please check if the format is correct.");
    }

//    public static void showErrorNotExist(int taskNo) {
//        System.out.println("-------------------------------- \n" +
//                "Oops, task " + (taskNo + 1) + " does not exist. Please try again! \n" +
//                "Here is your list for reference: \n");
//        System.out.println(taskList.toString());
//        System.out.println("-------------------------------- \n");
//    }

    public static void showErrorNumbersOnly() {
        System.out.println("-------------------------------- \n" +
                "Oops, please enter task numbers instead! \n" +
                "(e.g. mark 1) \n" +
                "-------------------------------- \n");
    }

    public static void showErrorDeadlineFormat() {
        System.out.println("-------------------------------- \n" +
                "Oops, wrong format! Please follow this format for deadline task entries (e.g. deadline submit report /by 11/10/2019 1800 ) \n" +
                "-------------------------------- \n");
    }

    public static void showErrorEventFormat() {
        System.out.println("-------------------------------- \n" +
                "Oops, wrong format! Please follow this format for event task entries (e.g. event team project meeting /from 11/10/2019 1800 /to 11/10/2019 1800 ) \n" +
                "-------------------------------- \n");
    }

    public static void showErrorDatetimeFormat() {
        System.out.println("-------------------------------- \n" +
                "Oops, wrong datetime format! \n" +
                "Please follow this format <dd/MM/yyyy HHmm> (e.g. 02/12/2019 1800) \n" +
                "-------------------------------- \n");
    }

    public static void showErrorEventTimingFormat() {
        System.out.println("-------------------------------- \n" +
                "Oops, wrong format! Please follow this format for event task entries (e.g. event team project meeting /from 11/10/2019 1800 /to 11/10/2019 1800 ) \n" +
                "-------------------------------- \n");
    }


    public static void showErrorIncorrectNumFormat(String command) {
        System.out.println("-------------------------------- \n" +
            "Oops, I'm not sure which task you are referring to! " +
            "Please indicate a task number (e.g. " + command + " 1) \n" +
                "-------------------------------- \n");
    }

    public static void showErrorAndPrintCommands() {
        System.out.println( "-------------------------------- \n" +
                "Oops, I'm not sure what you meant by that! Commands available:");
        for (CommandEnum c: EnumSet.allOf(CommandEnum.class)) {
            System.out.println(c.COMMAND_SIGNATURE);
        }

        System.out.println("-------------------------------- \n");
    }

    public static void showExit() {
        System.out.println("================================ \n" +
                "Bye. Hope to see you again soon! \n" +
                "================================ \n");
    }

}
