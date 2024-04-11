package project.Models.Exception;

public class MatchesNumberException extends Exception{
    /**
     * Exception for when the number of matches was greater than expected
     * Exception pour quand le nombre d'allumettes était plus grand que prévu
     */
    public MatchesNumberException() {
        super("The number of matches was greater than expected");
    }

    /**
     * get le message
     * @return message
     */
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
