import javax.swing.*;

public class MenuPrincipal {
    public MenuPrincipal() {
        String[] opc = { "Crear Archivo Secuencial", "Leer Archivo Secuencial", "Consultar Calificaciones", "Salir" };
        int opcion = JOptionPane.showOptionDialog(null, "Seleccione una opción", "Menú Principal",
                1, 1, null, opc, opc[0]);
        switch (opcion) {
            case 0:
                new CrearArchivoSecuencial();
                break;
            case 1:
                new LeerArchivoSecuencial();
                break;
            case 2:
                new ConsultaCalif();
                break;
            case 3:
            case JOptionPane.CLOSED_OPTION:
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción no válida");
        }
    }

    public static void main(String[] args) {
        new MenuPrincipal();
    }
}