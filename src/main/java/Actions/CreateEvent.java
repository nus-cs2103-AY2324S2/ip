package Actions;

import ChatBot.Duke;
import TaskList.TaskList;
import Tasks.Event;

public class CreateEvent implements Action {
    private String desc;
    private String from;
    private String to;
    public CreateEvent(String desc, String from, String to) {
        this.desc = desc;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(Duke bot) {
        Event e = new Event(desc, from, to);
        bot.getTaskList().addToList(e);
        System.out.println("Event successfully added!");
    }
}
