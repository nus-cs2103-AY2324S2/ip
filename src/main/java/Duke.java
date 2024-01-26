import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private enum InputType {
        LIST,
        MARK,
        UNMARK,
        DELETE,
        BYE,
        TODO,
        DEADLINE,
        EVENT,
        UNKNOWN
    }

    public static void main(String[] args) {
        String logo = "\t  ____   __  _  _  __    ___  ___  ____  ___  ____\n\t"
                + " (_  _) /__\\( \\/ )/__\\  / __)/ __)(_  _)/ __)(_  _)\n\t"
                + " .-_)( /(__)\\\\  //(__)\\ \\__ \\\\__ \\ _)(_ \\__ \\  )(\n\t"
                + "\\____)(__)(__)\\/(__)(__)(___/(___/(____)(___/ (__)\n";

        String line = "\t________________________________________________________________";

        Scanner read = new Scanner(System.in);

        ArrayList<Task> list = null;
        try {
            list = readFromFile();
        } catch (FileNotFoundException e) {
            list = new ArrayList<Task>();
        }

        System.out.println(line + "\n" + logo + "\n\t Hello! I'm JavAssist ૮ฅ・ﻌ・აฅ");
        System.out.println("\t What can I do for you?\n" + line);

        String input, lowerInput;

        boolean flag = true;

        while (flag) {
            System.out.println();
            input = read.nextLine();
            lowerInput = input.trim().toLowerCase();
            InputType type = getCommandType(lowerInput);
            System.out.println(line);

            switch (type) {
            case LIST:
                print(list);
                System.out.println(line);
                break;

            case MARK:
                if (lowerInput.matches("mark\\s\\d+")) {
                    mark(input, true, list);
                } else {
                    System.out.println("\t OOPS!!! Your mark instruction is unclear ૮(˶˃ᆺ˂˶)");
                    System.out.println("\t Try 'mark [task number to mark as done]'.");
                }
                try {
                    writeToFile(list);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(line);
                break;

            case UNMARK:
                if (lowerInput.matches("unmark\\s\\d+")) {
                    mark(input, false, list);
                } else {
                    System.out.println("\t OOPS!!! Your unmark instruction is unclear ૮(˶˃ᆺ˂˶)");
                    System.out.println("\t Try 'unmark [task number to mark as not done]'.");
                }
                try {
                    writeToFile(list);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(line);
                break;

            case DELETE:
                if (lowerInput.matches("delete\\s\\d+")) {
                    delete(input, list);
                } else {
                    System.out.println("\t OOPS!!! Your delete instruction is unclear ૮₍ ´ ꒳ `₎ა");
                    System.out.println("\t Try 'delete [task number to be deleted]'.");
                }
                try {
                    writeToFile(list);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(line);
                break;

            case BYE:
                flag = false;
                break;

            case TODO:
                if (lowerInput.matches("todo\\s.+")) {
                    Task t = new Todo(input.substring(5));
                    add(t, list);
                } else {
                    System.out.println("\t OOPS!!! The description of a todo cannot be empty ૮₍´˶• . • ⑅ ₎ა");
                    System.out.println("\t Try 'todo [task description]'.");
                }
                try {
                    writeToFile(list);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(line);
                break;

            case DEADLINE:
                if (lowerInput.matches("deadline\\s.+\\s/by\\s.+")) {
                    String[] task = input.split("/by");
                    Task t = new Deadline(task[0].substring(9).trim(), task[1].trim());
                    add(t, list);
                } else {
                    System.out.println("\t OOPS!!! The description and due of a deadline cannot be empty ૮₍´˶• . • ⑅ ₎ა");
                    System.out.println("\t Try 'deadline [task description] /by [date/time]'.");
                }
                try {
                    writeToFile(list);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(line);
                break;

            case EVENT:
                if (lowerInput.matches("event\\s.+\\s/from\\s.+\\s/to\\s.+")) {
                    String[] task = input.split("/from");
                    String[] time = task[1].split("/to");
                    Task t = new Event(task[0].substring(6).trim(), time[0].trim(), time[1].trim());
                    add(t, list);
                } else {
                    System.out.println("\t OOPS!!! The description, start and end time of an event cannot be empty ૮₍´˶• . • ⑅ ₎ა");
                    System.out.println("\t Try 'event [task description] /from [start date/time] /to [end date/time]'.");
                }
                try {
                    writeToFile(list);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(line);
                break;

            case UNKNOWN:
                System.out.println("\t OOPS!!! Sorry I don't know what that means ૮₍ ´• ˕ •` ₎ა");
                System.out.println("\t Try keywords: todo, deadline, event, list, mark, unmark, delete.");
                System.out.println(line);
                break;
            }

        }

        System.out.println("\t Bye. Hope to see you again soon! ʕ •ɷ•ʔฅ\n" + line);
    }

    private static InputType getCommandType(String input) {

        if (input.equals("list")) {
            return InputType.LIST;
        } else if (input.startsWith("mark")) {
            return InputType.MARK;
        } else if (input.startsWith("unmark")) {
            return InputType.UNMARK;
        } else if (input.startsWith("delete")) {
            return InputType.DELETE;
        } else if (input.equals("bye")) {
            return InputType.BYE;
        } else if (input.startsWith("todo")) {
            return InputType.TODO;
        } else if (input.startsWith("deadline")) {
            return InputType.DEADLINE;
        } else if (input.startsWith("event")) {
            return InputType.EVENT;
        } else {
            return InputType.UNKNOWN;
        }
    }

    private static void print(ArrayList<Task> list) {
        if (list.isEmpty()) {
            System.out.println("\t OOPS!!! No task in list ૮ ´• ﻌ ´• ა");
            System.out.println("\t You may add task with keywords: todo, deadline, event.");
        } else {
            System.out.println("\t Here are the tasks in your list ૮ ˙Ⱉ˙ ა :");
            int count = 1;
            for (Task t : list) {
                System.out.println("\t\t " + count + "." + t.printTask());
                count++;
            }
        }
    }

    private static void add(Task t, ArrayList<Task> list) {
        list.add(t);
        int size = list.size();
        System.out.println("\t ᨐฅ Got it! I've added this task:\n\t\t " + t.printTask());
        System.out.println("\t Now you have " + size + (size > 1 ? " tasks" : " task") + " in the list.");
    }

    private static void delete(String input, ArrayList<Task> list) {
        String[] s = input.split("\\s");
        int num = Integer.parseInt(s[1]);
        if (num <= list.size() && num >= 1) {
            Task t = list.remove(num - 1);
            System.out.println("\t Noted ૮ ˶ᵔ ᵕ ᵔ˶ ა I've removed this task:");
            System.out.println("\t\t " + t.printTask());
            System.out.println("\t Now you have " + list.size() + (list.size() > 1 ? " tasks" : " task") + " in the list.");
        } else {
            System.out.println("\t OOPS!!! Task (" + num + ") not found ૮₍ ˶0_0˶ ₎ა");
            print(list);
        }
    }
    private static void mark(String input, boolean done, ArrayList<Task> list) {
        String[] s = input.split("\\s");
        int num = Integer.parseInt(s[1]);
        if (num <= list.size() && num >= 1) {
            Task t = list.get(num - 1);
            if (!done) {
                t.undo();
                System.out.println("\t Ok, ૮₍ ˶•⤙•˶ ₎ა I've marked this task as not done yet:");
            } else {
                t.done();
                System.out.println("\t Nice! ૮₍ ˶ᵔ ᵕ ᵔ˶ ₎ა I've marked this task as done:");
            }
            System.out.println("\t\t " + t.printTask());
        } else {
            System.out.println("\t OOPS!!! Task (" + num + ") not found ૮₍ ˶0_0˶ ₎ა");
            print(list);
        }
    }

    private static void writeToFile(ArrayList<Task> list) throws IOException {
        File f = new File("./data/Duke.txt");
        File parent = new File("./data");
        if (!parent.exists()) {
            parent.mkdirs();
        }
        FileWriter fw = new FileWriter(f);
        String data = write(list);
        fw.write(data);
        fw.close();
    }

    private static String write(ArrayList<Task> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).toString());
            if (i < list.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    private static ArrayList<Task> readFromFile() throws FileNotFoundException {
        ArrayList<Task> list = new ArrayList<Task>();
        File f = new File("./data/Duke.txt");
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            list.add(read(s.nextLine()));
        }
        return list;
    }

    private static Task read(String s) {
        String[] cols = s.split(" \\| ");
        Task t = null;
        if (cols.length == 3) {
            t = new Todo(cols[2]);

        } else if (cols.length == 4) {
            t = new Deadline(cols[2], cols[3]);
        } else if (cols.length == 5) {
            t = new Event(cols[2], cols[3], cols[4]);
        }
        if (cols[1].equals("1")) {
            t.done();
        } else {
            t.undo();
        }

        return t;
    }
}
