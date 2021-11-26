package com.company.Entiätsklassen;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Verlustmeldung", schema = "PUBLIC", catalog = "TEST")
public class VerlustmeldungEK {
    private int verlustId;
    private String beschreibung;

    @Id
    @Column(name = "VerlustID")
    public int getVerlustId() {
        return verlustId;
    }

    public void setVerlustId(int verlustId) {
        this.verlustId = verlustId;
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
        VerlustmeldungEK that = (VerlustmeldungEK) o;
        return Objects.equals(verlustId, that.verlustId) && Objects.equals(beschreibung, that.beschreibung);
    }

    @Override
    public int hashCode() {
        return Objects.hash(verlustId, beschreibung);
    }
}
