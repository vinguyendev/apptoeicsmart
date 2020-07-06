package vinv.techsaku.toeicsmart.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vinv.techsaku.toeicsmart.MainActivity;
import vinv.techsaku.toeicsmart.R;
import vinv.techsaku.toeicsmart.models.User;
import vinv.techsaku.toeicsmart.networks.DataServices;
import vinv.techsaku.toeicsmart.utils.AppConfig;

public class UpdateActivity extends AppCompatActivity {

    EditText name, mobile;
    Button btnUpdate;
    int user_id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name = findViewById(R.id.name);
        mobile = findViewById(R.id.mobile);
        btnUpdate = findViewById(R.id.btnUpdate);

        SharedPreferences sharedPreferences = this.getSharedPreferences("profile", Context.MODE_PRIVATE);
        String idUser = sharedPreferences.getString("id","1");
        final String email = sharedPreferences.getString("email","vinguyen@gmail.com");
        if (idUser != null) {
            user_id = Integer.parseInt(idUser);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String txtName = name.getText().toString();
                String txtMobile = mobile.getText().toString();
                DataServices.getAPIService().postUpdateProfile(user_id, "Bearer " + AppConfig.getToken(view.getContext()), txtName, txtMobile, email).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Toast.makeText(view.getContext(),"Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.setClass(view.getContext(), MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });

            }
        });

    }
}
