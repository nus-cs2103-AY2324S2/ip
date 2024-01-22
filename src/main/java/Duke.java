import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Duke {
    public static final String DIVIDER = "────────────────────────────────────────────────────────────";
    public static final String GREETING = "Hello! I'm Seiki\nHow may I assist you today?";
    public static final String FAREWELL = "Goodbye! If you ever need assistance in the future, don't hesitate to reach out. Take care!";
    public static final String LOGO = "  _____      _  _     _\n"
                                    + " / ____|    (_)| | _ (_)\n"
                                    + "| (___  ___  _ | |/ / _\n"
                                    + " \\___ \\/ _ \\| ||   / | |\n"
                                    + " ____) | __/| || | \\ | |\n"
                                    + "|_____/\\___||_||_|\\_\\|_|\n";
    public static ArrayList<String> textList = new ArrayList<>(100);

    public static void start() {
        System.out.println(DIVIDER);
        System.out.println(LOGO);
        System.out.println(GREETING);
        System.out.println(DIVIDER);
    }

    public static void end() {
        System.out.println(DIVIDER);
        System.out.println(FAREWELL);
        System.out.println(DIVIDER);
    }

    public static void list() {
        System.out.println(DIVIDER);
        System.out.println("Here are the text(s) you have stored:");
        for (int i = 0; i < textList.size(); i++) {
            int textNum = i + 1;
            String textString = textNum + ". " + textList.get(i);
            System.out.println(textString);
        }
        System.out.println(DIVIDER);
    }

    public static void storeText(String text) {
        System.out.println(DIVIDER);
        textList.add(text);
        System.out.println("The following text has been stored.");
        System.out.println("→ '" + text + "'");
        System.out.println(DIVIDER);
    }

    public static void main(String[] args) throws IOException {
        start();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            String input = br.readLine();
            if (input.equalsIgnoreCase("bye")) {
                end();
                break;
            } else if (input.equalsIgnoreCase("list")) {
                list();
            } else {
                storeText(input);
            }
        }
    }
}
