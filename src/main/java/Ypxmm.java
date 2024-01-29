import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;


public class Ypxmm {

    public static ArrayList<Task> tasks = new ArrayList<Task>(); //tasks
    public static File f;
    private static void printFileContents() throws FileNotFoundException {
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }
    private static void writeToFile(String textToAdd) throws IOException, FileNotFoundException {
        FileWriter fw = new FileWriter(f);
        fw.write(textToAdd + "\n");
        fw.close();
    }
    private static void appendToFile(String textToAppend) throws IOException, FileNotFoundException {
        FileWriter fw = new FileWriter(f, true); // create a FileWriter in append mode
        fw.write(textToAppend + "\n");
        fw.close();
    }

    private static void reWrite() throws IOException, FileNotFoundException {
        FileWriter fw = new FileWriter(f);
        for (Task t : tasks) {
            fw.write(t.toWrite() + "\n");
        }
        fw.close();
    }

    private static void initToArray() throws FileNotFoundException {
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String[] line = s.nextLine().split(" \\| ");
            if (line[0].equals("T")) {
                Task task = new ToDo(line[2]);
                if (line[1].equals("1")) {
                    task.setCompleted();
                }
                tasks.add(task);
            } else if (line[0].equals("D")) {
                Task task = new Deadline(line[2], line[3]);
                if (line[1].equals("1")) {
                    task.setCompleted();
                }
                tasks.add(task);
            } else if (line[0].equals("E")) {
                String[] timing = line[3].split(" to ");
                Task task = new Event(line[2], timing[0], timing[1]);
                if (line[1].equals("1")) {
                    task.setCompleted();
                }
                tasks.add(task);
            }
        }
    }
    public static void main(String[] args) throws YpxmmException {
        Scanner sc = new Scanner(System.in);
        sayHello();
        boolean condition = true;
        f = new File("data/Ypxmm.txt");

        try {
            try {
                initToArray();
            } catch (FileNotFoundException e) {
                throw new YpxmmException("File not created yet la bro. In the ip folder, create a new folder \"data\" and a new txt file \"Ypxmm\" to proceed");
            }
        } catch (YpxmmException y) {
            System.out.println(y.getMessage());
        }

        while (condition) {
            try {
                String input = sc.nextLine();
                String[] splitInput = input.split(" ");
                EnumCommands enumc = EnumCommands.valueOf(splitInput[0].toUpperCase());
                switch (enumc) {
                    case BYE:
                        sayGoodbye();
                        condition = false;
                        break;
                    case LIST:
                        list();
                        break;
                    case MARK:
                        String[] valsMark = input.split(" ");
                        try {
                            int index = Integer.parseInt(valsMark[1]);
                            mark(index);
                        } catch (IndexOutOfBoundsException e) {
                            throw new YpxmmException("Brother, key in mark <space> then a valid number");
                        } catch (NumberFormatException n) {
                            throw new YpxmmException("You tell me now what task am I supposed to mark if you don't provide me with a number?");
                        }
                        break;
                    case UNMARK:
                        String[] valsUnmark = input.split(" ");
                        try {
                            int index = Integer.parseInt(valsUnmark[1]);
                            unmark(index);
                        } catch (IndexOutOfBoundsException e) {
                            throw new YpxmmException("Brother, key in unmark <space> then a valid number");
                        } catch (NumberFormatException n) {
                            throw new YpxmmException("You tell me now what task am I supposed to unmark if you don't provide me with a number?");
                        }
                        break;
                    case TODO:
                        addTask(input, "todo");
                        break;
                    case DEADLINE:
                        addTask(input, "deadline");
                        break;
                    case EVENT:
                        addTask(input, "event");
                        break;
                    case GETCOMMANDS:
                        getCommands();
                        break;
                    case DELETE:
                        String[] vals = input.split(" ");
                        try {
                            int index = Integer.parseInt(vals[1]);
                            delete(index);
                        } catch (IndexOutOfBoundsException e) {
                            throw new YpxmmException("Brother, key in delete <space> then a valid number");
                        } catch (NumberFormatException n) {
                            throw new YpxmmException("You tell me now what task am I supposed to delete if you don't provide me with a number?");
                        }
                        break;
                }

            } catch (YpxmmException y) {
                System.out.println(y.getMessage());
            } catch (IllegalArgumentException i) {
                System.out.println("Sorry bro, idk what that means. You try type in \"getcommands\" then see if got what u want.");
            }
        }
        sc.close();
    }
    public static void getCommands() {
        System.out.println("Take note ah, enter all time based commands are in <dd-mm-yyyy HHmm> format\n" +
                "  todo <task> - adds todo\n  deadline <task>/<by when> - adds deadline\n" +
                "  event <task>/<from when>/<to when> - adds event\n  list - lists out all tasks\n" +
                "  mark <x> - marks task x as done\n  unmark <x> - unmarks task x as undone\n" +
                "  bye - exit");
    }

    public static void sayHello() {
        System.out.println("Hello, I'm Ypxmm.\nNeed me do what for you?");
    }

    public static void sayGoodbye() {
        System.out.println("Oh you need zao alr? Okok see you next time!");
    }

    public static void list() {
        if (tasks.size() == 0) {
            System.out.println("No tasks yet la bro");
        } else {
            System.out.println("Ok wait ah, here are your tasks:");
            int count = 1;
            for (Task t : tasks) {
                System.out.println(count + ". " + t.toString());
                count++;
            }
        }
    }

    public static void mark(int index) {
        try {
            try {
                tasks.get(index - 1).markTask();
                reWrite();
            } catch (IndexOutOfBoundsException e) {
                throw new YpxmmException("Eh u seh isit? Now your list got " +
                        (tasks.size() == 0 ? "no tasks to mark." : tasks.size() +
                                " tasks, enter any number from 1 to " + tasks.size()));
            } catch (FileNotFoundException e) {
                throw new YpxmmException("File not created yet la bro. In the ip folder, create a new folder \"data\" and a new txt file \"Ypxmm\" to proceed");
            } catch (IOException e) {
                throw new YpxmmException("IOException");
            }
        } catch (YpxmmException y) {
            System.out.println(y.getMessage());
        }
    }

    public static void unmark(int index) {
        try {
            try {
                tasks.get(index - 1).unmarkTask();
                reWrite();
            } catch (IndexOutOfBoundsException e) {
                throw new YpxmmException("Eh u seh isit? Now your list got " +
                        (tasks.size() == 0 ? "no tasks to unmark." : tasks.size() +
                                " tasks, enter any number from 1 to " + tasks.size()));
            } catch (FileNotFoundException e) {
                throw new YpxmmException("File not created yet la bro. In the ip folder, create a new folder \"data\" and a new txt file \"Ypxmm\" to proceed");
            } catch (IOException e) {
                throw new YpxmmException("IOException");
            }
        } catch (YpxmmException y) {
            System.out.println(y.getMessage());
        }
    }

    public static void delete(int index) {
        try {
            try {
                String t = tasks.get(index - 1).toString();
                tasks.remove(index - 1);
                reWrite();
                System.out.println("Ok deleted liao:\n" + t + "\nNow your list got " +
                        (tasks.size() == 0 ? "no tasks." : tasks.size() + " tasks left."));
            } catch (IndexOutOfBoundsException e) {
                throw new YpxmmException("Eh u seh isit? Now your list got " +
                        (tasks.size() == 0 ? "no tasks to delete." : tasks.size() +
                                " tasks, enter any number from 1 to " + tasks.size()));
            } catch (FileNotFoundException e) {
                throw new YpxmmException("File not created yet la bro. In the ip folder, create a new folder \"data\" and a new txt file \"Ypxmm\" to proceed");
            } catch (IOException e) {
                throw new YpxmmException("IOException");
            }
        } catch (YpxmmException y) {
            System.out.println(y.getMessage());
        }
    }

    public static void addTask(String input, String type) {
        try {
            if (type.equals("todo")) {
                try {
                    String[] info = input.split("todo ");
                    if (info[1].isBlank()) {
                        throw new YpxmmException("Help la, can just tell me what is the name of your task anot?");
                    }
                    Task t = new ToDo(info[1].trim());
                    tasks.add(t);
                    appendToFile(t.toWrite());
                    System.out.println("Ok I help you add this one liao:\n" + t.toString() +
                            "\nNow your list got " + tasks.size() + ((tasks.size() == 1) ? " task." : " tasks."));
                } catch (IndexOutOfBoundsException e) {
                    throw new YpxmmException("You trying to test my patience ah? Type \"get commands\" if u blur and dunno how to use me properly.");
                } catch (FileNotFoundException e) {
                    throw new YpxmmException("File not created yet la bro. In the ip folder, create a new folder \"data\" and a new txt file \"Ypxmm\" to proceed");
                } catch (IOException e) {
                    throw new YpxmmException("IOException");
                }
            } else if (type.equals("deadline")) {
                try {
                    String[] info = input.split("/");
                    if (info[0].split("deadline ")[1].isBlank() || info[1].isBlank()) {
                        throw new YpxmmException("Help la, can just tell me what is the name of your task anot?");
                    }
                    Task t = new Deadline(info[0].substring(9).trim(), info[1].trim());
                    tasks.add(t);
                    appendToFile(t.toWrite());
                    System.out.println("Ok I help you add this one liao:\n" + t.toString() +
                            "\nNow your list got " + tasks.size() + ((tasks.size() == 1) ? " task." : " tasks."));
                } catch (IndexOutOfBoundsException e) {
                    throw new YpxmmException("You trying to test my patience ah? Check that u got key in the deadline lehhh\n" +
                            "Type \"get commands\" if u blur and dunno how to use me properly.");
                } catch (FileNotFoundException e) {
                    throw new YpxmmException("File not created yet la bro. In the ip folder, create a new folder \"data\" and a new txt file \"Ypxmm\" to proceed");
                } catch (IOException e) {
                    throw new YpxmmException("IOException");
                }
            } else {
                try {
                    String[] info = input.split("/");
                    if (info[0].split("event ")[1].isBlank() || info[1].isBlank() || info[2].isBlank()) {
                        throw new YpxmmException("Help la, can just tell me what is the name of your task anot?");
                    }
                    Task t = new Event(info[0].substring(6).trim(), info[1].trim(), info[2].trim());
                    tasks.add(t);
                    appendToFile(t.toWrite());
                    System.out.println("Ok I help you add this one liao:\n" + t.toString() +
                            "\nNow your list got " + tasks.size() + ((tasks.size() == 1) ? " task." : " tasks."));
                } catch (IndexOutOfBoundsException e) {
                    throw new YpxmmException("Eh brother last warning ah. Check that u got key in the start and end time\n" +
                            "Type \"get commands\" if u blur and dunno how to use me properly.");
                } catch (FileNotFoundException e) {
                    throw new YpxmmException("File not created yet la bro. In the ip folder, create a new folder \"data\" and a new txt file \"Ypxmm\" to proceed");
                } catch (IOException e) {
                    throw new YpxmmException("IOException");
                }
            }
        } catch (YpxmmException y) {
            System.out.println(y.getMessage());
        }
    }
}
