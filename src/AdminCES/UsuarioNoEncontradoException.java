package AdminCES;

public class UsuarioNoEncontradoException extends Exception {

    public UsuarioNoEncontradoException(String email) {
        super("No se encontró ningún usuario con el email: " + email);
    }
}