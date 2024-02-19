package earl.logic;

/**
 * Factory class for {@code Handler} objects.
 */
public enum HandlerType {

    LIST {
        @Override
        public Handler createHandler(String args) {
            return new ListHandler(args);
        }
    },
    TODO {
        @Override
        public Handler createHandler(String args) {
            return new TodoHandler(args);
        }
    },
    DEADLINE {
        @Override
        public Handler createHandler(String args) {
            return new DeadlineHandler(args);
        }
    },
    EVENT {
        @Override
        public Handler createHandler(String args) {
            return new EventHandler(args);
        }
    },
    MARK {
        @Override
        public Handler createHandler(String args) {
            return new MarkHandler(args);
        }
    },
    UNMARK {
        @Override
        public Handler createHandler(String args) {
            return new UnmarkHandler(args);
        }
    },
    DELETE {
        @Override
        public Handler createHandler(String args) {
            return new DeleteHandler(args);
        }
    },
    FIND {
        @Override
        public Handler createHandler(String args) {
            return new FindHandler(args);
        }
    },
    HELP {
        @Override
        public Handler createHandler(String args) {
            return new HelpHandler(args);
        }
    };

    public abstract Handler createHandler(String args);
}
