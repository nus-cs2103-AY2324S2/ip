public abstract class Command {
    protected enum cmd{
        list, todo, deadline, event, mark, unmark, delete, bye;
    }
    public abstract void run(TaskList taskList);
}
