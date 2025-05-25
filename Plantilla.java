
//Ejemeplo 6.6
// Una Interfaz Gráfica de Usuario (GUI) reutilizable para los ejemplos de este ejemplo.
import java.awt.*;
import javax.swing.*;

public class Plantilla extends JPanel {
    // texto de las etiquetas para la GUI
    protected final static String nombres[] = { "Num Ctrl", "Nombre", "Calif1", "Calif2", "Calif3", "Prom" };
    // componentes de GUI; protegidos para el acceso futuro de las subclases
    protected JLabel etiquetas[]; // se declara un arreglo de etiquetas
    protected JTextField campos[]; // se declara un arreglo, de campos de texto
    protected JButton hacerTarea1, hacerTarea2 , hacerTarea3 , hacerTarea4; // se declaran dos botones sin etiquetas
    protected JPanel panelInternoCentro, panelInternoSur;
    protected int tamanio; // número de campos de texto en la GUI
    // constantes que representan a los campos de texto en la GUI
    public static final int NUMERODECONTROL = 0, NOMBRE = 1, CALIF1 = 2, CALIF2 = 3, CALIF3 = 4, PROM = 5;

    // Configurar GUI. El argumento miTamanio del constructor determina el número de
    // filas de componentes de GUI.
    public Plantilla(int miTamanio) {
        tamanio = miTamanio;
        etiquetas = new JLabel[tamanio];
        campos = new JTextField[tamanio];
        // crear etiquetas
        for (int cuenta = 0; cuenta < etiquetas.length; cuenta++)
            etiquetas[cuenta] = new JLabel(nombres[cuenta]);
        // crear campos de texto
        for (int cuenta = 0; cuenta < campos.length; cuenta++)
            campos[cuenta] = new JTextField();
        // crear panel para distribuir etiquetas y campos
        panelInternoCentro = new JPanel();
        panelInternoCentro.setLayout(new GridLayout(tamanio, 2));
        // adjuntar etiquetas y campos a panelInternoCentro
        for (int cuenta = 0; cuenta < tamanio; cuenta++) {
            panelInternoCentro.add(etiquetas[cuenta]);
            panelInternoCentro.add(campos[cuenta]);
        }
        // crear botones genéricos; sin etiquetas ni manejadores de eventos
        hacerTarea1 = new JButton();
        hacerTarea2 = new JButton();
        hacerTarea3 = new JButton();
        hacerTarea4 = new JButton();
        // crear panel para distribuir los botones y adjuntarlos
        panelInternoSur = new JPanel();
        panelInternoSur.add(hacerTarea1);
        panelInternoSur.add(hacerTarea2);
        panelInternoSur.add(hacerTarea3);
        panelInternoSur.add(hacerTarea4);
        // establecer esquema de este contenedor y adjuntarle los paneles
        setLayout(new BorderLayout());
        add(panelInternoCentro, BorderLayout.CENTER);
        add(panelInternoSur, BorderLayout.SOUTH);
        validate(); // validar esquema
    } // fin del constructor
      // devolver referencia al botón de tarea genérico hacerTarea1

    public JButton obtenerBotonHacerTarea1() {
        return hacerTarea1;
    }

    // devolver referencia al botón de tarea genérico hacerTarea2
    public JButton obtenerBotonHacerTarea2() {
        return hacerTarea2;
    }

    public JButton obtenerBotonHacerTarea3() {
        return hacerTarea3;
    }

        public JButton obtenerBotonHacerTarea4() {
        return hacerTarea4;
    }

    // devolver referencia al arreglo campos de objetos JTextField
    public JTextField[] obtenerCampos() {
        return campos;
    }

    // borrar el contenido de los campos de texto
    public void borrarCampos() {
        for (int cuenta = 0; cuenta < tamanio; cuenta++)
            campos[cuenta].setText("");
    }

    // establecer valores de los campos de texto; lanzar IllegalArgumentException si
    // hay un número incorrecto de objetos String en el argumento
    public void establecerValoresCampos(String cadenas[]) throws IllegalArgumentException {
        if (cadenas.length != tamanio)
            throw new IllegalArgumentException("Debe haber " + tamanio + " objetos String en el arreglo");
        for (int cuenta = 0; cuenta < tamanio; cuenta++)
            campos[cuenta].setText(cadenas[cuenta]);
    }

    // obtener arreglo de objetos String con el contenido actual de los campos de
    // texto
    public String[] obtenerValoresCampos() {
        String valores[] = new String[tamanio];
        for (int cuenta = 0; cuenta < tamanio; cuenta++)
            valores[cuenta] = campos[cuenta].getText();
        return valores;
    }
} // fin de la clase IUBanco