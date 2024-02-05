import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class TaskHandler{

    public static void addTasks(String input, ArrayList<Task> storage, Path filePath) throws IOException {
        int taskNum = storage.size() + 1;
        if (input.equals("list")) {
            System.out.println("__________________________________\n" +
                    "Here are the tasks in your list:\n");
            for (int i = 0; i < storage.size(); i++) {
                int counter = i + 1;
                String output = counter + ". " + storage.get(i).toString();
                System.out.println(output);
            }

        } else if (input.startsWith("mark")) {
            String[] temp = input.split(" ");
            try {
                if (temp.length <= 1) {
                    throw new DukeException("__________________________________\n" +
                            "What tasks do you want to mark?");
                }

                int number = Integer.parseInt(temp[1]);
                if (number > storage.size() || number <= 0) {
                    throw new DukeException("__________________________________\n" +
                            "The task is not in the list!!");
                }
                int index = number - 1;
                Task task = storage.get(index);
                task.markTask();
                String output = "OK, I've marked this task as done:\n" +
                        "   " + task.toString();
                System.out.println(output);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

        } else if (input.startsWith("unmark")) {
            String[] temp = input.split(" ");
            try {
                if (temp.length <= 1) {
                    throw new DukeException("__________________________________\n" +
                            "What tasks do you want to unmark?");
                }

                int number = Integer.parseInt(temp[1]) ;
                if (number > storage.size() || number <= 0 ) {
                    throw new DukeException("__________________________________\n" +
                            "The task is not in the list!!");
                }
                int index = number - 1;
                Task task = storage.get(index);
                task.unmarkTask();
                String output = "OK, I've marked this task as not done yet:\n" +
                        "   " + task.toString();
                System.out.println(output);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

        } else if (input.startsWith("delete")) {
            String[] temp = input.split(" ");
            try {
                if (temp.length <= 1) {
                    throw new DukeException("__________________________________\n" +
                            "What tasks do you want to delete?");
                }
                int number = Integer.parseInt(temp[1]);
                if (number > storage.size() || number <= 0) {
                    throw new DukeException("__________________________________\n" +
                            "The task is not in the list!!");
                }
                int index = number - 1;
                Task task = storage.get(index);
                storage.remove(index);
                String output = "OK, I've removed this task:\n" +
                        "   " + task.toString() +
                        "\nNow you have " + storage.size() + " tasks in the list.";
                System.out.println(output);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

        } else if (input.startsWith("todo")) {
            try {
                String[] test = input.split(" ");
                if (test.length <= 1) {
                    throw new DukeException("__________________________________\n" +
                            "So where is the todo task????");
                }
                String msg = input.substring(5);
                ToDo task = new ToDo(msg);
                String output = "__________________________________\n" +
                        "Ok, I have added this task:\n" +
                        "   " + task.toString() +
                        String.format("\nNow you have %d tasks in the list", taskNum);
                System.out.println(output);
                storage.add(task);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

        } else if (input.startsWith("deadline")) {
            try {
                String[] test = input.split(" ");
                if (test.length <= 1) {
                    throw new DukeException("__________________________________\n" +
                            "So where is the deadline task????");
                }
                String msg = input.substring(9);
                Deadline task = new Deadline(msg);
                String output = "__________________________________\n" +
                        "Ok, I have added this task:\n" +
                        "   " + task.toString() +
                        String.format("\nNow you have %d tasks in the list", taskNum);
                System.out.println(output);
                storage.add(task);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

        } else if (input.startsWith("event")) {
            try {
                String[] test = input.split(" ");
                if (test.length <= 1) {
                    throw new DukeException("__________________________________\n" +
                            "SO where is the event????");
                }
                String msg = input.substring(6);
                Event task = new Event(msg);
                String output = "__________________________________\n" +
                        "Ok, I have added this task:\n" +
                        "   " + task.toString() +
                        String.format("\nNow you have %d tasks in the list", taskNum);
                System.out.println(output);
                storage.add(task);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

        } else {
            try {
                throw new DukeException("__________________________________\n" +
                        "What is this task???");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("__________________________________\n");
        Files.writeString(filePath, "");
        for (Task task : storage) {
            try {
                Files.writeString(filePath, "\n" + task.toString(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }


        }

}
