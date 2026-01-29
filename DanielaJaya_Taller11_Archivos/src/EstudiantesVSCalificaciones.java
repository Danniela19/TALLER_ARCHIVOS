import java.io.File;
import java.util.Formatter;
import java.util.Scanner;

public class EstudiantesVSCalificaciones {
    public static void main(String[] args) {
        int m = 10, n = 3;
        String matrizNotasIN[][] = new String[m][n];
        String matrizNotasOUTT[][] = new String[m][n];
        matrizNotasIN = leerArchivo_LlenarMatriz("NotasEST.csv", m, n);
        matrizNotasOUTT = procesarNotaIn(matrizNotasIN, m, n);
        persistirResultados("NotasSalida.csv", matrizNotasIN, matrizNotasOUTT);
    }
public static void persistirResultados(String nombreArchivoOut, String matrizNotasIN[][], String matrizNotasOUTT[][]) {
    try {
        Formatter fout = new Formatter(new File(nombreArchivoOut));
        fout.format("NOMBRE;NOTA1;NOTA2;PROMEDIO;ESTADO;BAJO_MEDIA;\n");
        double sumaPromedios = 0;
        int cont = 0;
        for (int i = 0; i < matrizNotasIN.length; i++) {
            if (matrizNotasIN[i][0] == null) break;
            double promedio = Double.valueOf(matrizNotasOUTT[i][0]);
            sumaPromedios = sumaPromedios + promedio;
            cont = cont + 1;
        }
        double media = sumaPromedios / cont;
        int aprobados = 0;
        int reprobados = 0;
        for (int i = 0; i < matrizNotasIN.length; i++) {
            if (matrizNotasIN[i][0] == null) break;
            String nombre = matrizNotasIN[i][0];
            String nota1 = matrizNotasIN[i][1];
            String nota2 = matrizNotasIN[i][2];
            String promedio = matrizNotasOUTT[i][0];
            String estado = matrizNotasOUTT[i][1];
            double prom = Double.valueOf(promedio);
            String bajoMedia;
            if (prom < media) {
                bajoMedia = "SI";
            } else {
                bajoMedia = "NO";
            }
            if (estado.equals("APROBADO")) {
                aprobados = aprobados + 1;
            } else {
                reprobados = reprobados + 1;
            }
            fout.format("%s;%s;%s;%s;%s;%s;\n",nombre, nota1, nota2, promedio, estado, bajoMedia);
        }
        fout.format("\n");
        fout.format("APROBADOS;%d\n", aprobados);
        fout.format("REPROBADOS;%d\n", reprobados);
        fout.format("MEDIA GENERAL DEL CURSO;%.2f\n", media);

        fout.close();
        } catch (Exception e) {
            System.out.println("ERROR");
        }
    }
    public static String[][] procesarNotaIn(String matrizNotasIN[][], int m, int n) {
        String datosNotas[][] = new String[m][2];
        for (int i = 0; i < matrizNotasIN.length; i++) {
            if (matrizNotasIN[i][0] == null) break;
            double n1 = Double.valueOf(matrizNotasIN[i][1]);
            double n2 = Double.valueOf(matrizNotasIN[i][2]);
            double promedio = (n1 + n2) / 2;
            String estado;
            if (promedio >= 7) {
                estado = "APROBADO";
            } else {
                estado = "REPROBADO";
            }
            datosNotas[i][0] = String.valueOf(promedio);
            datosNotas[i][1] = estado;
        }
        return datosNotas;
    }
    public static String[][] leerArchivo_LlenarMatriz(String nombreArchivo, int m, int n) {
        String datosNotas[][] = new String[m][n];
        int i = 0;
        try {
            Scanner fin = new Scanner(new File(nombreArchivo));
            String linea;
            linea = fin.nextLine(); 
            while (fin.hasNext()) {
                linea = fin.nextLine();
                String datos[] = linea.split(";");
                datosNotas[i][0] = datos[0]; 
                datosNotas[i][1] = datos[1];
                datosNotas[i][2] = datos[2];
                i++;
            }
        } catch (Exception e) {
            System.out.println("ERROR2");
        }
        return datosNotas;
    }
}
