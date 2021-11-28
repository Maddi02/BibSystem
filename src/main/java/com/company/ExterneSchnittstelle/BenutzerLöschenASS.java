package com.company.ExterneSchnittstelle;

import com.company.Anwendungslogik.HilfsfunktionenK;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BenutzerLöschenASS {
    JPanel jPanel = new JPanel();
    HilfsfunktionenK hilfsfunktionen = new HilfsfunktionenK();

    public void öffnen(){
        jPanel.setLayout(new GridLayout(4, 2));


        JComboBox  jComboBox = new JComboBox();
        jComboBox = hilfsfunktionen.getAllBenutzer();

        JButton löschen = new JButton("Löschen");

        JButton Abbruch = new JButton("Abbruch");

        jPanel.add(new JLabel("Sacharbeiter löschen"));
        jPanel.add(new JLabel(""));
        jPanel.add(new JLabel("Username"));
        jPanel.add(jComboBox);

        jPanel.add(löschen);
        jPanel.add(Abbruch);
        jPanel.setVisible(true);


        final JComboBox finalJComboBox = jComboBox;
        löschen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JDialogSetUP jDialogSetUP = new JDialogSetUP();
                String username = finalJComboBox.getSelectedItem().toString();
               if(hilfsfunktionen.benutzerLöschen(username))
               {
                   jDialogSetUP.setUpADialog("Löschen war erflogreich", "SUCCESS!!!").pack();
               }
               else{
                   jDialogSetUP.setUpADialog("Löschen war nicht erflogreich", "FAIL!!!").pack();
               }
            }
        });
    }

    public  JPanel getPanel() {
        return jPanel;
    }

}
