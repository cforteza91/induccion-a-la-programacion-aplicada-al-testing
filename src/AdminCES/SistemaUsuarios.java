package AdminCES;

public class SistemaUsuarios {

    private Usuario[] usuarios;
    private int cantidadUsuarios;

    public SistemaUsuarios() {
        usuarios = new Usuario[10];
        cantidadUsuarios = 0;
        cargarUsuariosDePrueba();
    }

    private void cargarUsuariosDePrueba() {
        usuarios[cantidadUsuarios] = new Admin(
                "Carlos",
                "Forteza",
                "Uruguay",
                "cforteza@ces.com.uy",
                "12345"
        );
        cantidadUsuarios++;

        usuarios[cantidadUsuarios] = new Tester(
                "Luis",
                "Suarez",
                "Uruguay",
                "lsuarez@ces.com.uy",
                "12345"
        );
        cantidadUsuarios++;

        usuarios[cantidadUsuarios] = new Tester(
                "Edinson",
                "Cavani",
                "Uruguay",
                "ecavani@ces.com.uy",
                "12345"
        );
        cantidadUsuarios++;
    }

    public boolean registrarUsuario(Usuario nuevoUsuario) {
        if (cantidadUsuarios >= usuarios.length) {
            System.out.println("No hay espacio disponible para registrar más usuarios.");
            return false;
        }

        if (existeUsuario(nuevoUsuario.getEmail())) {
            System.out.println("El usuario ya existe.");
            return false;
        }

        usuarios[cantidadUsuarios] = nuevoUsuario;
        cantidadUsuarios++;

        return true;
    }

    private Usuario buscarUsuarioPorEmail(String email) {
        for (int i = 0; i < cantidadUsuarios; i++) {
            if (usuarios[i].getEmail().equalsIgnoreCase(email)) {
                return usuarios[i];
            }
        }

        return null;
    }

    public boolean existeUsuario(String email) {
        return buscarUsuarioPorEmail(email) != null;
    }

    public Usuario login(String email, String contrasena) {
        Usuario usuarioEncontrado = buscarUsuarioPorEmail(email);

        if (usuarioEncontrado != null && usuarioEncontrado.validarCredenciales(email, contrasena)) {
            return usuarioEncontrado;
        }

        return null;
    }

    public void listarUsuarios() {
        System.out.println("Usuarios cargados en el sistema:");

        for (int i = 0; i < cantidadUsuarios; i++) {
            System.out.println("--------------------");
            System.out.println("Nombre: " + usuarios[i].getNombre());
            System.out.println("Apellido: " + usuarios[i].getApellido());
            System.out.println("Email: " + usuarios[i].getEmail());
            System.out.println("País de nacimiento: " + usuarios[i].getPaisDeNacimiento());
            System.out.println("Tipo de usuario: " + usuarios[i].getTipoUsuario());
        }
    }
}