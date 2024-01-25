import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String horizontalLine = "__________________________________________________________________\n";

        String greeting = horizontalLine + "Hello! I'm KwunBot!\nWhat can I do for you?\n" + horizontalLine;
        System.out.println(greeting);

        String goodbye = horizontalLine + "Bye. Hope to see you again soon!\n" + horizontalLine;

        Task[] tasks = new Task[100]; // Assume there are no more than 100 tasks
        int counter = 0;

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String input = sc.nextLine().toLowerCase(); // Read user input

            try {
                // Handle 'bye' command
                if (input.equals("bye")) {
                    System.out.println(goodbye);
                    break;
                }

                // Handle 'list' command
                if (input.equals("list")) {
                    System.out.println(horizontalLine + "Here are the tasks in your list:");

                    for (int i = 0; i < counter; i++) {
                        System.out.printf("%d. %s\n", i + 1, tasks[i]);
                    }
                    System.out.println(horizontalLine);
                    continue;
                }

                // Handle more complex commands
                String[] parts = input.split(" ", 2);

                // Handle 'mark' commands
                if (parts[0].equals("mark") || parts[0].equals("unmark")) {

                    try {
                        int taskId = Integer.parseInt(parts[1]) - 1;
                        Task task = tasks[taskId];
                        String statement = task.changeMark(parts[0]);
                        System.out.println(horizontalLine + statement + task + "\n" + horizontalLine);
                        continue;

                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new MissingArgumentException(parts[0]);

                    } catch (NullPointerException e) {
                        throw new NoTaskFoundException(parts[1]);
                    }
                }

                // Handle add tasks commands
                if (parts[0].equals("todo") || parts[0].equals("deadline") || parts[0].equals("event")) {

                    Task newTask = null;

                    switch (parts[0]) {
                        case "todo": {
                            try {
                                newTask = new Todo(parts[1]);

                            } catch (ArrayIndexOutOfBoundsException e) {
                                throw new EmptyDescriptionException(parts[0]);
                            }
                            break;
                        }
                        case "deadline": {
                            try {
                                String[] splitDate = parts[1].split(" /by ", 2);
                                newTask = new Deadline(splitDate[0], splitDate[1]);

                            } catch (ArrayIndexOutOfBoundsException e) {
                                throw new EmptyDescriptionException(parts[0]);
                            }
                            break;
                        }
                        case "event": {
                            try {
                                String[] splitTaskName = parts[1].split(" /from ", 2);
                                String[] splitFromToDates = splitTaskName[1].split(" /to ", 2);
                                newTask = new Event(splitTaskName[0], splitFromToDates[0], splitFromToDates[1]);

                            } catch (ArrayIndexOutOfBoundsException e) {
                                throw new EmptyDescriptionException(parts[0]);
                            }
                            break;
                        }
                    }

                    tasks[counter] = newTask;
                    counter++;
                    String taskCounter = String.format("Now you have %s tasks in the list.\n", counter);
                    System.out.println(horizontalLine
                            + "Got it. I've added this task:\n" + newTask + "\n" + taskCounter
                            + horizontalLine);

                } else {
                    throw new InvalidCommandException(input);
                }

            } catch (EmptyDescriptionException e) { // Handle empty task description
                System.out.println(horizontalLine + e + horizontalLine);

            } catch (MissingArgumentException e) { // Handle missing argument
                System.out.println(horizontalLine + e + horizontalLine);

            } catch (NoTaskFoundException e) { // Handle unknown task number
                System.out.println(horizontalLine + e + horizontalLine);

            } catch (InvalidCommandException e) { // Handle invalid input error
                System.out.println(horizontalLine + e + horizontalLine);
            }
        }
        sc.close();
    }
}
