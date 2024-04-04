package project.Models.Exception;

public class MatchesNumberException extends Exception{
    public MatchesNumberException() {
        super("The number of matches was greater than expected");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
