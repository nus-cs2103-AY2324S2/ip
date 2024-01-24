import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    public static String line = "-----------------------------";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(line);
        System.out.println("Hello! I'm Datuk");
        System.out.println("What can I do for you today?\n");
        System.out.println(line);

        boolean finish = false;

        while(!finish) {
            while(br.ready()) {
                String[] text = br.readLine().split(" ");

                if (text[0].equals("bye")) {
                    finish = true;
                } else {
                    System.out.println(line);
                    for (String x : text) {
                        System.out.print(x);
                        System.out.print(" ");
                    }
                    System.out.println();
                    System.out.println(line);
                }
            }
        }

        System.out.println(line);
        System.out.println("Bye. See you later!");
        System.out.println(line);
    }
}
