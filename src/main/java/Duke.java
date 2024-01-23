import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int taskCount = 0;

        System.out.println(
                "Hello! I'm Bob\n" +
                "What can I do for you?");

        Scanner userInput = new Scanner(System.in);
        while (true) {
            try {
                String command = userInput.nextLine();
                if (command.equals("list")) {
                    System.out.println("Here are your tasks in your list");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i+1) + "." + tasks[i]);
                    }
                } else if (command.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    System.exit(0);
                } else if (command.contains("unmark")) {
                    Task t = tasks[Integer.valueOf(command.split(" ")[1]) - 1];
                    t.unmark();
                    System.out.println("Ok, I've marked this task as not done yet:");
                    System.out.println(t);
                } else if (command.contains("mark") ) {
                    Task t = tasks[Integer.valueOf(command.split(" ")[1]) - 1];
                    t.mark();
                    System.out.println("Nice, I've marked this task as done:");
                    System.out.println(t);
                } else if (command.contains("todo") ||
                        command.contains("deadline") ||
                        command.contains("event")) {
                    Task t = null;
                    if (command.contains("todo")) {
                        String[] splitCommand = command.split(" ",2);
                        if (splitCommand.length==1) {
                            throw new DukeException("Description of a todo cannot be empty");
                        } else {
                            t = new Todo(splitCommand[1]);
                        }
                    } else if (command.contains("deadline")) {
                        String[] splitCommand = command.split(" ",2);
                        if (splitCommand.length==1) {
                            throw new DukeException("Description of a deadline cannot be empty");
                        }

                        String[] splitDesc = splitCommand[1].split(" /by ", 2);
                        if (splitDesc.length==1) {
                            throw new DukeException("Date/time of a deadline cannot be empty");
                        }

                        t = new Deadline(splitDesc[0], splitDesc[1]);
                    } else if (command.contains("event")) {
                        String[] splitCommand = command.split(" ",2);
                        if (splitCommand.length==1) {
                            throw new DukeException("Description of a deadline cannot be empty");
                        }

                        String[] splitDesc = splitCommand[1].split(" /from ", 2);
                        if (splitDesc.length==1) {
                            throw new DukeException("Start date/time of a deadline cannot be empty");
                        }

                        String[] splitFrom = splitDesc[1].split(" /to ", 2);
                        if (splitFrom.length==1) {
                            throw new DukeException("End date/time of a deadline cannot be empty");
                        }

                        t = new Event(splitDesc[0], splitFrom[0], splitFrom[1]);
                    }
                    tasks[taskCount] = t;
                    taskCount++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(t);
                    System.out.println("Now you have " + taskCount + " task(s) in the list.");
                } else {
                    throw new DukeException("I don't know what that means");
                }
            } catch (DukeException err) {
                System.out.println(err);
            }
        }
    }
}
