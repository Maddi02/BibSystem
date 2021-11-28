package com.company.ExterneSchnittstelle;

import com.company.Anwendungslogik.HilfsfunktionenK;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErstellenAAS {

    static JPanel panel = new JPanel();
    JFrame Loginfenster = new JFrame();
    HilfsfunktionenK hilfsfunktionen = new HilfsfunktionenK();
    private String Role = "";

    public void öffne ()
    {
        final JTextField username = new JTextField();
        final JTextField passwort = new JTextField();

        panel.setLayout(new GridLayout(8, 2, 0,70));
        ButtonGroup buttonGroup = new ButtonGroup();
        final JCheckBox jCheckBox = new JCheckBox("Mitarbeiter");
        final JCheckBox jCheckBox1 = new JCheckBox("Student");
        buttonGroup.add(jCheckBox);
        buttonGroup.add(jCheckBox1);

        JButton OK = new JButton();
        OK.setText("OK");

        JButton Abbruch = new JButton();
        Abbruch.setText("Abbruch");

        // add components to panel
        panel.add(new JLabel("Login"));
        panel.add(new JLabel(""));
        panel.add(new JLabel("Username"));
        panel.add(username);
        panel.add(new JLabel("Passwort"));
        panel.add(passwort);
        panel.add(new JLabel("Berechtigungen"));
        panel.add(jCheckBox);
        panel.add(new JLabel(""));
        panel.add(jCheckBox1);
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(OK);
        panel.add(Abbruch);
        panel.setVisible(true);



        OK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JDialogSetUP jDialogSetUP = new JDialogSetUP();
                if(hilfsfunktionen.benutzerAnlegen(username.getText().toString(), passwort.getText().toString(), Role))
                {
                    jDialogSetUP.setUpADialog("Erstellung war erflogreich", "SUCCESS!!!").pack();
                }
                else{
                    jDialogSetUP.setUpADialog("Erstellung war nicht erflogreich", "FAIL!!!").pack();
                }
            }
        });
    }

    public static JPanel getPanel() {
        return panel;
    }
}
