package area;
public class DukeException extends Exception {

    protected String description;


    public DukeException(String description) {
        this.description = description;
    }



    @Override
    public String toString() {
        return this.description;
    }
}
