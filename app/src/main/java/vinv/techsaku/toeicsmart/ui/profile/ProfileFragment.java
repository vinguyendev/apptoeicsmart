package vinv.techsaku.toeicsmart.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vinv.techsaku.toeicsmart.R;
import vinv.techsaku.toeicsmart.models.Profile;
import vinv.techsaku.toeicsmart.networks.DataServices;
import vinv.techsaku.toeicsmart.utils.AppConfig;
import vinv.techsaku.toeicsmart.view.UpdateActivity;

public class ProfileFragment extends Fragment {

    TextView name, email, mobile;
    Button btnUpdate;
    LinearLayout containView;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        mobile = view.findViewById(R.id.mobile);
        name = view.findViewById(R.id.name);
        btnUpdate = view.findViewById(R.id.btnUpdate);
        progressBar = view.findViewById(R.id.progressBar);
        containView = view.findViewById(R.id.containView);

        DataServices.getAPIService().getProfile("Bearer " + AppConfig.getToken(view.getContext())).enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                Profile profile = response.body();

                name.setText(profile.getName());
                email.setText(profile.getEmail());
                mobile.setText(profile.getMobile());
                progressBar.setVisibility(View.GONE);
                containView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(view.getContext(), UpdateActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
