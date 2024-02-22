package duke.item;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * The Item interface ensures that ItemList is able to
 * contain all implementing classes of the Item interface.
 * All Items must implement the following methods to be
 * compatible with ItemList methods.
 */
public interface Item extends Serializable {

    void markDone();

    void markUndone();

    String getName();

    String doneMessage();

    String undoneMessage();

    String printChecked(boolean b);

    String addMessage(int num);

    String removeMessage(int num);

    LocalDateTime getTimeToSortBy();

}
