package yalantis.com.sidemenu.sample;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;
import yalantis.com.sidemenu.interfaces.Resourceble;
import yalantis.com.sidemenu.interfaces.ScreenShotable;
import yalantis.com.sidemenu.model.SlideMenuItem;
import yalantis.com.sidemenu.sample.fragment.ContentFragment;
import yalantis.com.sidemenu.sample.fragment.ContentFragment2;
import yalantis.com.sidemenu.sample.fragment.ContentFragment3;
import yalantis.com.sidemenu.sample.fragment.ContentFragment4;
import yalantis.com.sidemenu.sample.fragment.ContentFragment5;
import yalantis.com.sidemenu.sample.fragment.ContentFragment6;
import yalantis.com.sidemenu.sample.fragment.ContentFragment7;
import yalantis.com.sidemenu.util.ViewAnimator;


public class MainActivity extends ActionBarActivity implements ViewAnimator.ViewAnimatorListener {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private List<SlideMenuItem> list = new ArrayList<>();
    private ContentFragment contentFragment;
    private ContentFragment contentFragment2;
    private ContentFragment contentFragment3;
    private ContentFragment contentFragment4;
    private ContentFragment contentFragment5;
    private ContentFragment contentFragment6;
    private ContentFragment contentFragment7;

    private ViewAnimator viewAnimator;
    private int res ;
    private LinearLayout linearLayout;

    Button b1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1=(Button)findViewById(R.id.button);

        contentFragment = ContentFragment.newInstance(R.drawable.content_music);
        contentFragment2 = ContentFragment.newInstance(R.drawable.transportation_1);
        contentFragment3 = ContentFragment.newInstance(R.drawable.institute_info_1);
        contentFragment4 = ContentFragment.newInstance(R.drawable.culture_info_1);
        contentFragment5 = ContentFragment.newInstance(R.drawable.bank_info_1);
        contentFragment6 = ContentFragment.newInstance(R.drawable.living_info_1);
        contentFragment7 = ContentFragment.newInstance(R.drawable.call_info_1);


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, contentFragment)
                .commit();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        linearLayout = (LinearLayout)findViewById(R.id.left_drawer);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
            }
        });


        setActionBar();
        createMenuList();
        viewAnimator = new ViewAnimator<>(this, list, contentFragment, drawerLayout, this);



    }

    private void createMenuList() {
        SlideMenuItem menuItem0 = new SlideMenuItem(ContentFragment.CLOSE, R.drawable.icn_close);
        list.add(menuItem0);
        SlideMenuItem menuItem = new SlideMenuItem(ContentFragment.HOME, R.drawable.home);//icn_1
        list.add(menuItem);
        SlideMenuItem menuItem2 = new SlideMenuItem(ContentFragment.TRANSPORTATION, R.drawable.transinfo);//icn_2
        list.add(menuItem2);
        SlideMenuItem menuItem3 = new SlideMenuItem(ContentFragment.INSTITUTE, R.drawable.instinfo);//icn_3
        list.add(menuItem3);
        SlideMenuItem menuItem4 = new SlideMenuItem(ContentFragment.CULTURE, R.drawable.culture);//icn_4
        list.add(menuItem4);
        SlideMenuItem menuItem5 = new SlideMenuItem(ContentFragment.BANK, R.drawable.bankinfo);//icn_5
        list.add(menuItem5);
        SlideMenuItem menuItem6 = new SlideMenuItem(ContentFragment.LIVING, R.drawable.life);//icn_6
        list.add(menuItem6);
        SlideMenuItem menuItem7 = new SlideMenuItem(ContentFragment.CALL, R.drawable.number);//icn_7
        list.add(menuItem7);
    }


    private void setActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                toolbar,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                linearLayout.removeAllViews();
                linearLayout.invalidate();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if (slideOffset > 0.6 && linearLayout.getChildCount() == 0)
                    viewAnimator.showMenuContent();
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private ScreenShotable replaceFragment(ScreenShotable screenShotable, int topPosition) {

        this.res=R.drawable.content_music;
        View view = findViewById(R.id.content_frame);
        int finalRadius = Math.max(view.getWidth(), view.getHeight());
        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, topPosition, 0, finalRadius);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);

        findViewById(R.id.content_overlay).setBackgroundDrawable(new BitmapDrawable(getResources(), screenShotable.getBitmap()));
        animator.start();
        ContentFragment contentFragment = ContentFragment.newInstance(this.res);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, contentFragment).commit();
        return contentFragment;

    }

    private ScreenShotable replaceFragment2(ScreenShotable screenShotable, int topPosition) {

        this.res=R.drawable.transportation_1;
        View view = findViewById(R.id.content_frame);
        int finalRadius = Math.max(view.getWidth(), view.getHeight());
        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, topPosition, 0, finalRadius);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);

        findViewById(R.id.content_overlay).setBackgroundDrawable(new BitmapDrawable(getResources(), screenShotable.getBitmap()));
        animator.start();
        ContentFragment2 contentFragment2 = ContentFragment2.newInstance(this.res);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, contentFragment2).commit();
        return contentFragment2;
    }

    private ScreenShotable replaceFragment3(ScreenShotable screenShotable, int topPosition) {

        this.res=R.drawable.institute_info_1;
        View view = findViewById(R.id.content_frame);
        int finalRadius = Math.max(view.getWidth(), view.getHeight());
        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, topPosition, 0, finalRadius);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);

        findViewById(R.id.content_overlay).setBackgroundDrawable(new BitmapDrawable(getResources(), screenShotable.getBitmap()));
        animator.start();
        ContentFragment3 contentFragment3 = ContentFragment3.newInstance(this.res);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, contentFragment3).commit();
        return contentFragment3;
    }

    private ScreenShotable replaceFragment4(ScreenShotable screenShotable, int topPosition) {

        this.res=R.drawable.culture_info_1;
        View view = findViewById(R.id.content_frame);
        int finalRadius = Math.max(view.getWidth(), view.getHeight());
        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, topPosition, 0, finalRadius);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);

        findViewById(R.id.content_overlay).setBackgroundDrawable(new BitmapDrawable(getResources(), screenShotable.getBitmap()));
        animator.start();
        ContentFragment4 contentFragment4 = ContentFragment4.newInstance(this.res);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, contentFragment4).commit();
        return contentFragment4;
    }

    private ScreenShotable replaceFragment5(ScreenShotable screenShotable, int topPosition) {

        this.res=R.drawable.bank_info_1;
        View view = findViewById(R.id.content_frame);
        int finalRadius = Math.max(view.getWidth(), view.getHeight());
        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, topPosition, 0, finalRadius);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);

        findViewById(R.id.content_overlay).setBackgroundDrawable(new BitmapDrawable(getResources(), screenShotable.getBitmap()));
        animator.start();
        ContentFragment5 contentFragment5 = ContentFragment5.newInstance(this.res);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, contentFragment5).commit();
        return contentFragment5;
    }

    private ScreenShotable replaceFragment6(ScreenShotable screenShotable, int topPosition) {

        this.res=R.drawable.living_info_1;
        View view = findViewById(R.id.content_frame);
        int finalRadius = Math.max(view.getWidth(), view.getHeight());
        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, topPosition, 0, finalRadius);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);

        findViewById(R.id.content_overlay).setBackgroundDrawable(new BitmapDrawable(getResources(), screenShotable.getBitmap()));
        animator.start();
        ContentFragment6 contentFragment6 = ContentFragment6.newInstance(this.res);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, contentFragment6).commit();
        return contentFragment6;
    }

    private ScreenShotable replaceFragment7(ScreenShotable screenShotable, int topPosition) {

        this.res=R.drawable.call_info_1;
        View view = findViewById(R.id.content_frame);
        int finalRadius = Math.max(view.getWidth(), view.getHeight());
        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, topPosition, 0, finalRadius);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);

        findViewById(R.id.content_overlay).setBackgroundDrawable(new BitmapDrawable(getResources(), screenShotable.getBitmap()));
        animator.start();
        ContentFragment7 contentFragment7 = ContentFragment7.newInstance(this.res);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, contentFragment7).commit();
        return contentFragment7;
    }


    @Override
    public ScreenShotable onSwitch(Resourceble slideMenuItem, ScreenShotable screenShotable, int position) {
        switch (slideMenuItem.getName()) {
            case ContentFragment.CLOSE:
                return screenShotable;
            case ContentFragment.HOME:
                return replaceFragment(screenShotable, position);
            case ContentFragment.TRANSPORTATION:
                return replaceFragment2(screenShotable, position);
            case ContentFragment.INSTITUTE:
                return replaceFragment3(screenShotable, position);
            case ContentFragment.CULTURE:
                return replaceFragment4(screenShotable, position);
            case ContentFragment.BANK:
                return replaceFragment5(screenShotable, position);
            case ContentFragment.LIVING:
                return replaceFragment6(screenShotable, position);
            case ContentFragment.CALL:
                return replaceFragment7(screenShotable, position);
            default:
                return replaceFragment(screenShotable, position);
        }
    }

    @Override
    public void disableHomeButton() {
        getSupportActionBar().setHomeButtonEnabled(false);

    }

    @Override
    public void enableHomeButton() {
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerLayout.closeDrawers();

    }

    @Override
    public void addViewToContainer(View view) {
        linearLayout.addView(view);
    }

    //교통정보
    public void findroad(View view) {

    }

    public void transport(View view) {
        Intent intent= new Intent(this,subway.class);
        startActivity(intent);
    }

    //기관정보
    public void koreaninstitute(View view) {
        Toast.makeText(getApplicationContext(),"한국어교육기관",Toast.LENGTH_SHORT).show();
    }

    public void mainstitute(View view) {
        Toast.makeText(getApplicationContext(),"주요정보기관",Toast.LENGTH_SHORT).show();
    }

    //문화관광
    public void culture(View view) {
        Toast.makeText(getApplicationContext(),"문화",Toast.LENGTH_SHORT).show();
    }

    public void visit(View view) {
        Intent intent = new Intent(MainActivity.this,visit.class);
        startActivity(intent);
    }

    //보험정보
    public void insurance(View view) {
        //Toast.makeText(getApplicationContext(),"보험",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, insurance.class);
        startActivity(intent);
    }

    public void hospital(View view) {
        Intent intent = new Intent(MainActivity.this,nearhospital.class);
        startActivity(intent);
    }

    //생활정보
    public void life(View view) {
        //Toast.makeText(getApplicationContext(),"생활정보",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this,living4.class);
        startActivity(intent);
    }

    public void nation(View view) {
        //Toast.makeText(getApplicationContext(),"국적취득",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, nation.class);
        startActivity(intent);
    }

    //전화정보
    public void foreign(View view) {
        Toast.makeText(getApplicationContext(),"대사관",Toast.LENGTH_SHORT).show();
    }

    public void urgent(View view) {
        Intent intent = new Intent(MainActivity.this,number.class);
        startActivity(intent);
    }
}
