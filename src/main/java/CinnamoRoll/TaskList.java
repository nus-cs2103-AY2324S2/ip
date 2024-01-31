package CinnamoRoll;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class TaskList {
    private final ArrayList<Task> tasks;
    private final String PATH;
    private final Parser parser = new Parser();

    private enum Users {
        MARK,
        UNMARK,
        LIST,
        DELETE,
        TODO,
        DEADLINE,
        EVENT
    }

    TaskList(String path) {
        this.tasks = new ArrayList<Task>();
        this.PATH = path;
    }

    TaskList(ArrayList<Task> tasks, String path) {
        this.tasks = tasks;
        this.PATH = path;
    }

    public void writeInto() throws IOException {
        try {
            FileWriter filewriter = new FileWriter(this.PATH);
            filewriter.write(this.list());
            filewriter.close();
        } catch (IOException ex) {
            System.out.println("No input provided!");
        }
    }

    private String execute(String[] instruction) throws IOException, CinnamoException {
        try {
            Task task = this.parser.parse_tasks(instruction);
            this.tasks.add(task);
            this.writeInto();
            return task.added(this.tasks.size());
        } catch (ArrayIndexOutOfBoundsException exception) {
            if (instruction[0].equals("TODO")) {
                throw new CinnamoTodoException();
            } else if (instruction[0].equals("DEADLINE")) {
                throw new CinnamoDeadlineException();
            } else {
                throw new CinnamoEventException();
            }
        }
    }

    private String list() {
        String output = "Here are the tasks in your list:\n";
        for (int i = 0; i < this.tasks.size(); i++) {
            output += String.valueOf(i + 1) + "." + this.tasks.get(i).toString();
            if (i < this.tasks.size() - 1) {
                output += "\n";
            }
        }
        return output;
    }

    private String delete(String[] str) throws Exception, CinnamoIndexException {
        try {
            int index = Integer.parseInt(str[1]);
            Task temp = this.tasks.get(index - 1);
            this.tasks.remove(index - 1);
            this.writeInto();
            return String.format("Noted. I've removed the following task:%n   %s%n" +
                    "Now, you have %d tasks in the list", temp.toString(), this.tasks.size());
        } catch (IndexOutOfBoundsException exception) {
            throw new CinnamoIndexException();
        }
    }

    String mark(String[] str) throws Exception, CinnamoIndexException {
        try {
            int index = Integer.parseInt(str[1]) - 1;
            this.tasks.get(index).marked();
            this.writeInto();
            return String.format("Nice! I've marked this task as done:%n   %s",
                    this.tasks.get(index).toString());

        } catch (IndexOutOfBoundsException exception) {
            throw new CinnamoIndexException();
        }
    }

    String unmark(String[] str) throws Exception, CinnamoIndexException {
        try {
            int index = Integer.parseInt(str[1]) - 1;
            this.tasks.get(index).unmarked();
            this.writeInto();
            return String.format("Ok! I've marked this task as not done yet:%n      %s",
                    this.tasks.get(index).toString());

        } catch (IndexOutOfBoundsException exception) {
            throw new CinnamoIndexException();
        }
    }

    void respond(String str) throws Exception, CinnamoException {
        try {
            Parser parser = new Parser();
            String[] arr = parser.parse(str);
            switch (TaskList.Users.valueOf(arr[0])) {
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
                    System.out.println(this.execute(arr));
                    break;

                case DEADLINE:
                    System.out.println(this.execute(arr));
                    break;

                case EVENT:
                    System.out.println(this.execute(arr));
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
