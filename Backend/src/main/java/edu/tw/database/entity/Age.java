package edu.tw.database.entity;

import javax.persistence.*;

@Entity
@NamedQuery(name = "Age.findByLuna", query = "SELECT g FROM Age g WHERE g.luna = :lunaParam")
@NamedQuery(name = "Age.findByLunaAndCounty", query = "SELECT g FROM Age g WHERE g.luna = :lunaParam AND g.judet=:judetParam")
@NamedQuery(name = "Age.findByCounty", query = "SELECT g FROM Age g WHERE g.judet = :judetParam")
@NamedQuery(name = "Age.deleteByMonthAndCounty", query = "DELETE FROM Age g WHERE g.judet = :judetParam AND g.luna=:lunaParam")
@NamedQuery(name = "Age.all", query = "SELECT g FROM Age g ")
@NamedQuery(name = "Age.update", query = "UPDATE Age g SET g.maiMic25=:maiMic25Param, g.intre25si29=:intre25si29Param, g.intre30si39=:intre30si39Param, g.intre40si49=:intre40si49Param, g.intre50si55=:intre50si55Param, g.peste55=:peste55Param WHERE g.luna=:lunaParam and g.judet=:judetParam ")
@Table(name = "varsta")
public class Age {
    @Id
    int luna;
    @Id
    String judet;
    int maiMic25;
    int intre25si29;
    int intre30si39;
    int intre40si49;
    int intre50si55;
    int peste55;

    @Basic
    @Column
    public int getLuna() {
        return luna;
    }

    @Basic
    @Column
    public int getIntre25si29() {
        return intre25si29;
    }

    @Basic
    @Column
    public int getIntre30si39() {
        return intre30si39;
    }

    @Basic
    @Column
    public int getIntre40si49() {
        return intre40si49;
    }

    @Basic
    @Column
    public int getIntre50si55() {
        return intre50si55;
    }

    @Basic
    @Column
    public int getMaiMic25() {
        return maiMic25;
    }

    @Basic
    @Column
    public int getPeste55() {
        return peste55;
    }

    @Basic
    @Column
    public String getJudet() {
        return judet;
    }

    public void setIntre25si29(int intre25si29) {
        this.intre25si29 = intre25si29;
    }

    public void setLuna(int luna) {
        this.luna = luna;
    }

    public void setIntre30si39(int intre30si39) {
        this.intre30si39 = intre30si39;
    }

    public void setIntre40si49(int intre40si49) {
        this.intre40si49 = intre40si49;
    }

    public void setIntre50si55(int intre50si55) {
        this.intre50si55 = intre50si55;
    }

    public void setJudet(String judet) {
        this.judet = judet;
    }

    public void setMaiMic25(int maiMic25) {
        this.maiMic25 = maiMic25;
    }

    public void setPeste55(int peste55) {
        this.peste55 = peste55;
    }

    @Override
    public String toString() {
        return "Varsta " +
                "luna=" + luna +
                ", judet='" + judet + '\'' +
                ", maiMic25=" + maiMic25 +
                ", intre25si29=" + intre25si29 +
                ", intre30si39=" + intre30si39 +
                ", intre40si49=" + intre40si49 +
                ", intre50si55=" + intre50si55 +
                ", peste55=" + peste55;
    }
}
