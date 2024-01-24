import java.util.*;
import java.util.Random;
public class Duke {
    public static void main(String[] args) {
        String line = "____________________________________________________________\n";
        Random random = new Random();

        String[] catchphrases = {
                "Yee-haw!",
                "So long, partner.",
                "To infinity and beyond!",
                "Reach for the sky!"
        };

        String greeting =
                line +
                "   Hello! I'm Woody\n" +
                "   What can I do for you?\n" +
                line;

        ArrayList<String> userTexts = new ArrayList<>();

        String[] terminateKeywords = {"bye", "BYE", "Bye"};
        List<String> exitProgramme = Arrays.asList(terminateKeywords);

        // Programme start
        System.out.print(greeting);

        Scanner input = new Scanner(System.in);
        String currInput = input.nextLine();

        while (!exitProgramme.contains(currInput)) {

            if (currInput.equals("list")) { // request for list
                for (int i = 0; i < userTexts.size(); i++) {
                    String listIdx = i + 1 + ". ";
                    System.out.println("    " + listIdx + userTexts.get(i));
                }
            } else {                        // tasks
                userTexts.add(currInput);
                System.out.println("    added: " + currInput);
            }
            System.out.println(line);
            currInput = input.nextLine();
        }

        System.out.println(
                "   Bye! " + catchphrases[random.nextInt(catchphrases.length)] + "\n" +
                        line);
    }
}
