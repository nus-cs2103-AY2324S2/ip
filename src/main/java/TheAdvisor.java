import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TheAdvisor {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String intro = "Hello, I am TheAdvisor. The one and only advisor you will ever need in your investing " +
                "journey. What can I do for you?";
        System.out.println(intro + "\n");
        ArrayList<String> output = new ArrayList<>();

        while (true) {
            String toAdd = br.readLine();
            if (toAdd.equals("bye")) {
                System.out.println("    Goodbye. Thank you for using TheAdvisor chatbox and I hope that my advice has managed" +
                        "to help you in your investing journey!");
                break;
            } else if (toAdd.equals("list")){
                int counter = 1;
                for (int i = 0; i < output.size(); i++) {
                    System.out.println("    " + counter + ". " + output.get(i));
                    counter++;
                }
            } else {
                output.add(toAdd);
                System.out.println("    added: " + toAdd);
            }
        }
    }
}
