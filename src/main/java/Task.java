import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean isDone;
    
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void markDone() {
        this.isDone = true;
    }
    
    public void markUndone() {
        this.isDone = false;
    }
    
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
    
    public static LocalDate formatDateForParsing(String inputDate) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(inputDate, inputFormatter);
        DateTimeFormatter storageFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String acceptedDate = date.format(storageFormatter);
        return LocalDate.parse(acceptedDate);
    }
    public static String formatDateForPrinting(LocalDate date) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return date.format(outputFormatter);
    }
    
    public static LocalDate parseFileDateToStorageDate(String fileDate) {
        DateTimeFormatter fileDateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        LocalDate date = LocalDate.parse(fileDate, fileDateFormatter);
        DateTimeFormatter storageDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String acceptedDate = date.format(storageDateFormatter);
        return LocalDate.parse(acceptedDate);
    }
    
    public static Task parseStringToTask(String line) {
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
}
