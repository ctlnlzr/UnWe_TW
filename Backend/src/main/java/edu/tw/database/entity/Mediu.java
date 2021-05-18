package edu.tw.database.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQuery(name="Mediu.findByLuna", query = "SELECT g FROM Mediu g WHERE g.luna = :lunaParam")
@NamedQuery(name="Mediu.findByLunaAndCounty", query = "SELECT g FROM Mediu g WHERE g.luna = :lunaParam AND g.judet=:judetParam")
@NamedQuery(name="Mediu.findByCounty", query = "SELECT g FROM Mediu g WHERE g.judet = :judetParam")
@Entity
@Table(name="mediu")
public class Mediu {
    @Id
    int luna;
    @Id
    String judet;
    int femeiDinMediulUrban;
    int femeiDinMediulRural;
    int barbatiDinMediulUrban;
    int barbatiDinMediulRural;

    public int getLuna() {
        return luna;
    }

    public String getJudet() {
        return judet;
    }

    public int getBarbatiDinMediulRural() {
        return barbatiDinMediulRural;
    }

    public int getBarbatiDinMediulUrban() {
        return barbatiDinMediulUrban;
    }

    public int getFemeiDinMediulRural() {
        return femeiDinMediulRural;
    }

    public int getFemeiDinMediulUrban() {
        return femeiDinMediulUrban;
    }

    public void setLuna(int luna) {
        this.luna = luna;
    }

    public void setJudet(String judet) {
        this.judet = judet;
    }

    public void setBarbatiDinMediulRural(int barbatiDinMediulRural) {
        this.barbatiDinMediulRural = barbatiDinMediulRural;
    }

    public void setBarbatiDinMediulUrban(int barbatiDinMediulUrban) {
        this.barbatiDinMediulUrban = barbatiDinMediulUrban;
    }

    public void setFemeiDinMediulRural(int femeiDinMediulRural) {
        this.femeiDinMediulRural = femeiDinMediulRural;
    }

    public void setFemeiDinMediulUrban(int femeiDinMediulUrban) {
        this.femeiDinMediulUrban = femeiDinMediulUrban;
    }

    @Override
    public String toString() {
        return "luna=" + luna +
                ", judet='" + judet + '\'' +
                ", femeiDinMediulUrban=" + femeiDinMediulUrban +
                ", femeiDinMediulRural=" + femeiDinMediulRural +
                ", barbatiDinMediulUrban=" + barbatiDinMediulUrban +
                ", barbatiDinMediulRural=" + barbatiDinMediulRural;
    }
}
