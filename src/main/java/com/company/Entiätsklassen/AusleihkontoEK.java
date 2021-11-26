package com.company.Entiätsklassen;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Ausleihkonto", schema = "PUBLIC", catalog = "TEST")
public class AusleihkontoEK {
    private int id;
    private int kundenId;
    private int ausleihbaresMediumId;
    private int mahnungId;
    private int verlustId;
    private boolean rückgabe;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "KundenID")
    public int getKundenId() {
        return kundenId;
    }

    public void setKundenId(int kundenId) {
        this.kundenId = kundenId;
    }

    @Basic
    @Column(name = "AusleihbaresMediumID")
    public int getAusleihbaresMediumId() {
        return ausleihbaresMediumId;
    }

    public void setAusleihbaresMediumId(int ausleihbaresMediumId) {
        this.ausleihbaresMediumId = ausleihbaresMediumId;
    }

    @Basic
    @Column(name = "MahnungID")
    public int getMahnungId() {
        return mahnungId;
    }

    public void setMahnungId(int mahnungId) {
        this.mahnungId = mahnungId;
    }

    @Basic
    @Column(name = "VerlustID")
    public int getVerlustId() {
        return verlustId;
    }

    public void setVerlustId(int verlustId) {
        this.verlustId = verlustId;
    }

    @Basic
    @Column(name = "Rückgabe")
    public boolean isRückgabe() {
        return rückgabe;
    }

    public void setRückgabe(boolean rückgabe) {
        this.rückgabe = rückgabe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AusleihkontoEK that = (AusleihkontoEK) o;
        return rückgabe == that.rückgabe && Objects.equals(id, that.id) && Objects.equals(kundenId, that.kundenId) && Objects.equals(ausleihbaresMediumId, that.ausleihbaresMediumId) && Objects.equals(mahnungId, that.mahnungId) && Objects.equals(verlustId, that.verlustId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, kundenId, ausleihbaresMediumId, mahnungId, verlustId, rückgabe);
    }
}
