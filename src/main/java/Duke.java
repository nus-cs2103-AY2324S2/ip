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

        ArrayList<Task> userTexts = new ArrayList<>();

        String[] terminateKeywords = {"bye", "BYE", "Bye"};
        List<String> exitProgramme = Arrays.asList(terminateKeywords);

        // Programme start
        System.out.print(greeting);

        Scanner input = new Scanner(System.in);
        String currInput = input.nextLine();

        while (!exitProgramme.contains(currInput)) {
            if (currInput.equals("list")) { // list tasks
                System.out.println("    Here are the items in your list: ");
                for (int i = 0; i < userTexts.size(); i++) {
                    String listIdx = i + 1 + ". ";
                    Task currTask = userTexts.get(i);
                    System.out.println(
                            "    " + listIdx +
                            currTask.getStatusIcon() +
                            currTask.getDescription()
                    );
                }
            } else if (currInput.contains("mark") ||
                    currInput.contains("unmark")) {     // mark tasks
                if (currInput.contains("mark")) {

                } else {

                }
            } else {                        // add tasks
                Task newTask = new Task(currInput);
                userTexts.add(newTask);
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
