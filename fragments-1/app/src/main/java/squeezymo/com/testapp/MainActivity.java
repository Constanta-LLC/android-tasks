package squeezymo.com.testapp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showMainFragment();
        showFragment1();
        showFragment2();
    }

    public void goBack(@NonNull View view) {
        getSupportFragmentManager()
                .popBackStackImmediate();
    }

    private void showMainFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new MainFragment())
                .commit();
    }

    private void showFragment1() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new Fragment1())
                .addToBackStack(null)
                .commit();
    }

    private void showFragment2() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new Fragment2())
                .commit();
    }

}
