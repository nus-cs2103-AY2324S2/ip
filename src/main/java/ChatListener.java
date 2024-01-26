import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Handles the main chat listening and parsing of messages
public class ChatListener {
    private Scanner sc;
    private Storage taskStorage;
    private TaskLoader tskLoader = new TaskLoader("data/task_lists.txt");

    public ChatListener() {
        this.sc = new Scanner(System.in);
        this.taskStorage = new Storage();
    }

    public ChatListener(Storage taskStorage) {
        this.sc = new Scanner(System.in);
        this.taskStorage = taskStorage;
    }


    public int parseCommand(String task) throws RyanGoslingException {
        String[] taskSplit = task.split(" ");
        if (task.equals(String.valueOf(CommandsEnum.bye))) {
            MessagePrinter.bye();
            return 1;
        } else if (task.equals(String.valueOf(CommandsEnum.list))) {
            taskStorage.printList();
        } else if (taskSplit[0].equals(String.valueOf(CommandsEnum.mark))
                || taskSplit[0].equals(String.valueOf(CommandsEnum.unmark))) {
            //All items to be 0-index referenced other than user input.
            taskStorage.changeStatusOfItem(taskSplit[0], Integer.parseInt(taskSplit[1])-1);
            taskStorage.writeToFile(tskLoader);
        } else if (taskSplit[0].equals(String.valueOf(CommandsEnum.todo))) {
            //Idea from chatGPT
            Pattern pattern = Pattern.compile("todo (.*?)");
            Matcher matcher = pattern.matcher(task);
            if (matcher.matches()) {
                taskStorage.add(new Todo(matcher.group(1)));
                taskStorage.writeToFile(tskLoader);
            } else {
                throw new RyanGoslingException("Incomplete todo command, todo <event>");
            }

        } else if (taskSplit[0].equals(String.valueOf(CommandsEnum.deadline))) {
            Pattern pattern = Pattern.compile("deadline (.*?) /by (.*?)");
            Matcher matcher = pattern.matcher(task);
            if (matcher.matches()) {
                taskStorage.add(new Deadline(matcher.group(1), matcher.group(2)));
                taskStorage.writeToFile(tskLoader);
            } else {
                throw new RyanGoslingException("Incomplete deadline command, " +
                        "deadline <event> /by <time>");
            }
        } else if (taskSplit[0].equals(String.valueOf(CommandsEnum.event))) {
            //System.out.println(task);
            Pattern pattern = Pattern.compile("event (.*?) /from (.*?) /to (.*?)");
            Matcher matcher = pattern.matcher(task);
            if (matcher.matches()) {
                // Retrieve matched groups
                System.out.println(matcher.group(3));
                taskStorage.add(new Events(matcher.group(1), matcher.group(2), matcher.group(3)));
                taskStorage.writeToFile(tskLoader);
            } else {
                throw new RyanGoslingException("Incomplete event command, " +
                        "event <event> /from <time> /to <time>");
            }
        } else if (taskSplit[0].equals(String.valueOf(CommandsEnum.delete))) {
            taskStorage.removeIndex(Integer.parseInt(taskSplit[1])-1);
            taskStorage.writeToFile(tskLoader);
        }
        else {
            throw new RyanGoslingException("I was created in a few hours so " +
                    "I don't know what that means :(");
        }
        return 0;
    }
    public void chatListener() {
        while (true) {
            String task = sc.nextLine();
            int status = 0;
            try {
                status = this.parseCommand(task);
            } catch (Exception e){
                MessagePrinter.errorPrinter(e);
            } finally {
                if (status == 1) {
                    return;
                }
            }
        }
    }
}
