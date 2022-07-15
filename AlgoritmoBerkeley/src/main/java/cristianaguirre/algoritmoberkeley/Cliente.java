package cristianaguirre.algoritmoberkeley;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author Cristian Aguirre
 */
public class Cliente extends Thread{
    private int idCliente;
    private long tiempoCliente;
    private Salida sda;
    private final boolean agrDemora = true;
    
    public Cliente(int idCliente, Salida sda){
        this.sda =  sda;
        this.idCliente = idCliente;
        this.tiempoCliente = System.nanoTime();//BUSCAR PARA QUE SIRVE nanoTime
    }
    public void run(){
        while (true) {            
            long convert = TimeUnit.SECONDS.convert(tiempoCliente, TimeUnit.NANOSECONDS);//Para convertir de Segundos a Nanosegundos
            this.tiempoCliente += (this.agrDemora)?(this.idCliente+1)*2:0;
            System.out.println("(Cliente =>"+idCliente+" ) : "+this.tiempoCliente);
            this.sda.setDifTiempos(this.tiempoCliente,this.idCliente);
            this.tiempoCliente += this.sda.getConfTiempo(this.idCliente);//getSettingTime en SALIDA
            System.out.println("(Cliente =>"+idCliente+") : "+this.tiempoCliente);
        }
        
    }
    
}
