package duke.task;

/**
 * List of user commands that can be understood by the programme parser
 */
public enum TaskType {

        /**
         * list out every task in the task list
         */
        list,

        /**
         * mark a task as complete
         */
        mark,

        /**
         * mark a task as incomplete
         */
        unmark,

        /**
         * remove a task from task list
         */
        delete,

        /**
         * append a new todo task to task list
         */
        todo,

        /**
         * append a new deadline task to task list
         */
        deadline,

        /**
         * append a new event task to task list
         */
        event,

        find,
        write,
        notes,
        remove

        /*,
        exit */;
}
