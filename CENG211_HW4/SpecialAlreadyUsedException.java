public class SpecialAlreadyUsedException extends Exception {
    public SpecialAlreadyUsedException() {
        super();
    }

    public SpecialAlreadyUsedException(String message) {
        super(message);
    }
}