package ump.doctorapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import ump.doctorapp.fragment.VoucherList1Fragment;
import ump.doctorapp.fragment.VoucherList2Fragment;


/**
 * Created by Dionysus.Poon on 8/6/2018.
 */

public class VoucherActivity extends BaseActivity {
    //Fragment Page Adapter
    private SectionsPagerAdapter sectionsPagerAdapter;

    //ViewPager
    private ViewPager viewPager;
//sdsada
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorapp_voucher);
        getSupportActionBar().hide();
//
        //Adapter for Fragement
        sectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager
        viewPager = (ViewPager) findViewById(R.id.voucher_main_contents);
        viewPager.setAdapter(sectionsPagerAdapter);

        //Tab in ToolBar
        TabLayout tabLayout = (TabLayout) findViewById(R.id.voucher_tabLayout);

        //Change the page when click the tabs
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

    }


    //Adapter for Fragement
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        //Define the Layout
        @Override
        public Fragment getItem(int position) {
            Fragment frag = null;
            switch (position) {
                case 0:
                    frag = new VoucherList1Fragment();
                    break;
                case 1:
                    frag = new VoucherList2Fragment();
                    break;
            }
            return frag;

        }

        //Define # pages
        @Override
        public int getCount() {
            return 2;
        }
    }

}

