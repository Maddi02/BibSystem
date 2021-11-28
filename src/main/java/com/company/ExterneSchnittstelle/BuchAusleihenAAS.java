package com.company.ExterneSchnittstelle;

import com.company.Anwendungslogik.HilfsfunktionenK;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuchAusleihenAAS extends JFrame {

    static JPanel jPanel = new JPanel(new GridBagLayout());
    HilfsfunktionenK hilfsfunktionen = new HilfsfunktionenK();
    private static JTextArea jTextArea = new JTextArea();
    private static JScrollPane scrollPane;
    int kundenId;
    int buchID;
    JScrollPane jScrollPane;

    public void öffne() {

        GridBagConstraints z = new GridBagConstraints();
        z.gridx = 0;
        z.gridy = 1;
        z.anchor = GridBagConstraints.CENTER;
        JComboBox jComboBox = hilfsfunktionen.showAllBooks();
        jPanel.add(jComboBox, z);

        GridBagConstraints f = new GridBagConstraints();
        f.gridx = 0;
        f.gridy = 2;
        f.anchor = GridBagConstraints.CENTER;
        JButton jbuttonAusleihen = new JButton("Ausleihen");
        jPanel.add(jbuttonAusleihen, f);

        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 1;
        g.gridy = 2;
        g.anchor = GridBagConstraints.CENTER;
        JButton jbuttonAbbrechen = new JButton("Abbrechen");
        jPanel.add(jbuttonAbbrechen, g);
        final String[] test = {""};
        jComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                test[0] =  jComboBox.getSelectedItem().toString();
            }
        });


        jbuttonAusleihen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int buchid = hilfsfunktionen.getMediumIdSelectMedium(test[0]);

                int benutzerID = HilfsfunktionenK.getLoginID();
                hilfsfunktionen.setAusleihkonto(benutzerID,buchid,0,0,false);
            }
        });




        jPanel.setVisible(true);
    }



    public static JPanel getPanel() {
        return jPanel;
    }
}
