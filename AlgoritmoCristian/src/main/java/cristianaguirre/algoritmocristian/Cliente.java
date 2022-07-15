package cristianaguirre.algoritmocristian;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Cristian Aguirre
 */
public class Cliente {
    public static void main(String[] args) throws IOException {
        String puerto, nombreHost;
        BufferedReader toIn = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("... Ingresa el n[umero de puerto ...");
        puerto = toIn.readLine();
        
        int numeroPuerto = Integer.parseInt(puerto);
        System.out.println("... Ingresa el nombre del host ...");
        nombreHost = toIn.readLine();
        try {
            Socket echoSocket = new Socket(nombreHost,numeroPuerto);
            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            
            String inputUsuario;
            System.out.println("... CLIENTE INICIADO ...");
            System.out.println("Ingresa=> Exit, para salir...");
            
            long TiempoCero;
            long Tiempo_Servidor;
            long TiempoUno;
            long Tiempo_total;
            
            out.println(TiempoCero=System.currentTimeMillis());
            Tiempo_Servidor = Long.parseLong(in.readLine());
            TiempoUno = System.currentTimeMillis();
            Tiempo_total= (Tiempo_Servidor+(TiempoUno-TiempoCero))/2;
            DateFormat formato = new SimpleDateFormat("HH:mm:ss:SSS");
            
            System.out.println("Tiempo del CLIENTE = "+formato.format(new Date(TiempoUno)));
            System.out.println("Tiempo del SERVIDOR = "+formato.format(new Date(Tiempo_Servidor)));
            System.out.println("Tiempo del CLIENTE luego del REINICIO ===> "+formato.format(new Date(Tiempo_total)));
            out.println("FIN DEL PROCESO");
        } catch (Exception e) {
            System.out.println("Tiempo Agotado");            
        }
    }
}
