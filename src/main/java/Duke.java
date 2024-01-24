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

        System.out.println(line);
        System.out.println("Greetings friend! I am Datuk");
        System.out.println("How can I serve you today? ^_^' \n");
        System.out.println(line);

        boolean finish = false;

        while (!finish) {
            while (br.ready()) {
                String text = br.readLine();
                String[] split = text.split(" ");

                if (text.equals("bye")) {
                    finish = true;
                } else if (text.equals("list")) { //(split[0].equals("list));
                    printList();
                } else if (split[0].equals("mark") || split[0].equals("unmark")) {
                    marked(split[0], Integer.parseInt(split[1]));
                } else if (split[0].equals("todo") || split[0].equals("deadline") || split[0].equals("event")){
                    addList(text);
                } else {
                    System.out.println(line);
                    System.out.println("Incorrect input");
                    System.out.println(line);
                }
            }
        }

        System.out.println(line);
        System.out.println("Farewell!");
        System.out.println(line);
    }

    public static void printList() {
        System.out.println(line);
        for (int i = 0; i < ls.size(); i++) {
            System.out.println(i + 1 + ". " + ls.get(i));
        }
        System.out.println(line);
    }

    public static void addList(String cmd) {
        String[] split = cmd.split(" ",2);
        Task t;

        //error check for missing params;

        if (split[0].equals("todo")) {
            t = new Todo(split[1]);
            ls.add(t);
        } else if (split[0].equals("deadline")){
            t = new Deadline(split[1]);
            ls.add(t);
        } else {
            t = new Event(split[1]);
            ls.add(t);
        }


        System.out.println(line);
        System.out.println("Understood. Added the following:");
        System.out.println("\t " + t);
        System.out.println("You have " + ls.size() + " remaining tasks.");
        System.out.println(line);
    }

    public static void marked(String cmd, int index) {
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
