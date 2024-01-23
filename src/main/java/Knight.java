import java.util.ArrayList;
import java.util.Scanner;

public class Knight {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final String logo = "    ┓┏┓  •  ┓  \n"
            + "    ┃┫ ┏┓┓┏┓┣┓╋\n"
            + "    ┛┗┛┛┗┗┗┫┛┗┗\n"
            + "           ┛\n";
    private static void speak(String s) {
        System.out.println("    " + s);
    }
    public static void main(String[] args) {


        speak("Greetings, Your Excellency! Thy humble\n"
                + logo
                + "    doth stand before thee. How may I serve thee on this day?");

        Scanner scan = new Scanner( System.in );
        String command;

        while (true) {
            command = scan.nextLine();

            if (command.equals("bye")) break;

            if (command.equals("list")) {
                if (tasks.isEmpty()) {
                    speak("Your Excellency, thy list is bereft of any tasks at this moment.");
                } else {
                    speak("Behold, the duties thou hast assigned:");
                    int i = 1;
                    for (Task t : tasks) {
                        speak(i + "." + t);
                        i++;
                    }
                }
                continue;
            }

            tasks.add(new Task(command));
            speak("added " + command);
        }

        speak("Farewell, Your Excellency! May we cross paths once more in the near future.");
    }
}
