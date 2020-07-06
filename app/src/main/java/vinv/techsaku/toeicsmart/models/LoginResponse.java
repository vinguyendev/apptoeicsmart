package vinv.techsaku.toeicsmart.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

@SerializedName("user")
@Expose
private User user;
@SerializedName("access_token")
@Expose
private String accessToken;

public User getUser() {
return user;
}

public void setUser(User user) {
this.user = user;
}

public String getAccessToken() {
return accessToken;
}

public void setAccessToken(String accessToken) {
this.accessToken = accessToken;
}

}