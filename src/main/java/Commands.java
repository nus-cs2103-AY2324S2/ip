public abstract class Commands {
    public abstract boolean execute(TaskList tasks, UI ui, Storage s) throws DukeException;
}


//public enum Commands {
//    bye,
//    list,
//    mark,
//    todo,
//    event,
//    unmark,
//    delete,
//    deadline
//}
