package Duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Ui {
    Scanner inputs = new Scanner(System.in);

    public void welcomeMessage () {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm TALKTOMEORILLDIE");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
    }

    public void goodbyeMessage () {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
    public void showMarkedAsDone (Task task){
        System.out.println("    ____________________________________________________________");
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + task);
        System.out.println("    ____________________________________________________________");
    }

    public void showMarkedAsNotDone (Task task){
        System.out.println("    ____________________________________________________________");
        System.out.println("     Nice! I've marked this task as not done:");
        System.out.println("       " + task);
        System.out.println("    ____________________________________________________________");
    }

    public void showAddedTask (Task task,int taskNum){
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + (taskNum) + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    public void showDeleteTask (Task task,int taskNum){
        System.out.println("    ____________________________________________________________");
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + (taskNum + 1) + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    public void showDeadlinesEventsOnDate (Task[]tasks, int taskNum, LocalDate dateToCheck){
        System.out.println("    ____________________________________________________________");
        System.out.println("     Deadlines/Events occurring on " +
                dateToCheck.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ":");
        for (int i = 0; i < taskNum; i++) {
            if (tasks[i] instanceof Deadline) {
                Deadline deadline = (Deadline) tasks[i];
                if (deadline.getBy().toLocalDate().isEqual(dateToCheck)) {
                    System.out.println("       " + tasks[i].toString());
                }
            }
        }
        System.out.println("    ____________________________________________________________");
    }
    public static void showLoadingError () {
        System.out.println("Error: Your file can't be loaded");
    }


    public void showTasks(Task[] task,int taskNum){
        System.out.println("    _______________________________________________________");
        if (taskNum == 0) {
            System.out.println("     You are doing absolutely nothing, lazy :))");
        } else {
            for (int i = 0; i < taskNum; i++) {
                System.out.println("     " + (i + 1) + ". " + task[i]);
            }
        }
        System.out.println("    _______________________________________________________");
    }

    public void showMatchingTasks(TaskList tasks, String keyword) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the matching tasks in your list:");
        int matchCount = 0;
        for (Task task : tasks.getTasks()) {
            if (task != null && task.description.contains(keyword)) {
                System.out.println("     " + (matchCount + 1) + "." + task);
                matchCount++;
            }
        }
        if (matchCount == 0) {
            System.out.println("     No matching tasks found.");
        }
        System.out.println("    ____________________________________________________________");
    }

    public String readInput() {
        return inputs.nextLine();
    }

    public void closeScanner() {
        inputs.close();
    }
}