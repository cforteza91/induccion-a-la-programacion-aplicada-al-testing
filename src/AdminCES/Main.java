package AdminCES;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        SistemaUsuarios sistema = new SistemaUsuarios();

        int opcion;

        do {
            System.out.println("\n===== SISTEMA DE USUARIOS =====");
            System.out.println("1 - Iniciar sesión");
            System.out.println("2 - Registrar usuario");
            System.out.println("3 - Listar usuarios");
            System.out.println("4 - Buscar usuario");
            System.out.println("0 - Salir");
            System.out.print("Seleccione una opción: ");

            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    iniciarSesion(sistema, scanner);
                    break;

                case 2:
                    registrarUsuario(sistema, scanner);
                    break;

                case 3:
                    listarUsuarios(sistema);
                    break;

                case 4:
                    buscarUsuario(sistema, scanner);
                    break;

                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 0);

        scanner.close();
    }

    private static void iniciarSesion(
            SistemaUsuarios sistema,
            Scanner scanner
    ) {
        System.out.print("Ingrese email: ");
        String email = scanner.nextLine();

        System.out.print("Ingrese contraseña: ");
        String contrasena = scanner.nextLine();

        Usuario usuario = sistema.login(email, contrasena);

        if (usuario != null) {
            System.out.println("Inicio de sesión exitoso.");
            System.out.println("Bienvenido, "
                    + usuario.getNombre() + ".");
            System.out.println("Tipo de usuario: "
                    + usuario.getTipoUsuario());
            System.out.println("Tarea principal: "
                    + usuario.realizarTareaPrincipal());
        } else {
            System.out.println("Email o contraseña incorrectos.");
        }
    }

    private static void registrarUsuario(
            SistemaUsuarios sistema,
            Scanner scanner
    ) {
        System.out.println("\n===== REGISTRO DE USUARIO =====");

        System.out.print("Ingrese nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese apellido: ");
        String apellido = scanner.nextLine();

        System.out.print("Ingrese país de nacimiento: ");
        String pais = scanner.nextLine();

        System.out.print("Ingrese email: ");
        String email = scanner.nextLine();

        System.out.print("Ingrese contraseña: ");
        String contrasena = scanner.nextLine();

        System.out.println("Seleccione el tipo de usuario:");
        System.out.println("1 - Admin");
        System.out.println("2 - Tester");
        System.out.print("Opción: ");

        int tipoUsuario = Integer.parseInt(scanner.nextLine());

        Usuario nuevoUsuario;

        if (tipoUsuario == 1) {
            nuevoUsuario = new Admin(
                    nombre,
                    apellido,
                    pais,
                    email,
                    contrasena
            );
        } else if (tipoUsuario == 2) {
            nuevoUsuario = new Tester(
                    nombre,
                    apellido,
                    pais,
                    email,
                    contrasena
            );
        } else {
            System.out.println("Tipo de usuario inválido.");
            return;
        }

        boolean registrado =
                sistema.registrarUsuario(nuevoUsuario);

        if (registrado) {
            System.out.println("Usuario registrado exitosamente.");
        }
    }

    private static void listarUsuarios(
            SistemaUsuarios sistema
    ) {
        List<Usuario> usuarios =
                sistema.listarUsuarios();

        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }

        System.out.println("\n===== LISTA DE USUARIOS =====");

        for (Usuario usuario : usuarios) {
            mostrarUsuario(usuario);
        }
    }

    private static void buscarUsuario(
            SistemaUsuarios sistema,
            Scanner scanner
    ) {
        System.out.print("Ingrese el email del usuario: ");
        String email = scanner.nextLine();

        Usuario usuarioEncontrado =
                sistema.buscarUsuarioPorEmail(email);

        if (usuarioEncontrado == null) {
            System.out.println(
                    "No se encontró ningún usuario con ese email."
            );
            return;
        }

        System.out.println("\nUsuario encontrado:");
        mostrarUsuario(usuarioEncontrado);
    }

    private static void mostrarUsuario(
            Usuario usuario
    ) {
        System.out.println("--------------------");
        System.out.println("Nombre: "
                + usuario.getNombre());
        System.out.println("Apellido: "
                + usuario.getApellido());
        System.out.println("País de nacimiento: "
                + usuario.getPaisDeNacimiento());
        System.out.println("Email: "
                + usuario.getEmail());
        System.out.println("Tipo de usuario: "
                + usuario.getTipoUsuario());
        System.out.println("Tarea principal: "
                + usuario.realizarTareaPrincipal());
    }
}