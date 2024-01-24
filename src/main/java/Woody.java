import java.util.*;
import java.util.Random;
public class Woody {
    public static void main(String[] args) {
        String line = "____________________________________________________________\n";
        String[] catchphrases = {
                "Yee-haw!",
                "So long, partner.",
                "To infinity and beyond!",
                "Reach for the sky!"
        };
        Random random = new Random();

        String greeting =
                line +
                " Hello! I'm Woody\n" +
                " What can I do for you?\n" +
                line;

        System.out.print(greeting);

        Scanner input = new Scanner(System.in);
        String currInput = input.nextLine();

        String[] terminateKeywords = {"bye", "BYE", "Bye"};
        List<String> exitProgramme = Arrays.asList(terminateKeywords);

        while (!exitProgramme.contains(currInput)) {
            System.out.println(currInput  + "\n" + line);
            currInput = input.nextLine();
        }

        System.out.println(
                "Bye! " + catchphrases[random.nextInt(catchphrases.length)] + "\n" +
                        line);
    }
}
