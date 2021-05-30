package api.JsonModels.TotalJsonModels;

import database.entity.TotalPerMonth;
import java.util.List;

public class TotalGetResponse {
    List<TotalPerMonth> groups;

    public TotalGetResponse() { }

    public List<TotalPerMonth> getGroups() {
        return groups;
    }

    public void setGroups(List<TotalPerMonth> groups) {
        this.groups = groups;
    }
}
