package vinv.techsaku.toeicsmart.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserSkillTest {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("skill_test_id")
    @Expose
    private Integer skillTestId;
    @SerializedName("correct_sentences")
    @Expose
    private String correctSentences;
    @SerializedName("correct_ratio")
    @Expose
    private String correctRatio;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("id")
    @Expose
    private Integer id;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getSkillTestId() {
        return skillTestId;
    }

    public void setSkillTestId(Integer skillTestId) {
        this.skillTestId = skillTestId;
    }

    public String getCorrectSentences() {
        return correctSentences;
    }

    public void setCorrectSentences(String correctSentences) {
        this.correctSentences = correctSentences;
    }

    public String getCorrectRatio() {
        return correctRatio;
    }

    public void setCorrectRatio(String correctRatio) {
        this.correctRatio = correctRatio;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}