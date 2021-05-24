package edu.tw.database.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;

@NamedQuery(name = "Education.findByLuna", query = "SELECT g FROM Education g WHERE g.luna = :lunaParam")
@NamedQuery(name = "Education.findByLunaAndCounty", query = "SELECT g FROM Education g WHERE g.luna = :lunaParam AND g.judet=:judetParam")
@NamedQuery(name = "Education.findByCounty", query = "SELECT g FROM Education g WHERE g.judet = :judetParam")
@NamedQuery(name = "Education.deleteByMonthAndCounty", query = "DELETE FROM Education g WHERE g.judet = :judetParam AND g.luna=:lunaParam")
@NamedQuery(name = "Education.all", query = "SELECT g FROM Education g")
@NamedQuery(name = "Education.filterByMonths", query = "SELECT g FROM Education g WHERE g.luna<=:lunaParam AND g.judet=:judetParam")
@NamedQuery(name = "Education.update", query = "UPDATE Education g SET g.faraStudii=:faraStudiiParam, g.primar=:primarParam, g.gimnaziu=:gimnaziuParam, g.liceu=:liceuParam, g.postliceala=:postlicealaParam, g.profesionala=:profesionalaParam, g.universitate=:universitateParam WHERE g.luna=:lunaParam and g.judet=:judetParam ")
@Entity
@Table(name = "educatie")
public class Education implements Serializable {
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
