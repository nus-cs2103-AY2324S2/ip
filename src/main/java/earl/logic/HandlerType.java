package earl.logic;

/**
 * Factory class for {@code Handler} objects.
 */
public enum HandlerType {

    list {
        @Override
        public Handler createHandler(String args) {
            return new ListHandler(args);
        }
    },
    todo {
        @Override
        public Handler createHandler(String args) {
            return new TodoHandler(args);
        }
    },
    deadline {
        @Override
        public Handler createHandler(String args) {
            return new DeadlineHandler(args);
        }
    },
    event {
        @Override
        public Handler createHandler(String args) {
            return new EventHandler(args);
        }
    },
    mark {
        @Override
        public Handler createHandler(String args) {
            return new MarkHandler(args);
        }
    },
    unmark {
        @Override
        public Handler createHandler(String args) {
            return new UnmarkHandler(args);
        }
    },
    delete {
        @Override
        public Handler createHandler(String args) {
            return new DeleteHandler(args);
        }
    },
    find {
        @Override
        public Handler createHandler(String args) {
            return new FindHandler(args);
        }
    };

    public abstract Handler createHandler(String args);
}
