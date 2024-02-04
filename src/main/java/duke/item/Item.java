package duke.item;

import java.io.Serializable;

public interface Item extends Serializable {

    void markDone();

    void markUndone();

    String doneMessage();

    String undoneMessage();

    String printChecked(boolean b);

    String addMessage(int num);

    String removeMessage(int num);

}
