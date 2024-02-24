package Duke.Commands;

import Duke.Activities.Activity;
import Duke.Activities.ActivityList;

/**
 * An interface representing the capability to add an activity to an {@link ActivityList}.
 */
public interface AddActivity {

    /**
     * Adds the implementing activity to the specified {@link ActivityList}.
     *
     * @param list The activity list to which the activity should be added.
     * @return The added activity.
     */
    Activity addToList(ActivityList list);
}
