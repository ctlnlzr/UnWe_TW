package api.JsonModels.commonModels;

public class CreateRequest {
  String filePath;

    public CreateRequest() { }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
