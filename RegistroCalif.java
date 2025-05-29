// Jesus Armando Diaz Santoyo
// Una clase que representa un registro de información.

import java.io.Serializable; // Permite que la clase pueda ser serializada para guardarse en archivos

// Clase RegistroCalif implementa Serializable para poder guardar objetos en un archivo
public class RegistroCalif implements Serializable {
    // Declaración de los campos de trabajo para captar el registro
    private int NumeroDeControl; // Número de control del estudiante
    private String Nombre;       // Nombre del estudiante
    private int Calif1;          // Calificación 1
    private int Calif2;          // Calificación 2
    private int Calif3;          // Calificación 3

    // Constructor sin argumentos, llama al otro constructor con valores predeterminados
    public RegistroCalif() {
        this(0, "", 0, 0, 0); // Llama al constructor principal con valores por defecto
    }

    // Constructor para inicializar los campos de un registro con datos específicos
    public RegistroCalif(int ctrl, String nombre, int Calif1, int Calif2, int Calif3) {
        EstablecerNumeroDeControl(ctrl); // Asigna número de control
        EstablecerNombre(nombre);        // Asigna nombre
        EstablecerCalif1(Calif1);        // Asigna calificación 1
        EstablecerCalif2(Calif2);        // Asigna calificación 2
        EstablecerCalif3(Calif3);        // Asigna calificación 3
    }

    // Métodos setters para asignar valores a los atributos

    // Establecer número de control
    public void EstablecerNumeroDeControl(int NumeroDeControl) {
        this.NumeroDeControl = NumeroDeControl;
    }

    // Obtener número de control
    public int ObtenerNumeroDeControl() {
        return NumeroDeControl;
    }

    // Establecer nombre del estudiante
    public void EstablecerNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    // Obtener nombre del estudiante
    public String ObtenerNombre() {
        return Nombre;
    }

    // Establecer calificación 1
    public void EstablecerCalif1(int Calif1) {
        this.Calif1 = Calif1;
    }

    // Obtener calificación 1
    public int ObtenerCalif1() {
        return Calif1;
    }

    // Establecer calificación 2
    public void EstablecerCalif2(int Calif2) {
        this.Calif2 = Calif2;
    }

    // Obtener calificación 2
    public int ObtenerCalif2() {
        return Calif2;
    }

    // Establecer calificación 3
    public void EstablecerCalif3(int Calif3) {
        this.Calif3 = Calif3;
    }

    // Obtener calificación 3
    public int ObtenerCalif3() {
        return Calif3;
    }

    // Calcular y obtener el promedio de las tres calificaciones (como entero)
    public int ObtenerProm() {
        return (Calif1 + Calif2 + Calif3) / 3; // Suma las tres calificaciones y divide entre 3
    }
} // fin de la clase RegistroCalif
