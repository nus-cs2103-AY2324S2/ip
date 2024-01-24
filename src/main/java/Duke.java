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
        System.out.println("How can I serve you today?\n");
        System.out.println(line);

        boolean finish = false;

        while (!finish) {
            while (br.ready()) {
                String text = br.readLine();
                String[] split = text.split(" ");

                if (text.equals("bye")) {
                    finish = true;
                } else if (text.equals("list")) {
                    printList();
                } else if (split[0].equals("mark") || split[0].equals("unmark")) {
                    marked(split[0], Integer.parseInt(split[1]));
                } else {
                    addList(text);
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
        ls.add(new Task(cmd));
        System.out.println(line);
        System.out.println("added: " + cmd);
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
            System.out.println("I have set this task < " + ls.get(index).getDesc() + " > as completed." );

            ls.get(index).setCheck(true);
        } else {
            System.out.println("I have set this task < " + ls.get(index).getDesc() + " > as incomplete." );
            ls.get(index).setCheck(false);
        }

        System.out.println(line);
    }

}
