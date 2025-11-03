import java.util.ArrayList;
import java.util.List;

public class Cavallo extends Thread {
    private String nome;
    private final int distanza;//lungezza totale del percorso
    private  int partenza = 0;//
    private static final int PASSO = 5; //2 metri
    private static final int RIPOSO_MS = 2000; // 2 sec


    private static List<String> risultati = (new ArrayList<>());

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
                Thread.sleep(RIPOSO_MS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        synchronized (risultati) {
            risultati.add(nome);
        }


    }

    // ottenere la classifica finale
    public static List<String> getRisultato() {
        return new ArrayList<>(risultati);

    }
    // get
    public String getNome() {
        return nome;
    }

}
