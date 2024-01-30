import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;

public class Duke {
    private static void addToFile(String filePath, String textToAdd) throws IOException {
        BufferedWriter fw = new BufferedWriter(new FileWriter(filePath, true));
        String line = "0 " + textToAdd;
        fw.write(line);
        fw.newLine();
        fw.close();
    }

    private static void removeFromFile(String filePath, int lineNumberToRemove) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        StringBuilder content = new StringBuilder();
        String line;
        int lineCounter = 1; // Keep track of the current line number

        // Read lines and skip the line to be deleted
        while ((line = reader.readLine()) != null) {
            if (lineCounter != lineNumberToRemove) {
                content.append(line).append(System.getProperty("line.separator"));
            }
            lineCounter++;
        }

        // Close the reader
        reader.close();

        // Write the modified content back to the file
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(content.toString());
        writer.close();
    }

    private static void editLineInFile(String filePath, int lineToEdit, int isDone) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        StringBuilder content = new StringBuilder();
        String line;
        int lineCounter = 1;

        // Read lines and modify the specified line
        while ((line = reader.readLine()) != null) {
            if (lineCounter == lineToEdit) {
                // Split the line by space
                String[] parts = line.split(" ", 2); // Split only once

                // Check if the line has at least two parts
                if (parts.length >= 2) {
                    parts[0] = String.valueOf(isDone);

                    // Reconstruct the modified line
                    String modifiedLine = String.join(" ", parts);

                    // Append the modified line to the content
                    content.append(modifiedLine).append(System.getProperty("line.separator"));
                }
            } else {
                content.append(line).append(System.getProperty("line.separator"));
            }
            lineCounter++;
        }

        // Close the reader
        reader.close();

        // Write the modified content back to the file
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(content.toString());
        writer.close();
    }
    public static void main(String[] args) throws IOException {
        ArrayList<Task> list = new ArrayList<>();
        String file = "data/duke.txt";

        try {
            File f = new File("data/duke.txt");
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String input = sc.nextLine();
                String[] words = input.split(" ");
                int isDone = Integer.parseInt(words[0]);
                String command = words[1];
                if (command.equals("todo")) {
                    String[] parts = input.split("todo", 2);
                    String description = parts[1].trim();
                    list.add(new ToDo(description, isDone));
                } else if (command.equals("deadline")) {
                    String[] parts = input.split("deadline", 2);
                    String description = parts[1].trim();
                    String[] deadlineParts = description.split("/by", 2);
                    description = deadlineParts[0].trim();
                    String deadline = deadlineParts[1].trim();
                    list.add(new Deadline(description, isDone, deadline));
                } else {
                    String[] parts = input.split("/from| /to");
                    String description = parts[0].trim().substring("0 event".length()).trim();
                    String start = parts[1].trim();
                    String end = parts[2].trim();
                    list.add(new Event(description, isDone, start, end));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        System.out.println("Hello! I'm Taro\n"
                + "What can I do for you?\n");

        Scanner s = new Scanner(System.in);

        while (true) {
            String input = s.nextLine();
            String[] words = input.split("\\s+");
            String command = words[0];

            if (command.equals("bye")) {
                break;

            } else if (command.equals("list")) {
                int num = 1;
                Iterator<Task> it = list.iterator();
                System.out.println("Here are the tasks in the list:");
                while (it.hasNext()) {
                    System.out.println(num + "." + it.next().toString());
                    num++;
                }

            } else if (command.equals("mark")) {
                int index = Integer.parseInt(words[1]);
                if (index < 0 || index > list.size()) {
                    System.out.println("Please enter index ranging from 1 to " + String.valueOf(list.size()));
                } else {
                    list.get(index - 1).markAsDone();
                    editLineInFile(file, index, 1);
                }

            } else if (command.equals("unmark")) {
                int index = Integer.parseInt(words[1]);
                if (index < 0 || index > list.size()) {
                    System.out.println("Please enter index ranging from 1 to " + String.valueOf(list.size()));
                } else {
                    list.get(index - 1).markAsUndone();
                    editLineInFile(file, index, 0);
                }
            } else if (command.equals("delete")) {
                int index = Integer.parseInt(words[1]);
                if (index < 0 || index > list.size()) {
                    System.out.println("Please enter index ranging from 1 to " + String.valueOf(list.size()));
                } else {
                    System.out.println("Ok. I'll be removing this task:\n "
                            + list.get(index - 1).toString()
                            + "\n"
                            + "Now you have " + String.valueOf(list.size() - 1) + " task(s) left");
                    removeFromFile(file, index);
                    list.remove(index-1);
                }

            } else if (command.equals("todo")) {
                String description = input.trim().substring("todo".length()).trim();
                if (description.isEmpty()) {
                    System.out.println("Please add a description for todo");
                } else {
                    addToFile(file, input);
                    list.add(new ToDo(description, 0));
                    String task = list.get(list.size() - 1).toString();
                    String numberOfTasks = "Now you have "
                            + String.valueOf(list.size())
                            + " task(s) left";
                    System.out.println("Ok. I added this task:\n" + task + "\n" + numberOfTasks);
                }

            } else if (command.equals("deadline")) {
                if (!input.contains("/by")) {
                    System.out.println("Ensure that the format is 'deadline [task] /by [deadline]'");
                } else {
                    String[] parts = input.split("/by");
                    String description = parts[0].trim().substring("deadline".length()).trim();
                    String deadline = parts[1].trim();
                    if (description.isEmpty() || deadline.isEmpty()) {
                        System.out.println("Ensure that the format is 'deadline [task] /by [deadline]'");
                    } else {
                        addToFile(file, input);
                        list.add(new Deadline(description, 0, deadline));
                        String task = list.get(list.size() - 1).toString();
                        String numberOfTasks = "Now you have "
                                + String.valueOf(list.size())
                                + " task(s) left";
                        System.out.println("Ok. I added this task:\n" + task + "\n" + numberOfTasks);
                    }
                }
            } else if (command.equals("event")) {
                if (!input.contains("/from") || !input.contains("/to")) {
                    System.out.println("Ensure that the format is 'event [task] /from [start] /to end'");
                } else {
                    String[] parts = input.split("/from| /to");
                    String description = parts[0].trim().substring("event".length()).trim();
                    String start = parts[1].trim();
                    String end = parts[2].trim();
                    if (description.isEmpty() || start.isEmpty() || end.isEmpty()) {
                        System.out.println("Ensure that the format is 'event [task] /from [start] /to end'");
                    } else {
                        addToFile(file, input);
                        list.add(new Event(description, 0, start, end));
                        String task = list.get(list.size() - 1).toString();
                        String numberOfTasks = "Now you have "
                                + String.valueOf(list.size())
                                + " task(s) left";
                        System.out.println("Ok. I added this task:\n" + task + "\n" + numberOfTasks);
                    }
                }
            } else {
                System.out.println("Please type a valid input:\n"
                        + "1. todo [task]\n"
                        + "2. deadline [task] /by[deadline]\n"
                        + "3. event [task] /from [start] /to end\n"
                        + "4. list");
            }
        }
        s.close();
        System.out.println("BYE BYE");
    }
}
