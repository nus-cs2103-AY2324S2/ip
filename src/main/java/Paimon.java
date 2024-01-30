package main.java;

import jdk.jfr.Event;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Paimon {
    public static void main(String[] args) {
        greeting();
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();
        boolean isActive = true;
        while (isActive) {
            String userInput = scanner.nextLine();
            CommandParser parser = new CommandParser(userInput);
            switch (parser.getType()) {
                case "bye":
                    isActive = false;
                    break;
                case "list":
                    taskList.printTaskList();
                    break;
                case "help":
                    System.out.println("You can perform the following actions! Make sure to follow the syntax\n----->");
                    System.out.println("list : Lists all your Tasks");
                    System.out.println("todo <Task> : Adds a Task without any deadline");
                    System.out.println("deadline <Task> /by <time> : Adds a task done before a time");
                    System.out.println("event<Task> /from <time> /to <time> : Adds a task with a time window");
                    System.out.println("mark/unmark <Index>: Mark a Task as done or not done. Index must be a number!");
                    System.out.println("bye : Exits the program");
                    System.out.println("----->");
                    break;
                case "mark":
                    String markIndexString= parser.parseInput()[0];
                    int markIndex = Integer.parseInt(markIndexString);
                    if (markIndex < taskList.getSize() && markIndex >= 1) {
                        taskList.getTask(markIndex - 1).setTaskState(true);
                        System.out.println("Okay Traveller, I've marked this task as done! \n" + taskList.getTask(markIndex - 1).getTask());
                    } else {
                        System.out.println("Sorry Traveller, your input is invalid.");
                    }
                    break;
                case "unmark":
                    String unmarkIndexString = parser.parseInput()[0];
                    int unmarkIndex = Integer.parseInt(unmarkIndexString);
                    if (unmarkIndex < taskList.getSize() && unmarkIndex >= 1) {
                        taskList.getTask(unmarkIndex - 1).setTaskState(false);
                        System.out.println("Okay Traveller, I've unmarked this task! \n" + taskList.getTask(unmarkIndex - 1).getTask());
                    } else {
                        System.out.println("Sorry Traveller, your input is invalid.");
                    }
                    break;
                case "todo":
                    String todoDescription = parser.parseInput()[0];
                    Task todoTask = new TodoTask(todoDescription);
                    taskList.addTask(todoTask);
                    System.out.println("Okay Traveller, I've added the following task! \n" + todoTask.getTask());

                    break;
                case "deadline":
                    String deadlineDescription = parser.parseInput()[0];
                    String deadlineEndDate = parser.parseInput()[1];
                    Task deadlineTask = new DeadlineTask(deadlineDescription, deadlineEndDate);
                    taskList.addTask(deadlineTask);
                    System.out.println("Okay Traveller, I've added the following task! \n" + deadlineTask.getTask());
                    break;
                case "event":
                    String eventDescription = parser.parseInput()[0];
                    String eventStartDate = parser.parseInput()[1];
                    String eventEndDate = parser.parseInput()[2];
                    Task eventTask = new EventTask(eventDescription, eventStartDate, eventEndDate);
                    taskList.addTask(eventTask);
                    System.out.println("Okay Traveller, I've added the following task! \n" + eventTask.getTask());

                    break;

                default:
                    System.out.println("Sorry Traveller, your input doesn't match any of our commands!");
                    break;
            }
        }
        goodbye();
    }




    private static void greeting() {
        System.out.println("-------------------->" + "\nGreetings Traveller!\nI'm Paimon, your friendly guide!\nType help to see what I can help you with!" + "\n-------------------->");
    }
    private static void goodbye() {
        System.out.println("-------------------->" + "\nSee you later Traveller!" + "\n-------------------->");
    }
}

