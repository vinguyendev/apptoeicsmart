package vinv.techsaku.toeicsmart.networks;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import vinv.techsaku.toeicsmart.models.Book;
import vinv.techsaku.toeicsmart.models.Exam;
import vinv.techsaku.toeicsmart.models.LoginResponse;
import vinv.techsaku.toeicsmart.models.PartType;
import vinv.techsaku.toeicsmart.models.SkillTest;
import vinv.techsaku.toeicsmart.models.UserSkillTest;
import vinv.techsaku.toeicsmart.models.UserTest;


public interface APIServices {

    @GET("api/part-types")
    Call<List<PartType>> getPartTypes();

    @POST("api/auth/login")
    @FormUrlEncoded
    Call<LoginResponse> login(@Field("email") String email, @Field("password") String password);

    @GET("api/books")
    Call<ArrayList<Book>> getListBooks(@Header("Authorization") String token);

    @GET("api/exams")
    Call<ArrayList<Exam>> getListExams(@Header("Authorization") String token);

    @GET("api/skill-test/")
    Call<SkillTest> getSkillTest(@Header("Authorization") String token, @Query("exam_id") Integer examId, @Query("part_type_id") Integer partTypeId);

    @POST("api/user-skill-tests")
    @FormUrlEncoded
    Call<UserSkillTest> postUserSkillTest(
            @Header("Authorization") String token,
            @Field("part_id") int part_id,
            @Field("user_id") int user_id,
            @Field("correct_sentences") int correct_sentences,
            @Field("correct_ratio") String correct_ratio);

    @GET("api/skill-test-by-user")
    Call<ArrayList<UserTest>> getUserTest(@Header("Authorization") String token, @Query("user_id") Integer user_id);

}