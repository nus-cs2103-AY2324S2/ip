import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Duke {
    public static String line = "-----------------------------";
    public static ArrayList<Task> ls;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ls = new ArrayList<>();

        //load from file
        ls = new ArrayList<>(Storage.loadTasks());

        System.out.println(line);
        System.out.println("Greetings friend! I am Datuk");
        System.out.println("How can I serve you today? ^_^' \n");
        System.out.println(line);

        boolean finish = false;

        while (!finish) {
            while (br.ready()) {
                String text = br.readLine();
                String[] split = text.split(" ");

                try {
                    if (text.equals("bye")) {
                        finish = true;
                    } else if (text.equals("list")) { //(split[0].equals("list));
                        printList();
                    } else if (split[0].equals("mark") || split[0].equals("unmark")) {
                        marked(split[0], Integer.parseInt(split[1]));
                    } else if (split[0].equals("todo") || split[0].equals("deadline") || split[0].equals("event")){
                        addList(text);
                    } else if (split[0].equals("delete")) {
                            deleteItem(text);
                    } else {
                        throw new DukeException("Your input is invalid!");
                    }
                } catch (DukeException de) {
                    System.out.println(de.toString());
                }
            }
        }
        
        Storage.saveTasks(ls);

        System.out.println(line);
        System.out.println("Farewell!");
        System.out.println(line);
    }

    private static void printList() {
        System.out.println(line);
        System.out.println("These are all your tasks:");
        if (ls.isEmpty()) System.out.println("\tOh noes! The list is empty! :(");

        for (int i = 0; i < ls.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + ls.get(i));
        }
        System.out.println(line);
    }

    private static void addList(String cmd) throws DukeException {
        String[] split;
        String desc, from, to, by;
        split = cmd.split(" ", 2);

        Task t;

        if (split[0].equals("todo")) {
            if (split.length != 2) {
                throw new DukeException("Missing params for todo!");
            }
            t = new Todo(split[1]);
            ls.add(t);
        } else if (split[0].equals("deadline")) {
            if (split.length != 2) {
                throw new DukeException("Missing params for deadline!");
            }

            String[] temp = split[1].split("/by");

            if (temp.length != 2) {
                throw new DukeException("Missing deadline for deadline!");
            }

            desc = temp[0];
            by = temp[1];
            t = new Deadline(desc, by);
            ls.add(t);
        } else {
            if (split.length != 2) {
                throw new DukeException("Missing params for event!");
            }

            String[] temp = split[1].split("/from");

            if (temp.length != 2) {
                throw new DukeException("Missing [from] and [to] for event!");
            }

            desc = split[1].split("/from")[0];

            String[] temp2 = split[1].split("/from")[1].split("/to");

            if (temp2.length != 2) {
                throw new DukeException("Missing [to] for event!");
            }
            from = temp2[0];
            to = temp2[1];
            t = new Event(desc, from, to);
            ls.add(t);
        }


        System.out.println(line);
        System.out.println("Understood. Added the following:");
        System.out.println("\t " + t);
        System.out.println("You have " + ls.size() + " remaining tasks.");
        System.out.println(line);
    }

    private static void deleteItem(String s) throws DukeException {
        String[] str = s.split(" ");

        if (str.length < 2) {
            throw new DukeException("Missing params for delete!");
        } else if (str.length > 2) {
            throw new DukeException("Too many params for delete!");
        }

        int index = Integer.parseInt(str[1]) - 1;

        try {
            ls.get(index);
        } catch (IndexOutOfBoundsException  e) {
            throw new DukeException("Index does not exist!");
        }

        System.out.println(line);
        System.out.println("Removed the following: ");
        System.out.println("\t" + ls.get(index));
        ls.remove(index);
        System.out.println(ls.size() + " tasks remaining.");
        System.out.println(line);
    }

    private static void marked(String cmd, int index) {
        if (index > ls.size()) {
            System.out.println(line);
            System.out.println("Index out of bounds.");
            System.out.println(line);
            return;
        }

        index--;
        System.out.println(line);

        if (cmd.equals("mark")) {
            System.out.println("I have set this task < " + ls.get(index).getDesc() + "> as completed." );
            ls.get(index).setCheck(true);
        } else {
            System.out.println("I have set this task < " + ls.get(index).getDesc() + "> as incomplete." );
            ls.get(index).setCheck(false);
        }

        System.out.println(line);
    }

}

