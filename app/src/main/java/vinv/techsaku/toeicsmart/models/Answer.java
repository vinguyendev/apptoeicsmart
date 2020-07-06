package vinv.techsaku.toeicsmart.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Answer {

@SerializedName("code")
@Expose
private String code;
@SerializedName("content")
@Expose
private String content;
@SerializedName("translate")
@Expose
private String translate;
@SerializedName("is_correct")
@Expose
private Integer isCorrect;

public String getCode() {
return code;
}

public void setCode(String code) {
this.code = code;
}

public String getContent() {
return content;
}

public void setContent(String content) {
this.content = content;
}

public String getTranslate() {
return translate;
}

public void setTranslate(String translate) {
this.translate = translate;
}

public Integer getIsCorrect() {
return isCorrect;
}

public void setIsCorrect(Integer isCorrect) {
this.isCorrect = isCorrect;
}

}