package com.company.ExterneSchnittstelle;

import com.company.Anwendungslogik.HilfsfunktionenK;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BenutzerkontoModifizierenAAS extends JFrame {

    static JPanel  jPanel = new JPanel();
    HilfsfunktionenK hilfsfunktionen = new HilfsfunktionenK();
    final String[] ausgewähltesMedium = {""};
    final String[] ausgewähltMahnung = {"alles gut"};
    final String[] ausgewählterVerlust = {"alles gut"};
    final String[] ausgewälterNutzer = {"b"};
    boolean rückgabe = false;
    public void öffne()
    {
        jPanel.setLayout(new GridBagLayout());

        GridBagConstraints b = new GridBagConstraints();
        b.gridx = 0;
        b.gridy = 0;
        b.anchor = GridBagConstraints.CENTER;
        JLabel jLabel = new JLabel("Select Student");
        jPanel.add(jLabel,b);

        GridBagConstraints a = new GridBagConstraints();
        a.gridx = 1;
        a.gridy = 0;
        a.anchor = GridBagConstraints.CENTER;
        JComboBox<String> jComboBox = hilfsfunktionen.getAllBenutzer();
        jPanel.add(jComboBox,a);



        GridBagConstraints selectAusgeliehenesMedium = new GridBagConstraints();
        selectAusgeliehenesMedium.gridx = 0;
        selectAusgeliehenesMedium.gridy = 1;
        selectAusgeliehenesMedium.anchor = GridBagConstraints.CENTER;
        JLabel jLabelStudent = new JLabel("Select AusgeliehenesMedium");
        jPanel.add(jLabelStudent,selectAusgeliehenesMedium);


        JComboBox getAusgelieheneMedienFortStudent = new JComboBox();
        JComboBox finalGetAusgelieheneMedienFortStudent = getAusgelieheneMedienFortStudent;

        GridBagConstraints z = new GridBagConstraints();
        z.gridx = 1;
        z.gridy = 1;
        z.anchor = GridBagConstraints.CENTER;
        jPanel.add(getAusgelieheneMedienFortStudent,z);
        jPanel.setVisible(true);


        GridBagConstraints cLable = new GridBagConstraints();
        cLable.gridx = 0;
        cLable.gridy = 2;
        cLable.anchor = GridBagConstraints.CENTER;
        JLabel selectedMahung =  new JLabel("Select Mahnung");
        jPanel.add(selectedMahung,cLable);


        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 2;
        c.gridx = 1;
        c.anchor = GridBagConstraints.CENTER;
        JComboBox<String> jComboBoxMahnung = hilfsfunktionen.getAllMahnung();
        jPanel.add(jComboBoxMahnung,c);

        GridBagConstraints cVerlust = new GridBagConstraints();
        cVerlust.gridx = 0;
        cVerlust.gridy = 3;
        cVerlust.anchor = GridBagConstraints.CENTER;
        JLabel selectedVerlust =  new JLabel("Select Verlust");
        jPanel.add(selectedVerlust,cVerlust);


        GridBagConstraints d = new GridBagConstraints();
        d.gridx = 1;
        d.gridy = 3;
        d.anchor = GridBagConstraints.CENTER;
        JComboBox<String> jComboBoxVerluste = hilfsfunktionen.getAllVerluste();
        jPanel.add(jComboBoxVerluste,d);



        GridBagConstraints e = new GridBagConstraints();
        e.gridx = 0;
        e.gridy = 4;
        e.anchor = GridBagConstraints.CENTER;
        JLabel Rückgabe = new JLabel("Rückgabe");
        jPanel.add(Rückgabe,e);

        GridBagConstraints f = new GridBagConstraints();
        f.gridx = 1;
        f.gridy = 4;
        f.anchor = GridBagConstraints.CENTER;
        JRadioButton jRadioButtonPasst = new JRadioButton("Akzeptiert");
        jPanel.add(jRadioButtonPasst,f);

        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 1;
        g.gridy = 5;
        g.anchor = GridBagConstraints.CENTER;
        JRadioButton jRadioButtonPasstNichts = new JRadioButton("Abgehlehnt");
        jPanel.add(jRadioButtonPasstNichts,g);

        ButtonGroup group = new ButtonGroup();
        group.add(jRadioButtonPasst);
        group.add(jRadioButtonPasstNichts);

        GridBagConstraints k = new GridBagConstraints();
        k.gridx = 0;
        k.gridy = 6;
        k.anchor = GridBagConstraints.CENTER;
        JButton jButtonPasst = new JButton("Set");
        jPanel.add(jButtonPasst,k);

        GridBagConstraints h = new GridBagConstraints();
        h.gridx = 0;
        h.gridy = 7;
        h.anchor = GridBagConstraints.CENTER;
        JButton jButtonPasstNicht = new JButton("Abbrechen");
        jPanel.add(jButtonPasstNicht,h);


        jComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ausgewälterNutzer[0] = jComboBox.getSelectedItem().toString();
                DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>( hilfsfunktionen.getAusgelieheneMedienFortStudent(hilfsfunktionen.getIDFromNutzer(ausgewälterNutzer[0]) ));
                finalGetAusgelieheneMedienFortStudent.setModel(model);
            }
        });

        finalGetAusgelieheneMedienFortStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ausgewähltesMedium[0] = finalGetAusgelieheneMedienFortStudent.getSelectedItem().toString();
            }
        });

        jComboBoxVerluste.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ausgewählterVerlust[0] = jComboBoxVerluste.getSelectedItem().toString();
            }
        });

        jComboBoxMahnung.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ausgewähltMahnung[0] = jComboBoxMahnung.getSelectedItem().toString();
            }
        });




        jButtonPasst.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(jRadioButtonPasst.isSelected())
                {
                    System.out.println("JOOO");
                    rückgabe = true;
                }
                if(jRadioButtonPasstNichts.isSelected()){
                    System.out.println("NO" +
                            "");
                    rückgabe = false;
                }
                hilfsfunktionen.upDateKonto(hilfsfunktionen.getBenutzerID(ausgewälterNutzer[0]), hilfsfunktionen.getMediumId(ausgewähltesMedium[0]) , hilfsfunktionen.getMahungID(ausgewähltMahnung[0]), hilfsfunktionen.getVerlustID(ausgewählterVerlust[0]),rückgabe);
            }
        });
    }

    public static JPanel getjPanel() {
        return jPanel;
    }
}
