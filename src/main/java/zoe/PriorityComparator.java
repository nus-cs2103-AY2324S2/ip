package zoe;
import java.util.Comparator;

public class PriorityComparator implements Comparator<Task>{
    /**
     * Compares tasks according to their type first and if they are deadlines, sorts them according to date time
     * @param firstTask the first object to be compared.
     * @param secondTask the second object to be compared.
     */
    @Override
    public int compare(Task firstTask, Task secondTask) {
        if ((firstTask instanceof Deadline) && (secondTask instanceof Deadline)) {

            return ((Deadline) firstTask).getDateTime().isBefore(((Deadline) secondTask).getDateTime()) ? -1 : 0;
        }

        return firstTask.getPriority() - secondTask.getPriority();
    }
}
