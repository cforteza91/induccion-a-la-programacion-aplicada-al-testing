package AdminCES;

public class CredencialesInvalidasException extends Exception {

    public CredencialesInvalidasException() {
        super("El email o la contraseña son incorrectos.");
    }
}