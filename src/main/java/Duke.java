/**
 * The main user interface for the "ChatBot", promptly named BobBot.
 * <p>
 * This class is a front to manage user input, displaying the corresponding output, 
 * and managed the conditional statements for the prompting.
 */
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
        Scanner reader = new Scanner(System.in);
        TaskStorage ts = new TaskStorage();
        Duke.intro();
        
        String command = reader.nextLine();
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                System.out.println(ts);
            }
            else {
                String[] commandSplit = command.split(" ");
                String commandType = commandSplit[0];

                try {
                    if (commandType.equals("mark")) {
                        try {
                            if (commandSplit.length != 2) {
                                throw new DukeExceptionInvalidParameters(command);
                            }
                            int commandId = Integer.parseInt(commandSplit[1]);
                            if (commandId > ts.size() || ts.size() == 0) {
                                throw new DukeExceptionInvalidNumbering(command, commandId);
                            }
                            System.out.println("Nicely done! I've marked this task as done: ");
                            System.out.println(ts.markTask(commandId, true));
                            System.out.println("______________________________________");
                        } catch (NumberFormatException e) {
                            System.out.println("Hey, stop that! Please enter a number for the Task ID.");
                            System.out.println("______________________________________");
                        }
                         catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                    else if (commandType.equals("unmark")) {
                        try {
                            if (commandSplit.length != 2) {
                                throw new DukeExceptionInvalidParameters(command);
                            }
                            int commandId = Integer.parseInt(commandSplit[1]);
                            if (commandId > ts.size() || ts.size() == 0) {
                                throw new DukeExceptionInvalidNumbering(command, commandId);
                            }
                            System.out.println("Hey you! I've marked this task as not done, yet: ");
                            System.out.println(ts.markTask(commandId, false));
                            System.out.println("______________________________________");
                        } catch (NumberFormatException e) {
                            System.out.println("Hey, stop that! Please enter a number for the Task ID.");
                            System.out.println("______________________________________");
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                    else if (commandType.equals("delete")) {
                        try {
                            if (commandSplit.length != 2) {
                                throw new DukeExceptionInvalidParameters(command);
                            }
                            int commandId = Integer.parseInt(commandSplit[1]);
                            if (commandId > ts.size() || ts.size() == 0) {
                                throw new DukeExceptionInvalidNumbering(command, commandId);
                            }
                            System.out.println("Alright-o, I have deleted the following task:");
                            System.out.println(ts.removeTask(commandId));
                            System.out.println("______________________________________");
                        } catch (NumberFormatException e) {
                            System.out.println("Hey, stop that! Please enter a number for the Task ID.");
                            System.out.println("______________________________________");
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                    else if (commandType.equals("deadline")) {
                        String[] newCommandSplit = command.split("/");
                        String[] taskName = newCommandSplit[0].split("deadline ");
                        try {
                            if (newCommandSplit.length != 2 || (newCommandSplit[1].split("by ").length != 2)) {
                                throw new DukeExceptionInvalidParameters(command);
                            }
                            Task t = new Deadlines(taskName[1], newCommandSplit[1]);
                            ts.addTask(t);
                            System.out.println("Gotchu! I've added this deadline for you: ");
                            System.out.println(t);
                            System.out.println("You now have " + ts.size() + " tasks in the list.");
                            System.out.println("______________________________________");
                        } catch (ArrayIndexOutOfBoundsException e) {
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                    else if (commandType.equals("event")) {
                        String[] newCommandSplit = command.split("/");
                        String[] taskName = newCommandSplit[0].split("event ");
                        try {
                            int fromLength = newCommandSplit[1].split("from ").length;
                            int toLength = newCommandSplit[2].split("to ").length;
                            if (newCommandSplit.length != 3 || fromLength != 2 || toLength != 2) {
                                throw new DukeExceptionInvalidParameters(command);
                            }
                            Task t = new Events(taskName[1], newCommandSplit[1], newCommandSplit[2]);
                            ts.addTask(t);
                            System.out.println("Fun! I've added this event for you: ");
                            System.out.println(t);
                            System.out.println("You now have " + ts.size() + " tasks in the list.");
                            System.out.println("______________________________________");
                        } catch (ArrayIndexOutOfBoundsException e) {
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                    else if (commandType.equals("todo")) {
                        String[] taskName = command.split("todo ");
                        try {
                            if (taskName.length != 2) {
                                throw new DukeExceptionInvalidParameters(command);
                            }
                            Task t = new ToDo(taskName[1]);
                            ts.addTask(t);
                            System.out.println("Gotchu! I've added this task: ");
                            System.out.println(t);
                            System.out.println("You now have " + ts.size() + " tasks in the list.");
                            System.out.println("______________________________________");
                        } catch (ArrayIndexOutOfBoundsException e) {
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                    else {
                        throw new DukeExceptionInvalidCommand(command);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            command = reader.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        reader.close();
    }

    /**
     * Manages the startup script for the ChatBot. 
     */
    public static void intro() {
        String logo = "  ____        _     ____        _   \n"
            + " |  _ \\      | |   |  _ \\      | |  \n"
            + " | |_) | ___ | |__ | |_) | ___ | |_ \n"
            + " |  _ < / _ \\| '_ \\|  _ < / _ \\| __|\n"
            + " | |_) | (_) | |_) | |_) | (_) | |_ \n"
            + " |____/ \\___/|_.__/|____/ \\___/ \\__|\n";
        System.out.println("Hellooo! I'm \n" + logo);
        System.out.println("As I am a Chatbot, I therefore have no arms.");
        System.out.println("Knock knock, who's there? Definitely not BobBot!");
        System.out.println("Jokes aside, what can I do for you?");
        System.out.println("______________________________________");
    }

}