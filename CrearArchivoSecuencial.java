
//Ejemplo 6.8
// Escribir objetos secuencialmente en un archivo, mediante la clase ObjectOutputStream.
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CrearArchivoSecuencial extends JFrame {
    private ObjectOutputStream salida;
    private Plantilla interfazUsuario;
    private JButton botonIntro, botonAbrir, botonSalir, botonRegresar;

    // configurar GUI
    public CrearArchivoSecuencial() {
        super("Creación de un archivo secuencial de objetos");
        // crear instancia de interfaz de usuario reutilizable
        interfazUsuario = new Plantilla(6); // cuatro campos de texto
        getContentPane().add(interfazUsuario, BorderLayout.CENTER);
        // configurar botón hacerTarea1 para usarlo en este programa
        botonAbrir = interfazUsuario.obtenerBotonHacerTarea1();
        botonAbrir.setText("Crear el archivo ...");
        // registrar componente de escucha para llamar a abrirArchivo cuando se oprima
        // el botón
        botonAbrir.addActionListener(
                // clase interna anónima para manejar evento de botonAbrir
                new ActionListener() { // llamar a abrirArchivo cuando se oprima el botón
                    public void actionPerformed(ActionEvent evento) {
                        abrirArchivo();
                    }
                } // fin de la clase interna anónima
        ); // fin de la llamada a addActionListener
           // configurar botón hacerTarea2 para usarlo en este programa
        botonIntro = interfazUsuario.obtenerBotonHacerTarea2();
        botonIntro.setText("Introducir");
        botonIntro.setEnabled(false);
        botonSalir = interfazUsuario.obtenerBotonHacerTarea3();
        botonRegresar = interfazUsuario.obtenerBotonHacerTarea4();
        botonRegresar.setText("Anterior Registro");
        botonIntro.setEnabled(false);
        interfazUsuario.obtenerBotonHacerTarea4().setVisible(false);

        botonSalir.setText("Salir");
        botonSalir.setEnabled(false); // deshabilitar botón
        // registrar componente de escucha para llamar a agregarRegistro cuando se
        // oprima el botón
        botonIntro.addActionListener(
                // clase interna anónima para manejar evento de botonIntro
                new ActionListener() { // llamar a agregarRegistro cuando se oprima el botón
                    public void actionPerformed(ActionEvent evento) {
                        agregarRegistro();
                    }
                } // fin de la clase interna anónima
        );
        botonSalir.addActionListener(
                // clase interna anónima para manejar evento de botonIntro
                new ActionListener() { // llamar a agregarRegistro cuando se oprima el botón
                    public void actionPerformed(ActionEvent evento) {
                        System.exit(0);
                    }
                } // fin de la clase interna anónima
        ); // fin de la llamada a addActionListener
           // registrar componente de escucha de ventana para manejar evento de cierre de
        setSize(800, 400);
        setLocationRelativeTo(null); // centrar ventana
        setVisible(true);
    } // fin del constructor de CrearArchivoSecuencial
      // permitir al usuario especificar el nombre del archivo

    private void abrirArchivo() {
        // mostrar cuadro de diálogo de archivo, para que el usuario pueda elegir el
        // archivo a abrir
        JFileChooser selectorArchivo = new JFileChooser();
        selectorArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int resultado = selectorArchivo.showSaveDialog(this);
        // si el usuario hizo clic en el botón Cancelar del cuadro de diálogo, regresar
        if (resultado == JFileChooser.CANCEL_OPTION)
            return;
        File nombreArchivo = selectorArchivo.getSelectedFile(); // obtener archivo seleccionado
        // mostrar error si es inválido
        if (nombreArchivo == null || nombreArchivo.getName().equals(""))
            JOptionPane.showMessageDialog(this, "Nombre de archivo inválido",
                    "Nombre de archivo inválido", JOptionPane.ERROR_MESSAGE);
        else { // abrir archivo
            try {
                salida = new ObjectOutputStream(new FileOutputStream(nombreArchivo));
                botonAbrir.setEnabled(false);
                botonIntro.setEnabled(true);
                botonSalir.setEnabled(true);
            }
            // procesar excepciones que pueden ocurrir al abrir el archivo
            catch (IOException excepcionES) {
                JOptionPane.showMessageDialog(this, "Error al abrir el archivo",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } // fin de instrucción else
    } // fin del método abrirArchivo
      // cerrar archivo y terminar la aplicación

    private void cerrarArchivo() { // cerrar el archivo
        try {
            salida.close();
            System.exit(0);
        }
        // procesar excepciones que pueden ocurrir al cerrar el archivo
        catch (IOException excepcionES) {
            JOptionPane.showMessageDialog(this, "Error al cerrar el archivo",
                    "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    } // fin del método cerrarArchivo
      // agregar registro al archivo

    public void agregarRegistro() {
        int numeroCuenta = 0;
        RegistroCalif registro;
        String valoresCampos[] = interfazUsuario.obtenerValoresCampos();
        // si el valor del campo cuenta no está vacío
        if (!valoresCampos[Plantilla.NUMERODECONTROL].equals("")) {
            // escribir valores en el archivo
            try {
                numeroCuenta = Integer.parseInt(valoresCampos[Plantilla.NUMERODECONTROL]);
                if (numeroCuenta > 0) {
                    // crear nuevo registro
                    registro = new RegistroCalif(numeroCuenta,
                            valoresCampos[Plantilla.NOMBRE],
                            Integer.parseInt(valoresCampos[Plantilla.CALIF1]),
                            Integer.parseInt(valoresCampos[Plantilla.CALIF2]),
                            Integer.parseInt(valoresCampos[Plantilla.CALIF3]),
                            Integer.parseInt(valoresCampos[Plantilla.PROM]));
                    // escribir el registro y vaciar el búfer
                    salida.writeObject(registro);
                    salida.flush();
                } else {
                    JOptionPane.showMessageDialog(this,
                            "El Num. de la Cta. debe ser mayor que 0",
                            "Num. de la Cta. incorrecto", JOptionPane.ERROR_MESSAGE);
                }
                // borrar campos de texto
                interfazUsuario.borrarCampos();
            } // fin de bloque try
              // procesar formato inválido de número de cuenta o saldo
            catch (NumberFormatException excepcionFormato) {
                JOptionPane.showMessageDialog(this, "Num. de la Cta. o Saldo incorrecto",
                        "Formato de número incorrecto",
                        JOptionPane.ERROR_MESSAGE);
            }
            // procesar excepciones que pueden ocurrir al escribir en el archivo
            catch (IOException excepcionES) {
                JOptionPane.showMessageDialog(this, "Error al escribir en el archivo",
                        "Excepción de ES", JOptionPane.ERROR_MESSAGE);
                cerrarArchivo();
            }
        } // fin de instrucción if
    } // fin del método agregarRegistro
      // implementación del método principal

    public static void main(String args[]) {
        CrearArchivoSecuencial Cas = new CrearArchivoSecuencial();
        Cas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
} // fin de la clase CrearArchivoSecuencial