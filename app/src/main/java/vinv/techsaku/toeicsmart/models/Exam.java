package vinv.techsaku.toeicsmart.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Exam {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("name")
@Expose
private String name;
@SerializedName("url_audio")
@Expose
private String urlAudio;
@SerializedName("status")
@Expose
private Integer status;
@SerializedName("book")
@Expose
private String book;

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

public String getUrlAudio() {
return urlAudio;
}

public void setUrlAudio(String urlAudio) {
this.urlAudio = urlAudio;
}

public Integer getStatus() {
return status;
}

public void setStatus(Integer status) {
this.status = status;
}

public String getBook() {
return book;
}

public void setBook(String book) {
this.book = book;
}

}