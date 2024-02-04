import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;


public class Duke {
    public static void main(String[] args) {

        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Your Only Friend\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");

        ArrayList<Task> listOfTasks = new ArrayList<>();

        load(listOfTasks);

        Scanner whatToDo = new Scanner(System.in);
        String whatToDoCall = whatToDo.nextLine().trim().toLowerCase();
        String[] command = whatToDoCall.split(" ", 2);

        try {
            while (!whatToDoCall.equals("bye")) {
                if ((whatToDoCall.equals("list") | whatToDoCall.equals("List") | whatToDoCall.equals("LIST"))) {
                    System.out.println("____________________________________________________________\n");
                    System.out.println(" Here are the tasks in your list:\n");
                    for (int i = 1; i <= listOfTasks.size(); i++) {
                        System.out.println(" " + i + "." + listOfTasks.get(i - 1).toString());
                    }
                    System.out.println("____________________________________________________________\n");
                } else if (command[0].equals("mark")) {
                    int indexToMark = Integer.parseInt(command[1]) - 1;

                    listOfTasks.get(indexToMark).markComplete();
                    System.out.println("____________________________________________________________\n");
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println(listOfTasks.get(indexToMark).toString());
                    System.out.println("____________________________________________________________\n");
                } else if (command[0].equals("unmark")) {
                    int indexToUnmark = Integer.parseInt(command[1]) - 1;
                    listOfTasks.get(indexToUnmark).markIncomplete();
                    System.out.println("____________________________________________________________\n");
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println(listOfTasks.get(indexToUnmark).toString());
                    System.out.println("____________________________________________________________\n");
                } else if (command[0].equals("delete")) {
                    int indexToDelete = Integer.parseInt(command[1]) - 1;
                    String str = listOfTasks.get(indexToDelete).toString();
                    listOfTasks.remove(indexToDelete);

                    System.out.println("____________________________________________________________\n"
                            + " Noted. I've removed this task:\n"
                            + str + "\n"
                            + " Now you have " + listOfTasks.size() + (listOfTasks.size() <= 1 ? " task in the list." : " tasks in the list.")
                            + "\n____________________________________________________________\n");


                } else {

                    Task task;
                    if (command.length <= 1) {
                        throw new DukeException("____________________________________________________________\n" +
                                " OOPS! Your Only Friend cannot take in an empty " + command[0] +
                                "\n Make sure " + command[0] + "has a description!\n" +
                                "____________________________________________________________\n");
                    }
                    if (command[0].equals("todo")) {
                        task = new ToDo(command[1]);
                    } else if (command[0].equals("deadline")) {
                        task = new Deadline(command[1]);
                    } else if (command[0].equals("event")) {
                        task = new Event(command[1]);
                    } else {
                        throw new DukeException("____________________________________________________________\n" +
                                " OOPS! Turns out Your Only Friend does not know what that is :(\n" +
                                "____________________________________________________________\n");

                    }

                    listOfTasks.add(task);
                    System.out.println("____________________________________________________________\n"
                            + " Got it. I've added this task:\n"
                            + task.toString() + "\n"
                            + " Now you have " + listOfTasks.size() + (listOfTasks.size() <= 1 ? " task in the list." : " tasks in the list.")
                            + "\n____________________________________________________________\n");
                }

                whatToDoCall = whatToDo.nextLine().trim().toLowerCase();
                command = whatToDoCall.split(" ", 2);
            }

            try {
                File directory = new File("./data/");

                if (!directory.exists()) {
                    directory.mkdirs();
                }

                saveToFile("./data/duke.txt", stringToSave(listOfTasks));
            } catch (IOException e) {
                System.out.println("Error while saving, please try again!");
            }
        } catch (DukeException exception) {
            System.out.println(exception.getMessage());
        }

        System.out.println("____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n - Your Only Friend\n"
                + "____________________________________________________________\n");


    }

    public static void load(ArrayList<Task> listOfTasks) {

        try {
            File file = new File("./data/duke.txt");
            Scanner whatToDo = new Scanner(file);

            while (whatToDo.hasNext()) {
                String readText = whatToDo.nextLine();
                char taskType = readText.charAt(1);
                char doneOrNot = readText.charAt(5);
                String description = readText.substring(9);

                Task task = null;

                if (taskType == 'T') {
                    task = new ToDo(description);
                    listOfTasks.add(task);
                } else if (taskType == 'D') {
                    try {
                        task = new Deadline(description);
                        listOfTasks.add(task);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    try {
                        task = new Event(description);
                        listOfTasks.add(task);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                }

                if (doneOrNot == '1') {
                    task.markComplete();
                }

            }

        } catch (FileNotFoundException e) {
                System.out.println("____________________________________________________________\n" +
                        "No Saved Tasks Found. Let's start with an empty list!\n" +
                        "____________________________________________________________\n");
        }
    }

    public static String stringToSave(ArrayList<Task> listOfTasks) {
        String toSave = "";

        for (int i = 0; i < listOfTasks.size(); i++) {
                 toSave += listOfTasks.get(i).toSave() + "\n";
        }

        return toSave;
    }

    public static void saveToFile(String filepath, String text) throws IOException {
        FileWriter file = new FileWriter(filepath);
        file.write(text);
        file.close();
    }

}