package project.Models.Exception;

public class invalidColumException extends Exception {
    //Mon precieux
    public invalidColumException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Erreur : Entier numéro de colonne incorrect (hors 1-7)";
    }
}
