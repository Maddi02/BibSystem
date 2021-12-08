package com.company.ExterneSchnittstelle;

import com.company.Anwendungslogik.HilfsfunktionenK;

import javax.swing.*;

public class ShowAusleikonto extends JFrame {

    static JPanel  jPanel = new JPanel();
    JTextArea jTextArea = new JTextArea();
    HilfsfunktionenK hilfsfunktionen = new HilfsfunktionenK();


    ShowAusleikonto()
    {
        showAusleihkonto();
    }


    public void showAusleihkonto()
    {
        jPanel.add(jTextArea);
        jTextArea.setText( hilfsfunktionen.getAusleihkontenAuskunft(HilfsfunktionenK.getLoginID()));
        jPanel.setVisible(true);
    }

    public static JPanel getjPanel() {
        return jPanel;
    }
}
