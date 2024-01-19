import java.time.format.DateTimeFormatter;
import java.time.*;
import java.io.*;
import java.util.*;

public class Nicole {
    private static final String botName = "Nicole";
    private static final List<String> taskList = new ArrayList<>();
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static String greet() {
        DateTimeFormatter digitalTime = DateTimeFormatter.ofPattern("dd/MM/yyyy | HH:mm:ss");
        DateTimeFormatter parseHour = DateTimeFormatter.ofPattern("HH");
        LocalDateTime now = LocalDateTime.now();
        String salutation = "Good ";
        int currentHour = Integer.parseInt(parseHour.format(now));
        if (currentHour < 12 && currentHour >= 5) {
            salutation += "morning!";
        } else if (currentHour >= 12 && currentHour <= 5) {
            salutation += "afternoon!";
        } else {
            salutation += "evening!";
        }
        return "Welcome to Nicole 1.0. New conversation at " + digitalTime.format(now) + "\n" +
                "----------------------------- x ------------------------------" + "\n" +
                Nicole.botName +
                ": " + salutation + " What's our objective for today?";
    }

    private static String addTask() throws IOException {
        String task = br.readLine();
        while (!task.equals("bye")) {
            if (!task.equals("list")) {
                Nicole.taskList.add(task);
                System.out.println("Oki I added " + "\"" + task + "\"");
            } else {
                System.out.println("Your tasks are: ");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + ": " + taskList.get(i));
                }
            }
            task = br.readLine();
        }
        return Nicole.exit();
    }

    private static String echo() throws IOException {
        String request = br.readLine();
        while (!request.equals("bye")) {
            System.out.println("You said: " + request);
            request = br.readLine();
        }
        return Nicole.exit();
    }

    private static String exit() {
        return "Bye, for now ;) Let's catch up again soon.";
    }

    public static void main(String[] args) {
        System.out.println(Nicole.greet());
        try {
            System.out.println(Nicole.addTask());
        } catch (IOException ioException) {
            System.out.println("Error reading request.");
        }
    }
}
