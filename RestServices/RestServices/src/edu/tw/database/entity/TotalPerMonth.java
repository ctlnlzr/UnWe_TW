package edu.tw.database.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQuery(name = "TotalPerMonth.findByLuna", query = "SELECT g FROM TotalPerMonth g WHERE g.luna = :lunaParam")
@NamedQuery(name = "TotalPerMonth.findByLunaAndCounty", query = "SELECT g FROM TotalPerMonth g WHERE g.luna = :lunaParam AND g.judet=:judetParam")
@NamedQuery(name = "TotalPerMonth.findByCounty", query = "SELECT g FROM TotalPerMonth g WHERE g.judet = :judetParam")
@NamedQuery(name = "TotalPerMonth.deleteByMonthAndCounty", query = "DELETE FROM TotalPerMonth g WHERE g.judet = :judetParam AND g.luna=:lunaParam")
@NamedQuery(name = "TotalPerMonth.all", query = "SELECT g FROM TotalPerMonth g")
@NamedQuery(name = "TotalPerMonth.filterByMonths", query = "SELECT g FROM TotalPerMonth g WHERE g.luna<=:lunaParam AND g.judet=:judetParam")
@NamedQuery(name = "TotalPerMonth.update", query = "UPDATE TotalPerMonth g SET g.total=:totalParam, g.rata=:rataParam  WHERE g.luna=:lunaParam and g.judet=:judetParam ")
@Entity
@Table(name = "totalPerLuna")
public class TotalPerMonth {
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
