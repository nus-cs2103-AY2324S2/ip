import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Tobias {

    public static void addedTaskPrinter(Task task, int size, String divider) {
        System.out.println(divider);
        System.out.println("    Got it. I've added this task: ");
        task.taskPrinter();
        System.out.println("    Now you have " + size + " tasks in the list!");
        System.out.println(divider);
    }

    public static void commandHandler(String divider) {
        Scanner scanner = new Scanner(System.in);
        String outro = "Bye. Hope to see you soon!";

        Boolean isExit = false;
        String command = "";

        List<Task> tasks = new ArrayList<Task>();

        while (!isExit) {
            try {
                command = scanner.nextLine().trim();
                if (command.equals("bye")) {
                    System.out.println(divider);
                    System.out.println("    " + outro);
                    System.out.println(divider);
                    isExit = true;
                }
                else if (command.equals("list")) {
                    try {
                        if (tasks.isEmpty()) {
                            throw new TobiasException("    Your list is empty at the moment !");
                        } else {
                            System.out.println(divider);
                            System.out.println("    Here are the tasks in your list:");
                            for (Task task : tasks) {
                                int index = tasks.indexOf(task);
                                task.taskPrinter(index);
                            }
                            System.out.println(divider);
                        }
                    } catch (TobiasException e) {
                        e.printMessage();
                    }
                }
                else if (command.startsWith("mark")) {
                    try {
                        int size = tasks.size();
                        if (command.equals("mark")) {
                            if (size == 0) {
                                throw new TobiasException("    Your list is empty at the moment, add some todos/events/deadlines to MARK them!");
                            } else {
                                throw new TobiasException("    No index provided! Please give a valid index from 1 to " + size +" !!");
                            }
                        }

                        int index = Integer.parseInt(command.substring(5)) - 1;

                        if (index >= 0 && index < tasks.size()) {
                            Task curr = tasks.get(index);
                            curr.markAsDone();

                            System.out.println(divider);
                            System.out.println("    Nice! I've marked this task as done: ");
                            curr.taskPrinter(index);
                            System.out.println(divider);
                        } else {
                            throw new TobiasException("    Invalid number provided! Please give a valid index from 1 to " + size +" !!");
                        }
                    } catch (TobiasException e) {
                        e.printMessage();
                    }
                }
                else if (command.startsWith("unmark")) {
                    try {
                        int size = tasks.size();

                        if (command.equals("unmark")) {
                            if (size == 0) {
                                throw new TobiasException("    Your list is empty at the moment, add some todos/events/deadlines to UNMARK them!");
                            } else {
                                throw new TobiasException("    No index provided! Please give a valid index from 1 to " + size +" !!");
                            }
                        }

                        int index = Integer.parseInt(command.substring(7)) - 1;

                        if (index >= 0 && index < tasks.size()) {
                            Task curr = tasks.get(index);
                            curr.markAsUndone();

                            System.out.println(divider);
                            System.out.println("    OK, I've marked this task as not done : ");
                            curr.taskPrinter(index);
                            System.out.println(divider);
                        } else {
                            throw new TobiasException("    Invalid number provided! Please give a valid index from 1 to " + size +" !!");
                        }
                    } catch (TobiasException e) {
                        e.printMessage();
                    }
                }
                else if (command.startsWith("delete")) {
                    try {
                        int size = tasks.size();

                        if (command.equals("delete")) {
                            if (size == 0) {
                                throw new TobiasException("    Your list is empty at the moment, add some todos/events/deadlines to DELETE them!");
                            } else {
                                throw new TobiasException("    No index provided! Please give a valid index from 1 to " + size +" !!");
                            }
                        }

                        int index = Integer.parseInt(command.substring(7)) - 1;
                        if (index >= 0 && index <= tasks.size()) {
                            Task curr = tasks.get(index);
                            tasks.remove(index);

                            System.out.println(divider);
                            System.out.println("    Noted, I've removed this task: ");
                            curr.taskPrinter();
                            System.out.println(divider);
                        } else {
                            throw new TobiasException("    Invalid number provided! Please give a valid index from 1 to " + size +" !!");
                        }
                    }   catch (TobiasException e) {
                        e.printMessage();
                    }
                }
                else if (command.startsWith("todo")) {
                    try {
                        if (command.equals("todo")) {
                            throw new TobiasException("    Hey, please enter a description !");
                        }
                        String description = command.substring(4);

                        Task newTask = new ToDo(description);
                        tasks.add(newTask);

                        addedTaskPrinter(newTask, tasks.size(), divider);
                    } catch(TobiasException e) {
                        e.printMessage();
                    }
                }
                else if (command.startsWith("deadline")) {
                    try {
                        if (command.equals("deadline")) {
                            throw new TobiasException("    Hey, please enter a description !");
                        }

                        int byIndex = command.indexOf("/by");

                        if (byIndex == -1) {
                            throw new TobiasException("    Hey, please use '/by' to specify the deadline!!");
                        }

                        String description = command.substring(9, byIndex);

                        int deadlineIndex = byIndex+4;

                        if (deadlineIndex > command.length()) {
                            throw new TobiasException("     Hey, please enter a date/time !");
                        }

                        String deadline = command.substring(byIndex + 4);

                        if (deadline.isEmpty() || deadline.isBlank()) {
                            throw new TobiasException("     Hey, please enter a date/time !");
                        }

                        Task newTask = new Deadline(description, deadline);
                        tasks.add(newTask);

                        addedTaskPrinter(newTask, tasks.size(), divider);
                    } catch (TobiasException e) {
                        e.printMessage();
                    }

                }
                else if (command.startsWith("event")) {
                    try {

                        if (command.equals("event")) {
                            throw new TobiasException("     Hey, please enter a description !");
                        }

                        int fromIndex = command.indexOf("/from");
                        int toIndex = command.indexOf("/to");

                        if (fromIndex == -1 && toIndex == -1) {
                            throw new TobiasException("     Hey, please enter a /from and /to date/day/time!!");
                        }

                        if (fromIndex == -1) {
                            throw new TobiasException("     Hey, please enter a /from date/day/time!!");
                        }

                        if (toIndex == -1) {
                            throw new TobiasException("     Hey, please enter a /to date/day/time!!");
                        }

                        if (fromIndex > toIndex && toIndex > 0) {
                            throw new TobiasException("     Hey, you have to enter /from before /to !!");
                        }

                        String from = command.substring(fromIndex + 6, toIndex);

                        if (from.isEmpty() || from.isBlank()) {
                            throw new TobiasException("     Hey, please enter a /from date/day/time!!");
                        }

                        if ((toIndex + 4) > command.length()) {
                            throw new TobiasException("     Hey, please enter a /to date/day/time!!");
                        }

                        String to = command.substring(toIndex + 4);

                        if (to.isEmpty() || to.isBlank()) {
                            throw new TobiasException("     Hey, please enter a /to date/day/time!!");
                        }

                        String description = command.substring(6, fromIndex);

                        Task newTask = new Event(description, from, to);
                        tasks.add(newTask);

                        addedTaskPrinter(newTask, tasks.size(), divider);
                    } catch (TobiasException e) {
                        e.printMessage();
                    }

                }
                else {
                    throw new TobiasException("    You have said something I cannot yet understand !!!\n" +
                            "    You can ask me simple stuff like : 'bye', 'list', 'mark', 'unmark','delete'\n" +
                            "    You can ask me to create these   : 'todo', 'deadline', 'event'");
                }
            } catch (TobiasException e) {
                e.printMessage();
            }
        }


    }

    public static void introPrinter(String botName, String divider) {
        System.out.println(divider);
        System.out.println("   Hello there! I'm " + botName);
        System.out.println("   What can I do for you today ?");
        System.out.println(divider);
    }

    public static void main(String[] args) {
        String botName = "TOBIAS";
        String divider = "  ---------------------------------------------------------------------------------------";
        introPrinter(botName, divider);
        commandHandler(divider);
    }
}
