package api.JsonModels.EnvironmentJsonModels;

import database.entity.Environment;
import java.util.List;
public class EnvironmentResponse {
    List<Environment> groups;

    public EnvironmentResponse() { }


    public List<Environment> getGroups() {
        return groups;
    }

    public void setGroups(List<Environment> groups) {
        this.groups = groups;
    }
    public void addGroups(List<Environment> groups) {
        this.groups.addAll(groups);
    }
}
