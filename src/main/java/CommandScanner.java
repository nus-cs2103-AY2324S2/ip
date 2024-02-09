import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class CommandScanner {
    public static void scanCommand(String[] inputs) throws DukeException {
        TaskList tasksList = TaskList.getInstance(null);

        if (inputs.length > 0) {
            String firstWord = inputs[0].toUpperCase();
            int index;
            switch (firstWord) {
                case "TODO":
                    tasksList.addTask(new ToDoTask(ToDoTask.getDescription(inputs)));
                    break;
                case "DEADLINE":
                    String[] deadlineDesc = DeadlineTask.getDescription(inputs);
                    tasksList.addTask(new DeadlineTask(deadlineDesc[0], deadlineDesc[1]));
                    break;
                case "EVENT":
                    String[] eventDesc = EventTask.getDescription(inputs);
                    tasksList.addTask(new EventTask(eventDesc[0], eventDesc[1], eventDesc[2]));
                    break;
                case "LIST":
                    if (inputs.length > 1) {
                        throw new DukeException("No additional characters needed after list! Appreciate the extra-ness but please try again! :)");
                    }
                    tasksList.displayTasks();
                    break;
                case "MARK":
                    index = Integer.parseInt(inputs[inputs.length - 1]) - 1;
                    tasksList.getTask(index).markDone();
                    break;
                case "UNMARK":
                    index = Integer.parseInt(inputs[inputs.length - 1]) - 1;
                    tasksList.getTask(index).markNotDone();
                    break;
                case "DELETE":
                    index = Integer.parseInt(inputs[inputs.length - 1]) - 1;
                    tasksList.deleteTask(index);
                    break;
                case "BYE":
                    break;
                default:
                    throw new DukeException("Invalid command used! I am not powered by GPT-4 so do lower your expectations heh :)");
            }
        } else {
            throw new IllegalArgumentException("Empty input, please enter a command.");
        }
    }

    public static void stringToTaskParser(String[] textSections, TaskList taskList) throws DukeException{
        String taskType = textSections[0].trim().toUpperCase();
        boolean isDone = textSections[1].trim().equals("[X]");
        Task task = null;
        Pattern pattern;
        Matcher matcher;
        try {
            switch (taskType) {
                case ("T"):
                    System.out.println(Arrays.toString(textSections));
                    task = new ToDoTask(textSections[2].trim());
                    break;
                case ("D"):
                    System.out.println(Arrays.toString(textSections));
                    pattern = Pattern.compile("\\(by: (.*?)\\)");
                    matcher = pattern.matcher(textSections[3].trim());
                    if (matcher.find()) {
                        String deadline = matcher.group(1);
                        task = new DeadlineTask(textSections[2].trim(), deadline);
                        if (isDone) {
                            task.markDone();
                        }
                    } else {
                        System.out.println("Invalid deadline task format.");
                    }
                    break;
                case ("E"):
                    System.out.println(Arrays.toString(textSections));
                    pattern = Pattern.compile("\\(from: (.*?) to: (.*?)\\)");
                    matcher = pattern.matcher(textSections[3].trim());
                    if (matcher.find()) {
                        String start = matcher.group(1);
                        String end = matcher.group(2);
                        task = new EventTask(textSections[2].trim(), start, end);
                        if (isDone) {
                            task.markDone();
                        }
                    } else {
                        System.out.println("Invalid event task format.");
                    }

                    break;
                default:
                    System.out.println("Invalid task format.");
            }
            taskList.add(task);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Accessing out of bounds index.");
        }
    }

}
