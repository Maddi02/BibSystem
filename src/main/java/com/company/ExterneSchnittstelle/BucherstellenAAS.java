package com.company.ExterneSchnittstelle;

import com.company.Anwendungslogik.HilfsfunktionenK;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BucherstellenAAS {
    static JPanel panel = new JPanel();
    JFrame buchHinzufügenFenster = new JFrame();
    HilfsfunktionenK hilfsfunktionen = new HilfsfunktionenK();


    public void öffnen()
    {
    final JTextField buchname = new JTextField();
    final JTextField autor = new JTextField();
    final JTextField erscheinungsjahr = new JTextField();
    final JTextField isbn = new JTextField();
    final JTextField anzahl = new JTextField();
    final JTextField reserviert = new JTextField();

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
        panel.add(new JLabel("Buchname"));
        panel.add(buchname);
        panel.add(new JLabel("Author"));
        panel.add(autor);
        panel.add(new JLabel("Erscheinungsjahr"));
        panel.add(erscheinungsjahr);
        panel.add(new JLabel("ISBN"));
        panel.add(isbn);
        panel.add(new JLabel("Anzahl"));
        panel.add(anzahl);
        panel.add(new JLabel("Reserviert"));
        panel.add(reserviert);
        panel.add(OK);
        panel.add(Abbruch);
        panel.setVisible(true);



        OK.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JDialogSetUP jDialogSetUP = new JDialogSetUP();
            if(hilfsfunktionen.buchHinzufuegen(buchname.getText(),autor.getText(),Integer.valueOf(erscheinungsjahr.getText() ),
                    isbn.getText().toString(), Integer.valueOf(anzahl.getText())  , Boolean.valueOf(reserviert.getText())))
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
