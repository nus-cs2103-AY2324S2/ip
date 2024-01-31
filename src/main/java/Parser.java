import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Parser {
    private static ArrayList<Task> inventory;
    private static int num;

    public void parse() {
        inventory = new ArrayList<>();
        num = 0;
        String path  = "../../../data/rah.txt";
        //BOT NAME :RSH
        String logo = " _____   _____  _    _ \n"
                    + "|  __ \\ / ____|| |  | |\n"
                    + "| |__) | (___  | |__| |\n"
                    + "|  _  / \\___ \\ |  __  |\n"
                    + "| | \\ \\ ____) || |  | |\n"
                    + "|_|  \\_|_____/ |_|  |_|\n";

        Scanner scanner = new Scanner(System.in); 

        System.out.println(layer("Hello! I'm \n" + logo + "What can I do for you?"));

        while (true) {
            try {
                String input = scanner.nextLine(); 
                if (input.equalsIgnoreCase("bye")) {
                    System.out.println(layer("Bye. Hope to see you again soon!"));
                    break; 
                }
                else if (input.equalsIgnoreCase("list")) {
                    if (!input.trim().equals("list")) {
                        throw new DukeException("OOPS!!! This is an invalid call of list command.");
                    } else {
                        String result = "";
                        int count = 1;
                        for (Task s : inventory) {
                            result += count + ". " + s.toString() + "\n";
                            count++;
                        }
                        System.out.println(layer(result));
                        //if marked
                    }
                } 
                else if (input.startsWith("mark")) {
                    if (input.trim().equals("mark")) {
                        throw new DukeException("OOPS!!! Invalid Command, highlight which task to mark");
                    } else {
                        int index = Integer.parseInt(input.substring(5));
                        inventory.get(index - 1).mark();
                        String temp = "Nice! I've marked this task as done: \n";
                        temp += inventory.get(index - 1).toString();
                        System.out.println(layer(temp));
                        //if unmarked
                    }
                } 
                else if (input.startsWith("unmark")) {
                    if (input.trim().equals("unmark")) {
                        throw new DukeException("OOPS!!! Invalid Command, highlight which task to unmark");
                    } else {
                        int index = Integer.parseInt(input.substring(7));
                        inventory.get(index - 1).unmark();
                        String temp = "OK, I've marked this task as not done yet: \n";
                        temp += inventory.get(index - 1).toString();
                        System.out.println(layer(temp));
                    }
                } 
                else if (input.startsWith("todo")) {
                    if (input.trim().equals("todo")) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    } else {
                        String task = input.substring(5);
                        num++;
                        inventory.add(new ToDo(task, num));
                        String temp = "Got it. I've added this task: \n";
                        temp += " " + inventory.get(inventory.size() - 1).toString();
                        temp += "\nNow you have " + num + " tasks in the list.";
                        System.out.println(layer(temp));
                    }
                }
                else if (input.startsWith("deadline")) {
                    String valid_format = "Please input in format: *deadline* *TASK* /*by yyyy-mm-dd TIME*";
                    if (input.trim().equals("deadline")) {
                        throw new DukeException("OOPS!!! The description of a deadline cannot be empty. " + valid_format);
                    } 
                    else if (!input.contains("/")) {
                        throw new DukeException("OOPS!!! Your deadline command's format is invalid. " + valid_format);
                    } 
                    else if (input.substring(9).split("/").length < 2) {
                        throw new DukeException("OOPS!!! Your deadline command's format is invalid. " + valid_format);
                    }
                    else {
                        String[] parts = input.substring(9).split("/");
                        LocalDateTime dateTime;
                        try {
                            String dateTimeString = parts[1].substring(3).trim();
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                            dateTime = LocalDateTime.parse(dateTimeString, formatter);
                        }
                        catch (DateTimeParseException e) {
                            throw new DukeException("OOPS!!! The date format is invalid. " + valid_format);
                        }
                        num++;
                        inventory.add(new Deadlines(parts[0], parts[1], dateTime, num));
                        String temp = "Got it. I've added this task: \n";
                        temp += " " + inventory.get(inventory.size() - 1).toString();
                        temp += "\nNow you have " + num + " tasks in the list.";
                        //Also include the tasks with the same deadline as the following task.
                        String similarities = "";
                        System.out.println(similarities.isBlank());
                        int counter = 1;
                        for (int i = 0; i < inventory.size() - 1; i++) {
                            Task t = inventory.get(i);
                            if (t.getDeadline().equals(dateTime.toLocalDate())) {
                                similarities += counter + ": " + t.toString() + "\n";
                            }
                            counter++;
                        }
                        if (similarities.isBlank()) {
                            temp += "\n\n\nThere are no other deadlines on the same day as this Task's deadline :)";
                            System.out.println(layer(temp));
                        } else {
                            temp += "\n\n\nThe following are the tasks with the same deadlines!: \n" + similarities;
                            System.out.println(layer(temp));
                        }
                    }
                }
                else if (input.startsWith("event")) {
                    String valid_format = "Please input in the format: Event *TASK* /*from YYYY-DD-MM TIME* /*to YYYY-DD-MM TIME*";
                    if (input.trim().equals("event")) {
                        throw new DukeException("OOPS!!! The description of a event cannot be empty. " + valid_format);
                    }
                    else if (!input.contains("/")) {
                        throw new DukeException("OOPS!!! Your event command's format is invalid. " + valid_format);
                    } else if (input.substring(6).split("/").length < 3) {
                        throw new DukeException("OOPS!!! Your event command's format is invalid. " + valid_format);
                    }  
                    else { 
                        String[] parts = input.substring(6).split("/");
                        LocalDateTime dateTime1;
                        LocalDateTime dateTime2;
                        try {
                            String dateTimeString1 = parts[1].substring(5).trim();
                            String dateTimeString2 = parts[2].substring(3).trim();
                            System.out.println(dateTimeString1);
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                            dateTime1 = LocalDateTime.parse(dateTimeString1, formatter);
                            dateTime2 = LocalDateTime.parse(dateTimeString2, formatter);
                        }
                        catch (DateTimeParseException e) {
                            throw new DukeException("OOPS!!! The date format is invalid. " + valid_format);
                        }
                        num++;
                        inventory.add(new Events(parts[0], parts[1], parts[2], dateTime1, dateTime2, num));
                        String temp = "Got it. I've added this task: \n";
                        temp += " " + inventory.get(inventory.size() - 1).toString();
                        temp += "\nNow you have " + num + " tasks in the list.";
                        System.out.println(layer(temp));
                    }
                }
                else if (input.startsWith("delete")) {
                    if (input.trim().equals("delete")) {
                        throw new DukeException("OOPS!!! Invalid delete command");
                    } else {
                        int index = Integer.parseInt(input.substring(7));
                        String temp = "Noted. I've removed this task: \n";
                        Task t = inventory.get(index - 1);
                        inventory.remove(index-1);
                        temp += " " + t.toString();
                        num--;
                        temp += "\nNow you have " + num + " tasks in this list.";
                        System.out.println(layer(temp));
                    }

                }
                else {
                    System.out.println(layer("OOPS!!! I'm sorry, but that's an invalid command :-("));
                }
            } catch (DukeException e) {
                System.out.println(layer(e.getMessage()));
            }
            
            try {
                writeToFile(path);
            }
            catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
            
        }

        scanner.close(); 
    }

    public static String layer(String s) {
        String line = "____________________________________________________________";
        return line + "\n" + s + "\n" + line; 
    }

    private static void writeToFile(String filePath) throws IOException {
        File file = new File(filePath);
        // Ensure the directory exists
        file.getParentFile().mkdirs();

        try (FileWriter fw = new FileWriter(file, false)) { // false to overwrite
            for (Task task : inventory) {
                fw.write(task.toString() + System.lineSeparator());
            }
        }
    }
}
