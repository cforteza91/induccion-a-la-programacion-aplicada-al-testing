package AdminCES;

public class Tester extends Usuario {

    public Tester(
            String nombre,
            String apellido,
            String paisDeNacimiento,
            String email,
            String contrasena
    ) {
        super(
                nombre,
                apellido,
                paisDeNacimiento,
                email,
                contrasena
        );
    }

    @Override
    public String getTipoUsuario() {
        return "Tester";
    }

    @Override
    public String realizarTareaPrincipal() {
        return "Diseñar y ejecutar pruebas de software.";
    }
}