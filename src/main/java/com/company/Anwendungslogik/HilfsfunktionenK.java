package com.company.Anwendungslogik;

import com.company.Entiätsklassen.*;
import com.company.ExterneSchnittstelle.Operation;


import javax.persistence.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class HilfsfunktionenK implements Operation {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    private static String role = "";
    static int benutzerId;

    @Override
    public boolean authentifizierung(String user, String password) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT e FROM AuthentifzierungEK e WHERE e.username = :employeeName ");
        query.setParameter("employeeName", user);
        List<AuthentifzierungEK> authentifzierungEKList = query.getResultList();
        if (authentifzierungEKList.size() == 0) {
            System.out.println("User not found");
            return false;
        }

        if (authentifzierungEKList.get(0).getUsername().equals(user) && authentifzierungEKList.get(0).getPassword().equals(password)) {
            role = authentifzierungEKList.get(0).getRole();
            benutzerId = authentifzierungEKList.get(0).getId();
            return true;
        } else {
            return false;
        }


    }

    @Override
    public boolean benutzerAnlegen(String user, String password, String role) {

        try {

            transaction.begin();
            System.out.println(role);
            AuthentifzierungEK authentifzierungEK = new AuthentifzierungEK();
            authentifzierungEK.setId(getMaxPublicKeyAuthenifizerung() + 1);
            authentifzierungEK.setUsername(user);
            authentifzierungEK.setPassword(password);
            authentifzierungEK.setRole(role);
            entityManager.persist(authentifzierungEK);
            transaction.commit();

        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            return false;
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        return true;
    }

    @Override
    public boolean buchHinzufuegen(String Buchname, String Author, int Erscheinungsjahr, String ISBN, int Anzahl, boolean reserviert) {
        try {

            transaction.begin();
            BuecherbestandEK buecherbestandEK = new BuecherbestandEK();
            buecherbestandEK.setId(getMaxPublicKeyBücher() + 1);
            buecherbestandEK.setBuchname(Buchname);
            buecherbestandEK.setAuthor(Author);
            buecherbestandEK.setErscheinungsjahr(Erscheinungsjahr);
            buecherbestandEK.setIsbn(ISBN);
            buecherbestandEK.setAnzahl(Anzahl);
            buecherbestandEK.setReserviert(reserviert);
            buecherbestandEK.setErscheinungsjahr(Erscheinungsjahr);
            entityManager.persist(buecherbestandEK);
            transaction.commit();

        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            return false;
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }

        }
        return true;
    }

    @Override
    public boolean benutzerLöschen(String username) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("DELETE FROM AuthentifzierungEK e WHERE e.username = :employeeName ");
        query.setParameter("employeeName", username);
        query.executeUpdate();
        entityManager.getTransaction().commit();
     //   entityManager.close();
        return true;
    }

    @Override
    public JComboBox showAllBooks() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT e FROM BuecherbestandEK e");
        List<BuecherbestandEK> bücherListe = query.getResultList();
        List<String> stingBücherliste = new ArrayList<>();

        JComboBox jComboBox = new JComboBox();
        for (int i = 0; i < bücherListe.size(); i++) {
            stingBücherliste.add("Buchname: " + bücherListe.get(i).getBuchname() + " Author: " + bücherListe.get(i).getAuthor() + " Erscheinungsjahr " + bücherListe.get(i).getErscheinungsjahr()   + "Erscheinungsjahr " + bücherListe.get(i).getErscheinungsjahr() + "\n"
                    + "ISBN " + bücherListe.get(i).getIsbn() + "\n"
                    + "Anzahl " + bücherListe.get(i).getAnzahl() + "\n"
                    + "ID " +  bücherListe.get(i).getId() + "\n");
        }

        for (int i = 0; i < stingBücherliste.size(); i++) {
            jComboBox.addItem(stingBücherliste.get(i));
        }
        return jComboBox;

    }

    @Override
    public String showAllBooksInfo() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT e FROM BuecherbestandEK e");
        List<BuecherbestandEK> bücherListe = query.getResultList();
        String allBookInfos = "Autor ";
        for (int i = 0; i < bücherListe.size(); i++) {
            allBookInfos = allBookInfos + bücherListe.get(i).getAuthor() + "\n"
                    + "Buchname " + bücherListe.get(i).getBuchname() + "\n"
                    + "Erscheinungsjahr " + bücherListe.get(i).getErscheinungsjahr() + "\n"
                    + "ISBN " + bücherListe.get(i).getIsbn() + "\n"
                    + "Anzahl " + bücherListe.get(i).getAnzahl() + "\n";
            if (bücherListe.get(i).isReserviert()) {
                allBookInfos = allBookInfos + "reserviert JA " + "\n";
            } else {
                allBookInfos = allBookInfos + "reserviert Nein " + "\n";
            }
            allBookInfos = allBookInfos + "--------------------\n";
            allBookInfos = allBookInfos + "Author ";

        }

        return allBookInfos;
    }

    public JComboBox getAllBenutzer() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT e FROM AuthentifzierungEK e");
        List<AuthentifzierungEK> result = query.getResultList();
        JComboBox jComboBox = new JComboBox();
        List<String> allUser = new ArrayList<String>();
        for (int i = 0; i < result.size(); i++) {
            allUser.add(result.get(i).getUsername());
            System.out.println(result.get(i).getUsername());
        }
        for (int i = 0; i < allUser.size(); i++) {
            jComboBox.addItem(allUser.get(i));
        }

        return jComboBox;
    }

    public JComboBox getAllBücher(String selectedItem) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT e FROM BuecherbestandEK e");
        List<BuecherbestandEK> result = query.getResultList();
        JComboBox jComboBox = new JComboBox();
        List<String> allUser = new ArrayList<String>();
        for (int i = 0; i < result.size(); i++) {
            allUser.add(result.get(i).getBuchname());
        }
        for (int i = 0; i < allUser.size(); i++) {
            jComboBox.addItem(allUser.get(i));
        }

        return jComboBox;
    }


    public static String getRole() {
        return role;
    }

    private int getMaxPublicKeyAuthenifizerung() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT MAX(e.id) FROM AuthentifzierungEK e ");
        List<Integer> result = query.getResultList();
        int max = result.get(0);
        return max;
    }

    private int getMaxPublicKeyBücher() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT MAX(e.id) FROM BuecherbestandEK e ");
        List<Integer> result = query.getResultList();
        int max = result.get(0);
        return max;
    }

    private int getMaxPublicKeyBAusleihkonto()
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT MAX(e.id) FROM AusleihkontoEK e ");
        List<Integer> result = query.getResultList();
        int max = result.get(0);
        return max;
    }

    @Override
    public boolean setAusleihkonto(int id, int buchid, int mahnungId ,  int verlustId, boolean rückgabe) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT e FROM AusleihkontoEK e WHERE e.ausleihbaresMediumId =: id");
        query.setParameter("id",id);
        List<AusleihkontoEK> AusleihkontoEK = query.getResultList();
        if(!(AusleihkontoEK.size() > 0)) {
            try {
                transaction.begin();
                AusleihkontoEK ausleihkontoEK = new AusleihkontoEK();
                ausleihkontoEK.setId(getMaxPublicKeyBAusleihkonto() + 1);
                ausleihkontoEK.setAusleihbaresMediumId(buchid);
                ausleihkontoEK.setMahnungId(mahnungId);
                ausleihkontoEK.setVerlustId(verlustId);
                ausleihkontoEK.setRückgabe(rückgabe);
                ausleihkontoEK.setKundenId(id);
                entityManager.persist(ausleihkontoEK);
                transaction.commit();

            } catch (Exception e) {
                System.out.println(e.getStackTrace());
                return false;
            } finally {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
//                entityManager.close();
//                entityManagerFactory.close();

            }
            return true;
        }
        return false;
    }

    public boolean upDateKonto(int id, int buchid, int mahnungId, int verlustId, boolean rückgab)
    {
        System.out.println("Benutzer = " + id);
        System.out.println("Buch= " +buchid);
        System.out.println("Mahnung= " +mahnungId);
        System.out.println("verlust= " +verlustId);
        System.out.println("rückgabe= " +rückgab);
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("UPDATE AusleihkontoEK e SET  e.mahnungId = :mahnungId, e.verlustId = :verlustId, e.rückgabe = :rüchgabe WHERE e.kundenId = :kudenID AND e.ausleihbaresMediumId =:ausleihbaresMediumId " );
        query.setParameter("kudenID" ,id );
        query.setParameter("ausleihbaresMediumId" ,buchid );
        query.setParameter("mahnungId" ,mahnungId );
        query.setParameter("verlustId" ,verlustId );
        query.setParameter("rüchgabe" ,rückgab);
        query.executeUpdate();
        entityManager.getTransaction().commit();
//        entityManager.close();
        return false;
    }

    @Override
    public JComboBox getAllMahnung() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT e FROM MahnungEK e");
        List<MahnungEK> result = query.getResultList();
        JComboBox jComboBox = new JComboBox();
        List<String> allMahnungen = new ArrayList<String>();
        for (int i = 0; i < result.size(); i++) {
            allMahnungen.add(result.get(i).getBeschreibung());
        }
        for (int i = 0; i < allMahnungen.size(); i++) {
            jComboBox.addItem(allMahnungen.get(i));
        }

        return jComboBox;
    }

    @Override
    public JComboBox getAllVerluste() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT e FROM VerlustmeldungEK e");
        List<VerlustmeldungEK> result = query.getResultList();
        JComboBox jComboBox = new JComboBox();
        List<String> allVerluste = new ArrayList<String>();
        for (int i = 0; i < result.size(); i++) {
            allVerluste.add(result.get(i).getBeschreibung());
        }
        for (int i = 0; i < allVerluste.size(); i++) {
            jComboBox.addItem(allVerluste.get(i));
        }
        return jComboBox;
    }



    @Override
    public boolean setVerlängerung(int id) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT e FROM AusleihkontoEK e WHERE e.ausleihbaresMediumId =: id");
        query.setParameter("id", id);
        List<AusleihkontoEK> result = query.getResultList();
        if (result.size() > 0) {
             entityManager = entityManagerFactory.createEntityManager();
            Query query1 = entityManager.createQuery("SELECT e FROM BuecherbestandEK e WHERE e.id =: id");
            query1.setParameter("id", id);
            List<BuecherbestandEK> result1 = query1.getResultList();

            boolean status = result1.get(0).isReserviert();
            if (!status) {
                entityManager.getTransaction().begin();
                query = entityManager.createQuery("UPDATE BuecherbestandEK e SET  e.reserviert =: reserviert WHERE e.id=:id");
                query.setParameter("id", id);
                query.setParameter("reserviert", true);
                query.executeUpdate();
                entityManager.getTransaction().commit();
//                entityManager.close();

                return true;
            }
            return false;
        }
        return false;
    }

    public int getMediumIdSelectMedium(String text)
    {
        int i = text.indexOf("ID");
        String id = "";
        for(int k = i +3 ; k < text.length() ; k++)
        {
            if(text.charAt(k) != ' ' && text.charAt(k) != '\n') {
                id = id + text.charAt(k);
            }
        }
        System.out.println(id.length());
       int buchID =   Integer.valueOf(id) ;
       return buchID;
    }

    public int getMediumId(String mediumId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT e FROM BuecherbestandEK e WHERE e.buchname =: username");
        query.setParameter("username",mediumId);
        List<BuecherbestandEK> result = query.getResultList();
        return  result.get(0).getId();
    }

    public int getBenutzerID(String user)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT e FROM AuthentifzierungEK e WHERE e.username =: username");
        query.setParameter("username",user);
        List<AuthentifzierungEK> result = query.getResultList();
        return  result.get(0).getId();
    }

    public int getMahungID(String Mahnug)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT e FROM MahnungEK e WHERE e.beschreibung =: mahung");
        query.setParameter("mahung",Mahnug);
        List<MahnungEK> result = query.getResultList();
        return  result.get(0).getMahnungid();
    }

    public int getVerlustID(String Verlust)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT e FROM MahnungEK e WHERE e.beschreibung =: verlust");
        query.setParameter("verlust",Verlust);
        List<MahnungEK> result = query.getResultList();
        return  result.get(0).getMahnungid();
    }







    public String getBuchname (int id)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("Select e FROM BuecherbestandEK e WHERE e.id = :id ");
        query.setParameter("id", id);
        List<BuecherbestandEK> result = query.getResultList();
//        entityManager.close();
        return result.get(0).getBuchname();
    }

    public String getMahung(int id)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("Select e FROM MahnungEK e WHERE e.id = :id ");
        query.setParameter("id", id);
        List<MahnungEK> result = query.getResultList();
     //   entityManager.close();
        return result.get(0).getBeschreibung();
    }

    public String getVerlust(int id)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("Select e FROM VerlustmeldungEK e WHERE e.id = :id ");
        query.setParameter("id", id);
        List<VerlustmeldungEK> result = query.getResultList();
     //   entityManager.close();
        return result.get(0).getBeschreibung();

    }
    public boolean getRückgabe(int id)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("Select e FROM AusleihkontoEK e WHERE e.id = :id ");
        query.setParameter("id", id);
        List<AusleihkontoEK> result = query.getResultList();
        return result.get(0).isRückgabe();
    }




    public String getAusleihkontenAuskunft(int id)
    {
        String text = "\n";
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("Select e FROM AusleihkontoEK e WHERE e.kundenId = :id ");
        query.setParameter("id", id);
         text = "Buchname: ";
        List<AusleihkontoEK> result = query.getResultList();
        for(int i = 0; i < result.size(); i++)
        {
            text = text + getBuchname(result.get(i).getAusleihbaresMediumId()) +
            " Verlustbemerkung: " + getVerlust(result.get(i).getVerlustId()) +
            " Mahnungbemerkung: " + getMahung(result.get(i).getMahnungId()) +
            " Rückgabe: " + result.get(i).isRückgabe()+ "\n-------------------\n";
        }
        return text;
    }

    public String[] getAusgelieheneMedienFortStudent(int id)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("Select e FROM AusleihkontoEK e WHERE e.kundenId = :id ");
        query.setParameter("id", id);
        List<AusleihkontoEK> list = query.getResultList();
        List<String> name = new ArrayList<>();
        for(int i = 0; i < list.size(); i++)
        {
            int  idBuch = (list.get(i).getAusleihbaresMediumId());
            name.add(getBuchname(idBuch));
        }
        String[] a = new String[name.size()];
        for(int i = 0; i < list.size(); i++)
        {
            a[i] = name.get(i);
        }
        return a;

    }

    public int getIDFromNutzer(String nutzer)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("Select e FROM AuthentifzierungEK e WHERE e.username = :id ");
        query.setParameter("id", nutzer);
        List<AuthentifzierungEK> list = query.getResultList();
        return list.get(0).getId();
    }

    public  static int getLoginID(){
        return benutzerId;
    }
}
