public class DukeException {

    protected String description;

    /**
     *
     * @param description
     */
    public DukeException(String description) {

        this.description = description;
    }

    @Override
    public String toString() {
        if(this.description.equals("todo") || this.description.equals("deadline")||this.description.equals("event")){
            return "Your input is incomplete. Please add more details for " + this.description + ".";
        }else{
            return "I do not understand your input.";
        }
    }
}
