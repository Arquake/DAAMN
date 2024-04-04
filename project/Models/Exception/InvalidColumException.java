package project.Models.Exception;

public class InvalidColumException extends Exception {
    //Mon precieux
    public InvalidColumException() {
        super();
    }

    @Override
    public String getMessage() {
        return "La colonne visée n'existe pas.";
    }
}
