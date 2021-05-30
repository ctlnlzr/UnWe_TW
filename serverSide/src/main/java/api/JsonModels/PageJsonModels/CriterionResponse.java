package api.JsonModels.PageJsonModels;

import java.util.List;

public class CriterionResponse {
    List<Criterion> groups;

    public List<Criterion> getGroups() {
        return groups;
    }

    public void setGroups(List<Criterion> groups) {
        this.groups = groups;
    }
    public void addGroups(List<Criterion> groups) {
        this.groups.addAll(groups);
    }
}
