package AdminCES;

public enum NivelTester {
    JUNIOR("Junior"),
    SENIOR("Senior"),
    LIDER("Líder");

    private final String descripcion;

    NivelTester(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static NivelTester desdeOpcion(int opcion) {
        switch (opcion) {
            case 1:
                return JUNIOR;
            case 2:
                return SENIOR;
            case 3:
                return LIDER;
            default:
                throw new IllegalArgumentException(
                        "El nivel de tester seleccionado no existe."
                );
        }
    }
}