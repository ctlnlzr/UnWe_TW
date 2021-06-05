package api.JsonModels.TotalJsonModels;

public class TotalItem {
    String varJudet;
    String varNumartotalsomeri;
    String varNumartotalsomerifemei;
    String varNumartotalsomeribarbati;
    String varNumarsomeriindemnizati;
    String varNumarsomerineindemnizati;
    String varRatasomajului;
    String varRatasomajuluifeminina;
    String varRatasomajuluimasculina;
    public String getVarJudet() { return varJudet; }
    public void setVarJudet(String varJudet) { this.varJudet = varJudet; }

    public int getVarNumartotalsomeri() { return Integer.parseInt(varNumartotalsomeri); }
    public void setVarNumartotalsomeri(String varNumartotalsomeri) { this.varNumartotalsomeri = varNumartotalsomeri; }

    public int getVarNumartotalsomerifemei() { return Integer.parseInt(varNumartotalsomerifemei); }
    public void setVarNumartotalsomerifemei(String varNumartotalsomerifemei) { this.varNumartotalsomerifemei = varNumartotalsomerifemei; }

    public int getVarNumartotalsomeribarbati() { return Integer.parseInt(varNumartotalsomeribarbati); }
    public void setVarNumartotalsomeribarbati(String varNumartotalsomeribarbati) { this.varNumartotalsomeribarbati = varNumartotalsomeribarbati; }

    public int getVarNumarsomeriindemnizati() { return Integer.parseInt(varNumarsomeriindemnizati); }
    public void setVarNumarsomeriindemnizati(String varNumarsomeriindemnizati) { this.varNumarsomeriindemnizati = varNumarsomeriindemnizati; }

    public int getVarNumarsomerineindemnizati() { return Integer.parseInt(varNumarsomerineindemnizati); }
    public void setVarNumarsomerineindemnizati(String varNumarsomerineindemnizati) { this.varNumarsomerineindemnizati = varNumarsomerineindemnizati; }

    public float getVarRatasomajului() { return Float.parseFloat(varRatasomajului); }
    public void setVarRatasomajului(String varRatasomajului) { this.varRatasomajului = varRatasomajului; }

    public float getVarRatasomajuluifeminina() { return Float.parseFloat(varRatasomajuluifeminina); }
    public void setVarRatasomajuluifeminina(String varRatasomajuluifeminina) { this.varRatasomajuluifeminina = varRatasomajuluifeminina; }

    public float getVarRatasomajuluimasculina() { return Float.parseFloat(varRatasomajuluimasculina); }
    public void setVarRatasomajuluimasculina(String varRatasomajuluimasculina) { this.varRatasomajuluimasculina = varRatasomajuluimasculina; }

    public TotalItem() { }
}
