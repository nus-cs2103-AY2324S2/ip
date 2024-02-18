package squid.tasks;

/**
 * Interface to implement Task object.
 */
public interface TaskInterface {
    public void setCompleted(boolean completed);
    public boolean isCompleted();
    public String completedIcon();

    public String getType();
    public String getAdditionalInfo();
    public String toString();
    public String parseStr();
}
