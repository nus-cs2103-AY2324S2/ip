package yapchit.yapchitbackend.tasks;

/**
 * Task class representing a general task in Yapchit/
 */
public class Task {
    private String name;
    private boolean isDone;

    /**
     * Constructor of task class.
     *
     * @param name name of task to perform
     */
    public Task(String name){
        this.name = name;
        this.isDone = false;
    }

    /**
     * setter function to set the tag of the class based on input.
     *
     * @param isDone the boolean value to update the tag to
     */
    public void setDone(boolean isDone){
        this.isDone = isDone;
    }

    /**
     * Getter function that returns name of tag
     *
     * @return String with name of task
     */
    public String getName(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getDone(){
        return this.isDone;
    }
}
