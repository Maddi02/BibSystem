package com.company.ExterneSchnittstelle;

import javax.swing.*;

public interface Operation {

    boolean authentifizierung(String user, String password);

    boolean benutzerAnlegen(String user, String password , String role);

    boolean buchHinzufuegen(String Buchname, String Author, int Erscheinungsjahr, String ISBN, int Anzahl, boolean reserviert);

    boolean benutzerLöschen(String username);

    JComboBox showAllBooks();

    String showAllBooksInfo();

    boolean setAusleihkonto(int id , int buchid, int mahnungId, int verlustId, boolean rückgabe);

}
