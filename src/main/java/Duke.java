import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        //Starting and Ending Text
        String start = "Hello! I'm Unknown \n"
                + "What can I do for you? \n";
        String end = "Bye. Hope to see you again soon!\n";

        //Boolean value to indicate whether the user has finished
        boolean finished = false;

        //Array to contain the task list
        ArrayList<Task> taskList = new ArrayList<Task>();

        //Printing of Start text
        printingString(start);

        Scanner in = new Scanner(System.in);

        while(!finished) {
            String s = in.next();

            try {
                if (s.equalsIgnoreCase("bye")) {
                    finished = true;
                } else if (s.equalsIgnoreCase("list")) {
                    printingList(taskList);
                } else if (s.equalsIgnoreCase("mark")) {
                    int num = Integer.parseInt(in.next()) - 1;
                    taskList.get(num).markAsDone();
                    printingString("Nice! I've marked this task as done\n" + "  " + taskList.get(num));
                } else if (s.equalsIgnoreCase("unmark")) {
                    int num = Integer.parseInt(in.next()) - 1;
                    taskList.get(num).markAsUndone();
                    printingString("OK, I've marked this task as not done yet\n" + "  " + taskList.get(num));
                } else if (s.equalsIgnoreCase("todo")) {
                    String out = in.nextLine();
                    if (out.length() <= 1) {
                        throw new DukeException("Please enter something that you want to do. \n");
                    } else {
                        taskList.add(new ToDos(out));
                        printingAdd(taskList.get(taskList.size() - 1), taskList.size());
                    }
                } else if (s.equalsIgnoreCase("deadline")) {
                    String out = in.nextLine();
                    if (out.length() <= 1) {
                        throw new DukeException("Please enter something that you want to do. \n");
                    } else {
                        String[] split = out.split("/by");
                        if (split[0].length() <= 1) {
                            throw new DukeException("Please enter something that you want to do. \n");
                        } else if (split.length != 2 || split[1].length() <= 1) {
                            throw new DukeException("Please enter the deadline of the task. \n");
                        } else {
                            taskList.add(new Deadlines(split[0], split[1]));
                            printingAdd(taskList.get(taskList.size() - 1), taskList.size());
                        }
                    }
                } else if (s.equalsIgnoreCase("event")) {
                    String out = in.nextLine();
                    if (out.length() <= 1) {
                        throw new DukeException("Please enter something that you want to do. \n");
                    } else {
                        String[] split1 = out.split("/from");
                        if (split1[0].length() <= 1) {
                            throw new DukeException("Please enter something that you want to do. \n");
                        } else if (split1.length != 2) {
                            throw new DukeException("Please enter the duration of the event. \n");
                        } else {
                            String[] split2 = split1[1].split("/to");
                            if (split2.length != 2) {
                                throw new DukeException("Please enter the ending time of the event. \n");
                            } else {
                                taskList.add(new Events(split1[0], split2[0], split2[1]));
                                printingAdd(taskList.get(taskList.size() - 1), taskList.size());
                            }
                        }
                    }
                } else {
                    throw new DukeException("Please do enter a new proper command.\n");
                }
            } catch (DukeException e) {
                printingString(e.toString());
            } catch (NumberFormatException e) {
                printingString("Please enter a number for the task that you wish to edit.\n");
            } catch (IndexOutOfBoundsException e) {
                printingString("Please enter a number for the task that is on the list.\n");
            }
        }

        //Printing of End text
        printingString(end);
    }

    private static void printingString(String str) {
        //Function to add the line in front and behind the text
        String lnBreak = "_______________________________________________________________\n";
        System.out.println(lnBreak + str + lnBreak);
    }

    private static void printingList(ArrayList<Task> lst) {
        //Function to produce the string for the list to be printed
        String out = "Here are the tasks in your list:\n";
        for(int i = 1; i < lst.size() + 1; i++) {
            out += i + "." + lst.get(i - 1) + "\n";
        }
        printingString(out);
    }

    private static void printingAdd(Task task, int size) {
        printingString("Got it. I've added this task: \n" + task + "\nNow you have " + size + " tasks in the list.\n");
    }
}
