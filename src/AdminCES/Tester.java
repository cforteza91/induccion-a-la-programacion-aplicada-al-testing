package AdminCES;

public class Tester extends Usuario {

    private final NivelTester nivel;

    public Tester(
            String nombre,
            String apellido,
            String paisDeNacimiento,
            String email,
            String contrasena,
            NivelTester nivel
    ) {
        super(nombre, apellido, paisDeNacimiento, email, contrasena);
        this.nivel = nivel;
    }

    public NivelTester getNivel() {
        return nivel;
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