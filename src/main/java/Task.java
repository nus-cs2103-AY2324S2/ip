import java.io.Serializable;

public interface Task extends Serializable {
    Task mark();
    Task unmark();

    String toString();
}
