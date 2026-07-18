package AdminCES;

public class EmailDuplicadoException extends Exception {

    public EmailDuplicadoException(String email) {
        super("Ya existe un usuario registrado con el email: " + email);
    }
}