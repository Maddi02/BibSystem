package com.company.ExterneSchnittstelle;

import com.company.ExterneSchnittstelle.LoginASS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BibliotheksystemHS extends JFrame {

    LoginASS login = new LoginASS();
    public BibliotheksystemHS()
    {

    }

    public void öffne()
    {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JButton login = new JButton("Login");
        JButton abbruch = new JButton("Abbrechen");
        panel.setLayout(new GridBagLayout());
        GridBagConstraints a = new GridBagConstraints();
        a.gridx = 0;
        a.gridy = 0;
        a.anchor = GridBagConstraints.CENTER;

        panel.add(new JLabel("Willkommen zur Bibliotheksverwaltung"),a);

        GridBagConstraints b = new GridBagConstraints();
        b.gridx = 0;
        b.gridy = 1;
        b.anchor = GridBagConstraints.CENTER;
        panel.add(login,b);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(abbruch,c);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginASS().öffne();
                frame.setVisible(false);
            }
        });

        abbruch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        frame.add(panel);
        makeFrameFullSize(frame);
        frame.setVisible(true);
    }

    private void makeFrameFullSize(JFrame aFrame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        aFrame.setSize(screenSize.width, screenSize.height);
    }
}
