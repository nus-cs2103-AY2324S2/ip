public abstract class Command {
     public abstract void execute(TaskList tasks, UI ui, Storage storage);

     public boolean isExit() {
          return false;
     };
}
