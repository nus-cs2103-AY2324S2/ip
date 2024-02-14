import java.util.ArrayList;
import java.util.List;

public class TaskList extends ArrayList<Task> {
    private final List<Task> taskLog;

    public TaskList() {
        taskLog = new ArrayList<>();
    }

    public TaskList(String content, UI ui, Storage storage) {
        if (content.isBlank()) {
            taskLog = new ArrayList<>();
            return;
        }

        taskLog = new ArrayList<>();
        String[] lines = content.split("\n");
        Integer indexCounter = 0;

        for (String line : lines) {
            System.out.println(line);
            String[] info = line.split("\\|");

            String taskType = info[0].trim();
            boolean isDone = info[1].trim().equals("1");
            String taskDescription = info[2].trim();

            switch (taskType) {
                case "T":
                    try {
                        Parser.parse("todo " + taskDescription);
                        if (isDone) {
                            Command done = new DoneCommand(indexCounter.toString());
                            done.execute(this, ui, storage);
                        }
                    } catch (Exception e) {
                        ui.printErrInvalidTask();
                    }
                    indexCounter++;
                    break;

                case "D":
                    String taskDueDate = info[3].trim();
                    try {
                        Parser.parse("due " + taskDescription + " /by " + taskDueDate);
                        if (isDone) {
                            Command done = new DoneCommand(indexCounter.toString());
                            done.execute(this, ui, storage);
                        }
                    } catch (Exception e) {
                        ui.printErrInvalidTask();
                    }
                    indexCounter++;
                    break;

                case "E":
                    String taskStart = info[3].trim();
                    String taskEnd = info[4].trim();
                    try {
                        Parser.parse("event " + taskDescription + " /from " + taskStart + " /to " + taskEnd);
                        if (isDone) {
                            Command done = new DoneCommand(indexCounter.toString());
                            done.execute(this, ui, storage);
                        }
                    } catch (Exception e) {
                        ui.printErrInvalidTask();
                    }
                    indexCounter++;
                    break;
                default:
                    ui.printErrInvalidCommand();
                    break;
            }
        }
    }

}
