/**
 * Represents a to-do task in the Bond task management program.
 * 
 * @author Benny Loh
 * @version 0.1
 */
package bond.task;

public class ToDoTask extends Task {

  public ToDoTask(String name) {
    super(name);
  }

  @Override
  public String toString() {
    return String.format("[T]%s", super.toString());
  }

}
