package vinv.techsaku.toeicsmart.ui.result;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vinv.techsaku.toeicsmart.MainActivity;
import vinv.techsaku.toeicsmart.R;
import vinv.techsaku.toeicsmart.adapters.ResultListViewAdapter;
import vinv.techsaku.toeicsmart.models.UserTest;
import vinv.techsaku.toeicsmart.networks.DataServices;
import vinv.techsaku.toeicsmart.utils.AppConfig;

public class ResultFragment extends Fragment {

    ListView listResult;
    int user_id = 1;
    ResultListViewAdapter resultListViewAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_result, container, false);

        listResult = view.findViewById(R.id.listResult);

        SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("profile", Context.MODE_PRIVATE);
        String idUser = sharedPreferences.getString("id","1");
        if (idUser != null) {
            user_id = Integer.parseInt(idUser);
        }

        DataServices.getAPIService().getUserTest("Bearer " + AppConfig.getToken(view.getContext()),user_id).enqueue(new Callback<ArrayList<UserTest>>() {
            @Override
            public void onResponse(Call<ArrayList<UserTest>> call, Response<ArrayList<UserTest>> response) {
                ArrayList<UserTest> userTests = response.body();
                resultListViewAdapter = new ResultListViewAdapter(userTests);
                listResult.setAdapter(resultListViewAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<UserTest>> call, Throwable t) {

            }
        });


        return view;
    }


}
