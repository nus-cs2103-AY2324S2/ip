package Irwyn;

import Irwyn.Exceptions.CommandException;
import Irwyn.Exceptions.InputException;
import Irwyn.Exceptions.TaskException;

import Irwyn.Tasks.Deadline;
import Irwyn.Tasks.Event;
import Irwyn.Tasks.Task;
import Irwyn.Tasks.ToDo;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Irwyn {
    private static final String filePath = System.getProperty("user.dir") + "/storage/taskData.txt";
    public static void main(String[] args) {
        String linebreak = "____________________________________________________________\n";
        ArrayList<Task> list = new ArrayList<>();
        String start = linebreak
                + "Hello! I'm Irwyn\n"
                + "What can I do for you?\n"
                + linebreak;
        System.out.println(start);

        // Load tasks from file
        File file = new File(filePath);
        if (file.exists()) {
            try {
                Scanner fileReader = new Scanner(file);
                while (fileReader.hasNextLine()) {
                    String data = fileReader.nextLine();
                    String[] parts = data.split(" \\| ");
                    switch (parts[0]) {
                        case "T":
                            list.add(new ToDo(parts[2]));
                            if (parts[1].equals("1")) {
                                list.get(list.size() - 1).mark();
                            }
                            break;
                        case "D":
                            list.add(new Deadline(parts[2], parts[3]));
                            if (parts[1].equals("1")) {
                                list.get(list.size() - 1).mark();
                            }
                            break;
                        case "E":
                            list.add(new Event(parts[2], parts[3], parts[4]));
                            if (parts[1].equals("1")) {
                                list.get(list.size() - 1).mark();
                            }
                            break;
                    }
                }
                fileReader.close();
            } catch (FileNotFoundException e) {
                System.out.println(linebreak
                        + "An error occurred while loading tasks from file."
                        + linebreak);
            }
        } else {
            System.out.println(linebreak
                    + "Data file not found. A new file will be created.\n"
                    + linebreak);
        }

        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();

        while (!userInput.isEmpty()) {
            try {
                if (userInput.equals("bye")) {
                    break;
                } else if (userInput.startsWith("mark")) {
                    int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    list.get(index).mark();
                    System.out.println(linebreak
                            + "Nice! I've marked this task as done:\n"
                            + "  " + list.get(index) + "\n"
                            + linebreak);
                } else if (userInput.startsWith("unmark")) {
                    int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    list.get(index).unmark();
                    System.out.println(linebreak
                            + "OK, I've marked this task as not done yet:\n"
                            + "  " + list.get(index) + "\n"
                            + linebreak);
                } else if (userInput.equals("list")) {
                    System.out.println(linebreak
                            + "Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(i + 1 + "." + list.get(i));
                    }
                    System.out.println(linebreak);
                } else if (userInput.startsWith("todo")) {
                    if (userInput.trim().equals("todo")) {
                        throw new InputException("Description of a todo cannot be empty!\n");
                    }
                    String taskDescription = userInput.substring(5);
                    list.add(new ToDo(taskDescription));
                    System.out.println(linebreak
                            + "Got it. I've added this task:\n"
                            + "  " + list.get(list.size() - 1) + "\n"
                            + "Now you have " + list.size() + " tasks in the list.\n"
                            + linebreak);
                } else if (userInput.startsWith("deadline")) {
                    if (!userInput.contains(" /by ")) {
                        throw new CommandException();
                    }
                    String[] parts = userInput.split(" /by ");
                    String taskDescription = parts[0].substring(9);
                    String by = parts[1];
                    try {
                        list.add(new Deadline(taskDescription, by));
                        System.out.println(linebreak
                                + "Got it. I've added this task:\n"
                                + "  " + list.get(list.size() - 1) + "\n"
                                + "Now you have " + list.size() + " tasks in the list.\n"
                                + linebreak);
                    } catch (DateTimeParseException e) {
                        System.out.println(linebreak
                                + "Invalid date format. Please use YYYY-MM-DD or YYYY-MM-DD HH:MM:SS format.\n"
                                + linebreak);
                    }
                } else if (userInput.startsWith("event")) {
                    if (!userInput.contains(" /from ") || !userInput.contains(" /to ")) {
                        throw new CommandException();
                    }
                    String[] parts = userInput.split(" /from | /to ");
                    String taskDescription = parts[0].substring(6);
                    String from = parts[1];
                    String to = parts[2];
                    list.add(new Event(taskDescription, from, to));
                    System.out.println(linebreak
                            + "Got it. I've added this task:\n"
                            + "  " + list.get(list.size() - 1) + "\n"
                            + "Now you have " + list.size() + " tasks in the list.\n"
                            + linebreak);
                } else if (userInput.startsWith("delete")) {
                    int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    Task removedTask = list.remove(index);
                    System.out.println(linebreak
                            + "Noted. I've removed this task:\n"
                            + "  " + removedTask + "\n"
                            + "Now you have " + list.size() + " tasks in the list.\n"
                            + linebreak);
                } else {
                    throw new TaskException();
                }

                // Save tasks to file
                try {
                    FileWriter fileWriter = new FileWriter(filePath);
                    for (Task task : list) {
                        fileWriter.write(task.toFileFormat() + "\n");
                    }
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println(linebreak
                            + "An error occurred while saving tasks to file."
                            + linebreak);
                }
            } catch (InputException e) {
                System.out.println(linebreak
                        + e.getMessage()
                        + linebreak);
            }
            userInput = input.nextLine();
        }
        String end = linebreak
                + "Bye. Hope to see you again soon!\n"
                + linebreak;
        System.out.println(end);
    }
}
