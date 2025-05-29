import javax.swing.*;

public class MenuPrincipal {
    public static void main(String[] args) {
        JOptionPane JO = new JOptionPane();
        String[] opc = { "Crear Archivo Secuencial", "Leer Archivo Secuencial", "Consultar Calificaciones", "Salir" };
        int opcion = JO.showOptionDialog(null, "Seleccione una opción", "Menú Principal",
                1, 1, null, opc, opc[0]);
        switch (opcion) {
            case 0
                new CrearArchivoSecuencial();
                break;
            case 1:
                new LeerArchivoSecuencial();
                break;
            case 2:
                new ConsultaCalif();
                break;
            case 3: 
            System.exit(0);
                break;
            default:
                JO.showMessageDialog(null, "Opción no válida");
        }
    }

}
