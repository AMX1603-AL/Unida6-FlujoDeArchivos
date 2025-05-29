// Jesus Armando Diaz Santoyo
// Escribir objetos secuencialmente en un archivo, mediante la clase ObjectOutputStream.

import java.io.*;    // Importar clases de entrada/salida
import java.awt.*;    // Importar clases para la GUI
import java.awt.event.*; // Importar eventos de la GUI
import javax.swing.*;    // Importar clases de Swing

public class CrearArchivoSecuencial extends JFrame { // Clase principal que hereda de JFrame (ventana)
    private ObjectOutputStream salida;   // Flujo de salida para escribir objetos
    private Plantilla interfazUsuario;   // Interfaz gráfica reutilizable (campos de texto y botones)
    private JButton botonIntro, botonAbrir, botonSalir, botonRegresar; // Botones de la interfaz

    // Configurar la GUI en el constructor
    public CrearArchivoSecuencial() {
        super("Creación de un archivo secuencial de objetos"); // Título de la ventana

        // Crear instancia de interfaz de usuario reutilizable (5 campos de texto)
        interfazUsuario = new Plantilla(5);
        getContentPane().add(interfazUsuario, BorderLayout.CENTER); // Añadir la interfaz al centro de la ventana

        // Configurar botón hacerTarea1 para abrir archivo
        botonAbrir = interfazUsuario.obtenerBotonHacerTarea1();
        botonAbrir.setText("Crear el archivo ...");
        // Registrar evento para abrir archivo al presionar el botón
        botonAbrir.addActionListener(
            new ActionListener() { // Clase interna anónima para manejar el evento
                public void actionPerformed(ActionEvent evento) {
                    abrirArchivo(); // Llamar a abrirArchivo() cuando se presione el botón
                }
            } // Fin de la clase interna anónima
        ); // Fin de la llamada a addActionListener

        // Configurar botón hacerTarea2 para introducir datos
        botonIntro = interfazUsuario.obtenerBotonHacerTarea2();
        botonIntro.setText("Introducir");
        botonIntro.setEnabled(false); // Inicia deshabilitado

        // Configurar botones restantes
        botonSalir = interfazUsuario.obtenerBotonHacerTarea3();
        botonRegresar = interfazUsuario.obtenerBotonHacerTarea4();
        botonRegresar.setText("Anterior Registro");
        botonIntro.setEnabled(false);
        interfazUsuario.obtenerBotonHacerTarea4().setVisible(false); // Ocultar botón no usado

        // Configurar botón salir
        botonSalir.setText("Salir");
        botonSalir.setEnabled(false); // Inicia deshabilitado

        // Registrar evento para introducir datos
        botonIntro.addActionListener(
            new ActionListener() { // Clase interna anónima para manejar evento
                public void actionPerformed(ActionEvent evento) {
                    agregarRegistro(); // Llamar a agregarRegistro() al presionar el botón
                }
            } // Fin de la clase interna anónima
        );

        // Registrar evento para salir del programa
        botonSalir.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent evento) {
                    System.exit(0); // Terminar el programa
                }
            } // Fin de la clase interna anónima
        ); // Fin de la llamada a addActionListener

        // Configuración final de la ventana
        setSize(800, 400); // Tamaño de la ventana
        setLocationRelativeTo(null); // Centrar la ventana en pantalla
        setVisible(true); // Hacer la ventana visible
    } // Fin del constructor de CrearArchivoSecuencial

    // Permitir al usuario especificar el nombre del archivo
    private void abrirArchivo() {
        // Mostrar cuadro de diálogo de archivo para elegir archivo
        JFileChooser selectorArchivo = new JFileChooser();
        selectorArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY); // Solo archivos, no carpetas
        int resultado = selectorArchivo.showSaveDialog(this); // Mostrar cuadro de guardar

        // Si el usuario canceló la selección, no hacer nada
        if (resultado == JFileChooser.CANCEL_OPTION)
            return;

        // Obtener archivo seleccionado
        File nombreArchivo = selectorArchivo.getSelectedFile();

        // Mostrar error si el nombre de archivo es inválido
        if (nombreArchivo == null || nombreArchivo.getName().equals(""))
            JOptionPane.showMessageDialog(this, "Nombre de archivo inválido",
                    "Nombre de archivo inválido", JOptionPane.ERROR_MESSAGE);
        else { // Si el nombre es válido
            try {
                salida = new ObjectOutputStream(new FileOutputStream(nombreArchivo)); // Crear flujo de salida
                botonAbrir.setEnabled(false);  // Desactivar botón de abrir
                botonIntro.setEnabled(true);   // Activar botón de introducir
                botonSalir.setEnabled(true);   // Activar botón de salir
            }
            // Procesar errores al abrir el archivo
            catch (IOException excepcionES) {
                JOptionPane.showMessageDialog(this, "Error al abrir el archivo",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } // Fin de else
    } // Fin del método abrirArchivo

    // Cerrar archivo y terminar la aplicación
    private void cerrarArchivo() {
        try {
            salida.close(); // Cerrar flujo de salida
            System.exit(0); // Terminar el programa
        }
        // Procesar excepciones al cerrar el archivo
        catch (IOException excepcionES) {
            JOptionPane.showMessageDialog(this, "Error al cerrar el archivo",
                    "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    } // Fin del método cerrarArchivo

    // Agregar un registro al archivo
    public void agregarRegistro() {
        int numeroCuenta = 0; // Número de control del alumno
        RegistroCalif registro; // Objeto a guardar
        String valoresCampos[] = interfazUsuario.obtenerValoresCampos(); // Obtener datos de la interfaz

        // Si el campo de número de control no está vacío
        if (!valoresCampos[Plantilla.NUMERODECONTROL].equals("")) {
            try {
                // Convertir el número de control a entero
                numeroCuenta = Integer.parseInt(valoresCampos[Plantilla.NUMERODECONTROL]);
                if (numeroCuenta > 0) { // Validar número positivo
                    // Crear un nuevo registro con los datos ingresados
                    registro = new RegistroCalif(numeroCuenta,
                            valoresCampos[Plantilla.NOMBRE],
                            Integer.parseInt(valoresCampos[Plantilla.CALIF1]),
                            Integer.parseInt(valoresCampos[Plantilla.CALIF2]),
                            Integer.parseInt(valoresCampos[Plantilla.CALIF3]));

                    // Escribir el objeto en el archivo
                    salida.writeObject(registro);
                    salida.flush(); // Asegurar que se escriba en disco
                } else {
                    // Mostrar error si el número es inválido
                    JOptionPane.showMessageDialog(this,
                            "El Num. de la Cta. debe ser mayor que 0",
                            "Num. de la Cta. incorrecto", JOptionPane.ERROR_MESSAGE);
                }

                // Borrar campos de la interfaz
                interfazUsuario.borrarCampos();
            }
            // Procesar error de formato en los datos ingresados
            catch (NumberFormatException excepcionFormato) {
                JOptionPane.showMessageDialog(this, "Num. de la Cta. o Saldo incorrecto",
                        "Formato de número incorrecto",
                        JOptionPane.ERROR_MESSAGE);
            }
            // Procesar errores al escribir en el archivo
            catch (IOException excepcionES) {
                JOptionPane.showMessageDialog(this, "Error al escribir en el archivo",
                        "Excepción de ES", JOptionPane.ERROR_MESSAGE);
                cerrarArchivo(); // Cerrar archivo en caso de error
            }
        } // Fin de if
    } // Fin del método agregarRegistro

    // Método principal
    public static void main(String args[]) {
        CrearArchivoSecuencial Cas = new CrearArchivoSecuencial(); // Crear ventana
        Cas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar al cerrar la ventana
    }
} // Fin de la clase CrearArchivoSecuencial
