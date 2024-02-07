import java.io.*;

public class Ui {
    private final BufferedReader br;

    public Ui(InputStream in) {
        this.br = new BufferedReader(new InputStreamReader(in));
    }

    public void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Hao Wen\n" + "What can I do for you?");
    }

    public String getUserRequest() {
        try {
            System.out.println("Enter a command:");
            return br.readLine();
        } catch (Exception e) {
            System.out.println("Error:");
            return "";
        }
    }

    public void showResultToUser(String s) {
        System.out.println(s);
    }
}
