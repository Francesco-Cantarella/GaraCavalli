import javax.swing.*;
import java.io.File;

public class FileChooserUtility extends JPanel{

    public static File scegliFilePerSalvare() {
        JFileChooser scelta = new JFileChooser();
        scelta.setDialogTitle("Scegli il file in cui salvare la classifica");

        int sceltaU = scelta.showSaveDialog(null);

        if (sceltaU == JFileChooser.APPROVE_OPTION) {
            return scelta.getSelectedFile();
        } else {
            return null;
        }
    }
}
