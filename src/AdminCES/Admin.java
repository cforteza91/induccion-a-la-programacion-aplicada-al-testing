package AdminCES;

public class Admin extends Usuario {

    public Admin(
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
        return "Admin";
    }

    @Override
    public String realizarTareaPrincipal() {
        return "Gestionar usuarios y permisos del sistema.";
    }
}