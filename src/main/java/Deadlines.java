import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task{
    private LocalDateTime deadline;
    public Deadlines(String task, LocalDateTime deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String getTag() {
        return "D";
    }

    @Override
    public String toStore() {
        return " D | " + (this.isDone ? "1" : "0") +  " | "  + this.description + " | " + this.parseDateTime() + "\n";
    }
    private String parseDateTime() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d MMM uuuu HHmm");
        return this.deadline.format(outputFormatter);
    }

    @Override
    public void printTaskDesc(int num, boolean isLast){
        if (!isLast) {
            if (num == 1) {
                System.out.print("      ________________________________________________________\n");
                System.out.printf("      Here are the tasks in your list:\n      %d.[%s][%s] %s (by: %s)\n",
                        num, this.getTag(), this.getStatusIcon(), this.getDescription(), this.parseDateTime());
            } else {
                System.out.printf("      %d.[%s][%s] %s (by: %s)\n",
                        num, this.getTag(), this.getStatusIcon(), this.getDescription(), this.parseDateTime());
            }
        } else {
            if (num == 1) {
                System.out.print("      ________________________________________________________\n");
                System.out.printf("      Here are the tasks in your list:\n      %d.[%s][%s] %s (by: %s)\n",
                        num, this.getTag(), this.getStatusIcon(), this.getDescription(), this.parseDateTime());
                System.out.print("      ________________________________________________________\n");
            } else {
                System.out.printf("      %d.[%s][%s] %s (by: %s)\n",
                        num, this.getTag(), this.getStatusIcon(), this.getDescription(), this.parseDateTime());
                System.out.print("      ________________________________________________________\n");
            }
        }
    }

    @Override
    public void printFullDesc() {
        System.out.printf("         [%s][%s] %s (by: %s)\n",
                this.getTag(), this.getStatusIcon(), this.getDescription(), this.parseDateTime());
    }
}
