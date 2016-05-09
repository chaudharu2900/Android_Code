 package activity;

 import android.os.Bundle;
 import android.support.design.widget.TabLayout;
 import android.support.v4.app.Fragment;
 import android.support.v4.app.FragmentManager;
 import android.support.v4.app.FragmentPagerAdapter;
 import android.support.v4.view.ViewPager;
 import android.support.v7.app.AppCompatActivity;
 import android.support.v7.widget.Toolbar;
 import android.widget.Toast;

 import org.json.JSONArray;
 import org.json.JSONObject;

 import java.util.ArrayList;
 import java.util.List;


 import fragments.Conversion;
 import fragments.Enquiry;
 import fragments.Performancefragment;
 import fragments.PipeLinefragment;
 import fragments.VisitHistory;
 import home.infieldapp.org.com.infield.R;

 import utils.Utils;


 public class ScrollableTabsActivity extends AppCompatActivity   {

     private Toolbar toolbar;
     private TabLayout tabLayout;
     private ViewPager viewPager;



     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_simple_tabs);


         toolbar = (Toolbar) findViewById(R.id.toolbar);
         setSupportActionBar(toolbar);
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);

         viewPager = (ViewPager) findViewById(R.id.viewpager);
         viewPager.setOffscreenPageLimit(1);
         setupViewPager(viewPager);

         tabLayout = (TabLayout) findViewById(R.id.tabs);
         tabLayout.setupWithViewPager(viewPager);
     }

     private void setupViewPager(ViewPager viewPager) {
         ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
         adapter.addFrag(new Performancefragment(), "Performance");
         adapter.addFrag(new Enquiry(), "Enquiry");
         adapter.addFrag(new PipeLinefragment(), "PipeLine");
         adapter.addFrag(new Conversion(), "Conversion");
         adapter.addFrag(new Conversion(), "Visit History");

         viewPager.setOffscreenPageLimit(1);
         viewPager.setAdapter(adapter);
     }

     class ViewPagerAdapter extends FragmentPagerAdapter {
         private final List<Fragment> mFragmentList = new ArrayList<>();
         private final List<String> mFragmentTitleList = new ArrayList<>();

         public ViewPagerAdapter(FragmentManager manager) {
             super(manager);
         }


         @Override
         public Fragment getItem(int position) {
             // return mFragmentList.get(position);
             switch (position) {
                 case 0:

                     Performancefragment tab1 = new Performancefragment();
                     return tab1;
                 case 1:
                     Enquiry tab3 = new Enquiry();
                     return tab3;

                 case 2:
                     PipeLinefragment tab2 = new PipeLinefragment();
                     return tab2;

                 case 3:
                     Conversion tab4 = new Conversion();
                     return tab4;
                 case 4:
                     VisitHistory tab5 = new VisitHistory();
                     return tab5;
                 default:
                     return null;
             }
         }

         @Override
         public int getCount() {
             return mFragmentList.size();
         }

         public void addFrag(Fragment fragment, String title) {
             mFragmentList.add(fragment);
             mFragmentTitleList.add(title);
         }

         @Override
         public CharSequence getPageTitle(int position) {

             return mFragmentTitleList.get(position);
         }
     }


}
