package api.JsonModels.EducationJsonModels;

public class EducationItem {
    String varJudet;
    String varTotalsomeri;
    String varFarastudii;
    String varInvatamantprimar;
    String varInvatamantgimnazial;
    String varInvatamantliceal;
    String varInvatamantposticeal;
    String varInvatamantprofesionalartesimeserii;
    String varInvatamantuniversitar;

    public EducationItem() {}

    public String getVarJudet() { return varJudet; }
    public void setVarJudet(String varJudet) { this.varJudet = varJudet; }

    public int getVarTotalsomeri() { return Integer.parseInt(varTotalsomeri); }
    public void setVarTotalsomeri(String varTotalsomeri) { this.varTotalsomeri = varTotalsomeri; }

    public int getVarFarastudii() { return Integer.parseInt(varFarastudii); }
    public void setVarFarastudii(String varFarastudii) { this.varFarastudii = varFarastudii; }

    public int getVarInvatamantprimar() { return Integer.parseInt(varInvatamantprimar); }
    public void setVarInvatamantprimar(String varInvatamantprimar) { this.varInvatamantprimar = varInvatamantprimar; }

    public int getVarInvatamantgimnazial() { return Integer.parseInt(varInvatamantgimnazial); }
    public void setVarInvatamantgimnazial(String varInvatamantgimnazial) { this.varInvatamantgimnazial = varInvatamantgimnazial; }

    public int getVarInvatamantliceal() { return Integer.parseInt(varInvatamantliceal); }
    public void setVarInvatamantliceal(String varInvatamantliceal) { this.varInvatamantliceal = varInvatamantliceal; }

    public int getVarInvatamantposticeal() { return Integer.parseInt(varInvatamantposticeal); }
    public void setVarInvatamantposticeal(String varInvatamantpostliceal) { this.varInvatamantposticeal = varInvatamantpostliceal; }

    public int getVarInvatamantprofesionalartesimeserii() { return Integer.parseInt(varInvatamantprofesionalartesimeserii); }
    public void setVarInvatamantprofesionalartesimeserii(String varInvatamantprofesionalartesimeserii) { this.varInvatamantprofesionalartesimeserii = varInvatamantprofesionalartesimeserii; }

    public int getVarInvatamantuniversitar() { return Integer.parseInt(varInvatamantuniversitar); }
    public void setVarInvatamantuniversitar(String varInvatamantuniversitar) { this.varInvatamantuniversitar = varInvatamantuniversitar; }

    public EducationItem(String varJudet, String varTotalsomeri, String varFarastudii, String varInvatamantprimar, String varInvatamantgimnazial, String varInvatamantliceal, String varInvatamantposticeal, String varInvatamantprofesionalartesimeserii, String varInvatamantuniversitar) {
        this.varJudet = varJudet;
        this.varTotalsomeri = varTotalsomeri;
        this.varFarastudii = varFarastudii;
        this.varInvatamantprimar = varInvatamantprimar;
        this.varInvatamantgimnazial = varInvatamantgimnazial;
        this.varInvatamantliceal = varInvatamantliceal;
        this.varInvatamantposticeal = varInvatamantposticeal;
        this.varInvatamantprofesionalartesimeserii = varInvatamantprofesionalartesimeserii;
        this.varInvatamantuniversitar = varInvatamantuniversitar;
    }
}
