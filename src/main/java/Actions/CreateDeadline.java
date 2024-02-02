package Actions;

import ChatBot.Duke;
import Tasks.Deadline;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CreateDeadline implements Action {
    private String desc;
    private LocalDate deadline;
    public CreateDeadline(String desc, LocalDate date) {
        this.desc = desc;
        this.deadline = date;
    }

    @Override
    public void execute(Duke bot) {
        Deadline d = new Deadline(desc, deadline);
        bot.getTaskList().addToList(d);
        System.out.println("Deadline successfully added!");
    }
}
