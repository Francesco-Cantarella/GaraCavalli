import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Cantarella Francesco
 */
public class Cavallo extends Thread {
    private String nome;
    private final int distanza;//lungezza totale del percorso
    private  int partenza = 0;//
    private static final int PASSO = 5; //2 metri
    private final Random random = new Random();

    private static List<String> risultati = (new ArrayList<>());
    /**
     * @param nome nome del cavallo
     * @param distanza lunghezza totale del percorso in metri
     */
    public Cavallo(String nome, int distanza) {
        this.nome = nome;
        this.distanza = distanza;
    }

    @Override
    public void run() {
        setName("Cavallo:" + nome);
        System.out.println(getName() + " parte");
        while (partenza < distanza) {
            partenza += PASSO;
            System.out.println(nome + " ha percorso " + partenza + " di  " + distanza + "m");
            try {
                int pausa = random.nextInt(5) + 1; // valore da 1 a 5
                Thread.sleep(pausa * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        synchronized (risultati) {
            risultati.add(nome);
        }


    }
    /**
     * Restituisce la classifica.
     * @return lista con i nomi dei cavalli in ordine di arrivo
     */
    // ottenere la classifica finale
    public static List<String> getRisultato() {
        return new ArrayList<>(risultati);

    }
    /** @return nome del cavallo */
    public String getNome() {
        return nome;
    }

}
