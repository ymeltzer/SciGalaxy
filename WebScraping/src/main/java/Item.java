import com.fasterxml.jackson.annotation.JsonGetter;



public class Item {
    private String title;
    private String firstAuthor ;
    private String references;
    private String url ;


    public String getTitle() {
        return title;
    }

    public String getFirstAuthor() {
        return firstAuthor;
    }
    public void setFirstAuthor(String firstAuthor) {
        this.firstAuthor = firstAuthor;
    }
    public String getReferences() {
        return references;
    }
    public void setReferences(String references) {
        this.references = references;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public void setTitle(String title) {
        this.title = title;
    }


}

