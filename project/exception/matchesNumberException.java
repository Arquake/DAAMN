package project.exception;

public class matchesNumberException extends Exception{
    public matchesNumberException() {
        super("The number of matches was greater than expected");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
