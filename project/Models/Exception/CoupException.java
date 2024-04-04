package project.Models.Exception;

public class CoupException extends Exception{
    public CoupException() {
        super("Invalid Input");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
