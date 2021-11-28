package com.company.ExterneSchnittstelle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MitarbeiterAS {

    String role = "";
    JFrame MitareiterFenster = new JFrame("Bibliothek Verwaltung - Mitarbeiter");
    JToolBar jToolBar = new JToolBar();
    JPanel constructorPanel = new JPanel();
    BucherstellenAAS buchErstellen = new BucherstellenAAS();
    ErstellenAAS erstellen = new ErstellenAAS();
    BenutzerLöschenASS benutzerLöschen = new BenutzerLöschenASS();
    BenutzerkontoModifizierenAAS benutzerkontoModifizierenAAS = new BenutzerkontoModifizierenAAS();

    Action benutzerErstellenAction = new AbstractAction("Benutzer erstellen") {
        public void actionPerformed(ActionEvent e) {

            erstellen.öffne();
            MitareiterFenster.setContentPane(erstellen.getPanel());
            SwingUtilities.updateComponentTreeUI(MitareiterFenster);
        }
    };

    Action buchErstellenAction = new AbstractAction("Buch hinzufügen") {
        @Override
        public void actionPerformed(ActionEvent e) {
            buchErstellen.öffnen();
            MitareiterFenster.setContentPane(buchErstellen.getPanel());
            SwingUtilities.updateComponentTreeUI(MitareiterFenster);
        }
    };

    Action benutzerLöschenAction = new AbstractAction("Benutzer löschen") {
        @Override
        public void actionPerformed(ActionEvent e) {

            benutzerLöschen.öffnen();
            MitareiterFenster.setContentPane(benutzerLöschen.getPanel());
            SwingUtilities.updateComponentTreeUI(MitareiterFenster);
        }
    };

    Action benutzerAusleihkontoModifizierenAction = new AbstractAction("Benutzerausleihkonto modifizieren") {
        @Override
        public void actionPerformed(ActionEvent e) {


            benutzerkontoModifizierenAAS.öffne();
            MitareiterFenster.setContentPane(BenutzerkontoModifizierenAAS.getjPanel());
            SwingUtilities.updateComponentTreeUI(MitareiterFenster);

        }
    };

    public void setToolbar() {
        JButton buchErstellen = new JButton(buchErstellenAction);
        JButton benutzerErstellen = new JButton(benutzerErstellenAction);
        JButton benutzerLöschen = new JButton(benutzerLöschenAction);
        JButton benutzerAusleikontoModizieren = new JButton(benutzerAusleihkontoModifizierenAction);

        jToolBar.add(buchErstellen);
        jToolBar.add(benutzerErstellen);
        jToolBar.add(benutzerLöschen);
        jToolBar.add(benutzerAusleikontoModizieren);
        constructorPanel.add(jToolBar);

        MitareiterFenster.setContentPane(constructorPanel);
        MitareiterFenster.getContentPane().setLayout(new BorderLayout());
        MitareiterFenster.getContentPane().add(jToolBar, BorderLayout.NORTH);
    }

    public void setDropDownMitarbeiter() {
        setToolbar();
        KeyStroke ctrlN = KeyStroke.getKeyStroke(KeyEvent.VK_N, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
        KeyStroke ctrlE = KeyStroke.getKeyStroke(KeyEvent.VK_E, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
        KeyStroke ctrlD = KeyStroke.getKeyStroke(KeyEvent.VK_D, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
        KeyStroke ctrlF = KeyStroke.getKeyStroke(KeyEvent.VK_F, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());

        final JMenuBar jMenuBar = new JMenuBar();
        JMenu Actionen = new JMenu("Aktionen");
        JMenu zurück = new JMenu("zurück");

        JMenuItem benutzerAnlegen = new JMenuItem(benutzerErstellenAction);
        benutzerAnlegen.setAccelerator(ctrlN);
        benutzerAnlegen.setToolTipText("Hier können Sie einen neuen User anlegen");
        JMenuItem buchHinzufuegen = new JMenuItem(buchErstellenAction);
        buchHinzufuegen.setAccelerator(ctrlE);
        buchHinzufuegen.setToolTipText("Hier können Sie ein Buch hinzufügen");
        JMenuItem benutzerLöschen = new JMenuItem(benutzerLöschenAction);
        benutzerLöschen.setAccelerator(ctrlD);
        benutzerLöschen.setToolTipText("Hier können Sie einen User löschen");
        JMenuItem bentzerAusleihkontoModizieren = new JMenuItem(benutzerAusleihkontoModifizierenAction);
        bentzerAusleihkontoModizieren.setAccelerator(ctrlF);
        bentzerAusleihkontoModizieren.setToolTipText("Hier können Sie einem Ausleihkonto Mahunungen hinzufügen");

        Actionen.add(benutzerAnlegen);
        Actionen.add(buchHinzufuegen);
        Actionen.add(benutzerLöschen);
        Actionen.add(bentzerAusleihkontoModizieren);

        jMenuBar.add(Actionen);
        jMenuBar.add(zurück);


        MitareiterFenster.setJMenuBar(jMenuBar);
        makeFrameFullSize(MitareiterFenster);
        MitareiterFenster.setVisible(true);
        MitareiterFenster.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    public JPanel getPanel() {
        return constructorPanel;
    }

    private void makeFrameFullSize(JFrame aFrame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        aFrame.setSize(screenSize.width, screenSize.height);
    }
}
