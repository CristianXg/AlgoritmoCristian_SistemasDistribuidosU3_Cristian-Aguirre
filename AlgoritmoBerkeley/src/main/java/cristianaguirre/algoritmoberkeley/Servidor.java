package cristianaguirre.algoritmoberkeley;
/**
 *
 * @author Cristian Aguirre
 */
public class Servidor extends Thread {

    private Salida sda;
    private final int sleepSegundos;
    private long tiempoServidor;

    public Servidor(Salida sda) {
        this.sda = sda;
        this.sleepSegundos = 3000;
        this.tiempoServidor = System.nanoTime();
    }
    public void run(){
        while (true) {
            try {
                Thread.sleep(this.sleepSegundos);
                System.out.println("Temporizando(Servidor): "+this.tiempoServidor);
                this.sda.setTiempoServidor(this.tiempoServidor);
                this.sda.setCalPromedio();
                this.tiempoServidor += this.sda.getPromedio();
                System.out.println("Temporizador acordado(Servidor): "+this.tiempoServidor);
                this.sda.reinicioProceso();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }
    }
}
