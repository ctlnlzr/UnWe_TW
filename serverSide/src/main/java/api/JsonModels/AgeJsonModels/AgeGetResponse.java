package api.JsonModels.AgeJsonModels;

import database.entity.Age;
import java.util.List;

public class AgeGetResponse {
    List<Age> groups;

    public AgeGetResponse() {
    }

    public List<Age> getGroups() {
        return groups;
    }
    public void addGroups(List<Age> groups) { this.groups.addAll( groups); }

    public void setGroups(List<Age> groups) {
        this.groups = groups;
    }

    public AgeGetResponse(List<Age> groups) {
        this.groups = groups;
    }
}
