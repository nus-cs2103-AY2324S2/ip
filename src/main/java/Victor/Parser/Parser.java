package victor.parser;

import victor.tasklist.TaskList;
import victor.tasktype.Deadline;
import victor.tasktype.Event;
import victor.tasktype.Task;
import victor.tasktype.Todo;
import victor.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    enum taskName {
        list,
        mark,
        unmark,
        todo,
        deadline,
        event,
        delete
    }
    String taskType = "";
    public Parser() {

    }

    public void parse(String commandLine, Ui ui, TaskList currentTasks) {
        String[] inputList = commandLine.split(" ", 2);
        switch (inputList[0]) {
            case "list" -> currentTasks.printList();
            case "mark" -> {
                try {
                    int position = Integer.parseInt(inputList[1]);
                    Task currentTask = currentTasks.getPosValue(position - 1);
                    System.out.println();
                    System.out.println("Nice! I've marked this task as done:");
                    currentTask.markAsDone();
                    System.out.println(currentTask);
                    ui.displayBarrier();
                } catch (IndexOutOfBoundsException e) {
                    ui.displayBarrier();
                    System.out.println("Check how many items are in the list again. The number you gave is too high");
                    System.out.println("Can't mark an item not in the list");
                    System.out.println("The format to mark a task is: mark (task list number)");
                    ui.displayBarrier();
                } catch (NumberFormatException e) {
                    ui.displayBarrier();
                    System.out.println("Sorry, I'm only smart enough to find the task based on numbers.");
                    System.out.println("Please give a number. If you refuse, too bad, this is all I can do.");
                    ui.displayBarrier();
                }
            }
            case "unmark" -> {
                try {
                    int position = Integer.parseInt(inputList[1]);
                    Task currentTask = currentTasks.getPosValue(position - 1);
                    ui.displayBarrier();
                    System.out.println("OK, I've marked this task as not done yet:");
                    currentTask.unmarkAsDone();
                    System.out.println(currentTask);
                    ui.displayBarrier();
                } catch (IndexOutOfBoundsException e) {
                    ui.displayBarrier();
                    System.out.println("Check how many items are in the list again. The number you gave is too high.");
                    System.out.println("Can't unmark an item not in the list");
                    System.out.println("The format to unmark a task is: unmark (task list number)");
                    ui.displayBarrier();
                } catch (NumberFormatException e) {
                    ui.displayBarrier();
                    System.out.println("Sorry, I'm only smart enough to find the task based on numbers.");
                    System.out.println("Please give a number. If you refuse, too bad, this is all I can do.");
                    ui.displayBarrier();
                }
            }
            case "todo" -> {
                try {
                    Todo userTask = new Todo(inputList[1], false);
                    currentTasks.addTask(userTask);
                    ui.displayBarrier();
                    System.out.println(userTask.toString());
                    System.out.println("Now you have " + currentTasks.getSize() + " tasks in the list.");
                    ui.displayBarrier();
                } catch (IndexOutOfBoundsException e) {
                    ui.displayBarrier();
                    System.out.println("Sorry pal, but your description is empty.");
                    System.out.println("Please redo the command and remember to add a description of the action");
                    System.out.println("The format to schedule a todo task is: " + taskName.todo + " (Description)");
                    ui.displayBarrier();
                }
            }
            case "deadline" -> {
                try {
                    String[] differentParts = inputList[1].split("/", 2);
                    String[] deadLine = differentParts[1].split(" ", 2);
                    LocalDate deadDate = LocalDate.parse(deadLine[1]);
                    Deadline userTask = new Deadline(differentParts[0], false, deadLine[1]);
                    currentTasks.addTask(userTask);
                    ui.displayBarrier();
                    System.out.println(userTask.toString());
                    System.out.println("Now you have " + currentTasks.getSize() + " tasks in the list.");
                    ui.displayBarrier();
                } catch (IndexOutOfBoundsException e) {
                    ui.displayBarrier();
                    System.out.println("Oh, you forgot to indicate when is the deadline or maybe you forgot the description.");
                    System.out.println("Please redo the command and remember to add the necessary information.");
                    System.out.println("The format to schedule a deadline is: " + taskName.deadline + " (Description) /by (Deadline Date + time)");
                    ui.displayBarrier();
                } catch (DateTimeParseException e) {
                    ui.displayBarrier();
                    System.out.println("Incorrect Date format. Please insert format using yyyy-mm-dd.");
                    System.out.println("E.g. 2019-02-15 is 15 February 2019");
                    ui.displayBarrier();
                }
            }
            case "event" -> {
                try {
                    String[] differentParts = inputList[1].split("/");
                    String[] startDate = differentParts[1].split(" ", 2);
                    String[] endDate = differentParts[2].split(" ", 2);
                    Event userTask = new Event(differentParts[0], false, startDate[1], endDate[1]);
                    currentTasks.addTask(userTask);
                    ui.displayBarrier();
                    System.out.println(userTask.toString());
                    System.out.println("Now you have " + currentTasks.getSize() + " tasks in the list.");
                    ui.displayBarrier();
                } catch (IndexOutOfBoundsException e) {
                    ui.displayBarrier();
                    System.out.println("You forgot to provide either a description, an start date or an end date for this event.");
                    System.out.println("Sorry, but mind reading is not installed in me yet.");
                    System.out.println("Please redo the command and remember to add the necessary information.");
                    System.out.println("The format to schedule a event is: " + taskName.event + " (Description) /from (Start date + time) /to (End date + time)");
                    ui.displayBarrier();
                }
            }
            case "delete" -> {
                try {
                    int position = Integer.parseInt(inputList[1]);
                    Task chosenTask = currentTasks.getPosValue(position - 1);
                    ui.displayBarrier();
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(chosenTask.toString());
                    currentTasks.removeTask(position - 1);
                    System.out.println("Now you have " + currentTasks.getSize() + " tasks in the list.");
                    ui.displayBarrier();
                } catch (IndexOutOfBoundsException e) {
                    ui.displayBarrier();
                    System.out.println("The number you gave exceeds how many items is in the list.");
                    System.out.println("Can't " + taskName.delete + " an item not in the list. Please try again.");
                    System.out.println("The format to delete a task is: " + taskName.delete + " (task list number)");
                    ui.displayBarrier();
                } catch (NumberFormatException e) {
                    ui.displayBarrier();
                    System.out.println("Sorry, I'm only smart enough to find the task based on numbers.");
                    System.out.println("Please give a number. If you refuse, too bad, this is all I can do.");
                    System.out.println("The format to delete a task is: " + taskName.delete + " (task list number)");
                    ui.displayBarrier();
                }
            }
        }
    }
}
