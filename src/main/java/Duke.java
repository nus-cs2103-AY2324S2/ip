import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
public class Duke {
    //Array to contain the task list
    private final static ArrayList<Task> taskList = new ArrayList<>();
    private final static String fileName = "./data/duke.txt";

    public static void main(String[] args) {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                File f = new File("./data");
                f.mkdir();
                file.createNewFile();
            } else {
                readFile(file);
            }
        } catch (Exception e){
            printingString(e.toString());
        }

        //Starting and Ending Text
        String start = "Hello! I'm Unknown \n"
                + "What can I do for you? \n";
        String end = "Bye. Hope to see you again soon!\n";

        //Boolean value to indicate whether the user has finished
        boolean finished = false;

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
                    mark(Integer.parseInt(in.next()) - 1);
                } else if (s.equalsIgnoreCase("unmark")) {
                    unmark(Integer.parseInt(in.next()) - 1);
                } else if (s.equalsIgnoreCase("todo")) {
                    toDo(in.nextLine());
                } else if (s.equalsIgnoreCase("deadline")) {
                    deadline(in.nextLine());
                } else if (s.equalsIgnoreCase("event")) {
                    event(in.nextLine());
                } else if (s.equalsIgnoreCase("delete")) {
                    delete(Integer.parseInt(in.next()) - 1);
                } else {
                    throw new DukeException("Please do enter a new proper command.\n");
                }
            } catch (DukeException e) {
                printingString(e.toString());
            } catch (NumberFormatException e) {
                printingString("Please enter a number for the task that you wish to edit.\n");
            } catch (IndexOutOfBoundsException e) {
                printingString("Please enter a number for the task that is on the list.\n");
            } catch (IOException e) {
                printingString("Error when writing to file\n");
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

    private static void mark(int num) throws DukeException,IOException {
        taskList.get(num).markAsDone();
        arrayToFile();
        printingString("Nice! I've marked this task as done\n" + "  " + taskList.get(num) + "\n");
    }

    private static void unmark(int num) throws DukeException,IOException {
        taskList.get(num).markAsUndone();
        arrayToFile();
        printingString("OK, I've marked this task as not done yet\n" + "  " + taskList.get(num) + "\n");
    }

    private static void toDo(String out) throws DukeException,IOException {
        if (out.length() <= 1) {
            throw new DukeException("Please enter something that you want to do. \n");
        } else {
            appendToFile("T|0|" + out);
            taskList.add(new ToDos(out));
            printingAdd(taskList.get(taskList.size() - 1), taskList.size());
        }
    }

    private static void deadline(String out) throws DukeException,IOException {
        if (out.length() <= 1) {
            throw new DukeException("Please enter something that you want to do. \n");
        } else {
            String[] split = out.split("/by");
            if (split[0].length() <= 1) {
                throw new DukeException("Please enter something that you want to do. \n");
            } else if (split.length != 2 || split[1].length() <= 1) {
                throw new DukeException("Please enter the deadline of the task. \n");
            } else {
                appendToFile("D|0|" + split[0] + "|" + split[1]);
                taskList.add(new Deadlines(split[0], split[1]));
                printingAdd(taskList.get(taskList.size() - 1), taskList.size());
            }
        }
    }

    private static void event(String out) throws DukeException,IOException {
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
                    appendToFile("E|0|" + split1[0] + "|" + split2[0] + "|" + split2[1]);
                    taskList.add(new Events(split1[0], split2[0], split2[1]));
                    printingAdd(taskList.get(taskList.size() - 1), taskList.size());
                }
            }
        }
    }

    private static void delete(int num) throws DukeException,IOException {
        Task task = taskList.get(num);
        taskList.remove(num);
        arrayToFile();
        printingString("Noted. I've removed this task: \n" + task + "\nNow you have " + taskList.size() + " tasks in the list.\n");
    }

    private static void readFile(File file) throws FileNotFoundException {
        Scanner s = new Scanner(file);
        int count = 0;
        while (s.hasNext()) {
            String str = s.nextLine();
            String[] split = str.split("[|]");

            if (split[0].equalsIgnoreCase("T")) {
                taskList.add(new ToDos(split[2]));
            } else if (split[0].equalsIgnoreCase("D")) {
                taskList.add(new Deadlines(split[2],split[3]));
            } else if (split[0].equalsIgnoreCase("E")) {
                taskList.add(new Events(split[2],split[3],split[4]));
            }

            if (Boolean.parseBoolean(split[0])) {
                taskList.get(count).markAsDone();
            }
        }
    }

    private static void appendToFile(String str) throws IOException {
        FileWriter fw = new FileWriter(fileName, true);
        fw.write(str + "\n");
        fw.close();
    }

    private static void arrayToFile() throws IOException{
        FileWriter fw = new FileWriter(fileName);
        fw.write("");
        fw.close();

        for (Task task : taskList) {
            appendToFile(task.toFile());
        }
    }
}
