FUNCIONALIDADES DE http://cestore.ces.com.uy/adminces/
------------------------------------------------------

--------------- PREVIO A ESTAR LOGUEADO AL SISTEMA


* INICIAR SESIÓN
        - Iniciar sesión administrador (email y contraseña)
        - Cambiar contraseña
  
* REGISTRARSE
        - Crear cuenta de administrador (Nombre, apellido, email, contaseña, repetir contraseña, país de nacimiento)
        - Cambiar contraseña

* REINICIAR CONTRASEÑA
        - Reiniciar contraseña (Email, contraseña, repetir contraseña)


--------------- LOGUEADO AL SISTEMA


* CREAR USUARIO
        - Alta de cuenta para tester (Nombre, apellido, email, país de nacimiento, contraseña por defecto, tester (junior, senior, líder))

* REINICIAR CONTRASEÑA
        - Reiniciar contraseña (Email, contraseña, repetir contraseña)

* VER USUARIOS
        - Usuarios registrados en el sistema (datos de usuarios, botón borrar usuarios)

* ELIMINAR USUARIOS
        - Eliminar usuarios registrados

* VER PERFIL DE USUARIO LOGUEADO
        - Detalles del perfil (Nombre, apellido, email, país, perfil)

* EDITAR PERFIL
        - Detalles del perfil (Nombre, apellido, email, país, perfil)

* CERRAR SESIÓN
        - Cerrar la sesión activa del usuario logueado



DIAGRAMA DE CLASES UML
## Diagrama UML

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
        +Tester(String nombre, String apellido, String paisDeNacimiento, String email, String contrasena)
        +String getTipoUsuario()
        +String realizarTareaPrincipal()
    }

    class SistemaUsuarios {
        -List~Usuario~ usuarios

        +SistemaUsuarios()
        -void cargarUsuariosDePrueba()
        +boolean registrarUsuario(Usuario nuevoUsuario)
        +Usuario buscarUsuarioPorEmail(String email)
        +boolean existeUsuario(String email)
        +Usuario login(String email, String contrasena)
        +List~Usuario~ listarUsuarios()
    }

    class Main {
        +void main(String[] args)
        -void iniciarSesion(SistemaUsuarios sistema, Scanner scanner)
        -void registrarUsuario(SistemaUsuarios sistema, Scanner scanner)
        -void listarUsuarios(SistemaUsuarios sistema)
        -void buscarUsuario(SistemaUsuarios sistema, Scanner scanner)
        -void mostrarUsuario(Usuario usuario)
    }

    Usuario <|-- Admin
    Usuario <|-- Tester

    SistemaUsuarios "1" --> "0..*" Usuario : administra

    Main ..> SistemaUsuarios : utiliza
    Main ..> Usuario : muestra
    Main ..> Admin : crea
    Main ..> Tester : crea
```
