package klee;

/**
 * Represents the exceptions that Klee can catch.
 */
public class KleeException extends Exception {
    /**
     * Constructor of KleeException class.
     *
     * @param errorMessage
     */
    public KleeException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Checks if obj is the same KleeException.
     *
     * @param obj
     * @return If obj has the same error as this instance.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == KleeException.class) {
            return this.getMessage().equals(((KleeException) obj).getMessage());
        } else {
            return false;
        }
    }
}
