package api.JsonModels.EducationJsonModels;

import java.util.List;

public class EducationCreate {
    List<EducationItem> groups;

    public List<EducationItem> getGroups() { return groups; }
    public void setGroups(List<EducationItem> groups) { this.groups = groups; }

    public EducationCreate() { }
    public EducationCreate(List<EducationItem> groups) { this.groups = groups; }
}
