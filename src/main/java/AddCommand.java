import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddCommand extends Command{

    private final Parser.TaskType type;

    private String task;

    private String[] splitedDateTime;
    private String deadline;
    private String content;

    private String[] splitedFromDateTime;
    private String[] splitedToDateTime;
    private String from;
    private String to;

    public AddCommand(String task) {
        this.task = task;
        this.type = Parser.TaskType.TODO;
    }

    public AddCommand(String[] splitedDateTime, String deadline, String content) {
        this.type = Parser.TaskType.DEADLINE;
        this.splitedDateTime = splitedDateTime;
        this.deadline = deadline;
        this.content = content;
    }

    public AddCommand(String[] splitedFromDateTime, String[] splitedToDateTime, String from, String to, String content) {
        this.type = Parser.TaskType.EVENT;
        this.splitedFromDateTime = splitedFromDateTime;
        this.splitedToDateTime = splitedToDateTime;
        this.from = from;
        this.to = to;
        this.content = content;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        switch (type) {
            case TODO: {
                Task newTask = new Todo(task.substring(5));
                tasks.add(newTask);
                ui.add(newTask, tasks);
                break;
            }

            case DEADLINE: {
                // Create deadline task
                try {
                    if (splitedDateTime.length == 2) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        LocalDateTime parsedDateTime = LocalDateTime.parse(deadline, formatter);
                        Task newTask = new Deadline(content, parsedDateTime);
                        tasks.add(newTask);
                        ui.add(newTask, tasks);
                    } else {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate parsedDate = LocalDate.parse(deadline, formatter);
                        Task newTask = new Deadline(content, parsedDate);
                        tasks.add(newTask);
                        ui.add(newTask, tasks);
                    }
                } catch (Exception e) {
                    throw new DukeException("Syntax of deadline: deadline {task description} /by ({yyyy-MM-dd HH:mm} or {yyyy-MM-dd})\n"
                            + "E.g. deadline breakfast /by 2022-12-31 15:00");
                }
                break;
            }

            case EVENT: {
                // Create event task
                try {
                    if (splitedFromDateTime.length == 2 && splitedToDateTime.length == 2) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        LocalDateTime parsedFromDateTime = LocalDateTime.parse(from, formatter);
                        LocalDateTime parsedToDateTime = LocalDateTime.parse(to, formatter);
                        Task newTask = new Event(content, parsedFromDateTime, parsedToDateTime);
                        tasks.add(newTask);
                        ui.add(newTask, tasks);
                    } else if (splitedFromDateTime.length == 2 && splitedToDateTime.length == 1) {
                        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDateTime parsedFromDateTime = LocalDateTime.parse(from, formatter1);
                        LocalDate parsedToDate = LocalDate.parse(to, formatter2);
                        Task newTask = new Event(content, parsedFromDateTime, parsedToDate);
                        tasks.add(newTask);
                        ui.add(newTask, tasks);
                    } else if (splitedFromDateTime.length == 1 && splitedToDateTime.length == 2) {
                        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate parsedFromDate = LocalDate.parse(from, formatter2);
                        LocalDateTime parsedToDateTime = LocalDateTime.parse(to, formatter1);
                        Task newTask = new Event(content, parsedFromDate, parsedToDateTime);
                        tasks.add(newTask);
                        ui.add(newTask, tasks);
                    } else {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate parsedFromDate = LocalDate.parse(from, formatter);
                        LocalDate parsedToDate = LocalDate.parse(to, formatter);
                        Task newTask = new Event(content, parsedFromDate, parsedToDate);
                        tasks.add(newTask);
                        ui.add(newTask, tasks);
                    }
                } catch (Exception e) {
                    throw new DukeException("Syntax of event: deadline {task description} /from ({yyyy-MM-dd HH:mm} or {yyyy-MM-dd}) "
                            + "/to ({yyyy-MM-dd HH:mm} or {yyyy-MM-dd})\n"
                            + "E.g. event exam /from 2022-12-31 15:00 /to 17:00");
                }
                break;
            }
        }
        storage.saveChanges(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
