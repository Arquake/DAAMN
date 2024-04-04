package project.Models.Exception;

public class NombreRotationMaximumAtteintException extends Exception {
    public NombreRotationMaximumAtteintException() {
        super("Nombre maximum de rotation atteinte !");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
