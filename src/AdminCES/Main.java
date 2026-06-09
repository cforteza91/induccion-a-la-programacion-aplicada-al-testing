package AdminCES;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        SistemaUsuarios sistema = new SistemaUsuarios();

        String opcion;

        do {
            System.out.println();
            System.out.println("===== SISTEMA DE USUARIOS CES =====");
            System.out.println("1 - Login");
            System.out.println("2 - Registrar usuario");
            System.out.println("3 - Listar usuarios");
            System.out.println("0 - Salir");
            System.out.print("Ingrese una opción: ");

            opcion = scan.nextLine();

            if (opcion.equals("1")) {
                realizarLogin(scan, sistema);

            } else if (opcion.equals("2")) {
                registrarUsuario(scan, sistema);

            } else if (opcion.equals("3")) {
                sistema.listarUsuarios();

            } else if (opcion.equals("0")) {
                System.out.println("Ha salido del sistema");

            } else {
                System.out.println("No existe la opción indicada.");
            }

        } while (!opcion.equals("0"));

        scan.close();
    }

    private static void realizarLogin(Scanner scan, SistemaUsuarios sistema) {

        System.out.println();
        System.out.println("===== LOGIN =====");

        System.out.print("Ingrese email: ");
        String emailIngresado = scan.nextLine();

        System.out.print("Ingrese contraseña: ");
        String contrasenaIngresada = scan.nextLine();

        Usuario usuarioLogueado = sistema.login(emailIngresado, contrasenaIngresada);

        if (usuarioLogueado != null) {
            System.out.println("Login exitoso.");
            System.out.println("Bienvenido, " + usuarioLogueado.getNombre());
        } else {
            System.out.println("Login incorrecto.");
        }
    }

    private static void registrarUsuario(Scanner scan, SistemaUsuarios sistema) {

        System.out.println();
        System.out.println("===== REGISTRO DE USUARIO =====");

        System.out.print("Ingrese nombre: ");
        String nombre = scan.nextLine();

        System.out.print("Ingrese apellido: ");
        String apellido = scan.nextLine();

        System.out.print("Ingrese email: ");
        String email = scan.nextLine();

        if (sistema.existeUsuario(email)) {
            System.out.println("El usuario ya existe.");
            return;
        }

        System.out.print("Ingrese contraseña: ");
        String contrasena = scan.nextLine();

        System.out.print("Ingrese contraseña nuevamente: ");
        String contrasena2 = scan.nextLine();

        if (!contrasena.equals(contrasena2)) {
            System.out.println("La contraseña no coincide.");
            return;
        }

        System.out.print("Ingrese país de nacimiento: ");
        String paisDeNacimiento = scan.nextLine();

        System.out.println("Seleccione tipo de usuario:");
        System.out.println("1 - Admin");
        System.out.println("2 - Tester");
        System.out.print("Opción: ");
        String tipoUsuario = scan.nextLine();

        Usuario nuevoUsuario;

        if (tipoUsuario.equals("1")) {

            nuevoUsuario = new Admin(
                    nombre,
                    apellido,
                    paisDeNacimiento,
                    email,
                    contrasena
            );

        } else if (tipoUsuario.equals("2")) {

            nuevoUsuario = new Tester(
                    nombre,
                    apellido,
                    paisDeNacimiento,
                    email,
                    contrasena
            );

        } else {
            System.out.println("Tipo de usuario inválido.");
            return;
        }

        boolean registroExitoso = sistema.registrarUsuario(nuevoUsuario);

        if (registroExitoso) {
            System.out.println("Registro de usuario exitoso.");
        } else {
            System.out.println("No se pudo registrar el usuario.");
        }
    }
}