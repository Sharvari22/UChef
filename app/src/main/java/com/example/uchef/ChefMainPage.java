package com.example.uchef;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChefMainPage extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private int currentFragment = -1;
    private static final int HOME_FRAGMENT = 0, REQUESTS_FRAGMENT = 1, SCHEDULE_FRAGMENT = 2,
            PAYMENTS_FRAGMENT = 3, MYACCOUNT_FRAGMENT = 4;
    private FrameLayout frameLayout;
    private ImageView actionbarLogo;
    private Window window;
    private AppBarLayout.LayoutParams params;
    private int scrollFlags;
    private CircleImageView profileView;
    private TextView fullname, email;
    private ImageView addProfileIcon;
    ActionBarDrawerToggle toggle;
    private TextView badgeCount;
    NavigationView navigationView;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_main_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        actionbarLogo = findViewById(R.id.action_bar_logo);
        setSupportActionBar(toolbar);

        window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
        scrollFlags = params.getScrollFlags();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_my_home, R.id.nav_requests, R.id.nav_schedule, R.id.nav_payment, R.id.nav_account, R.id.nav_signout)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.getMenu().getItem(0).setChecked(true);

        frameLayout = findViewById(R.id.main_framelayout);

        profileView = navigationView.getHeaderView(0).findViewById(R.id.profile_image);
        fullname = navigationView.getHeaderView(0).findViewById(R.id.fullname);
        email = navigationView.getHeaderView(0).findViewById(R.id.email);
        addProfileIcon = navigationView.getHeaderView(0).findViewById(R.id.add_profile_icon);

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        setFragment(new HomeFragment(), HOME_FRAGMENT);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawer.closeDrawer(GravityCompat.START);
                drawer.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
                    @Override
                    public void onDrawerClosed(View drawerView) {
                        super.onDrawerClosed(drawerView);
                        int id = item.getItemId();
                        if (id == R.id.nav_my_home) {
                            actionbarLogo.setVisibility(View.VISIBLE);
                            invalidateOptionsMenu();
                            setFragment(new HomeFragment(), HOME_FRAGMENT);
                            //navigationView.getMenu().getItem(0).setChecked(true);
                        } else if (id == R.id.nav_requests) {
                            gotoFragment("View Requests", new RequestsFragment(), REQUESTS_FRAGMENT);
                        } else if (id == R.id.nav_schedule) {
                            gotoFragment("My Schedule", new ScheduleFragment(), SCHEDULE_FRAGMENT);
                        } else if (id == R.id.nav_payment) {
                            gotoFragment("Payments", new PaymentsFragment(), PAYMENTS_FRAGMENT);
                        } else if (id == R.id.nav_account) {
                            gotoFragment("My Account", new MyAccountFragment(), MYACCOUNT_FRAGMENT);
                        } else if (id == R.id.nav_signout) {
                            //FirebaseAuth.getInstance().signOut();
                            //DBqueries.clearData();
                            Intent intent = new Intent(ChefMainPage.this, ChefLoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        drawer.removeDrawerListener(this);
                    }
                });
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (currentFragment == HOME_FRAGMENT) {
                currentFragment = -1;
                super.onBackPressed();
            } else {
                actionbarLogo.setVisibility(View.VISIBLE);
                invalidateOptionsMenu();
                setFragment(new HomeFragment(), HOME_FRAGMENT);
                navigationView.getMenu().getItem(0).setChecked(true);

            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (currentFragment == HOME_FRAGMENT) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getMenuInflater().inflate(R.menu.chef_main_page, menu);

            /*MenuItem notificationItem = menu.findItem(R.id.main_notification_icon);
            notificationItem.setActionView(R.layout.badge_layout);
            ImageView badgeeIcon=notificationItem.getActionView().findViewById(R.id.badge_icon);
            badgeeIcon.setImageResource(R.drawable.ic_notification);
            TextView notifyCount=notificationItem.getActionView().findViewById(R.id.badge_count);*/
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.main_search_icon) {
            Intent searchIntent = new Intent(this,SearchActivity.class);
            startActivity(searchIntent);
            return true;
        } else if (id == R.id.main_notification_icon) {
            Intent notificationIntent = new Intent(this,NotificationActivity.class);
            startActivity(notificationIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void gotoFragment(String title, Fragment fragment, int fragmentNo) {
        actionbarLogo.setVisibility(View.GONE);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(title);
        invalidateOptionsMenu();
        setFragment(fragment, fragmentNo);
        //params.setScrollFlags(scrollFlags);

    }

    private void setFragment(Fragment fragment, int fragmentNo) {
        if (fragmentNo != currentFragment) {
            currentFragment = fragmentNo;
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
            fragmentTransaction.replace(frameLayout.getId(), fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onStart() {
        super.onStart();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {
            navigationView.getMenu().getItem(navigationView.getMenu().size() - 1).setEnabled(false);
        }else {
            if(DBqueries.email == null) {
                FirebaseFirestore.getInstance().collection("CHEFS").document(currentUser.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DBqueries.fullname = task.getResult().getString("fullname");
                            DBqueries.email = task.getResult().getString("email");
                            DBqueries.profile = task.getResult().getString("profile");

                            fullname.setText(DBqueries.fullname);
                            email.setText(DBqueries.email);
                            if (DBqueries.profile.equals("")) {
                                addProfileIcon.setVisibility(View.VISIBLE);
                            } else {
                                addProfileIcon.setVisibility(View.INVISIBLE);
                                Glide.with(ChefMainPage.this).load(DBqueries.profile).apply(new RequestOptions().placeholder(R.drawable.cook2)).into(profileView);
                            }
                        } else {
                            String error = task.getException().getMessage();
                            Toast.makeText(ChefMainPage.this, error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            } else {
                fullname.setText(DBqueries.fullname);
                email.setText(DBqueries.email);
                if (DBqueries.profile.equals("")) {
                    profileView.setImageResource(R.drawable.cook2);
                    addProfileIcon.setVisibility(View.VISIBLE);
                } else {
                    addProfileIcon.setVisibility(View.INVISIBLE);
                    Glide.with(ChefMainPage.this).load(DBqueries.profile).apply(new RequestOptions().placeholder(R.drawable.cook2)).into(profileView);
                }

            }
        }
    }
}