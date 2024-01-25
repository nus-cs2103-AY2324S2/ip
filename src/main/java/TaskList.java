/**
 * This class represents a task list of up to 100 tasks that keeps tracks of tasks
 *
 * @author Billy Ho Cheng En
 */
class TaskList{

    private String[] arr;
    private int pointer;

    /**
     * Constructor for a task list.
     */
    public TaskList(){
        arr = new String[100];
        pointer = 0;
    }

    /**
     * Adds a task to the back of the task list.
     *
     * @param task The task to be added.
     */
    public void add(String task){
        this.arr[this.pointer] = task;
        this.pointer++;
    }

    @Override
    public String toString(){
        String ret = "";
        for(int i = 0; i < this.pointer; i++) {
            ret = ret + (i + 1) + ". " + this.arr[i] + "\n";
        }
        return ret;
    }
}