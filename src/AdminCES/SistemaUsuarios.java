package AdminCES;

import java.util.ArrayList;
import java.util.List;

public class SistemaUsuarios {

    private final List<Usuario> usuarios;

    public SistemaUsuarios() {
        usuarios = new ArrayList<>();
        cargarUsuariosDePrueba();
    }

    private void cargarUsuariosDePrueba() {

        usuarios.add(new Admin(
                "Carlos",
                "Forteza",
                "Uruguay",
                "cforteza@ces.com.uy",
                "12345"
        ));

        usuarios.add(new Tester(
                "Luis",
                "Suarez",
                "Uruguay",
                "lsuarez@ces.com.uy",
                "12345"
        ));

        usuarios.add(new Tester(
                "Edinson",
                "Cavani",
                "Uruguay",
                "ecavani@ces.com.uy",
                "12345"
        ));
    }

    public boolean registrarUsuario(Usuario nuevoUsuario) {

        if (existeUsuario(nuevoUsuario.getEmail())) {
            System.out.println("El usuario ya existe.");
            return false;
        }

        usuarios.add(nuevoUsuario);
        return true;
    }

    public Usuario buscarUsuarioPorEmail(String email) {

        if (email == null || email.isBlank()) {
            return null;
        }

        String emailBuscado = email.trim();

        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equalsIgnoreCase(emailBuscado)) {
                return usuario;
            }
        }

        return null;
    }

    public boolean existeUsuario(String email) {
        return buscarUsuarioPorEmail(email) != null;
    }

    public Usuario login(String email, String contrasena) {

        Usuario usuarioEncontrado = buscarUsuarioPorEmail(email);

        if (usuarioEncontrado != null
                && usuarioEncontrado.validarCredenciales(email, contrasena)) {

            return usuarioEncontrado;
        }

        return null;
    }

    public List<Usuario> listarUsuarios() {
        return new ArrayList<>(usuarios);
    }
}