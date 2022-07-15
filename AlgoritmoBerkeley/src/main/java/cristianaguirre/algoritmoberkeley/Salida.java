package cristianaguirre.algoritmoberkeley;

/**
 *
 * @author Cristian Aguirre
 */
public class Salida {
    private long tiempoServidor;
    private long[] difTiempos;
    private long sumaDifs;
    private final int nroClientes = 3;
    private int auxClientesOperando;
    
    public Salida(){
        this.tiempoServidor = 0;
        this.difTiempos = new long[nroClientes];
        this.sumaDifs = 0;
        this.auxClientesOperando = this.nroClientes;
    }
    
    public synchronized void setTiempoServidor(long tiempoServidor){
        this.tiempoServidor = tiempoServidor;
        try {
            notifyAll();
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public synchronized void setDifTiempos(long tiempo, int n){
        try {
            if(tiempoServidor==0) wait();
            this.difTiempos[n] = (tiempo-tiempoServidor);
            this.sumaDifs += tiempo;
            auxClientesOperando--;
            
            if(auxClientesOperando == 0) notify();
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public synchronized void setCalPromedio(){
        long promedio = (this.sumaDifs/(this.nroClientes+1));
        for (int i = 0; i < this.nroClientes; i++) {
            this.difTiempos[i]=((-this.difTiempos[i])+promedio);
            notifyAll();
        }
    }
    
    public synchronized long getConfTiempo(int n){
        return this.difTiempos[n];
    }
    
    public synchronized long getPromedio(){
        return this.sumaDifs/(this.nroClientes+1);
    }
    
    public synchronized void reinicioProceso(){
        this.tiempoServidor = 0;
        this.auxClientesOperando = this.nroClientes;
        this.sumaDifs=0;
    }
}
