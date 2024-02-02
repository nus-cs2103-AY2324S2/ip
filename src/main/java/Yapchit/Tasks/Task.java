package yapchit.tasks;

/**
 * Task class representing a general task in Yapchit/
 */
public class Task {
    private String name;
    private boolean tag;

    /**
     * Constructor of task class.
     *
     * @param name name of task to perform
     */
    public Task(String name){
        this.name = name;
        this.tag = false;
    }

    /**
     * setter function to set the tag of the class based on input.
     *
     * @param val the boolean value to update the tag to
     */
    public void updateTag(boolean val){
        this.tag = val;
    }

    /**
     * Getter function that returns name of tag
     *
     * @return String with name of task
     */
    public String getName(){
        return this.name;
    }

    public boolean getTag(){
        return this.tag;
    }
}
