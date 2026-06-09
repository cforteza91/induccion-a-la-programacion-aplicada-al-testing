package AdminCES;

public class Usuario {

    private String nombre;
    private String apellido;
    private String paisDeNacimiento;
    private String email;
    private String contrasena;

    public Usuario(String nombre, String apellido, String paisDeNacimiento, String email, String contrasena) {
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

    public String getContrasena() {
        return contrasena;
    }

    public boolean validarCredenciales(String emailIngresado, String contrasenaIngresada) {
        return this.email.equalsIgnoreCase(emailIngresado)
                && this.contrasena.equals(contrasenaIngresada);
    }

    public String getTipoUsuario() {
        return "Usuario";
    }
}