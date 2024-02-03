import java.io.Serializable;

public interface Item extends Serializable {
    public boolean isDone = false;

    public abstract void markDone();

    public abstract void markUndone();

    public abstract String doneMessage();

    public abstract String undoneMessage();

    public abstract String printChecked(boolean b);

    public abstract String addMessage(int num);

    public abstract String removeMessage(int num);

}
