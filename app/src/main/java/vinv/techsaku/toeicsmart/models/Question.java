package vinv.techsaku.toeicsmart.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Question {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("code_question")
@Expose
private Integer codeQuestion;
@SerializedName("content")
@Expose
private String content;
@SerializedName("answers")
@Expose
private ArrayList<Answer> answers = null;
@SerializedName("key")
@Expose
private String key;
@SerializedName("explain")
@Expose
private String explain;
@SerializedName("vocabularies")
@Expose
private String vocabularies;
@SerializedName("translate")
@Expose
private String translate;
@SerializedName("format")
@Expose
private String format;

private String answer;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public Integer getCodeQuestion() {
return codeQuestion;
}

public void setCodeQuestion(Integer codeQuestion) {
this.codeQuestion = codeQuestion;
}

public String getContent() {
return content;
}

public void setContent(String content) {
this.content = content;
}

public ArrayList<Answer> getAnswers() {
return answers;
}

public void setAnswers(ArrayList<Answer> answers) {
this.answers = answers;
}

public String getKey() {
return key;
}

public void setKey(String key) {
this.key = key;
}

public String getExplain() {
return explain;
}

public void setExplain(String explain) {
this.explain = explain;
}

public String getVocabularies() {
return vocabularies;
}

public void setVocabularies(String vocabularies) {
this.vocabularies = vocabularies;
}

public String getTranslate() {
return translate;
}

public void setTranslate(String translate) {
this.translate = translate;
}

public String getFormat() {
return format;
}

public void setFormat(String format) {
this.format = format;
}

public String getAnswer() {
    return answer;
}

public void setAnswer(String answer) {
    this.answer = answer;
}

}