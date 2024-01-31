import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.io.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Pattern;

public class DylanBot {

    static ArrayList<Task> tasks = new ArrayList<>();
    static String fileName = "./data/DylanBotData.txt";
    public static void main(String[] args) {
        String greeting = "Hello I am DylanBot! \nWhat can I do for you?";
        String exit = "Bye! Hope to see you again soon";
        System.out.println(greeting);

        try {
            System.out.println("Loading data from file...");
            LoadDataFromFile();
            System.out.println("Loading completed.");
        } catch (FileNotFoundException e) {
            System.out.println("Data file does not exist, created new file.");
        } catch (IOException e) {
            System.out.println("Done reading from file, no more lines");
        }

        Scanner scanner = new Scanner(System.in);
        String input;
        while (!"bye".equals((input = scanner.nextLine()))) {
            try {
                if (input.isEmpty()) {
                    throw new DylanBotException("HEY no input BETTER SAY SOMETHING");
                }
                if (input.equals("list")) {
                    if (tasks.isEmpty()) {
                        throw new DylanBotException("No tasks to list right now! Add something first la");
                    }
                    for (int i = 0; i < tasks.size(); i++) {
                        Task curr = tasks.get(i);
                        System.out.println("\t" + (i + 1) + ". " + curr.toString());
                    }
                } else if (input.startsWith("mark")) {
                    if (input.split(" ").length < 2) {
                        throw new DylanBotException("HEY no index specified for item to mark");
                    }
                    int idx = Integer.parseInt(input.split(" ")[1]);
                    if (idx > tasks.size() || idx < 0) {
                        throw new DylanBotException("HEY index requested is out of bounds");
                    }
                    tasks.get(idx - 1).completed = true;
                    System.out.println("Aight marked this task as done:\n\t"
                            + tasks.get(idx - 1).toString());
                } else if (input.startsWith("unmark")) {
                    if (input.split(" ").length < 2) {
                        throw new DylanBotException("HEY no index specified for item to mark");
                    }
                    int idx = Integer.parseInt(input.split(" ")[1]);
                    if (idx > tasks.size() || idx < 0) {
                        throw new DylanBotException("HEY index requested is out of bounds");
                    }
                    tasks.get(idx - 1).completed = false;
                    System.out.println("Sian marked this task as undone:\n\t"
                            + tasks.get(idx - 1).toString());
                } else if (input.startsWith("todo")) {
                    createTodo(input);
                } else if (input.startsWith("deadline")) {
                    createDeadline(input);
                } else if (input.startsWith("event")) {
                    createEvent(input);
                } else if (input.startsWith("delete")) {
                    deleteTask(input);
                } else {
                    throw new DylanBotException("Hello INVALID INPUT pls make it make sense");
                }
                if (tasks.isEmpty()) {
                    System.out.println("Wowza your list is empty!");
                } else {
                    System.out.println("Wow! Now you have " + tasks.size() + " tasks in your list");
                }
            } catch (DylanBotException e) {
                System.out.println(e);
            }
        }
        try {
            System.out.println("Saving data to file...");
            WriteDataToFile();
            System.out.println("Saving completed.");
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println(exit);
    }


    public static void LoadDataFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String nextLine;
        while ((nextLine = reader.readLine()) != null) {
            String[] tokens = nextLine.split(Pattern.quote(" | "));
            Task curr;
            if (tokens[0].equals("T")) {
                curr = new TodoTask("T", tokens[2]);
            } else if (tokens[0].equals("D")) {
                curr = new DeadlineTask("D", tokens[2], ConvertStringToDateTime(tokens[3]));
            } else {
                curr = new EventTask("E", tokens[2], ConvertStringToDateTime(tokens[3]),
                        ConvertStringToDateTime(tokens[4]));
            }
            curr.completed = tokens[1].equals("true");
            tasks.add(curr);
        }
    }

    public static void WriteDataToFile() throws IOException {
        File newFile = new File(fileName);
        newFile.getParentFile().mkdirs();
        newFile.createNewFile();

        FileWriter writer = new FileWriter(fileName);
        for (Task t : tasks) {
            String data = "";
            data += t.type + " | " + t.completed + " | " + t.desc;
            if (t.type.equals("D")) {
                data += " | " + ((DeadlineTask) t).deadline;
            } else if (t.type.equals("E")) {
                data += " | " + ((EventTask) t).from + " | " + ((EventTask) t).to;
            }
            writer.write(data);
            writer.write("\n");
        }
        writer.close();
    }

    public static void createTodo(String input) throws DylanBotException {
        String desc = input.substring(5);
        if (desc.isEmpty()) {
            throw new DylanBotException("HEY todo description cannot be empty!");
        }
        Task curr = new TodoTask("T", desc);
        tasks.add(curr);
        System.out.println("Roger doger, added this task: \n\t" + curr.toString());
    }

    public static LocalDateTime ConvertStringToDateTime(String input) throws DateTimeParseException{
        LocalDateTime deadline = null;
        DateTimeFormatter dFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter dtFormat  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (input.length() < 11) {
            deadline = LocalDate.parse(input).atStartOfDay();
        } else {
            deadline = LocalDateTime.parse(input);
        }
        return deadline;
    }

    public static void createDeadline(String input) throws DylanBotException, DateTimeParseException {
        String[] inputArr = input.split("/by");
        String desc = inputArr[0].substring(9).trim();
        String deadlineStr = inputArr[1].trim();
        if (desc.isEmpty()) {
            throw new DylanBotException("HEY deadline description cannot be empty!");
        }
        if (deadlineStr.isEmpty()) {
            throw new DylanBotException("HEY deadline tasks need deadlines!");
        }
        try {
            DeadlineTask curr = new DeadlineTask("D", desc, ConvertStringToDateTime(deadlineStr));
            tasks.add(curr);
            System.out.println("Roger doger, added this task: \n\t" + curr.toString());
        } catch (DateTimeParseException e) {
            System.out.println("Improper date format, TRY AGAIN!!!");
        }
    }

    public static void createEvent(String input) throws DylanBotException {
        String[] inputArr = input.split("/from|/to");
        String desc = inputArr[0].substring(6).trim();
        String fromString = inputArr[1].trim();
        String toString = inputArr[2].trim();
        if (desc.isEmpty()) {
            throw new DylanBotException("HEY event description cannot be empty!");
        }
        if (fromString.isEmpty()) {
            throw new DylanBotException("HEY event tasks need starting dates!");
        }
        if (toString.isEmpty()) {
            throw new DylanBotException("HEY event tasks need ending dates!");
        }
        try {
            EventTask curr = new EventTask("E", desc, ConvertStringToDateTime(fromString),
                    ConvertStringToDateTime(toString));            tasks.add(curr);
            System.out.println("Roger doger, added this task: \n\t" + curr.toString());
        } catch (DateTimeParseException e) {
            System.out.println("Improper date format, TRY AGAIN!!!");
        }
    }

    public static void deleteTask(String input) throws DylanBotException {
        String[] inputArr = input.split(" ");
        int idx = Integer.parseInt(inputArr[1]);
        if (idx > tasks.size() || idx < 0) {
            throw new DylanBotException("HEY index requested is out of bounds");
        }
        Task toRemove = tasks.get(idx - 1);
        tasks.remove(idx - 1);
        System.out.println("Aight removed this task:\n\t" + toRemove.toString());
    }

    public static class FastScanner {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        String next() {
            while (!st.hasMoreTokens())
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return st.nextToken();
        }
        String nextLine() {
            while (!st.hasMoreTokens())
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return st.nextToken("\n");
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        int[] readArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}


