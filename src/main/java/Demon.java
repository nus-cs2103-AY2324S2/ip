import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Demon {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello Master, I'm Demon \nWhat can I do for you today?");
        String input = sc.nextLine();
        // list to store actions specified by user.
        List<Task> list = new ArrayList<>();
        String taskFilePath = "src/main/taskList.txt";

        
        // Load file into list array
        try {
            // Create a FileReader
            FileReader fileReader = new FileReader(taskFilePath);

            // Create a BufferedReader
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            // Read file line by line
            while ((line = bufferedReader.readLine()) != null) {
                // Process the line
                Task item;
                String isCompleted;
                char firstChar = line.charAt(0);
                if (firstChar == 'D') {
                    isCompleted = line.split("\\|")[1].trim();
                    String description = line.split("\\|")[2].trim();
                    String by = line.split("\\|")[3].trim();
                    item = new Deadline(description, by);

                } else if (firstChar == 'T') {
                    isCompleted = line.split("\\|")[1].trim();
                    String description = line.split("\\|")[2].trim();
                    item = new Todo(description);

                } else {
                    isCompleted = line.split("\\|")[1].trim();
                    String description = line.split("\\|")[2].trim();
                    String from = line.split("\\|")[3].trim();
                    String to = line.split("\\|")[4].trim();
                    item = new Event(description, from, to);
                } 

                list.add(item);
                if (isCompleted.equals("1")) {
                    item.markDone();
                }
            }

            // Close the BufferedReader
            bufferedReader.close();
        } catch (IOException e) {
            System.err.println("No previous tasks loaded.");
        }

        while (!input.equalsIgnoreCase("bye")) {
            System.out.println("Entered: '" + input + "'");
            if (input.equalsIgnoreCase("list")) {
                printDivider();
                System.out.print("List of things to do :\n");
                for (int i = 1; i <= list.size(); i++) {
                    Task item = list.get(i-1);
                    System.out.println("\t" + i + "." + item.toString());
                }
                printDivider();
                System.out.println("Anything else? Please let me know: ");
                input = sc.nextLine();
            } else if (input.split(" ",2)[0].equalsIgnoreCase("unmark")) {
                int num = Integer.parseInt(input.split(" ")[1]);
                if (num < 1 | num > list.size()) {
                    System.out.println("Please provide valid integer within 1 to " + list.size());
                } else {
                    try {
                        Task item = list.get(num-1);
                        if (item.getStatusIcon().equals("X")) {
                            item.markNotDone();
                            printDivider();
                            System.out.println("Sure Master, I've marked this task as not done :");
                            System.out.println(item);
                            reWriteFile(taskFilePath, num);
                            printDivider();
                            System.out.println("Anything else? Please let me know: ");
                        } else {
                            System.out.println("Oops! Task already NOT done!");
                        }
                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                        System.out.println(e + ". Please provide valid integer.");
                    } 
                }
                input = sc.nextLine();
            } else if (input.split(" ",2)[0].equalsIgnoreCase("mark")) {
                int num = Integer.parseInt(input.split(" ")[1]);
                if (num < 1 | num > list.size()) {
                    System.out.println("Please provide valid integer within 1 to " + list.size());
                } else {
                    try {
                        Task item = list.get(num-1);
                        if (item.getStatusIcon().equals(" ")) {
                            item.markDone();
                            printDivider();
                            System.out.println("Sure Master, I've marked this task as done :");
                            System.out.println(item);
                            reWriteFile(taskFilePath, num);
                            printDivider();
                            System.out.println("Anything else? Please let me know: ");
                        } else {
                            System.out.println("Oops! Task already done!");
                        }
                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                        System.out.println(e + ". Please provide valid integer.");
                    } 
                }
                input = sc.nextLine();

            } else if (input.split(" ",2)[0].equalsIgnoreCase("deadline")) {
                try {
                    String[] parts = input.split(" ",2);
                    if (parts.length < 2) {
                        throw new EmptyDescriptionException("Description is EMPTY!");
                    }
                    String deadline = parts[1];
                    String[] parts2 = deadline.split("/by");
                    if (parts2.length < 2) {
                        throw new NoTimingException("WOI! Please include deadline!");
                    }
                    String description = parts2[0].trim();
                    String by = parts2[1].trim();
                    Deadline item_deadline = new Deadline(description, by);
                    list.add(item_deadline);
                    String stringToSave = "D | " + (item_deadline.getStatusIcon().equals("X") ? "1" : "0") + " | " + description + " | " + by + "\n";
                    // May produce IOException
                    writeToFile(taskFilePath, stringToSave);
                    printDivider();
                    System.out.println("Yes Master, I've added this task: ");
                    System.out.println(item_deadline);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    printDivider();
                    System.out.println("Anything else? Please let me know: ");
                } catch (NoTimingException | EmptyDescriptionException | IOException e) {
                    System.out.println("Error -> " + e);
                } finally {
                    input = sc.nextLine();
                }

            } else if (input.split(" ",2)[0].equalsIgnoreCase("todo")) {
                try {
                    String[] parts = input.split(" ",2);
                    if (parts.length < 2) {
                        throw new EmptyDescriptionException("Description is EMPTY!");
                    }
                    String toDo = parts[1];
                    Todo item_toDo = new Todo(toDo);
                    list.add(item_toDo);
                    String stringToSave = "T | " + (item_toDo.getStatusIcon().equals("X") ? "1" : "0") + " | " + toDo +"\n";
                    // May produce IOException
                    writeToFile(taskFilePath, stringToSave);
                    printDivider();
                    System.out.println("Yes Master, I've added this task: ");
                    System.out.println(item_toDo);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    printDivider();
                    System.out.println("Anything else? Please let me know: ");
                } catch (EmptyDescriptionException | IOException e) {
                    System.out.println("Error -> " + e);
                } finally {
                    input = sc.nextLine();
                }

            } else if (input.split(" ",2)[0].equalsIgnoreCase("event")) {
                try {
                    String[] parts = input.split(" ",2);
                    if (parts.length < 2) {
                        throw new EmptyDescriptionException("Description is EMPTY!");
                    }
                    String description_date = parts[1];
                    String[] parts2 = description_date.split("/from ");
                    if (parts2.length < 2) {
                        throw new NoTimingException("WOI! Please include deadline!");
                    }
                    String[] details = parts2[1].split("/to ");
                    String description = parts2[0].trim();
                    String from = details[0].trim();
                    String to = details[1].trim();
                    Event item_event = new Event(description, from, to);
                    list.add(item_event);
                    String stringToSave = "E | " + (item_event.getStatusIcon().equals("X") ? "1" : "0") + " | " + description + " | " + from + " | " + to + "\n";
                    // May produce IOException
                    writeToFile(taskFilePath, stringToSave);
                    printDivider();
                    System.out.println("Yes Master, I've added this task: ");
                    System.out.println(item_event);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    printDivider();
                    System.out.println("Anything else? Please let me know: ");
                } catch (NoTimingException | EmptyDescriptionException | IOException e) {
                    System.out.println("Error -> " + e);
                } finally {
                    input = sc.nextLine();
                }
            } else if (input.split(" ",2)[0].equalsIgnoreCase("delete")) {
                try {
                    String[] parts = input.split(" ");
                    if (parts.length < 2) {
                        throw new EmptyDescriptionException("You did not specify an number!");
                    }

                    printDivider();
                    System.out.println("Noted Master. I've removed this task:");
                    System.out.println(list.get(Integer.parseInt(parts[1]) - 1).toString());
                    list.remove(Integer.parseInt(parts[1]) - 1);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    printDivider();
                    System.out.println("Anything else? Please let me know: ");
                } catch (EmptyDescriptionException e) {
                    System.out.println("Error -> e");
                } finally {
                    input = sc.nextLine();
                }
            } else {
                printDivider();
                System.out.println("OOPS! Looks like you have entered an invalid command! Try again.");
                printDivider();
                input = sc.nextLine();
            }
        }
        System.out.println("Entered: '" + input + "'");
        printDivider();
        System.out.println("Bye Master, hope you had a great time, see you!");
        printDivider();
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    private static void reWriteFile(String filePath, int lineToEdit) {
        Path path = Paths.get(filePath);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String currentLineContent;
            int currentLineNum = 0;

            while ((currentLineContent = reader.readLine()) != null) {
                currentLineNum++;
                if (currentLineNum == lineToEdit) break;
            }

            assert currentLineContent != null;
            String[] partsOfString = currentLineContent.split("\\|");
            partsOfString[1] = partsOfString[1].trim().equals("1") ? " 0 " : " 1 ";
            String newContent = String.join("|", partsOfString);

            // Read all lines from the file
            List<String> lines = Files.readAllLines(path);
    
            // Check if the line to edit is within the file's line count
            if (lineToEdit <= lines.size()) {
                // Modify the specific line (adjust for 0-based index)
                lines.set(lineToEdit - 1, newContent);
            } else {
                System.out.println("The line number to edit is beyond the file's line count.");
            }

            Files.write(path, lines);
            reader.close();
        } catch (IOException e) {
            System.err.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void printDivider() {
        System.out.println("--------------------------------------------------------");
    }
}
