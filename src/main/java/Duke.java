import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    enum CommandType {
        BYE, LIST, DELETE, MARK, UNMARK, TODO, EVENT, DEADLINE
    }
    public static void main(String[] args) {
        String horizontalLine = "__________________________________________________________________\n";

        String greeting = horizontalLine + "Hello! I'm KwunTalk!\nWhat can I do for you?\n" + horizontalLine;
        System.out.println(greeting);

        String goodbye = horizontalLine + "Bye. Hope to see you again soon!\n" + horizontalLine;

        ArrayList<Task> tasks = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String input = sc.nextLine(); // Read user input

            // Handle commands
            String[] parts = input.split(" ", 2);

            try {
                String command = parts[0].toUpperCase();

                CommandType cmdType;

                try {
                    cmdType = CommandType.valueOf(command);

                } catch (IllegalArgumentException e) {
                    throw new InvalidCommandException(command);
                }

                switch (cmdType) {

                    case BYE: {
                        System.out.println(goodbye);
                        break;
                    }

                    case LIST: {
                        System.out.println(horizontalLine + "Here are the tasks in your list:");

                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.printf("%d. %s\n", i + 1, tasks.get(i));
                        }
                        System.out.println(horizontalLine);
                        break;
                    }

                    case DELETE: {
                        try {
                            int taskId = Integer.parseInt(parts[1]) - 1;
                            Task task = tasks.get(taskId);
                            tasks.remove(taskId);
                            String taskCounter = String.format("Now you have %s tasks in the list.\n",
                                    tasks.size());
                            System.out.println(horizontalLine +
                                    "OK. I've deleted this task:\n" +
                                    task + "\n" + taskCounter +
                                    horizontalLine);

                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new MissingArgumentException(command);

                        } catch (IndexOutOfBoundsException e) {
                            throw new NoTaskFoundException(parts[1]);
                        }
                        break;
                    }

                    case MARK:
                    case UNMARK: {
                        try {
                            int taskId = Integer.parseInt(parts[1]) - 1;
                            Task task = tasks.get(taskId);
                            String statement = task.changeMark(command);
                            System.out.println(horizontalLine + statement + task + "\n" + horizontalLine);

                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new MissingArgumentException(command);

                        } catch (IndexOutOfBoundsException e) {
                            throw new NoTaskFoundException(parts[1]);
                        }
                        break;
                    }

                    case TODO: {
                        try {
                            Task newTask = new Todo(parts[1]);
                            tasks.add(newTask);
                            String taskCounter = String.format("Now you have %s tasks in the list.\n", tasks.size());
                            System.out.println(horizontalLine
                                    + "Got it. I've added this task:\n" + newTask + "\n" + taskCounter
                                    + horizontalLine);

                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new EmptyDescriptionException(command);
                        }
                        break;
                    }

                    case DEADLINE: {
                        try {
                            String[] splitDate = parts[1].split(" /by ", 2);
                            Task newTask = new Deadline(splitDate[0], splitDate[1]);
                            tasks.add(newTask);
                            String taskCounter = String.format("Now you have %s tasks in the list.\n", tasks.size());
                            System.out.println(horizontalLine
                                    + "Got it. I've added this task:\n" + newTask + "\n" + taskCounter
                                    + horizontalLine);

                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new EmptyDescriptionException(command);
                        }
                        break;
                    }

                    case EVENT: {
                        try {
                            String[] splitTaskName = parts[1].split(" /from ", 2);
                            String[] splitFromToDates = splitTaskName[1].split(" /to ", 2);
                            Task newTask = new Event(splitTaskName[0], splitFromToDates[0], splitFromToDates[1]);
                            tasks.add(newTask);
                            String taskCounter = String.format("Now you have %s tasks in the list.\n", tasks.size());
                            System.out.println(horizontalLine
                                    + "Got it. I've added this task:\n" + newTask + "\n" + taskCounter
                                    + horizontalLine);

                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new EmptyDescriptionException(command);
                        }
                        break;
                    }

                    default: {
                        throw new InvalidCommandException(command);
                    }
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
