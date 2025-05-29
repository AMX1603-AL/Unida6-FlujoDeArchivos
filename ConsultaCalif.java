
//Ejemplo 6.10
// Este programa lee un archivo en forma secuencial y muestra su contenido en un
// área de texto, con base en el tipo de cuenta que el usuario solicita
// (saldo con crédito, saldo con débito o saldo en ceros).
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ConsultaCalif extends JFrame {
    private JTextArea areaMostrarRegistros;
    private JButton botonAbrir, botonCredito, botonDebito, botonCeros, botonSalir;
    private JPanel panelBotones;
    private ObjectInputStream entrada;
    private FileInputStream entradaArchivo;
    private File nombreArchivo;
    private String tipoCuenta;

    // configurar GUI
    public ConsultaCalif() {
        super("Programa de consulta de Calificaciones");
        Container contenedor = getContentPane();
        panelBotones = new JPanel(); // configurar panel para agregarle botones
        // crear y configurar botón para abrir el archivos
        botonAbrir = new JButton("Abrir archivo");
        panelBotones.add(botonAbrir);
        // registrar componente de escucha de botonAbrir
        botonAbrir.addActionListener(
                // clase interna anónima para manejar evento de botonAbrir
                new ActionListener() {
                    // abrir archivo para procesarlo
                    public void actionPerformed(ActionEvent evento) {
                        abrirArchivo();
                    }
                } // fin de la clase interna anónima
        ); // fin de la llamada a addActionListener
           // crear y configurar botón para obtener cuentas con saldos con crédito
        botonCredito = new JButton("Aprobados");
        panelBotones.add(botonCredito);
        botonCredito.addActionListener(new ManejadorBotones());
        // crear y configurar botón para obtener cuentas con saldos con débito
        botonDebito = new JButton("Reprobados");
        panelBotones.add(botonDebito);
        botonDebito.addActionListener(new ManejadorBotones());
        // crear y configurar botón para obtener cuentas con saldos en ceros
        botonCeros = new JButton("Dados de baja");
        panelBotones.add(botonCeros);
        botonCeros.addActionListener(new ManejadorBotones());
        botonSalir = new JButton("Salir");
        panelBotones.add(botonSalir);
        botonSalir.addActionListener(
                // clase interna anónima para manejar evento de botonSalir
                new ActionListener() {
                    // cerrar el programa
                    public void actionPerformed(ActionEvent evento) {
                        System.exit(0);
                    }
                } // fin de la clase interna anónima
        ); // fin de la llamada a addActionListener
        // establecer área para mostrar resultados
        areaMostrarRegistros = new JTextArea();
        JScrollPane desplazador = new JScrollPane(areaMostrarRegistros);
        // adjuntar componentes al panel de contenido
        contenedor.add(desplazador, BorderLayout.CENTER);
        contenedor.add(panelBotones, BorderLayout.SOUTH);
        botonCredito.setEnabled(false); // deshabilitar botonCredito
        botonDebito.setEnabled(false); // deshabilitar botonDebito
        botonCeros.setEnabled(false);
        botonSalir.setEnabled(false); // deshabilitar botonCeros
        // registrar componente de escucha de ventana
        addWindowListener(
                // clase interna anónima para evento windowClosing
                new WindowAdapter() {
                    // cerrar archivo y terminar el programa
                    public void windowClosing(WindowEvent evento) {
                        cerrarArchivo();
                        System.exit(0);
                    }
                } // fin de la clase interna anónima
        ); // fin de la llamada a addWindowListener
        pack(); // empaquetar componentes y mostrar ventana
        setSize(600, 250);
        setLocationRelativeTo(null);
        setVisible(true);
    } // fin del constructor de ConsultaCreditos
      // permitir al usuario seleccionar el archivo a abrir

    private void abrirArchivo() {
        // mostrar cuadro de diálogo, para que el usuario pueda seleccionar el archivo
        JFileChooser selectorArchivo = new JFileChooser();
        selectorArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int resultado = selectorArchivo.showOpenDialog(this);
        // si el usuario hizo clic en el botón Cancelar del cuadro de diálogo, regresar
        if (resultado == JFileChooser.CANCEL_OPTION)
            return;
        nombreArchivo = selectorArchivo.getSelectedFile(); // obtener archivo seleccionado
        // mostrar error si el nombre de archivo es incorrecto
        if (nombreArchivo == null || nombreArchivo.getName().equals(""))
            JOptionPane.showMessageDialog(this, "Nombre de archivo incorrecto",
                    "Nombre de archivo incorrecto", JOptionPane.ERROR_MESSAGE);
        // abrir el archivo
        try {
            // cerrar archivo de la operación anterior
            if (entrada != null)
                entrada.close();
            entradaArchivo = new FileInputStream(nombreArchivo);
            entrada = new ObjectInputStream(entradaArchivo);
            botonAbrir.setEnabled(false);
            botonCredito.setEnabled(true);
            botonDebito.setEnabled(true);
            botonCeros.setEnabled(true);
            botonSalir.setEnabled(true);
        }
        // atrapar problemas que pueden ocurrir al manipular el archivo
        catch (IOException excepcionES) {
            JOptionPane.showMessageDialog(this, "El archivo no existe",
                    "Nombre de archivo incorrecto", JOptionPane.ERROR_MESSAGE);
        }
    } // fin del método abrirArchivo
      // cerrar archivo antes de que termine la aplicación

    private void cerrarArchivo() { // cerrar el archivo
        try {
            if (entrada != null)
                entrada.close();
        }
        // procesar excepciones que puedan ocurrir al cerrar el archivo
        catch (IOException excepcionES) {
            JOptionPane.showMessageDialog(this, "Error al cerrar el archivo",
                    "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    } // fin del método cerrarArchivo
      // leer registros del archivo y mostrar sólo los registros del tipo apropiado

    private void leerRegistros() {
        RegistroCalif registro;
        // leer registros
        try {
            if (entrada != null)
                entrada.close();
            entradaArchivo = new FileInputStream(nombreArchivo);
            entrada = new ObjectInputStream(entradaArchivo);
            areaMostrarRegistros.setText("Las cuentas son:\n");
            // recibir como entrada los valores del archivo
            while (true) {
                // leer un RegistroCuentas
                registro = (RegistroCalif) entrada.readObject();
                // si es el tipo de cuenta apropiado, mostrar el registro
                if (debeMostrarse(registro.ObtenerProm())) areaMostrarRegistros.append(registro.ObtenerNumeroDeControl() + "\t" + registro.ObtenerNombre() + "\t" +"\t"+ (registro.ObtenerProm()) + "\t" + registro.ObtenerCalif1() + "\t" + registro.ObtenerCalif2() + "\t" + registro.ObtenerCalif3() + "\n");
            }
        } // fin del bloque try
          // cerrar archivo cuando se llega al fin de archivo
        catch (EOFException excepcionEOF) {
            cerrarArchivo();
        }
        // mostrar error si no se puede leer el objeto por no encontrar la clase
        catch (ClassNotFoundException claseNoEncontrada) {
            JOptionPane.showMessageDialog(this, "No se pudo crear el objeto",
                    "Clase no encontrada", JOptionPane.ERROR_MESSAGE);
        }
        // mostrar error si no se puede leer debido a un problema con el archivo
        catch (IOException excepcionES) {
            JOptionPane.showMessageDialog(this, "Error al leer del archivo",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    } // fin del método leerRegistros
      // use el tipo del registro para determinar si el registro debe mostrarse

    private boolean debeMostrarse(double saldo) {
        if (tipoCuenta.equals("Aprobados") && saldo >= 70)
            return true;
        else if (tipoCuenta.equals("No Aprobados") && saldo <= 60)
            return true;
        else if (tipoCuenta.equals("Dados de  baja") && saldo == 0)
            return true;
        return false;
    }

    public static void main(String args[]) {
        new ConsultaCalif();
    }

    // clase para el manejo de eventos de botonCredito, botonDebito y botonCeros
    private class ManejadorBotones implements ActionListener {
        // leer registros del archivo
        public void actionPerformed(ActionEvent evento) {
            tipoCuenta = evento.getActionCommand();
            leerRegistros();
        }
    } // fin de la clase ManejadorBotones
} // fin de la clase ConsultaCreditos