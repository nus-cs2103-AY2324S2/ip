public class list_Entry_Deadline extends list_Entry {
        public list_Entry_Deadline(String task, boolean check, String tEnd) {
            super(task, check, TYPE_DEADLINE, " ", tEnd);
        }

    @Override
    public String toString() {
            return super.toString() + " (by: "+ this.task_end + ")";
    }
}
