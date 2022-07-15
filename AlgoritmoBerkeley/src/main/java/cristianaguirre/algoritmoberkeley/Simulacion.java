package cristianaguirre.algoritmoberkeley;

/**
 *
 * @author Cristian Aguirre
 */
public class Simulacion {

    public static void main(String[] args) {
        System.out.println("********** ALGORITMO DE BERKELEY ***********");
        System.out.println("**** Tiempos iniciales en los clientes *****");
        Salida simuTiempo = new Salida();
        Servidor serv = new Servidor(simuTiempo);
        
        serv.start();
        
        Cliente aux[] = new Cliente[3];
        for (int i = 0; i < 3; i++) {
            aux[i] = new Cliente(i, simuTiempo);
            aux[i].start();
        }
    }
}