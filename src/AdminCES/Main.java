package AdminCES;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER =
            new Scanner(System.in);

    public static void main(String[] args) {
        SistemaUsuarios sistema =
                SistemaUsuarios.getInstancia();

        boolean salir = false;

        System.out.println(
                "===== SISTEMA DE GESTIÓN DE USUARIOS ====="
        );

        while (!salir) {
            try {
                if (sistema.haySesionActiva()) {
                    salir = mostrarMenuConSesion(sistema);
                } else {
                    salir = mostrarMenuPublico(sistema);
                }

            } catch (NumberFormatException e) {
                System.out.println(
                        "Error: debe ingresar una opción numérica."
                );

            } catch (IllegalArgumentException e) {
                System.out.println(
                        "Error: " + e.getMessage()
                );

            } catch (DatosInvalidosException
                     | EmailDuplicadoException
                     | UsuarioNoEncontradoException
                     | CredencialesInvalidasException
                     | AccesoDenegadoException e) {

                System.out.println(
                        "Error: " + e.getMessage()
                );

            } catch (Exception e) {
                System.out.println(
                        "Ocurrió un error inesperado: "
                                + e.getMessage()
                );
            }
        }

        SCANNER.close();

        System.out.println("Programa finalizado.");
    }

    private static boolean mostrarMenuPublico(
            SistemaUsuarios sistema
    ) throws DatosInvalidosException,
            EmailDuplicadoException,
            CredencialesInvalidasException {

        System.out.println("\n===== MENÚ PRINCIPAL =====");
        System.out.println("1 - Iniciar sesión");
        System.out.println("2 - Registrar administrador");
        System.out.println("0 - Salir");
        System.out.print("Seleccione una opción: ");

        int opcion = leerNumero();

        switch (opcion) {
            case 1:
                iniciarSesion(sistema);
                return false;

            case 2:
                registrarAdministrador(sistema);
                return false;

            case 0:
                return true;

            default:
                throw new IllegalArgumentException(
                        "La opción seleccionada no existe."
                );
        }
    }

    private static boolean mostrarMenuConSesion(
            SistemaUsuarios sistema
    ) throws DatosInvalidosException,
            EmailDuplicadoException,
            UsuarioNoEncontradoException,
            AccesoDenegadoException {

        Usuario usuarioLogueado =
                sistema.getUsuarioLogueado();

        if (usuarioLogueado instanceof Admin) {
            return mostrarMenuAdministrador(
                    sistema,
                    usuarioLogueado
            );
        }

        return mostrarMenuTester(
                sistema,
                usuarioLogueado
        );
    }

    private static boolean mostrarMenuAdministrador(
            SistemaUsuarios sistema,
            Usuario administrador
    ) throws DatosInvalidosException,
            EmailDuplicadoException,
            UsuarioNoEncontradoException,
            AccesoDenegadoException {

        System.out.println(
                "\n===== MENÚ DE ADMINISTRACIÓN ====="
        );

        System.out.println(
                "Sesión: " + administrador.getEmail()
        );

        System.out.println("1 - Alta de usuario Tester");
        System.out.println("2 - Listar usuarios");
        System.out.println("3 - Buscar usuario por email");
        System.out.println("4 - Ver mi perfil");
        System.out.println("5 - Cerrar sesión");
        System.out.println("0 - Salir");
        System.out.print("Seleccione una opción: ");

        int opcion = leerNumero();

        switch (opcion) {
            case 1:
                registrarTester(sistema);
                return false;

            case 2:
                listarUsuarios(sistema);
                return false;

            case 3:
                buscarUsuario(sistema);
                return false;

            case 4:
                mostrarUsuario(administrador);
                return false;

            case 5:
                cerrarSesion(sistema);
                return false;

            case 0:
                return true;

            default:
                throw new IllegalArgumentException(
                        "La opción seleccionada no existe."
                );
        }
    }

    private static boolean mostrarMenuTester(
            SistemaUsuarios sistema,
            Usuario tester
    ) throws AccesoDenegadoException {

        System.out.println(
                "\n===== MENÚ DE TESTER ====="
        );

        System.out.println(
                "Sesión: " + tester.getEmail()
        );

        System.out.println("1 - Ver mi perfil");
        System.out.println("2 - Cerrar sesión");
        System.out.println("0 - Salir");
        System.out.print("Seleccione una opción: ");

        int opcion = leerNumero();

        switch (opcion) {
            case 1:
                mostrarUsuario(tester);
                return false;

            case 2:
                cerrarSesion(sistema);
                return false;

            case 0:
                return true;

            default:
                throw new IllegalArgumentException(
                        "La opción seleccionada no existe."
                );
        }
    }

    private static void iniciarSesion(
            SistemaUsuarios sistema
    ) throws DatosInvalidosException,
            CredencialesInvalidasException {

        System.out.println(
                "\n===== INICIO DE SESIÓN ====="
        );

        System.out.print("Ingrese email: ");
        String email = SCANNER.nextLine();

        System.out.print("Ingrese contraseña: ");
        String contrasena = SCANNER.nextLine();

        Usuario usuario = sistema.iniciarSesion(
                email,
                contrasena
        );

        System.out.println(
                "Inicio de sesión exitoso."
        );

        System.out.println(
                "Bienvenido, "
                        + usuario.getNombre()
                        + "."
        );
    }

    private static void registrarAdministrador(
            SistemaUsuarios sistema
    ) throws DatosInvalidosException,
            EmailDuplicadoException {

        System.out.println(
                "\n===== REGISTRO DE ADMINISTRADOR ====="
        );

        System.out.print("Ingrese nombre: ");
        String nombre = SCANNER.nextLine();

        System.out.print("Ingrese apellido: ");
        String apellido = SCANNER.nextLine();

        System.out.print(
                "Ingrese país de nacimiento: "
        );
        String pais = SCANNER.nextLine();

        System.out.print("Ingrese email: ");
        String email = SCANNER.nextLine();

        System.out.print("Ingrese contraseña: ");
        String contrasena = SCANNER.nextLine();

        System.out.print("Repita la contraseña: ");
        String confirmacion = SCANNER.nextLine();

        sistema.registrarAdministrador(
                nombre,
                apellido,
                pais,
                email,
                contrasena,
                confirmacion
        );

        System.out.println(
                "Administrador registrado exitosamente."
        );
    }

    private static void registrarTester(
            SistemaUsuarios sistema
    ) throws DatosInvalidosException,
            EmailDuplicadoException,
            AccesoDenegadoException {

        System.out.println(
                "\n===== ALTA DE USUARIO TESTER ====="
        );

        System.out.print("Ingrese nombre: ");
        String nombre = SCANNER.nextLine();

        System.out.print("Ingrese apellido: ");
        String apellido = SCANNER.nextLine();

        System.out.print(
                "Ingrese país de nacimiento: "
        );
        String pais = SCANNER.nextLine();

        System.out.print("Ingrese email: ");
        String email = SCANNER.nextLine();

        System.out.print("Ingrese contraseña: ");
        String contrasena = SCANNER.nextLine();

        System.out.print("Repita la contraseña: ");
        String confirmacion = SCANNER.nextLine();

        System.out.println(
                "Seleccione el nivel del tester:"
        );

        System.out.println("1 - Junior");
        System.out.println("2 - Senior");
        System.out.println("3 - Líder");
        System.out.print("Opción: ");

        NivelTester nivel =
                NivelTester.desdeOpcion(
                        leerNumero()
                );

        sistema.registrarTester(
                nombre,
                apellido,
                pais,
                email,
                contrasena,
                confirmacion,
                nivel
        );

        System.out.println(
                "Tester registrado exitosamente."
        );
    }

    private static void listarUsuarios(
            SistemaUsuarios sistema
    ) throws AccesoDenegadoException {

        List<Usuario> usuarios =
                sistema.listarUsuarios();

        System.out.println(
                "\n===== LISTA DE USUARIOS ====="
        );

        if (usuarios.isEmpty()) {
            System.out.println(
                    "No hay usuarios registrados."
            );
            return;
        }

        for (Usuario usuario : usuarios) {
            mostrarUsuario(usuario);
        }
    }

    private static void buscarUsuario(
            SistemaUsuarios sistema
    ) throws DatosInvalidosException,
            UsuarioNoEncontradoException,
            AccesoDenegadoException {

        System.out.print(
                "Ingrese el email del usuario: "
        );

        String email = SCANNER.nextLine();

        Usuario usuario =
                sistema.buscarUsuarioPorEmail(email);

        System.out.println(
                "\nUsuario encontrado:"
        );

        mostrarUsuario(usuario);
    }

    private static void cerrarSesion(
            SistemaUsuarios sistema
    ) throws AccesoDenegadoException {

        sistema.cerrarSesion();

        System.out.println(
                "La sesión fue cerrada correctamente."
        );
    }

    private static void mostrarUsuario(
            Usuario usuario
    ) {
        System.out.println(
                "------------------------------"
        );

        System.out.println(
                "Nombre: " + usuario.getNombre()
        );

        System.out.println(
                "Apellido: " + usuario.getApellido()
        );

        System.out.println(
                "País de nacimiento: "
                        + usuario.getPaisDeNacimiento()
        );

        System.out.println(
                "Email: " + usuario.getEmail()
        );

        System.out.println(
                "Tipo de usuario: "
                        + usuario.getTipoUsuario()
        );

        if (usuario instanceof Tester) {
            Tester tester = (Tester) usuario;

            System.out.println(
                    "Nivel: "
                            + tester.getNivel()
                            .getDescripcion()
            );
        }

        System.out.println(
                "Tarea principal: "
                        + usuario.realizarTareaPrincipal()
        );
    }

    private static int leerNumero() {
        return Integer.parseInt(
                SCANNER.nextLine().trim()
        );
    }
}