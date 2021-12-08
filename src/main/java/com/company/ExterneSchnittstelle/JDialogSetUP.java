package com.company.ExterneSchnittstelle;

import javax.swing.*;
import java.awt.*;

public class JDialogSetUP {

    public JDialogSetUP() {
    }

    public JDialog setUpADialog(String Message, String Title)
    {
        JDialog meinJDialog = new JDialog();
        JLabel wrongUserData = new JLabel(Message,JLabel.CENTER);
        wrongUserData.setForeground(Color.red);
        meinJDialog.setTitle(Title);
        meinJDialog.add(wrongUserData);
        meinJDialog.setSize(200, 200);
        meinJDialog.setModal(true);
        meinJDialog.pack();
        meinJDialog.setVisible(true);

        return meinJDialog;
    }
}
