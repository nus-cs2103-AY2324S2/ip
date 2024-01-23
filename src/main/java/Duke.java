import Task.Task;
import Task.Todo;
import Task.Deadline;
import Task.Event;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        ArrayList<Task> taskList = new ArrayList<>();

        System.out.println("Hello! I'm TodoPal!");
        System.out.println("What can I do for you?");

        do {
            userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                if (taskList.isEmpty()) {
                    System.out.println("Your task list is empty.");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println((i + 1) + "." + taskList.get(i).toString());
                    }
                }
            } else if (userInput.startsWith("mark")) {
                markTask(userInput, taskList, true);
            } else if (userInput.startsWith("unmark")) {
                markTask(userInput, taskList, false);
            } else {
                // Sort out type of task
                if (userInput.startsWith("todo")) { // Todo
                    try {
                        String description = userInput.split(" ", 2)[1];
                        if (description.isEmpty()) {
                            System.out.println("OOPS!!! The description of a todo cannot be empty.");
                        } else {
                            Todo newTodo = new Todo(description);
                            taskList.add(newTodo);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(newTodo.toString());
                            System.out.println("Now you have " + taskList.size() + " task(s) in the list.");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("OOPS!!! The description of a todo cannot be empty.");
                    }
                } else if (userInput.startsWith("deadline")) { // Deadline
                    try {
                        String description = userInput.split("/by")[0].split(" ", 2)[1];
                        if (description.isEmpty()) {
                            System.out.println("OOPS!!! The description of a deadline cannot be empty.");
                        } else {
                            String by = userInput.split("/by")[1];
                            Deadline newDeadline = new Deadline(description, by);
                            taskList.add(newDeadline);
                            System.out.println("Got it. I've added this task:");
                            System.out.println("added: " + newDeadline.toString());
                            System.out.println("Now you have " + taskList.size() + " task(s) in the list.");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("OOPS!!! Please follow the given deadline format 'deadline *task description* /by *deadline*'");
                    }
                } else if (userInput.startsWith("event")) { // Event
                    try {
                        String description = userInput.split("/from")[0].split(" ", 2)[1];
                        if (description.isEmpty()) {
                            System.out.println("OOPS!!! The description of an event cannot be empty.");
                        } else {
                            String from = userInput.split("/from")[1].split(" /to")[0];
                            String to = userInput.split("/to")[1];
                            Event newEvent = new Event(description, from, to);
                            taskList.add(newEvent);
                            System.out.println("Got it. I've added this task:");
                            System.out.println("added: " + newEvent.toString());
                            System.out.println("Now you have " + taskList.size() + " task(s) in the list.");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("OOPS!!! Please follow the given event format 'event *task description* /from *start* /to *end*'");
                    }
                } else {
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-( Please start with todo, deadline or event.");
                }
            }
        } while (true);

        System.out.println("Bye! Hope to see you again!");
        scanner.close();
    }

    private static void markTask(String userInput, ArrayList<Task> taskList, boolean isMarked) {
        try {
            int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;

            if (taskIndex >= 0 && taskIndex < taskList.size()) {
                taskList.get(taskIndex).setDone(isMarked);

                System.out.println("Nice! I've marked this task as " + (isMarked ? "done:" : "not done yet:"));
                System.out.println(taskList.get(taskIndex).toString());
            } else {
                System.out.println("Task not found. Please provide a valid task index.");
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid input. Please provide a valid task index.");
        }
    }
}
