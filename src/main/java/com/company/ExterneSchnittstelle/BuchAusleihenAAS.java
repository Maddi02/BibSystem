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
    JDialogSetUP jDialogSetUP = new JDialogSetUP();
    private static JScrollPane scrollPane;
    int kundenId;
    int buchID;
    JScrollPane jScrollPane;

    public BuchAusleihenAAS()
    {
        öffne();
    }


    private void öffne() {

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

        GridBagConstraints k = new GridBagConstraints();
        k.gridx = 0;
        k.gridy = 3;
        k.anchor = GridBagConstraints.CENTER;
        JButton jbuttonVerlängern = new JButton("Verlängern ausleihen");
        jPanel.add(jbuttonVerlängern, k);


        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 1;
        g.gridy = 3;
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

        final int[] buchid = {0};
        jbuttonAusleihen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(test[0]);
                 buchid[0] = hilfsfunktionen.getMediumIdSelectMedium(test[0]);

                int benutzerID = HilfsfunktionenK.getLoginID();
                if(hilfsfunktionen.setAusleihkonto(benutzerID, buchid[0],0,0,false))
                {
                    jDialogSetUP.setUpADialog("Aktion war erflogreich", "SUCCESS!!!").pack();
                }
                else{
                    jDialogSetUP.setUpADialog("Aktion war nicht erflogreich", "FAIL!!!").pack();
                }
            }
        });

        jbuttonVerlängern.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog meinJDialog = new JDialog();
                if(hilfsfunktionen.setVerlängerung(buchid[0]))
                {
                    JLabel wrongUserData = new JLabel("Ihr Medium wurde erflogreich verlängert",JLabel.CENTER);
                    wrongUserData.setForeground(Color.red);
                    meinJDialog.setTitle("SUCCESS");
                    meinJDialog.add(wrongUserData);
                    meinJDialog.setSize(200, 200);
                    meinJDialog.setModal(true);
                    meinJDialog.pack();
                    meinJDialog.setVisible(true);

                }
                else{
                    JLabel wrongUserData = new JLabel("Ihr Medium konnte nicht verlängert werden",JLabel.CENTER);
                    wrongUserData.setForeground(Color.red);
                    meinJDialog.setTitle("FAIL");
                    meinJDialog.add(wrongUserData);
                    meinJDialog.setSize(200, 200);
                    meinJDialog.setModal(true);
                    meinJDialog.pack();
                    meinJDialog.setVisible(true);
                    jPanel.add(meinJDialog);
                }
                jPanel.add(meinJDialog);
            }
        });




        jPanel.setVisible(true);
    }



    public static JPanel getPanel() {
        return jPanel;
    }
}
