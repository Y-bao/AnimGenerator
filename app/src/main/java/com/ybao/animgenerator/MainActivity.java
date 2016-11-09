package com.ybao.animgenerator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Y-bao on 2016/11/8.
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new MoveFragment())
                    .commit();
            setTitle("Move");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.backboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_move:
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, new MoveFragment())
                        .commit();
                setTitle("Move");
                return true;

            case R.id.action_move_flower:
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, new MoveFlowerFragment())
                        .commit();
                setTitle("MoveFlower");
                return true;

            case R.id.action_press:
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, new PressFragment())
                        .commit();
                setTitle("Press");
                return true;

            case R.id.action_press_flower:
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, new PressFlowerFragment())
                        .commit();
                setTitle("PressFlower");
                return true;

            case R.id.follow:
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, new FollowFragment())
                        .commit();
                setTitle("Follow");
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
