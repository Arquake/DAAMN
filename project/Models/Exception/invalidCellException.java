package project.Models.Exception;

public class invalidCellException extends Exception{
    public invalidCellException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Invalid data";
    }
}
