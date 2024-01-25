/**
 * Represents a Invalid Numbering Exception for our Duke bot.
 * <p>
 * This exception should be thrown specifically for the commands: mark, unmark and delete.
 * It occurs when the number provided does not exist in storage, or if the storage is empty.
 */
public class DukeExceptionInvalidNumbering extends DukeException {
    private int id;
    
    /**
     * Creates a DukeExceptionInvalidNumbering object.
     * Will call the super constructor from the DukeException object.
     *
     * @param m The error message of the Exception.
     * @param id The attempted ID number provided for the object.
     */
    public DukeExceptionInvalidNumbering(String m, int id) {
        super(m);
        this.id = id;
    }

    /**
     * Returns a string representation of this DukeExceptionInvalidNumbering.
     * 
     * @return a string representation of DukeExceptionInvalidNumbering.
     */
    @Override
    public String toString() {
        if (this.id != 0) {
            return "Check the list! The task id of " + this.id + " you are referring doesn't exist!" + super.toString();
        }
        return "The list is empty! You have yet to enter any task!" + super.toString();
    }
}
