package vinv.techsaku.toeicsmart.ui.login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vinv.techsaku.toeicsmart.MainActivity;
import vinv.techsaku.toeicsmart.R;
import vinv.techsaku.toeicsmart.models.LoginResponse;
import vinv.techsaku.toeicsmart.networks.DataServices;
import vinv.techsaku.toeicsmart.utils.AppConfig;

public class LoginFragment extends Fragment {

    public EditText email, password;
    public Button btnLogin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        email = (EditText) view.findViewById(R.id.et_email);
        password = (EditText) view.findViewById(R.id.et_password);
        btnLogin = (Button)view.findViewById(R.id.btn_login);

        email.setText("user@gmail.com");
        password.setText("user");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ShowToast")
            @Override
            public void onClick(View view) {
                login(view);
            }
        });

        return view;
    }

    void login(final View view) {
        boolean error   = false;

        String textEmail = email.getText().toString().trim();
        String textPassword = password.getText().toString().trim();

        if (TextUtils.isEmpty(textEmail)) {
            email.requestFocus();
            email.setError(view.getContext().getResources().getString(R.string.error_field_required));
            error = true;
        }

        if (TextUtils.isEmpty(textPassword)) {
            password.requestFocus();
            password.setError(view.getContext().getResources().getString(R.string.error_field_required));
            error = true;
        }

        if (!error) {
            DataServices.getAPIService().login(textEmail,textPassword).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                    LoginResponse loginResponse = response.body();
                    if (loginResponse != null) {
                        AppConfig.setToken(view.getContext(), loginResponse.getAccessToken());

                        Context context = view.getContext();

                        SharedPreferences sharedPreferences = context.getSharedPreferences("profile",Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString("name", loginResponse.getUser().getName());
                        editor.putString("email", loginResponse.getUser().getEmail());
                        editor.putString("id", loginResponse.getUser().getId().toString());
                        editor.apply();

                        Toast.makeText(view.getContext(),R.string.success_login, Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent();

                        intent.setClass(view.getContext(), MainActivity.class);

                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    System.out.println(t.toString());
                    Toast.makeText(view.getContext(),R.string.error_login, Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

}