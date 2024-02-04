package Gops;

import java.io.File;
import java.util.Scanner;

public class Parser {
    /**
     * Parses the user input and does the appropriate operation on the taskList and updates the hard disk
     * @param input user input
     * @param taskList list of tasks
     * @param txtFile file containing tasks
     * @param inputTaker scanner
     */
    public void parse(String input, TaskList taskList, File txtFile, Scanner inputTaker) {
        String userReply = input;
        while (userReply != null) {
            if (userReply.equals("clear")) {
                taskList = new TaskList();
                System.out.println("List Cleared");
                Storage.writeToHardDisk(taskList, txtFile);
                userReply = inputTaker.nextLine();
            } else if (userReply.contains("todo")) {
                String[] splitter = userReply.split(" ", 2);
                try {
                    if (splitter.length == 1) {
                        throw new GopsException();
                    }
                    taskList.addNewTodo(splitter[1]);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("------------------------------------------------------------------");
                    taskList.printNewestTask();
                    Storage.writeToHardDisk(taskList, txtFile);
                    System.out.println("------------------------------------------------------------------");
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    userReply = inputTaker.nextLine();
                } catch (GopsException e) {
                    System.out.println("Please follow the format for setting todo\ntodo [your-task-here]");
                    userReply = inputTaker.nextLine();
                }
            } else if (userReply.contains("deadline")) {
                String[] firstSplitter = userReply.split(" ", 2);
                try {
                    if (firstSplitter.length == 1) {
                        throw new GopsException();
                    }
                    String[] secondSplitter = firstSplitter[1].split("/by", 2);
                    taskList.addNewDeadline(secondSplitter[0].trim(), secondSplitter[1].trim());
                    System.out.println("Task Added");
                    System.out.println("------------------------------------------------------------------");
                    taskList.printNewestTask();
                    Storage.writeToHardDisk(taskList, txtFile);
                    System.out.println("------------------------------------------------------------------");
                    System.out.println(taskList.size() + " Tasks Remaining");
                    userReply = inputTaker.nextLine();
                } catch (GopsException e) {
                    System.out.println("Please follow the format for setting deadlines\ndeadline [your-task-here] /by [deadline-of-your-task]");
                    userReply = inputTaker.nextLine();
                }
            } else if (userReply.contains("event")) {
                String[] firstSplitter = userReply.split(" ", 2);
                try {
                    if (firstSplitter.length == 1) {
                        throw new GopsException();
                    }
                    String[] secondSplitter = firstSplitter[1].split("/from", 2);
                    String[] thirdSplitter = secondSplitter[1].split("/to", 2);
                    Event eventObject = new Event(secondSplitter[0], thirdSplitter[0], thirdSplitter[1]);
                    taskList.addNewEvent(secondSplitter[0], thirdSplitter[0], thirdSplitter[1]);
                    System.out.println("Got it. I've added this task:");
                    Storage.writeToHardDisk(taskList, txtFile);
                    eventObject.Printer();
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    userReply = inputTaker.nextLine();
                } catch (GopsException e) {
                    System.out.println("Please follow the format for setting events\nevent [your-task-here] /from [start-time-of-your-task] /by [end-time-of-your-task]");
                    userReply = inputTaker.nextLine();
                }
            } else if (userReply.contains("unmark")) {
                try {
                    if (userReply.length() == 6) {
                        throw new GopsException();
                    }
                    int toDoListIndex = Integer.parseInt(userReply.substring(userReply.length() - 1)) - 1;
                    taskList.getTask(toDoListIndex).todoStatus = false;
                    System.out.println("OK! I've marked this task as not done yet:");
                    taskList.printTask(toDoListIndex);
                    Storage.writeToHardDisk(taskList, txtFile);
                    userReply = inputTaker.nextLine();
                } catch (GopsException e) {
                    System.out.println("Please follow the format for unmarking tasks\nunmark [task-number]");
                    userReply = inputTaker.nextLine();
                }
            } else if (userReply.contains("mark")) {
                try {
                    if (userReply.length() == 4) {
                        throw new GopsException();
                    }
                    int toDoListIndex = Integer.parseInt(userReply.substring(userReply.length() - 1)) - 1;
                    taskList.changeTaskStatus(toDoListIndex, true);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("------------------------------------------------------------------");
                    taskList.printTask(toDoListIndex);
                    System.out.println("------------------------------------------------------------------");
                    Storage.writeToHardDisk(taskList, txtFile);
                    userReply = inputTaker.nextLine();
                } catch (GopsException e) {
                    System.out.println("Please follow the format for marking tasks as done\nmark [task-number]");
                    userReply = inputTaker.nextLine();
                }
            } else if (userReply.equals("list")) {
                if (taskList.isEmpty()) {
                    System.out.println("No Tasks Left");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    System.out.println("------------------------------------------------------------------");
                    taskList.listPrinter();
                    System.out.println("------------------------------------------------------------------");
                }
                userReply = inputTaker.nextLine();
            } else if (userReply.contains("delete")) {
                try {
                    if (userReply.length() == 6) {
                        throw new GopsException();
                    }
                    String[] splitter = userReply.split(" ", 2);
                    int listIndex = Integer.parseInt(splitter[1]);
                    if (listIndex > taskList.size()) {
                        throw new GopsException();
                    }
                    System.out.println("I've deleted this task");
                    System.out.println("------------------------------------------------------------------");
                    taskList.printTask(listIndex - 1);
                    System.out.println("------------------------------------------------------------------");
                    taskList.removeTask(listIndex - 1);
                    Storage.writeToHardDisk(taskList, txtFile);
                    System.out.println("Here are the remaining " + taskList.size() + " task/s in your list:");
                    System.out.println("------------------------------------------------------------------");
                    taskList.listPrinter();
                    System.out.println("------------------------------------------------------------------");
                    userReply = inputTaker.nextLine();
                } catch (GopsException e) {
                    System.out.println("Please follow the format for deleting tasks\ndelete [task-number]");
                    userReply = inputTaker.nextLine();
                }
            } else if (userReply.equals("bye")){
                    System.out.print("Bye. Hope to see you again soon!");
                    break;
            }
            else {
                System.out.println("Please choose from the available prompts\n[todo/deadline/event/mark/unmark/list/bye]");
                userReply = inputTaker.nextLine();
            }
        }
    }
}
