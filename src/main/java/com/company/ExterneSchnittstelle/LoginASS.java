package com.company.ExterneSchnittstelle;

import com.company.Anwendungslogik.HilfsfunktionenK;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginASS {

    HilfsfunktionenK hilfsfunktionen = new HilfsfunktionenK();

    public LoginASS() {
    }

    public void öffne() {
        JPanel jPanel = new JPanel(new GridBagLayout());
        JFrame frame = new JFrame();
        JTextField username = new JTextField(15);
        JTextField password = new JTextField(15);

        JButton login = new JButton("Login");
        JButton abbruch = new JButton("Abbrechen");

        GridBagConstraints a = new GridBagConstraints();
        a.gridy = 0;
        a.gridx = 1;
        a.anchor = GridBagConstraints.CENTER;
        jPanel.add(new JLabel("Benutzername"), a);


        GridBagConstraints b = new GridBagConstraints();
        b.gridy = 1;
        b.gridx = 1;
        b.anchor = GridBagConstraints.CENTER;
        b.weightx = 5;
        jPanel.add(username, b);

        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 2;
        c.gridx = 1;
        c.anchor = GridBagConstraints.CENTER;
        jPanel.add(new JLabel("Passwort"), c);

        GridBagConstraints d = new GridBagConstraints();
        d.gridy = 3;
        d.gridx = 1;
        d.weightx = 5;
        d.anchor = GridBagConstraints.CENTER;
        jPanel.add(password, d);

        GridBagConstraints e = new GridBagConstraints();
        e.gridy = 4;
        e.gridx = 1;
        e.weightx = 5;
        e.anchor = GridBagConstraints.CENTER;
        jPanel.add(login, e);


        GridBagConstraints f = new GridBagConstraints();
        f.gridy = 5;
        f.gridx = 1;
        f.weightx = 5;
        f.anchor = GridBagConstraints.CENTER;
        jPanel.add(abbruch, f);


        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hilfsfunktionen.authentifizierung(username.getText().toString(), password.getText().toString())) {
                    if (HilfsfunktionenK.getRole().equals("Mitarbeiter")) {
                        frame.dispose();
                        new MitarbeiterAS().setDropDownMitarbeiter();
                    }
                    if(HilfsfunktionenK.getRole().equals("Student"))
                    {
                        frame.dispose();
                        new StudentAS().öffne();
                    }
                } else {
                    System.out.println("Login nicht erfolgreich");
                }

            }
        });

        abbruch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        frame.add(jPanel);
        makeFrameFullSize(frame);
        frame.setVisible(true);
    }

    private void makeFrameFullSize(JFrame aFrame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        aFrame.setSize(screenSize.width, screenSize.height);
    }


}
