package xyz.uniwards.uniwards_student.MainScreens;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import xyz.uniwards.uniwards_student.MainScreens.Fragments.CouponFragment;
import xyz.uniwards.uniwards_student.MainScreens.Fragments.DashboardFragment;
import xyz.uniwards.uniwards_student.MainScreens.Fragments.ProfileFragment;
import xyz.uniwards.uniwards_student.MainScreens.Fragments.RewardFragment;
import xyz.uniwards.uniwards_student.R;

public class DashboardActivity extends AppCompatActivity {

        TabLayout MyTabs;
        ViewPager MyPage;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_dashboard);
            Log.wtf("FUCK22222", "THIS");
            try {
                MyTabs = (TabLayout) findViewById(R.id.MyTabs);
                MyPage = (ViewPager) findViewById(R.id.MyPage);

                MyTabs.setupWithViewPager(MyPage);
                SetUpViewPager(MyPage);
               // MyTabs.getTabAt(0).setIcon(R.drawable.login_pass);
                TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
                tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.dashboard_icon, 0, 0);
                TextView tab2 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
                tab2.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.login_pass, 0, 0);
                TextView tab3 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
                tab3.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.login_pass, 0, 0);
                TextView tab4 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
                tab4.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.login_pass, 0, 0);
                MyTabs.getTabAt(0).setCustomView(tabOne);
                MyTabs.getTabAt(1).setCustomView(tab2);
                MyTabs.getTabAt(2).setCustomView(tab3);
                MyTabs.getTabAt(3).setCustomView(tab4);
            }
            catch (Exception e) { Log.wtf("FUCK<", "THIS"); }

        }

        public void SetUpViewPager (ViewPager viewpage){
            MyViewPageAdapter Adapter = new MyViewPageAdapter(getSupportFragmentManager());

            Adapter.AddFragmentPage(new DashboardFragment(), "    ");
            Adapter.AddFragmentPage(new CouponFragment(), "Coupons");
            Adapter.AddFragmentPage(new RewardFragment(), "Rewards");
            Adapter.AddFragmentPage(new ProfileFragment(), "Profile");
            //We Need Fragment class now

            viewpage.setAdapter(Adapter);

        }

        public class MyViewPageAdapter extends FragmentPagerAdapter{
            private List<Fragment> MyFragment = new ArrayList<>();
            private List<String> MyPageTittle = new ArrayList<>();

            public MyViewPageAdapter(FragmentManager manager){
                super(manager);
            }

            public void AddFragmentPage(Fragment Frag, String Title){
                MyFragment.add(Frag);
                MyPageTittle.add(Title);
            }

            @Override
            public Fragment getItem(int position) {
                return MyFragment.get(position);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return MyPageTittle.get(position);
            }

            @Override
            public int getCount() {
                return 4;
            }
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_test, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }
    }