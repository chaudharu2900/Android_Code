package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import home.example.com.rss.PoliticsFragment;
import home.example.com.rss.SpiritualFragment;
import home.example.com.rss.HealthFragment;
import home.example.com.rss.ReligionFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			// Top Rated fragment activity
			return new ReligionFragment();
		case 1:
			// Games fragment activity
			return new SpiritualFragment();
		case 2:
			// Movies fragment activity
			return new HealthFragment();

		case 3:
		// Movies fragment activity
		return new PoliticsFragment();
	}
		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 4;
	}

}
