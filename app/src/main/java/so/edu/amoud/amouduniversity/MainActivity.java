package so.edu.amoud.amouduniversity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    FrameLayout fragment_container;
    Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    BottomNavigationView bottomNav;

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        HomeFragment fragment = new HomeFragment();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.commit();


        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.getMenu().getItem(0).setCheckable(false);
//        bottomNav.setSelectedItemId(0);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                switch(item.getItemId()){

                    case R.id.navHome:
                        selectedFragment = new HomeFragment();
                        item.setCheckable(true);
                        break;

                    case R.id.navExam:
                        selectedFragment = new ExamFragment();
                        item.setCheckable(true);
                        break;

                     case R.id.navWeb:
                        selectedFragment = new WebFragment();
                        item.setCheckable(true);
                        break;

                    case R.id.navInfo:
                        selectedFragment = new InfoFragment();
                        item.setCheckable(true);
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();


                return true;
            }
        });
    }
}
