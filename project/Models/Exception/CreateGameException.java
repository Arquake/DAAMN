package project.Models.Exception;

public class CreateGameException extends Exception {
    public CreateGameException() {
        super("Invalid data");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
