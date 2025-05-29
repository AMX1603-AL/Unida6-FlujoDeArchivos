// Jesus Armando Diaz Santoyo
// Una Interfaz Gráfica de Usuario (GUI) reutilizable para los ejemplos de este ejemplo.

import java.awt.*;
import javax.swing.*;

// Clase Plantilla hereda de JPanel, sirve como base para la interfaz gráfica de usuario
public class Plantilla extends JPanel {
    // Texto de las etiquetas para la GUI (nombres de los campos)
    protected final static String nombres[] = { "Num Ctrl", "Nombre", "Calif1", "Calif2", "Calif3", "Prom" };

    // Componentes de la GUI; protegidos para acceso futuro desde subclases
    protected JLabel etiquetas[];     // Arreglo de etiquetas para mostrar textos
    protected JTextField campos[];    // Arreglo de campos de texto para ingresar datos
    protected JButton hacerTarea1, hacerTarea2, hacerTarea3, hacerTarea4; // Botones genéricos (sin etiquetas iniciales)
    protected JPanel panelInternoCentro, panelInternoSur; // Paneles para organizar los componentes
    protected int tamanio; // Número de campos de texto en la GUI

    // Constantes que representan los índices de los campos de texto
    public static final int NUMERODECONTROL = 0, NOMBRE = 1, CALIF1 = 2, CALIF2 = 3, CALIF3 = 4, PROM = 5;

    // Constructor que configura la GUI
    // El argumento miTamanio determina cuántas filas de componentes tendrá
    public Plantilla(int miTamanio) {
        tamanio = miTamanio; // Asigna el tamaño recibido al atributo

        // Inicializa arreglos de etiquetas y campos de texto
        etiquetas = new JLabel[tamanio];
        campos = new JTextField[tamanio];

        // Crea etiquetas usando los textos definidos en 'nombres'
        for (int cuenta = 0; cuenta < etiquetas.length; cuenta++)
            etiquetas[cuenta] = new JLabel(nombres[cuenta]);

        // Crea los campos de texto
        for (int cuenta = 0; cuenta < campos.length; cuenta++)
            campos[cuenta] = new JTextField();

        // Crea el panel para organizar las etiquetas y campos en un GridLayout
        panelInternoCentro = new JPanel();
        panelInternoCentro.setLayout(new GridLayout(tamanio, 2)); // Distribución en filas y columnas

        // Añade etiquetas y campos al panelInternoCentro
        for (int cuenta = 0; cuenta < tamanio; cuenta++) {
            panelInternoCentro.add(etiquetas[cuenta]);
            panelInternoCentro.add(campos[cuenta]);
        }

        // Crea los botones genéricos (sin etiquetas iniciales ni manejadores de eventos)
        hacerTarea1 = new JButton();
        hacerTarea2 = new JButton();
        hacerTarea3 = new JButton();
        hacerTarea4 = new JButton();

        // Crea panel para distribuir los botones y los añade
        panelInternoSur = new JPanel();
        panelInternoSur.add(hacerTarea1);
        panelInternoSur.add(hacerTarea2);
        panelInternoSur.add(hacerTarea3);
        panelInternoSur.add(hacerTarea4);

        // Establece el layout del contenedor principal y añade los paneles
        setLayout(new BorderLayout());
        add(panelInternoCentro, BorderLayout.CENTER);
        add(panelInternoSur, BorderLayout.SOUTH);

        validate(); // Valida el diseño para asegurarse de que se actualice correctamente
    } // fin del constructor

    // Métodos para devolver referencias a los botones
    public JButton obtenerBotonHacerTarea1() {
        return hacerTarea1;
    }

    public JButton obtenerBotonHacerTarea2() {
        return hacerTarea2;
    }

    public JButton obtenerBotonHacerTarea3() {
        return hacerTarea3;
    }

    public JButton obtenerBotonHacerTarea4() {
        return hacerTarea4;
    }

    // Método para devolver el arreglo de campos de texto
    public JTextField[] obtenerCampos() {
        return campos;
    }

    // Método para borrar el contenido de los campos de texto
    public void borrarCampos() {
        for (int cuenta = 0; cuenta < tamanio; cuenta++)
            campos[cuenta].setText(""); // Limpia cada campo
    }

    // Método para establecer valores en los campos de texto
    // Lanza excepción si el número de cadenas no coincide con el número de campos
    public void establecerValoresCampos(String cadenas[]) throws IllegalArgumentException {
        if (cadenas.length != tamanio)
            throw new IllegalArgumentException("Debe haber " + tamanio + " objetos String en el arreglo");

        for (int cuenta = 0; cuenta < tamanio; cuenta++)
            campos[cuenta].setText(cadenas[cuenta]); // Asigna cada valor al campo correspondiente
    }

    // Método para obtener el contenido actual de los campos de texto en un arreglo de String
    public String[] obtenerValoresCampos() {
        String valores[] = new String[tamanio];
        for (int cuenta = 0; cuenta < tamanio; cuenta++)
            valores[cuenta] = campos[cuenta].getText(); // Obtiene el texto de cada campo
        return valores; // Retorna el arreglo de valores
    }
} // fin de la clase Plantilla
