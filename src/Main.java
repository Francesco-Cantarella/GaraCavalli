/* NOTA PERSONALE
All'inizio pensavo di dover aggiungere  il try-catch per Thread.sleep(), ma penso che devo agugerlo anche in altri punit anche se non so dove.
Ho scoperto che Thread.sleep() crea dei problemi:
Mettendo  sleep fisso di 2 secondi per tutti i cavalli, si sincronizzavano e la gara diventava sempre uguale (magari devodiminuire lo sleep.

un latro porbema e che qundo stampo risultati non tutti sono slavati perche secondo me più thread cercano di eseguire risultati.add(nome) contemporaneamente, si crea una condizione di gara che può rovinare i dati.
cercato su internet e ho scoperto il synchronized.
synchronized è un comando  che permette a un solo thread alla volta  di eseguire un blocco di codice, risolvendo i problemi di concorrenza. qundi se ho caito bene il codice dentro synchronized(oggetto){} viene eseguito in modo
esclusivo da un thread per volta.
il file di nome classifica.md lo rannato una volta per porva lascando il risultato
*/
import java.util.List;
import java.util.Scanner;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Cantarella Francesco
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner tastiera = new Scanner(System.in);
        System.out.print("Inserisci la lunghezza del percorso (in metri): ");
        int distanza = tastiera.nextInt();
        int ncavalli = 5;

        // crea e avvia 5  Cavallo
        Cavallo[] cavalli = new Cavallo[ncavalli];
        for (int i = 0; i < ncavalli; i++) {
            System.out.print("Inserisci nome cavallo: ");
            String nome = tastiera.next();
            cavalli[i] = new Cavallo(nome, distanza);
        }

        //stampo i cavalli nel ordine in cui sono stati inseriti
        System.out.println("");
        System.out.println("cavalli in linea di partenza");
        for (int j = 0; j < ncavalli; j++) {
            System.out.println((j + 1) + " " +cavalli[j].getNome());
        }
        System.out.println("");

        //cavallo azoppato casuale
        Random random = new Random();
        int azzoppato = random.nextInt(ncavalli);
        System.out.println("Il cavallo azzoppato è: " + cavalli[azzoppato].getNome() + " non partecipa alla gara");

        //faccio partire i cavalli
        for (int i = 0; i < ncavalli; i++) {
            if (i != azzoppato) {
                cavalli[i].start();
            }
        }

        //per fare apsettare gli latri tread la fine di tutti
        for (int i = 0; i < ncavalli; i++) {
            if (i != azzoppato) {
                cavalli[i].join();
            }
        }
        // stampa classifica finale

        List<String> classifica = Cavallo.getRisultato();
        System.out.println("classifica finale");
        for (int i = 0; i < classifica.size(); i++) {
            System.out.println((i + 1) + " " + classifica.get(i));
        }

        //file dele classifiche
        try (FileWriter writer = new FileWriter("classifica.txt")) {
            writer.write("Classifica Finale\n");
            for (int i = 0; i < classifica.size(); i++) {
                writer.write((i + 1) + classifica.get(i) + "\n");
            }
            writer.write("cavallo azzoppato: " + cavalli[azzoppato].getNome() + "\n");
            System.out.println("Classifica salvata su file: classifica.txt");
        } catch (IOException e) {
            System.err.println("Errore nel salvataggio del file: " + e.getMessage());
        }
    }
}