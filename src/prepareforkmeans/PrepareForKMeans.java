/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prepareforkmeans;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maxime
 */
public class PrepareForKMeans {

    public static ArrayList<String> UEs = new ArrayList<>(Arrays.asList("MATHEMATIQUES", "INFORMATIQUE"));

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HashMap<String, ArrayList<Note>> map = new HashMap<>();
        try {
            try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Maxime\\Desktop\\finalM1.txt"))) {
                while (br.ready()) {
                    String[] split = br.readLine().split("\t");
                    if (split.length == 10) {
                        //System.out.println("OK");
                        if (split[8].equals("Session1")) {
                            String name = (split[2] + " " + split[3]);
                            Note n = new Note(split[6], split[7], new Double(split[9]));
                            if (map.containsKey(name)) {
                                map.get(name).add(n);
                            } else {
                                map.put(name, new ArrayList<>(Arrays.asList(n)));
                            }
                        }

                    } else {
                        //System.err.println("NON OK");
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PrepareForKMeans.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PrepareForKMeans.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* AFFICHAGE POUR VERIFIER */
 /*
            System.out.println(map.size());
            Iterator it = map.keySet().iterator();
            while(it.hasNext()) {
            String name = it.next().toString();
            System.out.println(name);
            for(Note n : map.get(name)) {
            System.out.println("\t"+n);
            }
            }
         */
        try {
            try (PrintWriter pw = new PrintWriter(new FileWriter("data.txt"))) {
                pw.println("ETUDIANT\tXMATH\tXINFO");
                Iterator it = map.keySet().iterator();
                while (it.hasNext()) {
                    String name = it.next().toString();
                    ArrayList<Note> notes = map.get(name);

                    try {
                        pw.println(getLine(name, notes));
                    } catch (NoNotesException ex) {
                        System.err.println("No notes for " + name);
                    }

                }
            }
        } catch (IOException ex) {
            Logger.getLogger(PrepareForKMeans.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static String getLine(String name, ArrayList<Note> notes) throws NoNotesException {
        StringBuilder buf = new StringBuilder(name);
        for(String ue : UEs) {
            buf.append("\t").append(Note.getMoyenne(notes, ue));
        }
        return new String(buf);
    }

}
