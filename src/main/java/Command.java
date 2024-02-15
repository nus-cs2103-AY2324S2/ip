public abstract class Command {

     private final boolean isExit;

     public Command() {
          this.isExit = false;
     }
     public abstract void execute(TaskList tasks, UI ui, Storage storage);

     public boolean isExit() {
          return this.isExit;
     };
}
