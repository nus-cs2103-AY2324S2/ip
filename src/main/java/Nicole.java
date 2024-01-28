import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Nicole {
    public static final String botName = "Nicole";
    public static final List<Task> taskList = new ArrayList<>();
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static String greet() {
        DateTimeFormatter digitalTime = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter parseHour = DateTimeFormatter.ofPattern("HH");
        LocalDateTime now = LocalDateTime.now();
        String salutation = "Good ";
        int currentHour = Integer.parseInt(parseHour.format(now));
        if (currentHour < 12 && currentHour >= 5) {
            salutation += "morns :D ! What's the plan today";
        } else if (currentHour >= 12 && currentHour < 17) {
            salutation += "afternoon zzz...how's it going";
        } else {
            salutation += "evening! Let's wrap it up soon? :P";
        }
        return "Welcome to Nicole Mark II. New conversation at " + digitalTime.format(now) + "\n" +
                "----------------------------- x ------------------------------" + "\n" +
                Nicole.botName +
                ": " + salutation;
    }

    private static String talkToUser() throws IOException {
        String request = br.readLine();
        while (request != null && !request.equals("bye")) {
            try {
                Request newRequest = new Request(request);
                newRequest.handleRequest();
            } catch (NicoleException e) {
                System.out.println(e);
            }
            request = br.readLine();
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

    private static void loadTasksFromFile() throws NicoleException, IOException {
        File tasksFile = new File("./data/tasks.txt");
        try {
            Scanner userTaskFileReader = new Scanner(tasksFile);
            int numTasksInFile = 0;
            BufferedReader reader = new BufferedReader(new FileReader(tasksFile));
            while (reader.readLine() != null) {
                numTasksInFile++;
            }
            int i = 1;
            while (userTaskFileReader.hasNextLine()) {
                String task = userTaskFileReader.nextLine();
                if (Nicole.taskList.size() < numTasksInFile) {
                    char taskType = task.charAt(1);
                    char taskCompleted = task.charAt(4);
                    String taskDescription = task.substring(7);
                    Task recreatedTask = Task.taskFactory(taskDescription, taskType);
                    if (taskCompleted == 'C') {
                        recreatedTask.markDone();
                    }
                    Nicole.taskList.add(recreatedTask);
                }
                i++;
            }
        } catch (FileNotFoundException e) {
            System.out.println(Nicole.botName + ": I have no past data with you, let's start something ;)");
        }
    }

    public static void main(String[] args) {
        System.out.println(Nicole.greet());
        try {
            new File("./data").mkdirs();
            new File("./data/tasks.txt");
            Nicole.loadTasksFromFile();
            System.out.println(Nicole.talkToUser());
        } catch (IOException ioException) {
            System.out.println("Error reading request: " + ioException);
        } catch (NicoleException nicoleException) {
            System.out.println(nicoleException);
        }
    }
}
