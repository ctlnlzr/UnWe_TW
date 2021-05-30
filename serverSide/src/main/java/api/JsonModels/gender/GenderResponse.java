package api.JsonModels.gender;

import java.util.List;

public class GenderResponse {
    List<Gender> groups;

    public void addGroups(List<Gender> groups){
        this.groups.addAll(groups);
    }
    public List<Gender> getGroups() {
        return groups;
    }

    public void setGroups(List<Gender> groups) {
        this.groups = groups;
    }
}
