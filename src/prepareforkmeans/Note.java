/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prepareforkmeans;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author Maxime BLAISE
 */
public class Note {

    String UE, matiere;
    double note;

    public Note(String UE, String matiere, double note) {
        this.UE = UE;
        this.matiere = matiere;
        this.note = note;
    }

    @Override
    public String toString() {
        return "Note{" + "UE=" + UE + ", matiere=" + matiere + ", note=" + note + '}';
    }

    public static String getMoyenne(ArrayList<Note> notes, String UEParam) throws NoNotesException {
        int nb = 0;
        double sum = 0;
        for (Note n : notes) {
            if (n.UE.equals(UEParam)) {
                nb++;
                sum += n.note;
            }
        }
        if(nb == 0) {
            throw new NoNotesException();
        }
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(sum / nb);
    }
}
