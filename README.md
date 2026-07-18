FUNCIONALIDADES DE http://cestore.ces.com.uy/adminces/
------------------------------------------------------

SISTEMA DE GESTIÓN DE USUARIOS

OBJETIVO: Administrar usuarios de tipo "Administrador" y "Tester" desde una aplicación de consola.



----- FUNCIONALIDADES -----

**** Sin sesión iniciada ****
- Iniciar sesión
- Registrar un nuevo administrador
- Salir del programa

**** Con sesión de administrador ****

- Dar de alta usuarios Tester
- Seleccionar el nivel del Tester: Junior, Senior o Líder
- Listar todos los usuarios
- Buscar usuarios por email
- Ver el perfil del administrador logueado
- Cerrar sesión
- Salir del programa

**** Con sesión de Tester ****

- Ver el perfil propio
- Cerrar sesión
- Salir del programa



---- VALIDACIONES ----

- Los campos obligatorios no pueden estar vacíos
- El email debe tener un formato válido
- No pueden registrarse dos usuarios con el mismo email
- La contraseña debe tener un mínimo de 6 caracteres
- La contraseña y su confirmación deben coincidir
- Las opciones de los menús deben ser numéricas y existentes
- El nivel del Tester debe ser válido
- Solo los administradores pueden gestionar usuarios



---- EXCEPCIONES ----

El sistema utiliza las siguientes excepciones:
- `NumberFormatException`
- `IllegalArgumentException`
- `DatosInvalidosException`
- `EmailDuplicadoException`
- `UsuarioNoEncontradoException`
- `CredencialesInvalidasException`
- `AccesoDenegadoException`

Los errores son informados al usuario y el sistema continúa ejecutándose sin finalizar inesperadamente.



---- MEJORA DE DISEÑO ----

La clase `SistemaUsuarios` implementa el patrón **Singleton**, por lo que existe una única instancia encargada de administrar la colección de usuarios y la sesión activa.

También se aplican:

- Encapsulamiento de atributos.
- Herencia y abstracción mediante la clase abstracta `Usuario`.
- Separación de responsabilidades.
- Uso de `ArrayList` para administrar usuarios.
- Clase `ValidadorDatos` para centralizar las validaciones.
- Enum `NivelTester` para representar niveles válidos sin usar textos libres.



---- USUARIOS DE PRUEBA ----

* ADMIN
- Email: `cforteza@ces.com.uy`
- Contraseña: `123456`

* TESTERS
- Email: `lsuarez@ces.com.uy`
- Contraseña: `123456`

- Email: `ecavani@ces.com.uy`
- Contraseña: `123456`

Los datos se almacenan en memoria, por lo que los usuarios creados durante la ejecución no se conservan después de cerrar el programa.




---- REQUISITOS pARA EJECUTAR EL PROGRAMA ----

- Java 11 o superior. IntelliJ IDEA u otro entorno compatible con Java.


* Ejecución en IntelliJ IDEA

1. Abrir el proyecto en IntelliJ IDEA.
2. Verificar que el SDK configurado sea Java 11 o superior.
3. Abrir `src/AdminCES/Main.java`.
4. Ejecutar el método `main`.


* Ejecución desde una terminal

Desde la carpeta principal del proyecto:

```bash
javac -encoding UTF-8 -d out src/AdminCES/*.java
java -cp out AdminCES.Main
```

## Diagrama de clases UML

```mermaid
classDiagram
    class Usuario {
        <<abstract>>
        -String nombre
        -String apellido
        -String paisDeNacimiento
        -String email
        -String contrasena
        #Usuario(String nombre, String apellido, String paisDeNacimiento, String email, String contrasena)
        +String getNombre()
        +String getApellido()
        +String getPaisDeNacimiento()
        +String getEmail()
        +boolean validarCredenciales(String emailIngresado, String contrasenaIngresada)
        +String getTipoUsuario()*
        +String realizarTareaPrincipal()*
    }

    class Admin {
        +Admin(String nombre, String apellido, String paisDeNacimiento, String email, String contrasena)
        +String getTipoUsuario()
        +String realizarTareaPrincipal()
    }

    class Tester {
        -NivelTester nivel
        +Tester(String nombre, String apellido, String paisDeNacimiento, String email, String contrasena, NivelTester nivel)
        +NivelTester getNivel()
        +String getTipoUsuario()
        +String realizarTareaPrincipal()
    }

    class NivelTester {
        <<enumeration>>
        JUNIOR
        SENIOR
        LIDER
        -String descripcion
        +String getDescripcion()
        +NivelTester desdeOpcion(int opcion)$
    }

    class SistemaUsuarios {
        -SistemaUsuarios INSTANCIA$
        -List~Usuario~ usuarios
        -Usuario usuarioLogueado
        -SistemaUsuarios()
        +SistemaUsuarios getInstancia()$
        -void cargarUsuariosDePrueba()
        +void registrarAdministrador(String nombre, String apellido, String paisDeNacimiento, String email, String contrasena, String confirmacionContrasena)
        +void registrarTester(String nombre, String apellido, String paisDeNacimiento, String email, String contrasena, String confirmacionContrasena, NivelTester nivel)
        +Usuario iniciarSesion(String email, String contrasena)
        +void cerrarSesion()
        +boolean haySesionActiva()
        +Usuario getUsuarioLogueado()
        +List~Usuario~ listarUsuarios()
        +Usuario buscarUsuarioPorEmail(String email)
        -void verificarEmailDisponible(String email)
        -void verificarAdministradorLogueado()
    }

    class ValidadorDatos {
        +int LONGITUD_MINIMA_CONTRASENA$
        -Pattern PATRON_EMAIL$
        -ValidadorDatos()
        +String validarTextoObligatorio(String valor, String nombreCampo)$
        +String validarEmail(String email)$
        +String validarContrasena(String contrasena)$
        +void validarContrasenasCoincidentes(String contrasena, String confirmacion)$
    }

    class Main {
        -Scanner SCANNER$
        +void main(String[] args)$
        -boolean mostrarMenuPublico(SistemaUsuarios sistema)$
        -boolean mostrarMenuConSesion(SistemaUsuarios sistema)$
        -boolean mostrarMenuAdministrador(SistemaUsuarios sistema, Usuario administrador)$
        -boolean mostrarMenuTester(SistemaUsuarios sistema, Usuario tester)$
        -void iniciarSesion(SistemaUsuarios sistema)$
        -void registrarAdministrador(SistemaUsuarios sistema)$
        -void registrarTester(SistemaUsuarios sistema)$
        -void listarUsuarios(SistemaUsuarios sistema)$
        -void buscarUsuario(SistemaUsuarios sistema)$
        -void cerrarSesion(SistemaUsuarios sistema)$
        -void mostrarUsuario(Usuario usuario)$
        -int leerNumero()$
    }

    class DatosInvalidosException
    class EmailDuplicadoException
    class UsuarioNoEncontradoException
    class CredencialesInvalidasException
    class AccesoDenegadoException

    Usuario <|-- Admin
    Usuario <|-- Tester
    Tester --> NivelTester
    SistemaUsuarios "1" --> "0..*" Usuario : administra
    SistemaUsuarios --> Usuario : sesión activa
    SistemaUsuarios ..> ValidadorDatos : valida con
    Main ..> SistemaUsuarios : utiliza
    Main ..> Usuario : muestra
    Main ..> NivelTester : selecciona

    Exception <|-- DatosInvalidosException
    Exception <|-- EmailDuplicadoException
    Exception <|-- UsuarioNoEncontradoException
    Exception <|-- CredencialesInvalidasException
    Exception <|-- AccesoDenegadoException
```
