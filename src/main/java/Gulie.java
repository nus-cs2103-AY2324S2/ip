import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Gulie {
    private final static String name = "Güliedistodiez";
    private final static String line = "____________________________________________________________";
    private ArrayList<Task> taskList;
    private final static String savepath = "./data/Gulie.txt";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd'/'MM'/'yyyy HH':'mm");

    public Gulie() {
        System.out.println(line);
        this.greet();
        this.taskList = new ArrayList<>();
        this.load();
        System.out.println(line);
    }

    public boolean input(String inp) {
        System.out.println(line);
        try {
            if (inp.indexOf("\t") != -1) {
                throw new GulieException("Invalid input. The use of tabs are not allowed.");
            }
            switch (inp.split(" ")[0]) {
            case "bye": {
                this.exit();
                System.out.println(line);
                return false;
            } case "list": {
                this.list();
                break;
            } case "mark": {
                this.mark(Gulie.getArgument(inp, "mark", "index"));
                break;
            } case "unmark": {
                this.unmark(Gulie.getArgument(inp, "unmark", "index"));
                break;
            } case "todo": {
                this.store(new ToDo(Gulie.getArgument(inp, "todo", "name")));
                break;
            } case "deadline": {
                String name = Gulie.getArgument(inp, "deadline", "name");
                String by = Gulie.getArgument(inp, "/by");
                this.store(new Deadline(name, by));
                break;
            } case "event": {
                String name = Gulie.getArgument(inp, "event", "name");
                String from = Gulie.getArgument(inp, "/from");
                String to = Gulie.getArgument(inp, "/to");
                this.store(new Event(name, from, to));
                break;
            } case "delete": {
                this.delete(Gulie.getArgument(inp, "delete", "index"));
                break;
            } default:
                throw new GulieException("Apologies. I do not understand.");
            }
        } catch (GulieException e){
            System.out.println(e.getMessage());
        }
        System.out.println(line);
        return true;
    }

    private static String getArgument(String inp, String arg, String argname) throws GulieException {
        arg = " " + arg + " ";
        inp = " " + inp;
        int i = inp.indexOf(arg);
        if (i == -1)
            throw new GulieException("You must provide the argument '" + argname + "'");
        String str = inp.substring(i + arg.length());
        if (str.indexOf(" /") == -1) {
            return str;
        } else {
            return str.substring(0, str.indexOf(" /"));
        }
    }

    private static String getArgument(String inp, String arg) throws GulieException {
        return Gulie.getArgument(inp, arg, arg.substring(1));
    }

    private void greet() {
        System.out.println(String.format(" Greetings. I am %s.\n What can I do for you?", name));
    }

    private void exit() {
        this.save();
        System.out.println(" Goodbye.");
    }

    private void list() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(String.format(" %d. %s", i + 1, taskList.get(i)));
        }
    }

    private void store(Task task, boolean silent) {
        this.taskList.add(task);
        if (!silent) {
            System.out.println(" Understood. I have added this task:\n   " + task);
            System.out.println(String.format("You now have %d tasks in the list", taskList.size()));
        }
    }

    private void store(Task task) {
        this.store(task, false);
    }

    private void mark(String str) throws GulieException {
        try {
            int i = Integer.parseInt(str);
            if (i > taskList.size() || i < 0)
                throw new GulieException("Invalid index: " + i);
            Task task = taskList.get(i - 1);
            task.setMark(true);
            System.out.println(" I have marked this task as completed:\n   " + task);
        } catch (NumberFormatException e) {
            throw new GulieException("Argument must in integer: " + str);
        }
    }

    private void unmark(String str) throws GulieException {
        try {
            int i = Integer.parseInt(str);
            if (i > taskList.size() || i < 0)
                throw new GulieException("Invalid index: " + i);
            Task task = taskList.get(i - 1);
            task.setMark(false);
            System.out.println(" I have marked this task as incomplete:\n   " + task);
        } catch (NumberFormatException e) {
            throw new GulieException("Argument must in integer: " + str);
        }
    }

    private void delete(String str) throws GulieException {
        try {
            int i = Integer.parseInt(str);
            if (i > taskList.size() || i < 0) {
                throw new GulieException("Invalid index: " + i);
            }
            Task task = taskList.get(i - 1);
            taskList.remove(i - 1);
            System.out.println(" I have removed this task:\n   " + task);
            System.out.println(String.format(" You now have %d tasks in the list", taskList.size()));
        } catch (NumberFormatException e) {
            throw new GulieException("Argument must in integer: " + str);
        }
    }

    private void save() {
        try {
            File save = new File(savepath);
            save.mkdirs();
            save.delete();
            save.createNewFile();
            FileWriter fw = new FileWriter(save);
            for (Task t: taskList) {
                fw.write(t.toSaveString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void load() {
        try {
            File save = new File(savepath);
            if (!save.exists()) {
                return;
            }
            Scanner scanner = new Scanner(save);
            while (scanner.hasNextLine()) {
                try {
                    this.store(Task.fromSaveString(scanner.nextLine()), true);
                } catch (GulieException e) {
                    System.out.println(e.getMessage());
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error loading: " + e.getMessage());
            e.printStackTrace();
        }
    }

    static DateTimeFormatter getDateTimeFormatter() {
        return DATE_TIME_FORMATTER;
    }

    public static void main(String[] args) {
        Gulie gulie = new Gulie();
        Scanner scanner = new Scanner(System.in);
        while (gulie.input(scanner.nextLine()));
    }
}
