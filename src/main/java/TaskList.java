import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class TaskList {
    private ArrayList<Task> listItems = new ArrayList<>();
    private FileHandler fileHandler = new FileHandler();
    public void addTask(Task task, String type, String[]data){
        this.listItems.add(task);
        fileHandler.addListStateRecord(type, data);
        OutputMessage.informItemAdded(task, this);
    }
    public TaskList(){
        this.listItems = fileHandler.loadTasks();
    }
    public void showList(){
        System.out.println("\t____________________________________________________________");
        System.out.println("\tPer your request, I am outlining the tasks from your designated list:");
        int index = 1;
        for (Task item : this.listItems){
            System.out.println("\t" + index + ". " + item);
            index++;
        }
        System.out.println("\t____________________________________________________________\n" );
    }
    public void markList(int index){
        this.listItems.get(index-1).mark();
        fileHandler.modifyStateRecord(true, index-1);
        OutputMessage.informListMarked(listItems.get(index-1));
    }
    public void unmarkList(int index){
        this.listItems.get(index-1).unmark();
        fileHandler.modifyStateRecord(false, index-1);
        OutputMessage.informListUnmarked(listItems.get(index-1));
    }
    public void deleteList(int index){
        Task task = listItems.remove(index-1);
        fileHandler.removeListStateRecord( index-1);
        System.out.println("\t____________________________________________________________");
        System.out.println("\tI acknowledge your update. The specified task has been duly removed:");
        System.out.println("\t   "+task);
        System.out.println("\tCurrently, the list comprises  " + listItems.size() + " tasks.");
        System.out.println("\t____________________________________________________________\n");
    }
    public int getSize(){
        return this.listItems.size();
    }
    @Override
    public String toString(){
        return "\tCurrently, the list comprises  " + listItems.size() + " tasks.";
    }

}
