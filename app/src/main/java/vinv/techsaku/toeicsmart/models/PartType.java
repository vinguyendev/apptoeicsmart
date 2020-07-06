package vinv.techsaku.toeicsmart.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PartType {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("name")
@Expose
private String name;
@SerializedName("title")
@Expose
private String title;
@SerializedName("url")
@Expose
private String url;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getTitle() {
return title;
}

public void setTitle(String title) {
this.title = title;
}

public String getUrl() {
return url;
}

public void setUrl(String url) {
this.url = url;
}

}