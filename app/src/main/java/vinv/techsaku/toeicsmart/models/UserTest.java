package vinv.techsaku.toeicsmart.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserTest {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name_skill")
    @Expose
    private String nameSkill;
    @SerializedName("title_skill")
    @Expose
    private String titleSkill;
    @SerializedName("date_test")
    @Expose
    private String dateTest;
    @SerializedName("correct_sentences")
    @Expose
    private Integer correctSentences;
    @SerializedName("correct_ratio")
    @Expose
    private String correctRatio;

    public String getNameSkill() {
    return nameSkill;
    }

    public void setNameSkill(String nameSkill) {
    this.nameSkill = nameSkill;
    }

    public String getTitleSkill() {
    return titleSkill;
    }

    public void setTitleSkill(String titleSkill) {
    this.titleSkill = titleSkill;
    }

    public String getDateTest() {
    return dateTest;
    }

    public void setDateTest(String dateTest) {
    this.dateTest = dateTest;
    }

    public Integer getCorrectSentences() {
    return correctSentences;
    }

    public void setCorrectSentences(Integer correctSentences) {
    this.correctSentences = correctSentences;
    }

    public String getCorrectRatio() {
    return correctRatio;
    }

    public void setCorrectRatio(String correctRatio) {
    this.correctRatio = correctRatio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
