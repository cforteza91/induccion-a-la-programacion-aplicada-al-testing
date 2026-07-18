package AdminCES;

public abstract class Usuario {

    private final String nombre;
    private final String apellido;
    private final String paisDeNacimiento;
    private final String email;
    private final String contrasena;

    protected Usuario(
            String nombre,
            String apellido,
            String paisDeNacimiento,
            String email,
            String contrasena
    ) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.paisDeNacimiento = paisDeNacimiento;
        this.email = email;
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getPaisDeNacimiento() {
        return paisDeNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public boolean validarCredenciales(
            String emailIngresado,
            String contrasenaIngresada
    ) {
        return emailIngresado != null
                && contrasenaIngresada != null
                && email.equalsIgnoreCase(emailIngresado.trim())
                && contrasena.equals(contrasenaIngresada);
    }

    public abstract String getTipoUsuario();

    public abstract String realizarTareaPrincipal();
}