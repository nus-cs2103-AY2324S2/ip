package raphael.ui;
import java.util.Scanner;
import raphael.task.TaskList;
import raphael.task.Task;
public class Ui {
    private static final String START_LINE = "----------------"
            + "------------[Rep"
            + "ort]------------"
            + "----------------";

    private static final String END_LINE = "----------------"
            + "------------[End"
            + "ing]------------"
            + "----------------";
    private static final String WARNING_LINE = "----------------"
            + "-----------<WARN"
            + "ing!>-----------"
            + "----------------";
    private static final String DIVIDER = "----------------"
            + "----------------"
            + "----------------"
            + "----------------";

    /**
     * Prints the starting line that acts as the upper border of the output.
     */
    private static void printStartLine() {
        System.out.print(Ui.START_LINE);
    }

    /**
     * Prints the ending line that acts as the lower border of the output.
     */
    private static void printEndLine() {
        System.out.println(Ui.END_LINE);
    }

    /**
     * Prints the line indicating that the following output is an error message.
     */
    private static void printWarningLine() {
        System.out.print(Ui.WARNING_LINE);
    }

    /**
     * The divider line between outputs.
     */
    public void showLine() {
        System.out.println(Ui.DIVIDER);
    }

    /**
     * Prints the given input message.
     *
     * @param errorMessage the error message that has to be displayed.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Prints the output message when loading the tasks from tasks file into the task list.
     */
    public void showLoadingError() {
        System.out.println("Failed to load task list!");
    }

    /**
     * Prints the welcome message upon activation of Raphael.
     */
    public void showWelcome() {
        System.out.println(raphael.Raphael.LOGO);
        System.out.printf("Hi! I am %s, how can I help you?\n", raphael.Raphael.BOT_NAME);
    }

    /**
     * Prints the task that has been added into the Task List.
     *
     * @param tasks the task list.
     * @param task the task that need to be added.
     */
    public void showAddOutput(TaskList tasks, Task task) {
        System.out.printf("Roger that! I have added the following task into your list:\n" +
                "\t%s\n", task);
        System.out.println(tasks.getSize());
    }
//    public static void start() {
//        Ui.printStartLine();
//        final String greetings = String.format( "Hello! I'm %s\n"
//                + "What can I do for you?\n", Duke.botName);
//        System.out.print(greetings);
//        Ui.printEndLine();
//    }

    /**
     * The farewell message from Raphael upon termination.
     */
    public static void end() {
        final String endings = "Bye. It is an honor to serve you.\n"
                + "Hope to see you again soon!";
        System.out.println(endings);
        Ui.printEndLine();
    }

    /**
     * Read inputs from the command line.
     * @return the input read.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

//    public static void readCommand() {
//        Scanner sc = new Scanner(System.in);
//        boolean terminate = false;
//        while (true) {
//            final String input = sc.nextLine();
//            final String command = Parser.getCommand(input);
//            if (command != null) {
//                Ui.printStartLine();
//                switch (command) {
//                    case "bye":
//                        terminate = true;
//                        break;
//                    case "list":
//                        Duke.taskList.listItem();
//                        break;
//                    case "mark":
//                        Integer idx = Parser.getInteger(input, 1);
//                        if (idx != null) {
//                            Duke.taskList.checkTask(idx - 1);
//                        } else {
//                            Ui.printWarningLine();
//                            final String output = "Failed to get the index!";
//                            System.out.println(output);
//                            Ui.printWarningLine();
//                        }
//                        break;
//                    case "unmark":
//                        Integer idx2 = Parser.getInteger(input, 1);
//                        if (idx2 != null) {
//                            Duke.taskList.uncheckTask(idx2 - 1);
//                        } else {
//                            Ui.printWarningLine();
//                            final String output = "Failed to get the index!";
//                            System.out.println(output);
//                            Ui.printWarningLine();
//                        }
//                        break;
//                    case "todo":
//                        String toDo = Parser.removeCommand(input);
//                        if (toDo == null || toDo.isEmpty()) {
//                            System.out.println("Task not found!");
//                        } else {
//                            Duke.taskList.storeItem(new Todo(toDo));
//                        }
//                        break;
//                    case "deadline":
//                        String deadline = Parser.removeCommand(input);
//                        if (deadline == null || deadline.isEmpty()) {
//                            System.out.println("Task not found!");
//                        } else {
//                            String[] inputArr = Parser.getTaskAndDates(deadline, false);
//                            if (inputArr == null) {
//                                final String output = "Due time not found!\n"
//                                        + "Please follow the format: deadline [task] /by [yyyy-mm-dd]\n";
//                                System.out.println(output);
//                            } else {
//                                Duke.taskList.storeItem(new Deadline(inputArr[0], inputArr[1]));
//                            }
//                        }
//                        break;
//                    case "event":
//                        String event = Parser.removeCommand(input);
//                        if (event == null || event.isEmpty()) {
//                            System.out.println("Task not found!");
//                        } else {
//                            String[] inputArr = Parser.getTaskAndDates(event, true);
//                            if (inputArr == null) {
//                                final String output = "Due time not found!\n"
//                                        + "Please follow the format: \n"
//                                        + "deadline [task] /from [yyyy-mm-dd] /to [yyyy-mm-dd]\n";
//                                System.out.println(output);
//                            } else {
//                                Duke.taskList.storeItem(new Event(inputArr[0], inputArr[1], inputArr[2]));
//                            }
//                        }
//                        break;
//                    case "delete":
//                        Integer idx3 = Parser.getInteger(input, 1);
//                        if (idx3 != null) {
//                            Duke.taskList.deleteItem(idx3 - 1);
//                        } else {
//                            Ui.printWarningLine();
//                            final String output = "Failed to get the index!";
//                            System.out.println(output);
//                            Ui.printWarningLine();
//                        }
//                        break;
//                    case "any":
//                        String specificDeadline = Parser.getDate(input);
//                        if (specificDeadline == null) {
//                            System.out.println("Please follow the format:\n"
//                                    + "any [yyyy-mm-dd]\n");
//                        } else {
//                            Duke.taskList.dueBy(specificDeadline);
//                        }
//                        break;
//                    default:
//                        System.out.println("Oh no! I'm sorry, I can't understand it :(");
//                }
//                if (terminate) {
//                    break;
//                }
//                Ui.printEndLine();
//            }
//        }
//        sc.close();
//    }
}
