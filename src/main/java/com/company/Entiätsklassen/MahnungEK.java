package com.company.Entiätsklassen;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "MAHNUNG", schema = "PUBLIC", catalog = "TEST")
public class MahnungEK {
    private int mahnungid;
    private String beschreibung;

    @Id
    @Column(name = "MAHNUNGID")
    public int getMahnungid() {
        return mahnungid;
    }

    public void setMahnungid(int mahnungid) {
        this.mahnungid = mahnungid;
    }

    @Basic
    @Column(name = "Beschreibung")
    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MahnungEK mahnungEK = (MahnungEK) o;
        return Objects.equals(mahnungid, mahnungEK.mahnungid) && Objects.equals(beschreibung, mahnungEK.beschreibung);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mahnungid, beschreibung);
    }
}
