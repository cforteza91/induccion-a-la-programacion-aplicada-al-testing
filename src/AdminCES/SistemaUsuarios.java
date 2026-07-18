package AdminCES;

import java.util.ArrayList;
import java.util.List;

public class SistemaUsuarios {

    private static final SistemaUsuarios INSTANCIA =
            new SistemaUsuarios();

    private final List<Usuario> usuarios;
    private Usuario usuarioLogueado;

    private SistemaUsuarios() {
        usuarios = new ArrayList<>();
        cargarUsuariosDePrueba();
    }

    public static SistemaUsuarios getInstancia() {
        return INSTANCIA;
    }

    private void cargarUsuariosDePrueba() {
        usuarios.add(new Admin(
                "Carlos",
                "Forteza",
                "Uruguay",
                "cforteza@ces.com.uy",
                "123456"
        ));

        usuarios.add(new Tester(
                "Luis",
                "Suárez",
                "Uruguay",
                "lsuarez@ces.com.uy",
                "123456",
                NivelTester.SENIOR
        ));

        usuarios.add(new Tester(
                "Edinson",
                "Cavani",
                "Uruguay",
                "ecavani@ces.com.uy",
                "123456",
                NivelTester.LIDER
        ));
    }

    public void registrarAdministrador(
            String nombre,
            String apellido,
            String paisDeNacimiento,
            String email,
            String contrasena,
            String confirmacionContrasena
    ) throws DatosInvalidosException,
            EmailDuplicadoException {

        String nombreValidado =
                ValidadorDatos.validarTextoObligatorio(
                        nombre,
                        "nombre"
                );

        String apellidoValidado =
                ValidadorDatos.validarTextoObligatorio(
                        apellido,
                        "apellido"
                );

        String paisValidado =
                ValidadorDatos.validarTextoObligatorio(
                        paisDeNacimiento,
                        "país de nacimiento"
                );

        String emailValidado =
                ValidadorDatos.validarEmail(email);

        String contrasenaValidada =
                ValidadorDatos.validarContrasena(
                        contrasena
                );

        ValidadorDatos.validarContrasenasCoincidentes(
                contrasenaValidada,
                confirmacionContrasena
        );

        verificarEmailDisponible(emailValidado);

        usuarios.add(new Admin(
                nombreValidado,
                apellidoValidado,
                paisValidado,
                emailValidado,
                contrasenaValidada
        ));
    }

    public void registrarTester(
            String nombre,
            String apellido,
            String paisDeNacimiento,
            String email,
            String contrasena,
            String confirmacionContrasena,
            NivelTester nivel
    ) throws DatosInvalidosException,
            EmailDuplicadoException,
            AccesoDenegadoException {

        verificarAdministradorLogueado();

        String nombreValidado =
                ValidadorDatos.validarTextoObligatorio(
                        nombre,
                        "nombre"
                );

        String apellidoValidado =
                ValidadorDatos.validarTextoObligatorio(
                        apellido,
                        "apellido"
                );

        String paisValidado =
                ValidadorDatos.validarTextoObligatorio(
                        paisDeNacimiento,
                        "país de nacimiento"
                );

        String emailValidado =
                ValidadorDatos.validarEmail(email);

        String contrasenaValidada =
                ValidadorDatos.validarContrasena(
                        contrasena
                );

        ValidadorDatos.validarContrasenasCoincidentes(
                contrasenaValidada,
                confirmacionContrasena
        );

        if (nivel == null) {
            throw new DatosInvalidosException(
                    "El nivel del tester es obligatorio."
            );
        }

        verificarEmailDisponible(emailValidado);

        usuarios.add(new Tester(
                nombreValidado,
                apellidoValidado,
                paisValidado,
                emailValidado,
                contrasenaValidada,
                nivel
        ));
    }

    public Usuario iniciarSesion(
            String email,
            String contrasena
    ) throws DatosInvalidosException,
            CredencialesInvalidasException {

        String emailValidado =
                ValidadorDatos.validarEmail(email);

        String contrasenaValidada =
                ValidadorDatos.validarContrasena(
                        contrasena
                );

        for (Usuario usuario : usuarios) {
            if (usuario.validarCredenciales(
                    emailValidado,
                    contrasenaValidada
            )) {
                usuarioLogueado = usuario;
                return usuario;
            }
        }

        throw new CredencialesInvalidasException();
    }

    public void cerrarSesion()
            throws AccesoDenegadoException {

        if (usuarioLogueado == null) {
            throw new AccesoDenegadoException(
                    "No hay ninguna sesión iniciada."
            );
        }

        usuarioLogueado = null;
    }

    public boolean haySesionActiva() {
        return usuarioLogueado != null;
    }

    public Usuario getUsuarioLogueado()
            throws AccesoDenegadoException {

        if (usuarioLogueado == null) {
            throw new AccesoDenegadoException(
                    "Debe iniciar sesión para acceder a esta opción."
            );
        }

        return usuarioLogueado;
    }

    public List<Usuario> listarUsuarios()
            throws AccesoDenegadoException {

        verificarAdministradorLogueado();

        return new ArrayList<>(usuarios);
    }

    public Usuario buscarUsuarioPorEmail(String email)
            throws DatosInvalidosException,
            UsuarioNoEncontradoException,
            AccesoDenegadoException {

        verificarAdministradorLogueado();

        String emailValidado =
                ValidadorDatos.validarEmail(email);

        for (Usuario usuario : usuarios) {
            if (usuario.getEmail()
                    .equalsIgnoreCase(emailValidado)) {

                return usuario;
            }
        }

        throw new UsuarioNoEncontradoException(
                emailValidado
        );
    }

    private void verificarEmailDisponible(String email)
            throws EmailDuplicadoException {

        for (Usuario usuario : usuarios) {
            if (usuario.getEmail()
                    .equalsIgnoreCase(email)) {

                throw new EmailDuplicadoException(email);
            }
        }
    }

    private void verificarAdministradorLogueado()
            throws AccesoDenegadoException {

        if (!(usuarioLogueado instanceof Admin)) {
            throw new AccesoDenegadoException(
                    "Esta opción está disponible únicamente "
                            + "para administradores."
            );
        }
    }
}