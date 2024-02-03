package Actions;

import UI.Duke;
import Tasks.Deadline;

import java.time.LocalDate;

/**
 * The `CreateDeadline` class implements the Action interface and represents an action to create a new Deadline task.
 * It stores the description and deadline date for the new Deadline task and provides a method to execute the action.
 */
public class CreateDeadline implements Action {

    private String desc;
    private LocalDate deadline;

    /**
     * Constructs a `CreateDeadline` object with the specified description and deadline date.
     *
     * @param desc     The description of the new Deadline task.
     * @param date The deadline date of the new Deadline task.
     */
    public CreateDeadline(String desc, LocalDate date) {
        this.desc = desc;
        this.deadline = date;
    }

    /**
     * Executes the create deadline action by creating a new Deadline task with the stored description and deadline date.
     * The new task is then added to the task list of the Duke chatbot.
     *
     * @param bot The Duke chatbot on which the action is to be executed.
     */
    @Override
    public void execute(Duke bot) {
        Deadline d = new Deadline(desc, deadline);
        bot.getTaskList().addToList(d);
        System.out.println("Deadline successfully added!");
    }
}