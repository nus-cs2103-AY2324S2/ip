public class Tasks {
    private static final int MAX_TASKS =100;
    private static Task[] tasks;
    private static int taskCount;

    public Tasks() {
        tasks = new Task[MAX_TASKS];
        taskCount = 0;
    }

    private static boolean isFull() {
        return taskCount >= (MAX_TASKS - 1);
    }

    private static boolean isValidTaskId(int id) {
        return id >= 0 && id < taskCount;
    }


    public void markTask(String command) throws DukeException {
        String[] words = command.split(" ");
        if (words.length == 2) {
            int taskId = Integer.parseInt(words[1]) -1;
            if(isValidTaskId(taskId)) {
                Task t = tasks[taskId];
                t.markAsDone();
                System.out.println(" Nice ! I've marked this task as done: \n" + t);
            } else {
                throw new DukeException(" Invalid task index. Type 'list' to list out your tasks. ");
            }
        } else {
            throw new DukeException(" Invalid format. Type 'mark 1' to mark task 1. ");
        }
    }

    public  void unmarkTask(String command) throws DukeException{
        String[] words = command.split(" ");
        if (words.length == 2) {
            int taskId = Integer.parseInt(words[1]) - 1;
            if (isValidTaskId(taskId)) {
                Task t = tasks[taskId];
                t.markAsUndone();
                System.out.println(" Ok, I've marked this task as not done yet: \n" + t);
            } else {
                throw new DukeException(" Invalid task index. Type 'list' to list out your tasks. ");
            }
        } else {
            throw new DukeException(" Invalid format. Use 'unmark 1' to unmark task 1. ");
        }
    }

    public void addTask(String command) throws DukeException {
        Task t;
        switch (getTaskType(command)) {
            case "todo":
                t = handleTodo(command);
                break;
            case "deadline":
                t = handleDeadline(command);
                break;
            case "event":
                t = handleEvent(command);
                break;
            default:
                throw new DukeException(" Sorry! I don't know what that means :c ");
        }

        if (! Tasks.isFull()) {
            tasks[taskCount++] = t;
            System.out.println(" Got it. I've added this task: \n" + t);
            System.out.println(" Now you have " + taskCount + " tasks in the list. ");
        } else {
            System.out.println(" Oops! Task list is full.");
        }
    }

    public void listOutTasks() {
        if (taskCount == 0) {
            System.out.println(" No task added yet!");
        } else {
            System.out.println(" Here are the tasks in your list: ");
            for(int i = 0; i < taskCount; i++) {
                int seq = i+1;
                Task t = tasks[i];
                System.out.println(" " + seq + ". " + t.toString());
            }
        }
    }
    private String getTaskType(String command) {
        return command.trim().split("\\s+")[0].toLowerCase();
    }

    private static Todo handleTodo(String command) throws DukeException{
        if( !command.contains(" ")) {
            throw new DukeException(" The description of a todo cannot be empty.");
        } else {
            String[] parts = command.split(" ");
            if (parts.length == 1) {
                throw new DukeException(" Invalid format :c \n" +
                        " (Eg format: todo <Description> )");
            } else {
                return new Todo(command.substring(4).trim());
            }
        }
    }

    private static Deadline handleDeadline(String command) throws DukeException{
        if (!command.contains(" ")) {
            throw new DukeException(" The description of a todo cannot be empty.");
        } else {
            String s = command.substring(8).trim();
            String[] parts = s.split("/by ", 2);
            if (parts.length == 2) {
                String taskDescription = parts[0].trim();
                String by = parts[1].trim();
                return new Deadline(taskDescription, by);
            } else {
                throw new DukeException(" Invalid format :c \n" +
                        " (Eg format: deadline <Description> /by <Date>)");
            }
        }
    }

    private static Event handleEvent(String command) throws DukeException {
            if (!command.contains(" ")) {
                throw new DukeException(" The description of an event cannot be empty.");
            } else {
                String s = command.substring(6).trim();
                String[] parts = s.split("/from ");
                if (parts.length == 2) {
                    String taskDescription = parts[0].trim();
                    String[] eventDetails = parts[1].split("/to ");
                    if (eventDetails.length == 2) {
                        String from = eventDetails[0].trim();
                        String to = eventDetails[1].trim();
                        return new Event(taskDescription, from, to);
                    } else {
                        throw new DukeException(" Oops! Invalid format :c \n " +
                                "(Try this: event <description> /from <time or date> /to <time or date>)");
                    }
                } else {
                    throw new DukeException(" Oops! Invalid format :c \n " +
                            " (Try this: event <description> /from <time or date> /to <time or date>)");
                }
            }
        }
    }