package api.JsonModels.gender;

public class Gender {
    int month;
    String county;
    int female;
    int male;
    public Gender() {
    }

    public int getFemale() {
        return female;
    }

    public void setFemale(int female) {
        this.female = female;
    }

    public int getMale() {
        return male;
    }

    public void setMale(int male) {
        this.male = male;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }
}
