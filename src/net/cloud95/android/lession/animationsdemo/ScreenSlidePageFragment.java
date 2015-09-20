package net.cloud95.android.lession.animationsdemo;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ScreenSlidePageFragment extends Fragment {
	public static final int[] contents={
			R.string.cotent001, R.string.cotent002, R.string.cotent003, R.string.cotent004, R.string.cotent005
	};
	//傳遞參數的參數名
    public static final String ARG_PAGE = "page";
    private int mPageNumber;
    
    //建立一個方法
    public static ScreenSlidePageFragment create(int pageNumber) {
    	ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        //準備傳遞參數
    	Bundle args = new Bundle();
    	args.putInt(ARG_PAGE, pageNumber);
        //將參數加附到 Fragment
    	fragment.setArguments(args);
    	return fragment;
    }

    public ScreenSlidePageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取得傳遞參數
        mPageNumber = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	//準備 Fragment 的畫面
    	ViewGroup rootView =(ViewGroup) inflater.inflate(R.layout.fragment_screen_slide_page, container,false);
        //從 res/value/strings.xml 中取得字串，並傳入參數
    	((TextView)rootView.findViewById(android.R.id.text1)).setText(getString(R.string.title_template_step,mPageNumber+1));
    	((TextView)rootView.findViewById(android.R.id.text2)).setText(getString(contents[mPageNumber]));
    	return rootView;
    }
    public int getPageNumber() {
    	return mPageNumber;
    }
}
