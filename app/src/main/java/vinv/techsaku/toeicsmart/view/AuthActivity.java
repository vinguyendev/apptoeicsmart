package vinv.techsaku.toeicsmart.view;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import vinv.techsaku.toeicsmart.MainActivity;
import vinv.techsaku.toeicsmart.R;
import vinv.techsaku.toeicsmart.adapters.AuthenticationPagerAdapter;
import vinv.techsaku.toeicsmart.ui.login.LoginFragment;
import vinv.techsaku.toeicsmart.ui.register.RegisterFragment;
import vinv.techsaku.toeicsmart.utils.AppConfig;

public class AuthActivity extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        ViewPager viewPager = findViewById(R.id.viewPager);

        String token = AppConfig.getToken(AuthActivity.this);

//        if (token!=null) {
//            Intent intent = new Intent();
//            intent.setClass(viewPager.getContext(), MainActivity.class);
//            startActivity(intent);
//        }

        AuthenticationPagerAdapter pagerAdapter = new AuthenticationPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new LoginFragment());
        pagerAdapter.addFragment(new RegisterFragment());
        viewPager.setAdapter(pagerAdapter);

    }

}
