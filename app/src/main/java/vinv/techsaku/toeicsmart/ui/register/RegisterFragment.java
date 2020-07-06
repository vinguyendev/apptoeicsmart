package vinv.techsaku.toeicsmart.ui.register;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
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

public class RegisterFragment extends Fragment {


    EditText email, mobile, password, name;
    Button btnRegister;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        init(view);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register(view);
            }
        });

        return view;
    }

    void init(View view) {
        email = view.findViewById(R.id.et_email);
        mobile = view.findViewById(R.id.et_mobile);
        password = view.findViewById(R.id.et_password);
        name = view.findViewById(R.id.et_name);
        btnRegister = view.findViewById(R.id.btn_register);
    }

    void register(final View view) {

        boolean error = false;

        String txtName = name.getText().toString().trim();
        String txtEmail = email.getText().toString().trim();
        String txtPassword = password.getText().toString().trim();
        String txtMobile = mobile.getText().toString().trim();

        if (TextUtils.isEmpty(txtName)) {
            name.requestFocus();
            name.setError(view.getContext().getResources().getString(R.string.error_field_required));
            error = true;
        }

        if (TextUtils.isEmpty(txtEmail)) {
            email.requestFocus();
            email.setError(view.getContext().getResources().getString(R.string.error_field_required));
            error = true;
        }

        if (TextUtils.isEmpty(txtPassword)) {
            password.requestFocus();
            password.setError(view.getContext().getResources().getString(R.string.error_field_required));
            error = true;
        }

        if (TextUtils.isEmpty(txtMobile)) {
            mobile.requestFocus();
            mobile.setError(view.getContext().getResources().getString(R.string.error_field_required));
            error = true;
        }

        if (!error) {
            DataServices.getAPIService().register(txtEmail,txtMobile,txtPassword,txtName).enqueue(new Callback<LoginResponse>() {
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

                        Toast.makeText(view.getContext(),R.string.success_register, Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent();

                        intent.setClass(view.getContext(), MainActivity.class);

                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    System.out.println(t.toString());
                    Toast.makeText(view.getContext(),R.string.error_register, Toast.LENGTH_SHORT).show();
                }
            });
        }


    }

}