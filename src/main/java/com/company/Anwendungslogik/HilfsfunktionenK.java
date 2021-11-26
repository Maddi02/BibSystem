package com.company.Anwendungslogik;

import com.company.Entiätsklassen.AuthentifzierungEK;
import com.company.Entiätsklassen.BuecherbestandEK;
import com.company.ExterneSchnittstelle.Operation;


import javax.persistence.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class HilfsfunktionenK implements Operation {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    private static String role = "";

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
            return true;
        } else {
            return false;
        }


    }

    @Override
    public boolean benutzerAnlegen(String user, String password, String role) {

        try {

            transaction.begin();
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
            entityManager.close();
            entityManagerFactory.close();

        }
        return true;
    }

    @Override
    public boolean buchHinzufuegen(String Buchname, String Author, int Erscheinungsjahr, String ISBN, int Anzahl, boolean reserviert) { try {

        transaction.begin();
        BuecherbestandEK buecherbestandEK = new BuecherbestandEK();
        buecherbestandEK.setId(getMaxPublicKeyBücher() +1 );
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
        entityManager.close();
        entityManagerFactory.close();

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
        entityManager.close();
        return true;
    }

    public JComboBox getAllBenutzer()
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT e FROM AuthentifzierungEK e");
        List<AuthentifzierungEK> result = query.getResultList();
        JComboBox jComboBox = new JComboBox();
        List<String> allUser = new ArrayList<String>();
        for(int i = 0 ; i < result.size(); i++)
        {
            allUser.add(result.get(i).getUsername());
            System.out.println(result.get(i).getUsername());
        }
        for(int i = 0; i < allUser.size(); i++)
        {
            jComboBox.addItem(allUser.get(i));
        }

        return jComboBox;
    }

    public JComboBox getAllBücher()
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT e FROM BuecherbestandEK e");
        List<BuecherbestandEK> result = query.getResultList();
        JComboBox jComboBox = new JComboBox();
        List<String> allUser = new ArrayList<String>();
        for(int i = 0 ; i < result.size(); i++)
        {
            allUser.add(result.get(i).getBuchname());
        }
        for(int i = 0; i < allUser.size(); i++)
        {
            jComboBox.addItem(allUser.get(i));
        }

        return jComboBox;
    }


    public static String getRole() {
        return role;
    }

    private int getMaxPublicKeyAuthenifizerung(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT MAX(e.id) FROM AuthentifzierungEK e ");
        List<Integer> result = query.getResultList();
        int max = result.get(0);
        return max;
    }
    private int getMaxPublicKeyBücher(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT MAX(e.id) FROM BuecherbestandEK e ");
        List<Integer> result = query.getResultList();
        int max = result.get(0);
        return max;
    }


}
