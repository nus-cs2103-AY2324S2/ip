// test master branch
import java.util.Scanner;
import java.util.ArrayList;

public class Youdon {

    // function to detect when task desc is missing
    public static void detectMissingDesc(String input) throws YoudonException.EmptyDescException {
        if ((input.equals("todo")) || (input.equals("deadline")) || (input.equals("event"))) {
            throw new YoudonException.EmptyDescException("Hey! The task description is empty!");
        }
    }

    // function to detect when command is invalid
    public static void detectInvalidCommand(String input) throws YoudonException.InvalidCommandException {
        for (validCommands command: validCommands.values()) {
            if (input.contains(command.getCommand())) {
                return;
            }
        }
        throw new YoudonException.InvalidCommandException("Sorry, I do not recognise that command.");
    }

    public static void main(String[] args) {
        // initialise array and index
        ArrayList<Task> taskList = new ArrayList<>();
        int index = 0;

        // string constants
        String line = "----------------------------------------------------------------";
        String welcomeMsg = "Hello! I'm Youdon!\nWhat can I do for you?\n";
        String byeMsg = "Bye. Hope to see you again soon!";

        // chatbot welcome message
        System.out.println(line);
        System.out.println(welcomeMsg);
        System.out.println(line);

        // scan input
        Scanner input = new Scanner(System.in);
        String data = input.nextLine();

        // when there is input present
        while (!(data.isEmpty())) {
            // try-catch block for exceptions
            try {
                detectMissingDesc(data);
                detectInvalidCommand(data);
            } catch (YoudonException.EmptyDescException | YoudonException.InvalidCommandException e) {
                // print out error message
                System.out.println(line);
                System.out.println(e.getMessage());
                System.out.println(line);
                // update data to next input
                if (input.hasNextLine()) {
                    data = input.nextLine();
                } else {
                    data = "";
                }
            }

            // if input == "bye", print chatbot bye message
            if (data.equals("bye")) {
                System.out.println(line);
                System.out.println(byeMsg);
                System.out.println(line);
                break;
            }

            // if input == "list", return tasklist
            if (data.equals("list")) {
                System.out.println(line);
                System.out.println("Here are your tasks:");
                for (int i = 0; i < index; i++) {
                    System.out.println((i + 1) + ". " + taskList.get(i).toString());
                }
                System.out.println(line);
                // wait for next input
                if (input.hasNextLine()) {
                    data = input.nextLine();
                } else {
                    data = "";
                }
                continue;
            }

            if (data.contains(" ")) {
                // if input data has 2 parts, split into command & task number
                String[] parts = data.split(" ", 2);
                String command = parts[0];
                String task = parts[1];

                // if input == "mark", mark the specified task as done
                if (command.equals("mark")) {
                    // mark task as done in array
                    int taskNumber = Integer.parseInt(task);
                    taskList.get(taskNumber - 1).isDone = true;

                    // print out changes
                    System.out.println(line);
                    System.out.println("Nicely done! The task has been marked as done:");
                    System.out.println("  " + taskList.get(taskNumber - 1).toString());
                    System.out.println(line);
                    // wait for next input
                    if (input.hasNextLine()) {
                        data = input.nextLine();
                    } else {
                        data = "";
                    }
                    continue;
                }

                // if input == "unmark", mark the specified task as undone
                if (command.equals("unmark")) {
                    // mark task as undone in array
                    int taskNumber = Integer.parseInt(task);
                    taskList.get(taskNumber - 1).isDone = false;

                    // print out changes
                    System.out.println(line);
                    System.out.println("Okies! The task has been marked as undone:");
                    System.out.println("  " + taskList.get(taskNumber - 1).toString());
                    System.out.println(line);
                    // wait for next input
                    if (input.hasNextLine()) {
                        data = input.nextLine();
                    } else {
                        data = "";
                    }
                    continue;
                }

                // if input == "delete", delete the specified task
                if (command.equals("delete")) {
                    int taskNumber = Integer.parseInt(task);
                    // print out changes
                    System.out.println(line);
                    System.out.println("Alright! The task has been deleted:");
                    System.out.println("  " + taskList.get(taskNumber - 1).toString());
                    System.out.println(line);
                    // delete task and fix indexing
                    taskList.remove(taskNumber - 1);
                    index--;
                    // wait for next input
                    if (input.hasNextLine()) {
                        data = input.nextLine();
                    } else {
                        data = "";
                    }
                    continue;
                }

                // differentiate between type of tasks and add to tasklist
                if (command.equals("todo")) {
                    // add to tasklist
                    taskList.add(new Todo(task));
                    // print out task added
                    System.out.println(line);
                    System.out.println("Alright! Task added:\n  " + taskList.get(index).toString());
                    System.out.println("You now have " + (index + 1) + " task(s) in the list.");
                    System.out.println(line);
                    index++;
                }

                // if task has "by", split into task and deadline
                if (task.contains("/by ")) {
                    String[] chunks = task.split("/by ");
                    if (command.equals("deadline")) {
                        // add to tasklist
                        String taskDesc = chunks[0];
                        String deadline = chunks[1];
                        taskList.add(new Deadline(taskDesc, deadline));

                        // print out task added
                        System.out.println(line);
                        System.out.println("Alright! Task added:\n  " + taskList.get(index).toString());
                        System.out.println("You now have " + (index + 1) + " task(s) in the list.");
                        System.out.println(line);
                        index++;
                    }
                }

                if (task.contains("/from ")) {
                    String[] sections = task.split("/from |/to");
                    if (command.equals("event")) {
                        // add to tasklist
                        String taskDesc = sections[0];
                        String start = sections[1];
                        String end = sections[2];
                        taskList.add(new Event(taskDesc, start, end));

                        // print out task added
                        System.out.println(line);
                        System.out.println("Alright! Task added:\n  " + taskList.get(index).toString());
                        System.out.println("You now have " + (index + 1) + " task(s) in the list.");
                        System.out.println(line);
                        index++;
                    }
                }
                // wait for next input
                if (input.hasNextLine()) {
                    data = input.nextLine();
                } else {
                    data = "";
                }
            }
        }
    }
}