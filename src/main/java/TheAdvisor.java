import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TheAdvisor {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String intro = "Hello, I am TheAdvisor. The one and only advisor you will ever need in your investing " +
                "journey. What can I do for you?";
        System.out.println(intro + "\n");

        while (true) {
            String toEcho = br.readLine();
            if (toEcho.equals("bye")) {
                System.out.println("    Goodbye. Thank you for using TheAdvisor chatbox and I hope that my advice has managed" +
                        "to help you in your investing journey!");
                break;
            } else {
                System.out.println("    " + toEcho);
            }
        }
    }
}
