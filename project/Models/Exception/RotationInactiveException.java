package project.Models.Exception;

public class RotationInactiveException extends Exception {
    public RotationInactiveException() {
        super("La rotation n'est pas active pour cette partie.");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
