package project.Models.Exception;

public class createGameException extends Exception {
    public createGameException() {
        super("Invalid data");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
