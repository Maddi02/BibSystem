package com.company.Entiätsklassen;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BUECHERBESTAND", schema = "PUBLIC", catalog = "TEST")
public class BuecherbestandEK {
    private String buchname;
    private String author;
    private int erscheinungsjahr;
    private String isbn;
    private int anzahl;
    private boolean reserviert;
    private int id;

    @Basic
    @Column(name = "Buchname")
    public String getBuchname() {
        return buchname;
    }

    public void setBuchname(String buchname) {
        this.buchname = buchname;
    }

    @Basic
    @Column(name = "Author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    @Column(name = "Erscheinungsjahr")
    public int getErscheinungsjahr() {
        return erscheinungsjahr;
    }

    public void setErscheinungsjahr(int erscheinungsjahr) {
        this.erscheinungsjahr = erscheinungsjahr;
    }

    @Basic
    @Column(name = "ISBN")
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Basic
    @Column(name = "Anzahl")
    public int getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }

    @Basic
    @Column(name = "Reserviert")
    public boolean isReserviert() {
        return reserviert;
    }

    public void setReserviert(boolean reserviert) {
        this.reserviert = reserviert;
    }

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuecherbestandEK that = (BuecherbestandEK) o;
        return reserviert == that.reserviert && Objects.equals(buchname, that.buchname) && Objects.equals(author, that.author) && Objects.equals(erscheinungsjahr, that.erscheinungsjahr) && Objects.equals(isbn, that.isbn) && Objects.equals(anzahl, that.anzahl) && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buchname, author, erscheinungsjahr, isbn, anzahl, reserviert, id);
    }
}
