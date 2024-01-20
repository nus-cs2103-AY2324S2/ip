import java.util.Scanner;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Handles the main chat listening and parsing of messages
public class ChatListener {
    private Scanner sc;
    private Storage taskStorage;

    public ChatListener() {
        this.sc = new Scanner(System.in);
    }

    public int parseCommand(String task) {
        String[] taskSplit = task.split(" ");
        if (task.equals("bye")) {
            MessagePrinter.bye();
            return 1;
        } else if (task.equals("list")) {
            taskStorage.printList();
        } else if (taskSplit[0].equals("mark") || taskSplit[0].equals("unmark")) {
            //All items to be 0-index referenced other than user input.
            taskStorage.changeStatusOfItem(taskSplit[0], Integer.parseInt(taskSplit[1])-1);
        } else if (taskSplit[0].equals("todo")) {
            StringJoiner joinerArray = new StringJoiner(" ");
            for (int i = 1; i < taskSplit.length; i += 1) {
                joinerArray.add(taskSplit[i]);
            }
            taskStorage.add(new Todo(joinerArray.toString()));

        } else if (taskSplit[0].equals("deadline")) {
            //Idea from chatGPT
            Pattern pattern = Pattern.compile("deadline (.*?) /by (.*?)");
            Matcher matcher = pattern.matcher(task);
            /*
            StringJoiner joinerArray = new StringJoiner(" ");
            String[] tmp = task.split("/by ");
            String[] leftOfTime = tmp[0].split(" ");
            for (int i = 1; i < leftOfTime.length; i += 1) {
                joinerArray.add(leftOfTime[i]);
            }
            */
            if (matcher.matches()) {
                taskStorage.add(new Deadline(matcher.group(1), matcher.group(2)));
            }
        } else if (taskSplit[0].equals("event")) {
            //System.out.println(task);
            Pattern pattern = Pattern.compile("event (.*?) /from (.*?) /to (.*?)");
            Matcher matcher = pattern.matcher(task);
            if (matcher.matches()) {
                // Retrieve matched groups
                System.out.println(matcher.group(3));
                taskStorage.add(new Events(matcher.group(1), matcher.group(2), matcher.group(3)));
            } else {
                System.out.println("Invalid event details format");
            }
        } else {
            taskStorage.add(new Task(task));
        }
        return 0;
    }
    public void chatListener() {
        Storage taskStorage = new Storage();
        this.taskStorage = taskStorage;
        while (true) {
            String task = sc.nextLine();
            int status = this.parseCommand(task);
            if (status == 1) {
                return;
            }
        }

    }
}
