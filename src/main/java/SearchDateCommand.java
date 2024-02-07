import java.time.LocalDate;
import java.util.ArrayList;

class SearchDateCommand extends Command {
    private final String date;

    SearchDateCommand(String date) {
        this.date = date;
    }

    @Override
    void execute(Storage storage, TaskList taskList) throws DukeException {
        LocalDate localDate = Task.parseDate(date);
        taskList.searchDate(localDate);
    }
}
