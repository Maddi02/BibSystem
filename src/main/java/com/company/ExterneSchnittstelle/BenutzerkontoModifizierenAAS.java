package com.company.ExterneSchnittstelle;

import com.company.Anwendungslogik.HilfsfunktionenK;
import com.company.Entiätsklassen.AuthentifzierungEK;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BenutzerkontoModifizierenAAS extends JFrame {

    static JPanel  jPanel = new JPanel();
    HilfsfunktionenK hilfsfunktionen = new HilfsfunktionenK();
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

        JComboBox getAusgelieheneMedienFortStudent = new JComboBox();
        String ausgewälterNutzer[] = {"b"};
        JComboBox finalGetAusgelieheneMedienFortStudent = getAusgelieheneMedienFortStudent;
        jComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AuthentifzierungEK authentifzierungEK = new AuthentifzierungEK();
                ausgewälterNutzer[0] = jComboBox.getSelectedItem().toString();
                DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>( hilfsfunktionen.getAusgelieheneMedienFortStudent(hilfsfunktionen.getIDFromNutzer(ausgewälterNutzer[0]) ));
                finalGetAusgelieheneMedienFortStudent.setModel( model );

            }
        });


        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER;
        jPanel.add(getAusgelieheneMedienFortStudent,c);


    }

    public static JPanel getjPanel() {
        return jPanel;
    }
}
