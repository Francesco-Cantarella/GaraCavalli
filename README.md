# Progetto Gara di Cavalli Multi-Thread
---

## Note Personali e Problemi Riscontrati

Il seguente testo riflette le note originali dello sviluppatore riguardo ai problemi di concorrenza e alle soluzioni implementate:

NOTA PERSONALE
All'inizio pensavo di dover aggiungere il try-catch per Thread.sleep(), ma penso che devo agugerlo anche in altri punit anche se non so dove.
Ho scoperto che Thread.sleep() crea dei problemi:
Mettendo sleep fisso di 2 secondi per tutti i cavalli, si sincronizzavano e la gara diventava sempre uguale (magari devodiminuire lo sleep.

un latro porbema e che qundo stampo risultati non tutti sono slavati perche secondo me più thread cercano di eseguire risultati.add(nome) contemporaneamente, si crea una condizione di gara che può rovinare i dati.
cercato su internet e ho scoperto il synchronized.
synchronized è un comando che permette a un solo thread alla volta di eseguire un blocco di codice, risolvendo i problemi di concorrenza. qundi se ho caito bene il codice dentro synchronized(oggetto){} viene eseguito in modo
esclusivo da un thread per volta.
il file di nome classifica.md lo rannato una volta per porva lascando il risultato.


---

## Descrizione del Progetto

Il progetto consiste in due classi principali:

*   **`Main.java`**: La classe principale che gestisce l'input utente, crea i cavalli (thread), avvia la gara, gestisce il cavallo "azzoppato" in modo casuale e si occupa della stampa finale e del salvataggio dei risultati nel file `classifica.md`.
*   **`Cavallo.java`**: La classe che estende `Thread` e definisce il comportamento di ciascun cavallo. il riposo tra un passo e l'altro (`Thread.sleep()`) e l'aggiunta del proprio nome alla classifica condivisa in modo sincronizzato.

---

## Risultati

Al termine della gara, la classifica finale verrà visualizzata a console e salvata automaticamente nel file `classifica.md` nella directory principale del progetto.
