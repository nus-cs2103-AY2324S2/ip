/**
* Deadline class
*/
public class Deadline extends Task {
   protected String by;

   public Deadline(String description, String by) {
       super(description);
       this.by = by;
   }

   @Override
   protected String taskType() {
       return "D";
   }

   @Override
   public String toString() {
       return super.toString() + " (by: " + by + ")";
   }
}