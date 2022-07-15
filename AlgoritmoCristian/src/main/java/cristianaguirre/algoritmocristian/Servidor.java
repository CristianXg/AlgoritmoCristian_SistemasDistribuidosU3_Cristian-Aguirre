package cristianaguirre.algoritmocristian;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Cristian Aguirre
 */
public class Servidor {

    public static void main(String[] args) throws IOException {
        String puerto;
        BufferedReader toIn = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("... Ingresa el nro de puerto ...");
        puerto = toIn.readLine();

        int numeroPuerto = Integer.parseInt(puerto);
        try {
            ServerSocket socketServidor = new ServerSocket(numeroPuerto);
            Socket socketCliente = socketServidor.accept();
            PrintWriter out = new PrintWriter(socketCliente.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));

            String inputLine;
            System.out.println("EL SERVIDOR HA SIDO INICIADO");
            while (true) {
                inputLine = in.readLine();
                if (inputLine.equalsIgnoreCase("Exit")) {
                    System.out.println("Saliendo del servidor");
                    out.println("HAS SALIDO DEL SERVIDOR");
                    break;
                }
                out.println(System.currentTimeMillis() + 5000);
            }
        } catch (IOException e) {
            System.out.println("TIEMPO DE ESPERA AGOTADO");
        }
    }
}
