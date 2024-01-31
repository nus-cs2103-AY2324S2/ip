import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Objects;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm PeWPeWPeW");

        ArrayList<Task> task_arr = new ArrayList<>();
        int index = 0;

        try {
            File file = new File("data/duke.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split("\\|");
                String type = line[0];
                boolean isDone = line[1].equals("1");
                String description = line[2];
                if (Objects.equals(type, "T")) {
                    task_arr.add(new ToDo(index, description));
                    if (isDone) {
                        task_arr.get(index).mark();
                    }
                    index++;
                } else if (Objects.equals(type, "D")) {
                    String deadline = line[3];
                    task_arr.add(new Deadline(index, description, deadline));
                    if (isDone) {
                        task_arr.get(index).mark();
                    }
                    index++;
                } else if (Objects.equals(type, "E")) {
                    String start = line[3];
                    String end = line[4];
                    task_arr.add(new Event(index, description, start, end));
                    if (isDone) {
                        task_arr.get(index).mark();
                    }
                    index++;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e);
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("What can I do for you?");
        String userInput = scanner.nextLine();

        while (!Objects.equals(userInput.toLowerCase(), "bye")) {
            try {
                DukeException.validateInstn(userInput);
                if (Objects.equals(userInput.toLowerCase(), "pewpewpew")) {
                    task_arr.add(new Task(index, userInput));
                    System.out.println("PeWPeWPeWPeWPeWPeWPeWPeWPeWPeWPeWPeW");
                    index++;
                } else if (Objects.equals(userInput.toLowerCase(), "list")) {
                    for (Task task : task_arr) {
                        System.out.println(task.getTask());
                    }
                } else if (userInput.toLowerCase().contains("unmark")) {
                    int markedIndex = Integer.parseInt(userInput.replaceAll("[^0-9]", "")) - 1;
                    try {
                        DukeException.validateArrIndex(markedIndex, task_arr);
                        task_arr.get(markedIndex).unmark();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(task_arr.get(markedIndex).getTask());
                    } catch (DukeException d) {
                        System.out.println("ERROR: " + d);
                    }
                } else if (userInput.toLowerCase().contains("mark")) {
                    int markedIndex = Integer.parseInt(userInput.replaceAll("[^0-9]", "")) - 1;
                    try {
                        DukeException.validateArrIndex(markedIndex, task_arr);
                        task_arr.get(markedIndex).mark();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(task_arr.get(markedIndex).getTask());
                    } catch (DukeException d) {
                        System.out.println("ERROR: " + d);
                    }
                } else if (userInput.toLowerCase().contains("delete")) {
                    int markedIndex = Integer.parseInt(userInput.replaceAll("[^0-9]", "")) - 1;
                    try {
                        DukeException.validateArrIndex(markedIndex, task_arr);

                        System.out.println("Noted. I've removed this task:");
                        System.out.println(task_arr.get(markedIndex).getTask());

                        task_arr.remove(markedIndex);

                        System.out.println("Now you have " + task_arr.size() + " tasks in the list");
                        
                        index--;
                    } catch (DukeException d) {
                        System.out.println("ERROR: " + d);
                    }
                } else if (userInput.toLowerCase().contains("todo")) {
                    try {
                        DukeException.validateToDo(userInput);
                        task_arr.add(new ToDo(index, userInput.substring(5)));
                        System.out.println("Got it. I've added this task:");
                        System.out.println(task_arr.get(index).getTask());
                        System.out.println("Now you have " + task_arr.size() + " tasks in the list");
                        index++;
                    } catch (DukeException d) {
                        System.out.println("ERROR: " + d);
                    }
                } else if (userInput.toLowerCase().contains("deadline")) {
                    String[] str = userInput.split("/");
                    String deadline = str[1];
                    task_arr.add(new Deadline(index, str[0], deadline));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(task_arr.get(index).getTask());
                    System.out.println("Now you have " + task_arr.size() + " tasks in the list");
                    index++;
                } else if (userInput.toLowerCase().contains("event")) {
                    String[] str = userInput.split("/");
                    String start = str[1];
                    String end = str[2];
                    task_arr.add(new Event(index, str[0], start, end));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(task_arr.get(index).getTask());
                    System.out.println("Now you have " + task_arr.size() + " tasks in the list");
                    index++;
                }
                try {
                    FileWriter writer = new FileWriter("data/duke.txt");
                    for (Task task : task_arr) {
                        writer.write(task.save() + "\n");
                    }
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (DukeException d) {
                System.out.println("ERROR: " + d);
            }
            System.out.println("What else can I do for you? (try typing my name 3 times with no space in between)" );
            userInput = scanner.nextLine();

        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}