import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    public Task getTaskTobeMarked(String input, TaskList list) {
        int index = Integer.parseInt(input.substring(5)) - 1;
        return list.getTask(index);
    }
    
    public Task getTaskToBeUnmarked(String input, TaskList list) {
        int index = Integer.parseInt(input.substring(7)) - 1;
        return list.getTask(index);
    }
    
    public Task getTaskToDelete(String input, TaskList list) {
        int indexOfTaskToDelete = Integer.parseInt(input.substring(7)) - 1;
        return list.getTask(indexOfTaskToDelete);
    }
    
    public Task createToDo(String input) {
        String todoDescription = input.substring(5);
        return new ToDos(todoDescription);
    }
    
    public Task createDeadline(String input, Ui ui) throws DukeException{
        int indexOfBy = input.indexOf("/by");
        String deadlineDescription = input.substring(9, indexOfBy - 1);
        String deadline = input.substring(indexOfBy + 4);
        LocalDate by = this.formatInputDate(deadline);
        ui.handleInvalidInputDate(deadline, DateTimeFormatter.ofPattern("dd/MM/yyyy"), by);
        return new Deadlines(deadlineDescription, by);
    }
    
    public Task createEvent(String input, Ui ui) throws DukeException{
        int indexOfFrom = input.indexOf("/from");
        int indexOfTo = input.indexOf("/to");
        String eventDescription = input.substring(6, indexOfFrom - 1);
        String startString = input.substring(indexOfFrom + 6, indexOfTo - 1);
        String endString = input.substring(indexOfTo + 4);
        LocalDate start = this.formatInputDate(startString);
        LocalDate end = this.formatInputDate(endString);
        ui.handleInvalidInputDate(startString, DateTimeFormatter.ofPattern("dd/MM/yyyy"), start);
        ui.handleInvalidInputDate(endString, DateTimeFormatter.ofPattern("dd/MM/yyyy"), end);
        return new Events(eventDescription, start, end);
    }
    
    
    public Task parseStringToTask(String line) {
        char typeOfTask = line.charAt(1);
        char status = line.charAt(4);
        Task t = null;
        if (typeOfTask == 'T') {
            String description = line.substring(7);
            t = new ToDos(description);
        } else if (typeOfTask == 'D') {
            int indexOfTime = line.indexOf("(by: ");
            String description = line.substring(7, indexOfTime - 1);
            String byString = line.substring(indexOfTime + 5, line.indexOf(")"));
            LocalDate by = parseFileDateToStorageDate(byString);
            t = new Deadlines(description, by);
        } else if (typeOfTask == 'E') {
            int indexOfStartTime = line.indexOf("(from");
            int indexOfEndTime = line .indexOf("to");
            String description = line.substring(7, indexOfStartTime - 1);
            String startString = line.substring(indexOfStartTime + 6, indexOfEndTime - 1);
            String endString = line.substring(indexOfEndTime + 3, line.indexOf(")"));
            LocalDate start = parseFileDateToStorageDate(startString);
            LocalDate end = parseFileDateToStorageDate(endString);
            t = new Events(description, start, end);
        }
        t.isDone = status == 'X';
        return t;
    }
    
    public LocalDate parseFileDateToStorageDate(String fileDate) {
        DateTimeFormatter fileDateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        LocalDate date = LocalDate.parse(fileDate, fileDateFormatter);
        DateTimeFormatter storageDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String acceptedDate = date.format(storageDateFormatter);
        return LocalDate.parse(acceptedDate);
    }
    
    public LocalDate formatInputDate(String input) throws DukeException {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(input, inputFormatter);
        DateTimeFormatter storageFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String acceptedDate = date.format(storageFormatter);
        return LocalDate.parse(acceptedDate);
    }
}
