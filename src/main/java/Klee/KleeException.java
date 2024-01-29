package Klee;

public class KleeException extends Exception {
    public KleeException(String errorMessage) {
        super(errorMessage);
    }

    public String NoDescriptionError() {
        return "";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == KleeException.class) {
            return this.getMessage().equals(((KleeException) obj).getMessage());
        } else {
            return false;
        }
    }
}
