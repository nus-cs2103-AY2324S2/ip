package yapchit.yapchitbackend.tasks;

/**
 * ToDo class representing a ToDo object.
 * Extends Task class
 */
public class ToDo extends Task{

    /**
     * Constructor of new ToDo object
     *
     * @param name name of ToDo
     */
    public ToDo(String name){
        super(name);
    }

    /**
     * Prints the ToDo details onto the screen.
     *
     * @return
     */
    @Override
    public String toString(){
        String tag = super.getDone() ? "[X]" : "[ ]";
        return super.wrapToString("[T]" + tag + " " + super.getName());
    }
}
