package ChatbotRan;

import java.util.Scanner;

public class Ran {
    TaskList taskList;


    public Ran(TaskIO taskIO) {
        this.taskList = new TaskList(taskIO);
    }

    public static void main(String[] args) {
        TaskIO ti = new TaskIO();


        Ran chatbot = new Ran(ti);
        chatbot.run();
    }

    public void run() {
        System.out.println("Hello. I am ");
        String art = "__________                \n" +
                "\\______   \\_____    ____  \n" +
                " |       _/\\__  \\  /    \\ \n" +
                " |    |   \\ / __ \\|   |  \\\n" +
                " |____|_  /(____  /___|  /\n" +
                "        \\/      \\/     \\/ ";
        System.out.println(art);
        System.out.println("What would you like to do today?");
        boolean running = true;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("____________________________________________________________");
            String line = sc.nextLine();
            int space = line.indexOf(' ');
            String command = space == -1 ? line : line.substring(0, space);
            Task task;
            switch (command) {
            case "mark":
                task = this.handleTaskNo(line, space);
                if (task != null) {
                    if (!task.isCompleted()) {
                        task.setCompleted(true);
                        taskList.updateTasks();
                        System.out.println("Alright. I have marked this task as complete: ");
                    } else {
                        System.out.println("That task is already complete: ");
                    }
                    System.out.println(task);
                }
                break;
            case "unmark":
                task = this.handleTaskNo(line, space);
                if (task != null) {
                    if (task.isCompleted()) {
                        task.setCompleted(false);
                        taskList.updateTasks();
                        System.out.println("If that's the case, I'll set that task as incomplete: ");
                    } else {
                        System.out.println("That task is already incomplete: ");
                    }
                    System.out.println(task);
                }
                break;
            case "delete":
                task = this.handleTaskNo(line, space);
                if (task != null) {
                    taskList.remove(task);
                    System.out.println("I've deleted this task: ");
                    System.out.println(task);
                    this.printNumber();
                }
                break;
            case "deadline":
                Deadline deadline = Deadline.parse(line, space);
                this.addTask(deadline);
                break;
            case "todo":
                Todo todo = Todo.parse(line, space);
                this.addTask(todo);
                break;
            case "event":
                Event event = Event.parse(line, space);
                this.addTask(event);
                break;
            default:
                switch (line) {
                case "bye":
                    running = false;
                    break;
                case "list":
                    int size = taskList.size();
                    if (size == 0) {
                        System.out.println("You haven't got any tasks.");
                    } else {
                        for (int i = 0; i < size; i++) {
                            System.out.println("Task " + (i + 1) + ":" + taskList.get(i));
                        }
                    }
                    break;
                default:
                    System.out.println("I didn't understand that.");
                }


            }
        } while (running);

        System.out.println("Goodbye, please return soon.");
    }


    private Task handleTaskNo(String line, int space) {
        if (space == -1) {
            System.out.println("Missing task number");
            return null;
        }
        Integer taskNo = Util.parseNumber(line, space);
        if (taskNo == null || taskNo < 1) {
            System.out.println("Invalid task number.");
        } else if (taskNo > taskList.size()) {
            System.out.println("No task by that number.");
        } else {
            return taskList.get(taskNo - 1);
        }
        return null;
    }

    private void addTask(Task task) {
        if (task != null) {
            taskList.add(task);
            System.out.println("I've added this task to the list: ");
            System.out.println(task);
            this.printNumber();
        }
    }

    private void printNumber() {
        if (taskList.size() == 1) {
            System.out.println("There is now 1 task in the list");
        } else {
            System.out.println("There are now " + taskList.size() + " tasks in the list");
        }
    }

}

