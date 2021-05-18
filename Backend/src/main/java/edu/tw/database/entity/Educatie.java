package edu.tw.database.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQuery(name="Educatie.findByLuna", query = "SELECT g FROM Educatie g WHERE g.luna = :lunaParam")
@NamedQuery(name="Educatie.findByLunaAndCounty", query = "SELECT g FROM Educatie g WHERE g.luna = :lunaParam AND g.judet=:judetParam")
@NamedQuery(name="Educatie.findByCounty", query = "SELECT g FROM Educatie g WHERE g.judet = :judetParam")
@Entity
@Table(name="educatie")
public class Educatie {
    @Id
    int luna;
    @Id
    String judet;
    int faraStudii;
    int primar;
    int gimnaziu;
    int liceu;
    int postliceala;
    int profesionala;
    int universitate;

    public String getJudet() {
        return judet;
    }

    public int getLuna() {
        return luna;
    }

    public int getFaraStudii() {
        return faraStudii;
    }

    public int getGimnaziu() {
        return gimnaziu;
    }

    public int getLiceu() {
        return liceu;
    }

    public int getPostliceala() {
        return postliceala;
    }

    public int getPrimar() {
        return primar;
    }

    public int getProfesionala() {
        return profesionala;
    }

    public int getUniversitate() {
        return universitate;
    }

    public void setJudet(String judet) {
        this.judet = judet;
    }

    public void setLuna(int luna) {
        this.luna = luna;
    }

    public void setFaraStudii(int faraStudii) {
        this.faraStudii = faraStudii;
    }

    public void setGimnaziu(int gimnaziu) {
        this.gimnaziu = gimnaziu;
    }

    public void setLiceu(int liceu) {
        this.liceu = liceu;
    }

    public void setPostliceala(int postliceala) {
        this.postliceala = postliceala;
    }

    public void setPrimar(int primar) {
        this.primar = primar;
    }

    public void setProfesionala(int profesionala) {
        this.profesionala = profesionala;
    }

    public void setUniversitate(int universitate) {
        this.universitate = universitate;
    }

    @Override
    public String toString() {
        return
                "luna=" + luna +
                ", judet='" + judet + '\'' +
                ", faraStudii=" + faraStudii +
                ", primar=" + primar +
                ", gimnaziu=" + gimnaziu +
                ", liceu=" + liceu +
                ", postliceala=" + postliceala +
                ", profesionala=" + profesionala +
                ", universitate=" + universitate;
    }
}
