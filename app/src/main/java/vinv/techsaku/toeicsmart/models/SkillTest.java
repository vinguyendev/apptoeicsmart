package vinv.techsaku.toeicsmart.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SkillTest {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("exam")
@Expose
private String exam;
@SerializedName("book")
@Expose
private String book;
@SerializedName("part_type")
@Expose
private String partType;
@SerializedName("direction")
@Expose
private String direction;
@SerializedName("questions")
@Expose
private ArrayList<Question> questions = null;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getExam() {
return exam;
}

public void setExam(String exam) {
this.exam = exam;
}

public String getBook() {
return book;
}

public void setBook(String book) {
this.book = book;
}

public String getPartType() {
return partType;
}

public void setPartType(String partType) {
this.partType = partType;
}

public String getDirection() {
return direction;
}

public void setDirection(String direction) {
this.direction = direction;
}

public ArrayList<Question> getQuestions() {
return questions;
}

public void setQuestions(ArrayList<Question> questions) {
this.questions = questions;
}

}