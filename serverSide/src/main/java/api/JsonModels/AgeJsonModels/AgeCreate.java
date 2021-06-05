package api.JsonModels.AgeJsonModels;

import java.util.List;

public class AgeCreate {
    List<AgeItem> groups;

    public List<AgeItem> getGroups() { return groups; }
    public void setGroups(List<AgeItem> groups) { this.groups = groups; }
    public AgeCreate() { }
}
