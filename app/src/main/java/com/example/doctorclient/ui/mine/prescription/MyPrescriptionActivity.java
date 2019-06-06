package com.example.doctorclient.ui.mine.prescription;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.doctorclient.R;
import com.example.doctorclient.actions.BaseAction;
import com.example.doctorclient.ui.mine.inquiry.MyInquiryFragment;
import com.example.doctorclient.util.base.UserBaseActivity;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.base.MyFragmentPagerAdapter;
import com.lgh.huanglib.util.cusview.CustomViewPager;
import com.lgh.huanglib.util.data.ResUtil;
import com.qmuiteam.qmui.widget.QMUITabSegment;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
* 我的处方
* @author lgh
* created at 2019/5/18 0018 10:17
*/
public class MyPrescriptionActivity extends UserBaseActivity {


    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_title_tv)
    TextView titleTv;

    @BindView(R.id.tabSegment)
    QMUITabSegment tabSegment;
    @BindView(R.id.viewpager)
    CustomViewPager viewPager;

    List<String> list = new ArrayList<>();
    ArrayList<Fragment> fragments;
    MyFragmentPagerAdapter fragmentPagerAdapter;

    public static int Position = 0;


    @Override
    public int intiLayout() {
        return R.layout.activity_my_prescription;
    }

    @Override
    protected BaseAction initAction() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<>(this));
        binding();
    }

    @Override
    protected void initTitlebar() {
        super.initTitlebar();
        mImmersionBar
                .statusBarView(R.id.top_view)
                .keyboardEnable(true)
                .statusBarDarkFont(true, 0.2f)
                .addTag("MyPrescriptionActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        titleTv.setText(ResUtil.getString(R.string.prescription_tip_title));
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        mActicity = this;
        initTabAndPager();
        tabSegment.addOnTabSelectedListener(new QMUITabSegment.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int index) {
                Position = index;
            }

            @Override
            public void onTabUnselected(int index) {

            }

            @Override
            public void onTabReselected(int index) {

            }

            @Override
            public void onDoubleTap(int index) {

            }
        });
    }

    //todo 初始化 tab和pager
    private void initTabAndPager() {
        list = Arrays.asList(getResources().getStringArray(R.array.prescription_list));
        tabSegment.setDefaultNormalColor(ResUtil.getColor(R.color.color_9));
        tabSegment.setDefaultSelectedColor(ResUtil.getColor(R.color.color_38a234));
        tabSegment.setHasIndicator(true);
        tabSegment.setIndicatorPosition(false);
        tabSegment.setIndicatorWidthAdjustContent(true);

        fragments = new ArrayList<Fragment>();

        for (int i = 0; i < list.size(); i++) {
            fragments.add(MyPrescriptionFragment.newInstance(i));

            tabSegment.addTab(new QMUITabSegment.Tab(list.get(i)));
        }
        fragmentPagerAdapter = new MyFragmentPagerAdapter(
                getSupportFragmentManager(), fragments);

        viewPager.setPagingEnabled(false);
        viewPager.setAdapter(fragmentPagerAdapter);
        viewPager.setCurrentItem(Position, false);
        viewPager.setOffscreenPageLimit(list.size());

        tabSegment.setMode(QMUITabSegment.MODE_FIXED);
        tabSegment.setupWithViewPager(viewPager, false);
    }
}
