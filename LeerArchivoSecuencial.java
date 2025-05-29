// Jesus Armando Diaz Santoyo
// Este programa lee un archivo de objetos en forma secuencial
// y muestra cada uno de los registros.

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LeerArchivoSecuencial extends JFrame {
    // Variables de instancia
    private ObjectInputStream entrada;  // Para leer objetos del archivo
    private Plantilla interfazUsuario;  // Interfaz gráfica reutilizable
    private File nombreArchivo;        // Archivo seleccionado
    private int posicionActual = 0;    // Para controlar la posición actual en el archivo

    // Botones de la interfaz
    private JButton botonSiguiente, botonAbrir, botonRegresar, botonSalir;

    // Constructor -- inicializar el marco
    public LeerArchivoSecuencial() {
        super("Leer un archivo secuencial de objetos");

        // Crear la interfaz de usuario con 6 campos de texto (Num Ctrl, Nombre, Calif1, Calif2, Calif3, Prom)
        interfazUsuario = new Plantilla(6);
        getContentPane().add(interfazUsuario, BorderLayout.CENTER);

        // Configurar botones reutilizados de la interfaz
        botonAbrir = interfazUsuario.obtenerBotonHacerTarea1();
        botonAbrir.setText("Abrir archivo");
        // Evento para abrir archivo
        botonAbrir.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent evento) {
                    abrirArchivo();
                }
            }
        );

        // Evento para el cierre de la ventana: cerrar archivo y salir
        addWindowListener(
            new WindowAdapter() {
                public void windowClosing(WindowEvent evento) {
                    if (entrada != null) cerrarArchivo();
                    cerrarArchivo();
                    new MenuPrincipal();
                }
            }
        );

        // Configurar el resto de botones
        botonSiguiente = interfazUsuario.obtenerBotonHacerTarea2();
        botonSiguiente.setText("Siguiente registro");
        botonSiguiente.setEnabled(false); // Desactivado inicialmente

        botonRegresar = interfazUsuario.obtenerBotonHacerTarea3();
        botonRegresar.setText("Regresar registro");
        botonRegresar.setEnabled(false); // Desactivado inicialmente

        botonSalir = interfazUsuario.obtenerBotonHacerTarea4();
        botonSalir.setText("Salir");
        botonSalir.setEnabled(false); // Desactivado inicialmente

        // Evento: Leer siguiente registro
        botonSiguiente.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent evento) {
                    leerRegistro();
                }
            }
        );

        // Evento: Regresar al registro anterior
        botonRegresar.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent evento) {
                    regresarRegistro();
                }
            }
        );

        // Evento: Salir del programa
        botonSalir.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent evento) {
                    dispose(); // Cerrar la ventana actual
                    new MenuPrincipal();
                }
            }
        );

        // Configuración de la ventana principal
        pack();
        setSize(800, 400);
        setLocationRelativeTo(null); // Centrar la ventana
        setVisible(true);
    } // fin del constructor

    // Método para abrir un archivo seleccionado por el usuario
    private void abrirArchivo() {
        JFileChooser selectorArchivo = new JFileChooser();
        selectorArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int resultado = selectorArchivo.showOpenDialog(this);

        // Si el usuario cancela, no hacer nada
        if (resultado == JFileChooser.CANCEL_OPTION) return;

        // Obtener el archivo seleccionado
        nombreArchivo = selectorArchivo.getSelectedFile();

        // Validar nombre de archivo
        if (nombreArchivo == null || nombreArchivo.getName().equals("")) {
            JOptionPane.showMessageDialog(this, "Nombre de archivo incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Intentar abrir el archivo
            try {
                entrada = new ObjectInputStream(new FileInputStream(nombreArchivo));
                // Activar los botones de navegación
                botonAbrir.setEnabled(false);
                botonSiguiente.setEnabled(true);
                botonRegresar.setEnabled(true);
                botonSalir.setEnabled(true);
            } catch (IOException excepcionES) {
                JOptionPane.showMessageDialog(this, "Error al abrir el archivo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Leer un registro del archivo
    public void leerRegistro() {
        RegistroCalif registro;

        try {
            // Leer el siguiente objeto del archivo
            registro = (RegistroCalif) entrada.readObject();

            // Crear arreglo de String con los valores a mostrar
            String valores[] = {
                String.valueOf(registro.ObtenerNumeroDeControl()),
                registro.ObtenerNombre(),
                String.valueOf(registro.ObtenerCalif1()),
                String.valueOf(registro.ObtenerCalif2()),
                String.valueOf(registro.ObtenerCalif3()),
                String.valueOf(registro.ObtenerProm())
            };

            // Mostrar los datos en la GUI
            interfazUsuario.establecerValoresCampos(valores);
            posicionActual++; // Avanzar a la siguiente posición
            System.out.println("Posicion actual: " + posicionActual);

        } catch (EOFException excepcionFinDeArchivo) {
            // Fin del archivo
            botonSiguiente.setEnabled(false);
            JOptionPane.showMessageDialog(this, "No hay más registros en el archivo", "Fin del archivo", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException excepcionClaseNoEncontrada) {
            // Error si la clase del objeto no se encuentra
            JOptionPane.showMessageDialog(this, "No se pudo crear el objeto", "Clase no encontrada", JOptionPane.ERROR_MESSAGE);
        } catch (IOException excepcionES) {
            // Error de lectura general
            JOptionPane.showMessageDialog(this, "Error al leer del archivo", "Error de lectura", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para regresar al registro anterior
    public void regresarRegistro() {
        if (posicionActual <= 1) {
            // No se puede regresar más
            JOptionPane.showMessageDialog(this, "Estás en el primer registro", "Inicio del archivo", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Retroceder 2 posiciones porque se leerá un registro al final
        posicionActual = posicionActual - 2;
        if (posicionActual < 0) posicionActual = 0;

        try {
            // Volver a abrir el archivo desde el inicio
            entrada = new ObjectInputStream(new FileInputStream(nombreArchivo));

            // Leer hasta la posición deseada
            for (int i = 0; i <= posicionActual; i++) {
                RegistroCalif registro = (RegistroCalif) entrada.readObject();
                String valores[] = {
                    String.valueOf(registro.ObtenerNumeroDeControl()),
                    registro.ObtenerNombre(),
                    String.valueOf(registro.ObtenerCalif1()),
                    String.valueOf(registro.ObtenerCalif2()),
                    String.valueOf(registro.ObtenerCalif3()),
                    String.valueOf(registro.ObtenerProm())
                };
                interfazUsuario.establecerValoresCampos(valores);
            }

            // Preparar para la siguiente lectura
            posicionActual++;
            System.out.println("Posición actual: " + posicionActual);
            botonSiguiente.setEnabled(true);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al reabrir el archivo", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Error al reabrir el archivo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Cerrar el archivo y salir
    private void cerrarArchivo() {
        try {
            entrada.close();
            dispose(); // Volver al menú principal
        } catch (IOException excepcionES) {
            JOptionPane.showMessageDialog(this, "Error al cerrar el archivo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método principal: crear la ventana
    public static void main(String args[]) {
        new LeerArchivoSecuencial();
    }
} // Fin de la clase LeerArchivoSecuencial
