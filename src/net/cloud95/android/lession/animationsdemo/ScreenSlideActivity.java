package net.cloud95.android.lession.animationsdemo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

public class ScreenSlideActivity extends FragmentActivity {
	
	private ViewPager mPager;//Layout是viewPager, fragment是放在viewPager裡面
	private PagerAdapter mPagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_slide);

		// 創建 ViewPager 與 PagerAdapter
		mPager = (ViewPager) findViewById(R.id.pager);
		mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
		// 連接 ViewPager 與 PagerAdapter
		mPager.setAdapter(mPagerAdapter);
		mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				// 重刷 Menu 選單
				invalidateOptionsMenu();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		// 載入 menu 描述檔
		getMenuInflater().inflate(R.menu.activity_screen_slide, menu);
		// 假如不是第一頁，上一頁 按鈕可按
		menu.findItem(R.id.action_previous).setEnabled(
				mPager.getCurrentItem() > 0);
		// 決定 下一頁 按鈕顯示的文字
		MenuItem item = menu
				.add(Menu.NONE,
						R.id.action_next,
						Menu.NONE,
						(mPager.getCurrentItem() == mPagerAdapter.getCount() - 1) ? R.string.action_finish
								: R.string.action_next);

		item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM
				| MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_previous:
			// 切換到上一頁
			mPager.setCurrentItem(mPager.getCurrentItem() - 1);
			return true;// 跳出函數
		case R.id.action_next:
			// 切換到下一頁
			mPager.setCurrentItem(mPager.getCurrentItem() + 1);
			return true;// 跳出函數
		}
		return super.onOptionsItemSelected(item);//交由父類別的方法來處理
	}

	private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
		public ScreenSlidePagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position){
			// 取得第 position 畫面em(int position) {
			return ScreenSlidePageFragment.create(position);
		}

		@Override
		public int getCount() {
			// 取得畫面數量
			return ScreenSlidePageFragment.contents.length;
		}
	}
}
