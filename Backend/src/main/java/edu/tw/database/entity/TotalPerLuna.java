package edu.tw.database.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQuery(name="TotalPerLuna.findByLuna", query = "SELECT g FROM TotalPerLuna g WHERE g.luna = :lunaParam")
@NamedQuery(name="TotalPerLuna.findByLunaAndCounty", query = "SELECT g FROM TotalPerLuna g WHERE g.luna = :lunaParam AND g.judet=:judetParam")
@NamedQuery(name="TotalPerLuna.findByCounty", query = "SELECT g FROM TotalPerLuna g WHERE g.judet = :judetParam")
@Entity
@Table(name="totalPerLuna")
public class TotalPerLuna {
    @Id
    int luna;
    @Id
    String judet;
    int total;
    float rata;

    public String getJudet() {
        return judet;
    }

    public int getLuna() {
        return luna;
    }

    public float getRata() {
        return rata;
    }

    public int getTotal() {
        return total;
    }

    public void setJudet(String judet) {
        this.judet = judet;
    }

    public void setLuna(int luna) {
        this.luna = luna;
    }

    public void setRata(float rata) {
        this.rata = rata;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "luna=" + luna +
                ", judet='" + judet + '\'' +
                ", total=" + total +
                ", rata=" + rata;
    }
}
