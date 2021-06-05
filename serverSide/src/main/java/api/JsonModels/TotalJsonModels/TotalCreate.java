package api.JsonModels.TotalJsonModels;

import java.util.List;

public class TotalCreate {
    List<TotalItem> groups;

    public List<TotalItem> getGroups() {
        return groups;
    }

    public void setGroups(List<TotalItem> groups) {
        this.groups = groups;
    }
    public TotalCreate() {
    }
}
