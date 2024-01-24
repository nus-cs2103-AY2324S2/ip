import java.util.*;
import java.util.Random;
public class Woody {
    public static void main(String[] args) {
        String line = "____________________________________________________________\n";
        String[] catchphrases = {};
        String greeting =
                line +
                " Hello! I'm Woody\n" +
                " What can I do for you?\n" +
                line;

        System.out.print(greeting);

        Scanner input = new Scanner(System.in);
        String currInput = input.nextLine() + "\n";

        String[] terminateKeywords = {"bye", "BYE", "Bye"};
        List<String> exitProgramme = Arrays.asList(terminateKeywords);

        while (!exitProgramme.contains(currInput)) {
            System.out.println(currInput + line);
            currInput = input.nextLine();
        }

        System.out.println("Bye! \n" + line);
    }
}
