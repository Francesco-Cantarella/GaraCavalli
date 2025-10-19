import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner tastiera = new Scanner(System.in);
        System.out.print("Inserisci la lunghezza del percorso (in metri): ");
        int distanza = tastiera.nextInt();

        int ncavalli = 5;
        // crea e avvia 5 thread Cavallo
        Cavallo[] cavalli = new Cavallo[ncavalli];
        for (int i = 0; i < ncavalli; i++) {
            System.out.print("Inserisci nome cavallo: ");
            String nome = tastiera.next();
            cavalli[i] = new Cavallo(nome, distanza);

        }
        //faccio partire i cavalli
        for (int i = 0; i < ncavalli; i++) {
            cavalli[i].start();
        }
        //per fare apsettare gli latri tread la fine di tutti
        for (int i = 0; i < ncavalli; i++) {
            cavalli[i].join();
        }
        // stampa classifica finale
        List<String> classifica = Cavallo.getRisultato();
        System.out.println("classifica finale");
        for (int i = 0; i < classifica.size(); i++) {
            System.out.println((i + 1) + " " + classifica.get(i));
        }
    }
}
