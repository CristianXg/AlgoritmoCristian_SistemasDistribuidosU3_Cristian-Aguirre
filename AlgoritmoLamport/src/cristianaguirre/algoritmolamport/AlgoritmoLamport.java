package cristianaguirre.algoritmolamport;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cristian Aguirre
 */
public class AlgoritmoLamport {
    private static final int Num = 10;
    private volatile static int numeros[] = new int[Num];
    private volatile static boolean escogiendoNum[]= new boolean[Num];
    
    public static Thread nuevoHilo(final int i){
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                escogiendoNum[i] = true;
                numeros[i] = 1 + max(numeros);
                escogiendoNum[i] = false;
            
                for (int j = 0; j < Num; j++) {
                    while(escogiendoNum[j]);
                    
                    while((numeros[j]!=0)&&((numeros[j]<numeros[i])
                            ||((numeros[j]==numeros[i])&&(j<i))));
                }
                System.out.println("("+i+")PROCESO 1: Ejecución de prueba en el primer proceso");
                System.out.println("("+i+")PROCESO 2: Otra frase más luego de la primera");
                System.out.println("("+i+")PROCESO 3: Un algortimo corriendo exitosamente");
                numeros[i] = 0;
                
                System.out.println("("+i+")PROCESO X: Distinto no es malo");
                System.out.println("("+i+")PROCESO Y: Similar no siempre es bueno");
            }
        },"HILO "+i);
        return hilo;
    }
    
    public static int max(int[] numeros){
        int maximo = 0;
        for (int i = 0; i < numeros.length; i++) {
            maximo += numeros[i];
        }
        return maximo;
    }

    public static void main(String[] args) {
        List<Thread> lista = new ArrayList<Thread>();
        for (int i = 0; i < Num; i++) {
            Thread thr =  nuevoHilo(i);
            lista.add(thr);
        }
        for (int i = 0; i < Num; i++) {
            Thread thr = lista.get(i);
            thr.start();
        }
    }
}