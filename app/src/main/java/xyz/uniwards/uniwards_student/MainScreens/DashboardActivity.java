package xyz.uniwards.uniwards_student.MainScreens;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
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
        private Integer[] tabIcons = {R.drawable.dashboard_icon, R.drawable.coupon_icon, R.drawable.reward_icon, R.drawable.profile_icon};

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

                int tabCount = MyTabs.getTabCount();
                for(int x =0; x < tabCount; x++) {
                    AddTabIcons(x, tabIcons[x]);
                }

            }
            catch (Exception e) { Log.wtf("FUCK<", "THIS"); }

        }

        private void AddTabIcons(int tab, Integer image) {
            TextView tabIcon = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
            tabIcon.setCompoundDrawablesWithIntrinsicBounds(0, image, 0, 0);

            MyTabs.getTabAt(tab).setCustomView(tabIcon);
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