package bob;

import java.time.LocalDate;

public class ListOnDateCommand extends ListCommand {
    LocalDate date;

    public ListOnDateCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ui.showList(taskList.listOnDate(date));
    }
}
