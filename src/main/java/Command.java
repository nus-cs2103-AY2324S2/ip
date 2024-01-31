import java.time.format.DateTimeFormatter;

public abstract class Command {

    private boolean isExit;
    public Command(int i){
        if(i == 1) {
            isExit = true;
        } else {
            isExit = false;
        }
    }
    public abstract void execute(TaskList tl, Ui ui, Storage st);

    public boolean isExit(){
        return isExit;
    }
}
