import java.io.IOException;

public class MarkCommand extends Command {
    String index;

    public MarkCommand(String index) {
        this.index = index;
    }

    @Override
    public void execute(Storage s, TaskList t, Ui u) throws BelleException {
        try {
            Task doingtask = t.getTask(Integer.valueOf(index)-1);
            doingtask.dotask();
            System.out.println("--------------------------");
            System.out.println("Nice! I have marked this task as done:");
            System.out.println(doingtask.toString());
            System.out.println("--------------------------");
            s.save(t.getList());
        } catch (IndexOutOfBoundsException e){
            throw new BelleException("This is not a valid number in my task list :(");
        } catch (IOException e) {
            throw new BelleException("Storage has error when running mark command");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}