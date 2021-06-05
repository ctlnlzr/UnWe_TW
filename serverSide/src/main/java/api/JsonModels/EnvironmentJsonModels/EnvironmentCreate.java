package api.JsonModels.EnvironmentJsonModels;

import java.util.List;

public class EnvironmentCreate {
    List<EnvironmentItem> groups;

    public EnvironmentCreate() {  }
    public List<EnvironmentItem> getGroups() { return groups; }
    public void setGroups(List<EnvironmentItem> groups) { this.groups = groups; }
}
