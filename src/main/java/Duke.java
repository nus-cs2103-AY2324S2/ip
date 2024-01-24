import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Duke {
    public static String line = "-----------------------------";
    public static ArrayList<String> ls;
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

                if (text.equals("bye")) {
                    finish = true;
                } else if (text.equals("list")) {
                    PrintList();
                } else {
                    AddList(text);
                }
            }
        }

        System.out.println(line);
        System.out.println("Farewell!");
        System.out.println(line);
    }

    public static void PrintList() {
        System.out.println(line);
        for (int i = 0; i < ls.size(); i++) {
            System.out.println(i + 1 + ". " + ls.get(i));
        }
        System.out.println(line);
    }

    public static void AddList(String cmd) {
        ls.add(cmd);
        System.out.println(line);
        System.out.println("added: " + cmd);
        System.out.println(line);
    }
}
