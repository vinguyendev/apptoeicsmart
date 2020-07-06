package vinv.techsaku.toeicsmart;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;


import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vinv.techsaku.toeicsmart.adapters.AuthenticationPagerAdapter;
import vinv.techsaku.toeicsmart.adapters.BookListViewAdapter;
import vinv.techsaku.toeicsmart.adapters.ExamListViewAdapter;
import vinv.techsaku.toeicsmart.models.Book;
import vinv.techsaku.toeicsmart.models.Exam;
import vinv.techsaku.toeicsmart.networks.DataServices;
import vinv.techsaku.toeicsmart.ui.gallery.GalleryFragment;
import vinv.techsaku.toeicsmart.ui.home.HomeFragment;
import vinv.techsaku.toeicsmart.ui.login.LoginFragment;
import vinv.techsaku.toeicsmart.ui.profile.ProfileFragment;
import vinv.techsaku.toeicsmart.ui.register.RegisterFragment;
import vinv.techsaku.toeicsmart.ui.result.ResultFragment;
import vinv.techsaku.toeicsmart.utils.AppConfig;
import vinv.techsaku.toeicsmart.view.AuthActivity;
import vinv.techsaku.toeicsmart.view.TestActivity;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private Context context;
    private LinearLayout viewHome;
    private FrameLayout frameLayout;
    private ListView listViewExam;
    ProgressBar progressBar;
    ExamListViewAdapter examListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        listViewExam = findViewById(R.id.listViewExam);
        setListViewExam();

        drawerLayout = (DrawerLayout) findViewById(R.id.mainDrawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        viewHome = findViewById(R.id.view_home);
        frameLayout = findViewById(R.id.frame);
        progressBar = findViewById(R.id.progressBar);

        navigationView = (NavigationView)findViewById(R.id.nav_item);

        View header = navigationView.getHeaderView(0);
        TextView nameUser = (TextView) header.findViewById(R.id.nameUser);
        TextView emailUser = (TextView) header.findViewById(R.id.emailUser);

        SharedPreferences sharedPreferences = context.getSharedPreferences("profile",Context.MODE_PRIVATE);
        String txtName = sharedPreferences.getString("name","Vi Nguyen");
        String txtEmail = sharedPreferences.getString("email","vinguyen@gmail.com");

        nameUser.setText(txtName);
        emailUser.setText(txtEmail);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Fragment fragment = new HomeFragment();
                frameLayout.setVisibility(View.VISIBLE);
                viewHome.setVisibility(View.GONE);

                switch (id) {
                    case R.id.result:
                        fragment = new ResultFragment();
                        break;
                    case R.id.profile:
                        fragment = new ProfileFragment();
                        break;
                    case R.id.logout:
                        logout();
                        break;
                    default:
                        frameLayout.setVisibility(View.GONE);
                        viewHome.setVisibility(View.VISIBLE);
                }

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment);
                fragmentTransaction.commit();
                drawerLayout.closeDrawers();
                return  true;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return  true;
        }

        return super.onOptionsItemSelected(item);
    }

    void setListViewExam() {

        DataServices.getAPIService().getListExams("Bearer " + AppConfig.getToken(MainActivity.this)).enqueue(new Callback<ArrayList<Exam>>() {
            @Override
            public void onResponse(Call<ArrayList<Exam>> call, Response<ArrayList<Exam>> response) {
                ArrayList<Exam> exams = response.body();
                examListViewAdapter = new ExamListViewAdapter(exams);
                listViewExam = findViewById(R.id.listViewExam);
                listViewExam.setAdapter(examListViewAdapter);
                progressBar.setVisibility(View.GONE);
                listViewExam.setVisibility(View.VISIBLE);

                listViewExam.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        int examId = (int)examListViewAdapter.getItemId(i);
                        Intent intent = new Intent();
                        intent.setClass(view.getContext(), TestActivity.class);
                        intent.putExtra("examId", examId);
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onFailure(Call<ArrayList<Exam>> call, Throwable t) {

            }
        });

    }

    void logout() {
        Intent intent = new Intent();
        intent.setClass(context, AuthActivity.class);
        startActivity(intent);
    }
}