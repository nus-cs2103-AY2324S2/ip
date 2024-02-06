import java.time.LocalDate;

public class TasksTodayCommand extends TasksByDateCommand {
    TasksTodayCommand() {
        super(new DateTime(LocalDate.now()));
    }
}
