package zoe;
import java.util.Comparator;

public class PriorityComparator implements Comparator<Task>{
    @Override
    public int compare(Task firstTask, Task secondTask) {
        if ((firstTask instanceof Deadline) && (secondTask instanceof Deadline)) {

            return ((Deadline) firstTask).getDateTime().isBefore(((Deadline) secondTask).getDateTime()) ? -1 : 0;
        }

        return firstTask.getPriority() - secondTask.getPriority();
    }
}
