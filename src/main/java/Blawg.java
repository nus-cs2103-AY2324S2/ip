import java.util.ArrayList;
import java.util.Scanner;

public class Blawg {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<String>(100);
        String intro = "____________________________________________________________\n" +
                " Paws what you're doing! I'm Blawg\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(intro);
        String outro = "____________________________________________________________\n" +
                " Alright, this kitty's got to go chase some shadows. See you later!\n" +
                "____________________________________________________________\n";
        while (true) {
            String userInput = sc.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println(outro);
                break;
            } else if (userInput.equalsIgnoreCase("list")){
                StringBuilder output = new StringBuilder();
                output.append("____________________________________________________________\n");
                for (int i = 0; i < list.size(); i++) {
                    output.append(i+1).append(".");
                    output.append(list.get(i)).append("\n");
                }
                output.append("____________________________________________________________\n");
                System.out.println(output);
            } else {
                System.out.println("____________________________________________________________\n" +
                        "added: " + userInput + "\n" +
                        "____________________________________________________________\n");
                list.add(userInput);
            }
        }
        sc.close();
    }
}
