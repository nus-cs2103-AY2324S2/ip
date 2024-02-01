package main.java;

import java.util.ArrayList;
import java.util.Scanner;

public class Dude {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Loading tasks from file...");
        ArrayList<Task> tasks = TaskStorage.loadTasksFromFile();
        System.out.println("Loaded " + tasks.size() + " tasks from file.");
        System.out.println("Hello! I'm Duddddde");
        System.out.println("What can I do for you?");

        while (true) {
            try {
                String input = scanner.nextLine();
                System.out.println(input);

                if (input.equalsIgnoreCase("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                } else if (input.startsWith("done ")) {
                    int index = Integer.parseInt(input.substring(5));
                    if (index < 0 || index >= tasks.size()) {
                        throw new IndexOutOfBoundsException("Task number does not exist.");
                    }
                    tasks.get(index).markAsDone();
                    TaskStorage.saveTasksToFile(tasks);
                    System.out.println("Marked as done: " + tasks.get(index));
                } else if (input.startsWith("undo ")) {
                    int index = Integer.parseInt(input.substring(5));
                    if (index < 0 || index >= tasks.size()) {
                        throw new IndexOutOfBoundsException("Task number does not exist.");
                    }
                    tasks.get(index).markAsNotDone();
                    TaskStorage.saveTasksToFile(tasks);
                    System.out.println("Marked as not done: " + tasks.get(index));
                } else if (input.startsWith("delete ")) {
                    int index = Integer.parseInt(input.substring(7));
                    if (index < 0 || index >= tasks.size()) {
                        throw new IndexOutOfBoundsException("Task number does not exist.");
                    }
                    Task removedTask = tasks.remove(index);
                    TaskStorage.saveTasksToFile(tasks);
                    System.out.println("Deleted: " + removedTask);

                } else {
                    String[] parts = input.split(" ", 2);
                    String command = parts[0];
                    String taskInfo = parts.length > 1 ? parts[1] : "";

                    switch (command.toLowerCase()) {
                        case "todo":
                            tasks.add(new ToDo(taskInfo));
                            System.out.println("Added ToDo: " + taskInfo);
                            break;
                        case "deadline":
                            if (!taskInfo.contains(" /by ")) {
                                throw new IllegalArgumentException(
                                        "Invalid deadline format. Use '/by' to specify the deadline.");
                            }
                            String[] deadlineParts = taskInfo.split(" /by ", 2);
                            tasks.add(new Deadline(deadlineParts[0], deadlineParts[1]));
                            System.out.println("Added Deadline: " + deadlineParts[0]);
                            break;
                        case "event":
                            if (!taskInfo.contains(" /from ") || !taskInfo.contains(" /to ")) {
                                throw new IllegalArgumentException(
                                        "Invalid event format. Use '/from' and '/to' to specify the event times.");
                            }
                            String[] eventParts = taskInfo.split(" /from ", 2);
                            String[] eventTimes = eventParts[1].split(" /to ", 2);
                            tasks.add(new Event(eventParts[0], eventTimes[0], eventTimes[1]));
                            System.out.println("Added Event: " + eventParts[0]);
                            break;
                        default:
                            System.out.println("Unknown command");
                            break;

                    }
                    TaskStorage.saveTasksToFile(tasks);
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid task number.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
