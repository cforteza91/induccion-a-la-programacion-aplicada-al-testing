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
+--------------------------------------------------+
| Usuario                                         |
+--------------------------------------------------+
| - nombre: String                                |
| - apellido: String                              |
| - paisDeNacimiento: String                      |
| - email: String                                 |
| - contrasena: String                            |
+--------------------------------------------------+
| + Usuario(nombre: String,                       |
|           apellido: String,                     |
|           paisDeNacimiento: String,             |
|           email: String,                        |
|           contrasena: String)                   |
| + getNombre(): String                           |
| + setNombre(nombre: String): void               |
| + getApellido(): String                         |
| + setApellido(apellido: String): void           |
| + getPaisDeNacimiento(): String                 |
| + setPaisDeNacimiento(paisDeNacimiento: String): void |
| + getEmail(): String                            |
| + setEmail(email: String): void                 |
| + getContrasena(): String                       |
| + setContrasena(contrasena: String): void       |
| + validarCredenciales(emailIngresado: String,   |
|                        contrasenaIngresada: String): boolean |
| + getTipoUsuario(): String                      |
+--------------------------------------------------+


+--------------------------------------------------+
| Admin                                           |
+--------------------------------------------------+
|                                                  |
+--------------------------------------------------+
| + Admin(nombre: String,                         |
|         apellido: String,                       |
|         paisDeNacimiento: String,               |
|         email: String,                          |
|         contrasena: String)                     |
| + getTipoUsuario(): String                      |
+--------------------------------------------------+


+--------------------------------------------------+
| Tester                                          |
+--------------------------------------------------+
|                                                  |
+--------------------------------------------------+
| + Tester(nombre: String,                        |
|          apellido: String,                      |
|          paisDeNacimiento: String,              |
|          email: String,                         |
|          contrasena: String)                    |
| + getTipoUsuario(): String                      |
+--------------------------------------------------+


+--------------------------------------------------+
| SistemaUsuarios                                 |
+--------------------------------------------------+
| - usuarios: Usuario[]                           |
| - cantidadUsuarios: int                         |
+--------------------------------------------------+
| + SistemaUsuarios()                             |
| - cargarUsuariosDePrueba(): void                |
| + registrarUsuario(nuevoUsuario: Usuario): boolean |
| - buscarUsuarioPorEmail(email: String): Usuario |
| + existeUsuario(email: String): boolean         |
| + login(email: String, contrasena: String): Usuario |
| + listarUsuarios(): void                        |
+--------------------------------------------------+


+--------------------------------------------------+
| Main                                            |
+--------------------------------------------------+
|                                                  |
+--------------------------------------------------+
| + main(args: String[]): void                    |
| - realizarLogin(scan: Scanner,                  |
|                 sistema: SistemaUsuarios): void |
| - registrarUsuario(scan: Scanner,               |
|                    sistema: SistemaUsuarios): void |
+--------------------------------------------------+
