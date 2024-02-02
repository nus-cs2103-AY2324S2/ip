import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> listItems = new ArrayList<>();
    private Storage storage = new Storage();
    public void addTask(Task task, String type, String[]data){
        this.listItems.add(task);
        storage.addListStateRecord(type, data);
        Ui.informItemAdded(task, this);
    }
    public TaskList(){
        this.listItems = storage.loadTasks();
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
        storage.modifyStateRecord(true, index-1);
        Ui.informListMarked(listItems.get(index-1));
    }
    public void unmarkList(int index){
        this.listItems.get(index-1).unmark();
        storage.modifyStateRecord(false, index-1);
        Ui.informListUnmarked(listItems.get(index-1));
    }
    public void deleteList(int index){
        Task task = listItems.remove(index-1);
        storage.removeListStateRecord( index-1);
        Ui.informItemRemoved(task, listItems.size());
    }
    public int getSize(){
        return this.listItems.size();
    }
    @Override
    public String toString(){
        return "\tCurrently, the list comprises  " + listItems.size() + " tasks.";
    }

}
