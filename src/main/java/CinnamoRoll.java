import java.util.ArrayList;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class CinnamoRoll {

    private ArrayList<Task> tasks;
    private static final String PATH = "src/main/Cinnamo.txt";
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private enum Users {
        MARK,
        UNMARK,
        LIST,
        DELETE,
        TODO,
        DEADLINE,
        EVENT
    }

    CinnamoRoll() {
        this.tasks = new ArrayList<>();
    }

    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    void greet() {
        System.out.println(logo);
        System.out.println("Hello! I'm CinnamoRoll!" + "\n" + "What can I do for you? \n");
    }

    void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    void load_data() throws IOException {
        try {
            BufferedReader bf = new BufferedReader(new FileReader(PATH));
            String input;
            while ((input = bf.readLine()) != null) {
                Task task;
                String[] info = input.split("|", 3);
                boolean marked = false;
                switch (info[1]) {
                    case "X":
                        marked = true;
                        break;
                    case " ":
                        marked = false;
                        break;
                    default:
                        System.out.println("Oops! No Markings Provided in Correct Format:(");
                }
                switch (info[0].toUpperCase()) {
                    case "T":
                        task = new Todos(info[2], marked);
                        this.tasks.add(task);
                        break;
                    case "D":
                        String[] deadline = info[2].split("/by");
                        task = new Deadlines(deadline[0], LocalDateTime.parse(deadline[1], this.format), marked);
                        this.tasks.add(task);
                        break;
                    case "E":
                        String[] event = info[2].split("/from | /to");
                        task = new Events(event[0], LocalDateTime.parse(event[1], this.format), LocalDateTime.parse(event[2], this.format), marked);
                        this.tasks.add(task);
                        break;
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not Found:( I will create one for you!");
        } finally {
            File f = new File(PATH);
            f.createNewFile();
        }
    }

    public void writeInto() throws IOException {
        try {
            FileWriter filewriter = new FileWriter(PATH);
            filewriter.write(this.list());
            filewriter.close();
        } catch (IOException ex) {
            System.out.println("No input provided!");
        }
    }

    private String todo(String[] instruction) throws Exception, CinnamoTodoException {
        try {
            Task task = new Todos(instruction[1]);
            this.tasks.add(task);
            this.writeInto();
            return task.added(this.tasks.size());
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new CinnamoTodoException();
        }
    }

    private String deadline(String[] instruction) throws Exception, CinnamoDeadlineException {
        try {
            String[] schedule = instruction[1].split(" /by ", 2);
            Task task = new Deadlines(schedule[0], LocalDateTime.parse(schedule[1], this.format));
            this.tasks.add(task);
            this.writeInto();
            return task.added(this.tasks.size());
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new CinnamoDeadlineException();
        }
    }

    private String event(String[] instruction) throws Exception, CinnamoEventException {
        try {
            String[] schedule = instruction[1].split(" /from | /to ");
            Task task = new Events(schedule[0], LocalDateTime.parse(schedule[1], this.format), LocalDateTime.parse(schedule[2], this.format));
            this.tasks.add(task);
            this.writeInto();
            return task.added(this.tasks.size());
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new CinnamoEventException();
        }
    }

    private String list() {
        String output = "   Here are the tasks in your list:\n";
        for (int i = 0; i < this.tasks.size(); i++) {
            output += "      " + String.valueOf(i + 1) + "." + this.tasks.get(i).toString() + "\n";
        }
        return output;
    }

    private String delete(String[] str) throws Exception, CinnamoIndexException {
        try {
            int index = Integer.parseInt(str[1]);
            Task temp = this.tasks.get(index - 1);
            this.tasks.remove(index - 1);
            this.writeInto();
            return "   Noted. I've removed the following task:\n" + "      " + temp.toString() + "\n" + "   Now, you have " +
                    String.valueOf(this.tasks.size()) + " tasks in the list";
        } catch (IndexOutOfBoundsException exception) {
            throw new CinnamoIndexException();
        }
    }

    String mark(String[] str) throws Exception, CinnamoIndexException {
        try {
            int index = Integer.parseInt(str[1]) - 1;
            this.tasks.get(index).marked();
            this.writeInto();
            return "   Nice! I've marked this task as done:\n" + "      " + this.tasks.get(index).toString();
        } catch (IndexOutOfBoundsException exception) {
            throw new CinnamoIndexException();
        }
    }

    String unmark(String[] str) throws Exception, CinnamoIndexException {
        try {
            int index = Integer.parseInt(str[1]) - 1;
            this.tasks.get(index).unmarked();
            this.writeInto();
            return "   Ok! I've marked this task as not done yet:\n" + "      " + this.tasks.get(index).toString();
        } catch (IndexOutOfBoundsException exception) {
            throw new CinnamoIndexException();
        }
    }

    void respond(String str) throws Exception, CinnamoException {
        try {
            String[] arr = str.split(" ", 2);
            String instruction = arr[0].toUpperCase();
            Users user = Users.valueOf(instruction);
            switch (user) {
                case MARK:
                    System.out.println(this.mark(arr));
                    break;

                case UNMARK:
                    System.out.println(this.unmark(arr));
                    break;

                case LIST:
                    System.out.println(this.list());
                    break;

                case DELETE:
                    System.out.println(this.delete(arr));
                    break;

                case TODO:
                    System.out.println(this.todo(arr));
                    break;

                case DEADLINE:
                    System.out.println(this.deadline(arr));
                    break;

                case EVENT:
                    System.out.println(this.event(arr));
                    break;

                default:
                    throw new CinnamoException();
            }
        } catch (CinnamoException cin) {
            System.out.println(cin.toString());
        } catch (IllegalArgumentException e) {
            CinnamoException cin = new CinnamoException();
            System.out.println(cin.toString());
        }
    }
}