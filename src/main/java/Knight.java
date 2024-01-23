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

    private static Task locateTask(String s) {
        int index;
        Task task;
        try {
            index = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            speak("Take heed, for thou shouldst reference the task thou wishest to alter by its index.");
            throw new TaskNotFoundException();
        }
        try {
            task = tasks.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            speak("I regret to inform thee, Your Excellency, that thou lackest a task bearing this index in thy list.");
            throw new TaskNotFoundException();
        }
        return task;
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
                    speak("Your Excellency, thy list remaineth free of tasks at this present moment.");
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

            if (command.startsWith("mark ")) {
                Task task;
                try {
                    task = locateTask(command.substring(5));
                } catch (TaskNotFoundException e) {
                    continue;
                }

                task.mark();
                speak("Well met! This task hath been marked as fulfilled:\n    " + task);
                continue;
            }

            if (command.startsWith("unmark ")) {
                Task task;
                try {
                    task = locateTask(command.substring(7));
                } catch (TaskNotFoundException e) {
                    continue;
                }

                task.unmark();
                speak("Verily, I have marked this task as not yet done:\n    " + task);
                continue;
            }

            tasks.add(new Task(command));
            speak("added " + command);
        }

        speak("Farewell, Your Excellency! May we cross paths once more in the near future.");
    }
}
