package AdminCES;

import java.util.regex.Pattern;

public final class ValidadorDatos {

    public static final int LONGITUD_MINIMA_CONTRASENA = 6;

    private static final Pattern PATRON_EMAIL = Pattern.compile(
            "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );

    private ValidadorDatos() {
    }

    public static String validarTextoObligatorio(
            String valor,
            String nombreCampo
    ) throws DatosInvalidosException {

        if (valor == null || valor.isBlank()) {
            throw new DatosInvalidosException(
                    "El campo " + nombreCampo + " es obligatorio."
            );
        }

        return valor.trim();
    }

    public static String validarEmail(String email)
            throws DatosInvalidosException {

        String emailValidado = validarTextoObligatorio(email, "email")
                .toLowerCase();

        if (!PATRON_EMAIL.matcher(emailValidado).matches()) {
            throw new DatosInvalidosException(
                    "El email ingresado no tiene un formato válido."
            );
        }

        return emailValidado;
    }

    public static String validarContrasena(String contrasena)
            throws DatosInvalidosException {

        if (contrasena == null || contrasena.isBlank()) {
            throw new DatosInvalidosException(
                    "La contraseña es obligatoria."
            );
        }

        if (contrasena.length() < LONGITUD_MINIMA_CONTRASENA) {
            throw new DatosInvalidosException(
                    "La contraseña debe tener al menos "
                            + LONGITUD_MINIMA_CONTRASENA
                            + " caracteres."
            );
        }

        return contrasena;
    }

    public static void validarContrasenasCoincidentes(
            String contrasena,
            String confirmacion
    ) throws DatosInvalidosException {

        if (!contrasena.equals(confirmacion)) {
            throw new DatosInvalidosException(
                    "Las contraseñas ingresadas no coinciden."
            );
        }
    }
}