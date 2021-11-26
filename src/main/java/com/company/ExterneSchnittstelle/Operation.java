package com.company.ExterneSchnittstelle;

public interface Operation {

    boolean authentifizierung(String user, String password);

    boolean benutzerAnlegen(String user, String password , String role);

    boolean buchHinzufuegen(String Buchname, String Author, int Erscheinungsjahr, String ISBN, int Anzahl, boolean reserviert);

    boolean benutzerLöschen(String username);

}
