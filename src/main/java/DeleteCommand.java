import java.io.IOException;

public class DeleteCommand extends Command {
    String index;

    public DeleteCommand(String index) {
        this.index = index;
    }

    public String getIndex() {
        return index;
    }

    @Override
    public void execute(Storage s, TaskList t, Ui u) throws BelleException {
        try {
            Task deletetask = t.getTask(Integer.valueOf(index)-1);
            t.removeTask(Integer.parseInt(index) - 1);
            System.out.println("--------------------------");
            System.out.println("Noted. I've removed this task:");
            System.out.println(deletetask.toString());
            System.out.println("Now you have "+ t.getSize() + " tasks in the list.");
            System.out.println("--------------------------");
            s.save(t.getList());
        } catch (IndexOutOfBoundsException e){
            throw new BelleException("This is not a valid number in my task list :(");
        } catch (IOException e) {
            throw new BelleException("Storage has error when running delete command");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}