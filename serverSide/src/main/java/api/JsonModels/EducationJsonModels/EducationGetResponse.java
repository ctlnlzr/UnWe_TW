package api.JsonModels.EducationJsonModels;
import database.entity.Education;

import java.util.List;
public class EducationGetResponse {

    List<Education> groups;

    public EducationGetResponse() { }


    public List<Education> getGroups() {
        return groups;
    }

    public void setGroups(List<Education> groups) {
        this.groups = groups;
    }
    public void addGroups(List<Education> groups) {
        this.groups.addAll(groups);
    }
}
