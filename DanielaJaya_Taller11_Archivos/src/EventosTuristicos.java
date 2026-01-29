import java.io.File;
import java.util.Formatter;
import java.util.Random;
import java.util.Scanner;

public class EventosTuristicos {
    public static void main(String[] args) {
        int n = 7;
        String[][] entrada = new String[n][3];
        String[][] salida = new String[n][8];
        generarEntrada(entrada, "EventosIn.csv");
        generarSalida(entrada, salida);
        persistirResultados("EventosOut.csv", salida);
    }
    
    public static void generarEntrada(String[][] in, String nombreArchivo) {
        try {
            Scanner fin = new Scanner(new File(nombreArchivo));
            fin.nextLine(); 
            int i = 0;
            while (fin.hasNextLine() && i < in.length) {
                String linea = fin.nextLine();
                String[] datos = linea.split(";");
                in[i][0] = datos[0];
                in[i][1] = datos[1];
                in[i][2] = datos[2];
                i++;
            }
            fin.close();
        } catch (Exception e) {
            System.out.println("Error al leer archivo.");
        }
    }

    public static String regalo(String modalidad) {
        if (modalidad.equals("Pagado")) return "Gorros";
        return "Lapiceros";
    }

    public static String generoPermitido(String modalidad) {
        if (modalidad.equals("Gratuito")) return "Hombres";
        return "Mujeres";
    }

    public static void generarSalida(String[][] in, String[][] out) {
        Random aleatorio = new Random();
        for (int i = 0; i < in.length; i++) {
            int ventaLinea = aleatorio.nextInt(4001);   
            int ventaPuntos = aleatorio.nextInt(4001);
            int gastos = aleatorio.nextInt(2001);
            for (int j = 0; j < 3; j++)
                out[i][j] = in[i][j];
                out[i][3] = String.valueOf(ventaLinea);
                out[i][4] = String.valueOf(ventaPuntos);
                out[i][5] = String.valueOf(gastos);
                out[i][6] = regalo(in[i][2]);
                out[i][7] = generoPermitido(in[i][2]);
        }
    }
    public static void persistirResultados(String nombreArchivoOut, String[][] out) {
        try {
            Formatter fout = new Formatter(new File(nombreArchivoOut));
            fout.format("EV;CIUDAD;MODALIDAD;VENTA_LINEA;VENTA_PUNTOS;GASTOS;REGALOS;GENERO_PERMITIDO;\n");
            for (int i = 0; i < out.length; i++) {
                for (int j = 0; j < out[0].length; j++) {
                    fout.format("%s;", out[i][j]);
                }
                fout.format("\n");
            }
            fout.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}

