
//Ejemplo 6.7
// Una clase que representa un registro de información.
import java.io.Serializable;

public class RegistroCalif implements Serializable { // declaración de los campos de trabajo para captar el registro
    private int NumeroDeControl;
    private String Nombre;
    private int Calif1;
    private int Calif2;
    private int Calif3;
    private int Prom;

    // el constructor sin argumentos llama al otro constructor con valores
    // predeterminados
    public RegistroCalif() {
        this(0, "", 0, 0, 0, 0);
    }

    // inicializar los campos de un registro
    public RegistroCalif(int ctrl, String nombre, int Calif1, int Calif2, int Calif3, int prom) {
        EstablecerNumeroDeControl(ctrl);
        EstablecerNombre(nombre);
        EstablecerCalif1(Calif1);
        EstablecerCalif2(Calif2);
        EstablecerCalif3(Calif3);
        EstablecerProm(prom);
    }

    public void EstablecerNumeroDeControl(int NumeroDeControl) {
        this.NumeroDeControl = NumeroDeControl;
    } // establecer número de cuenta

    public int ObtenerNumeroDeControl() {
        return NumeroDeControl;
    } // obtener número de cuenta
      // establecer primer nombre

    public void EstablecerNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    // obtener primer nombre
    public String ObtenerNombre() {
        return Nombre;
    }

    // establecer apellido paterno
    public void EstablecerCalif1(int Calif1) {
        this.Calif1 = Calif1;
    }

    // obtener apellido paterno
    public int ObtenerCalif1() {
        return Calif1;
    }

    // establecer saldo
    public void EstablecerCalif2(int Calif2) {
        this.Calif2 = Calif2;
    }

    // obtener saldo
    public int ObtenerCalif2() {
        return Calif2;
    }

    public void EstablecerCalif3(int Calif3) {
        this.Calif3 = Calif3;
    }

    // obtener saldo
    public int ObtenerCalif3() {
        return Calif3;
    }

    public void EstablecerProm(int Prom) {
        this.Prom = Prom;
    }

    // obtener saldo
    public int ObtenerProm() {
        return Prom;
    }
} // fin de la clase RegistroCuentas