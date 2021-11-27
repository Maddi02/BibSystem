package com.company.ExterneSchnittstelle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class StudentAS {

    String role= "";
    JFrame studentenFenster = new JFrame("Bibliothek Verwaltung - Student");
    JToolBar jToolBar = new JToolBar();
    JPanel constructorPanel = new JPanel();


    Action buchAusleihenAction = new AbstractAction("Buch ausleihen") {
        @Override
        public void actionPerformed(ActionEvent e) {
           BuchAusleihenAAS buchAusleihenAAS =  new BuchAusleihenAAS();
           buchAusleihenAAS.öffne();
           studentenFenster.setContentPane(buchAusleihenAAS.getPanel());
            SwingUtilities.updateComponentTreeUI(studentenFenster);
        }
    };

    Action showAusleihkonto = new AbstractAction("Ausleihkonten anzeigen") {
        @Override
        public void actionPerformed(ActionEvent e) {

            ShowAusleikonto showAusleihkonto =  new ShowAusleikonto();
            showAusleihkonto.showAusleihkonto();
            studentenFenster.setContentPane(ShowAusleikonto.getjPanel());
            SwingUtilities.updateComponentTreeUI(studentenFenster);
        }
    };

    public void setToolbar()
    {
        JButton buchAusleihen =  new JButton(buchAusleihenAction);
        JButton benutzerAusleikonto =  new JButton(showAusleihkonto);

        jToolBar.add(buchAusleihen);
        jToolBar.add(benutzerAusleikonto);
        constructorPanel.add(jToolBar);

        studentenFenster.setContentPane(constructorPanel);
        studentenFenster.getContentPane().setLayout(new BorderLayout());
        studentenFenster.getContentPane().add(jToolBar,BorderLayout.NORTH);
    }


    public void öffne()
    {
        setToolbar();
        KeyStroke ctrlN = KeyStroke.getKeyStroke(KeyEvent.VK_N, Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask());
        KeyStroke ctrlE = KeyStroke.getKeyStroke(KeyEvent.VK_E, Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask());

        final JMenuBar jMenuBar = new JMenuBar();
        JMenu Actionen = new JMenu("Aktionen");
        JMenu zurück = new JMenu("zurück");

        JMenuItem benutzerAnlegen = new JMenuItem(buchAusleihenAction);
        benutzerAnlegen.setAccelerator(ctrlN);
        benutzerAnlegen.setToolTipText("Hier können Sie einen neuen User anlegen");
        JMenuItem buchHinzufuegen = new JMenuItem(showAusleihkonto);
        buchHinzufuegen.setAccelerator(ctrlE);



        Actionen.add(buchAusleihenAction);
        Actionen.add(showAusleihkonto);

        jMenuBar.add(Actionen);
        jMenuBar.add(zurück);


        studentenFenster.setJMenuBar(jMenuBar);
        makeFrameFullSize(studentenFenster);
        studentenFenster.setVisible(true);
        studentenFenster.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void makeFrameFullSize(JFrame aFrame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        aFrame.setSize(screenSize.width, screenSize.height);
    }
}
