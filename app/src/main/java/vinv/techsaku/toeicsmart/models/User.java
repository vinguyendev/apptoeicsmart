package vinv.techsaku.toeicsmart.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("name")
@Expose
private String name;
@SerializedName("email")
@Expose
private String email;
@SerializedName("email_verified_at")
@Expose
private String emailVerifiedAt;
@SerializedName("password")
@Expose
private String password;
@SerializedName("remember_token")
@Expose
private String rememberToken;
@SerializedName("provider")
@Expose
private String provider;
@SerializedName("provider_id")
@Expose
private String providerId;
@SerializedName("mobile")
@Expose
private String mobile;
@SerializedName("is_vip")
@Expose
private Integer isVip;

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

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public String getEmailVerifiedAt() {
return emailVerifiedAt;
}

public void setEmailVerifiedAt(String emailVerifiedAt) {
this.emailVerifiedAt = emailVerifiedAt;
}

public String getPassword() {
return password;
}

public void setPassword(String password) {
this.password = password;
}

public String getRememberToken() {
return rememberToken;
}

public void setRememberToken(String rememberToken) {
this.rememberToken = rememberToken;
}

public String getProvider() {
return provider;
}

public void setProvider(String provider) {
this.provider = provider;
}

public String getProviderId() {
return providerId;
}

public void setProviderId(String providerId) {
this.providerId = providerId;
}

public String getMobile() {
return mobile;
}

public void setMobile(String mobile) {
this.mobile = mobile;
}

public Integer getIsVip() {
return isVip;
}

public void setIsVip(Integer isVip) {
this.isVip = isVip;
}

}