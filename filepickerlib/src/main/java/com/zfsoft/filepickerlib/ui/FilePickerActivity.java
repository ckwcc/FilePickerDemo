package com.zfsoft.filepickerlib.ui;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.zfsoft.filepickerlib.R;
import com.zfsoft.filepickerlib.model.ParamEntity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * Created by ckw
 * on 2021/10/15.
 */
public class FilePickerActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    private Toolbar mToolbar;
    List<String> titles = new ArrayList<>();
    private List<Fragment> mFragments;
    private ParamEntity mParamEntity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mParamEntity = (ParamEntity) getIntent().getExtras().getSerializable("param");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_picker);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initToolbar();
        tabLayout = findViewById(R.id.tl_tabs);
        viewPager = findViewById(R.id.vp_content);
        initFragments();

        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                super.destroyItem(container, position, object);
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {

                return titles.get(position);
            }
        });
        tabLayout.setupWithViewPager(viewPager);
    }

    /**
     * 更新Toolbar展示
     */
    private void initToolbar() {
        getSupportActionBar().setTitle("附件上传");
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private List<Fragment> initFragments(){
        titles.add("微信");
        titles.add("QQ");
        titles.add("钉钉");
        titles.add("全部");
        mFragments = new ArrayList<>(4);

        mFragments.add( FileTypeFragment.newInstance(1,mParamEntity));
        mFragments.add( FileTypeFragment.newInstance(2,mParamEntity));
        mFragments.add( FileTypeFragment.newInstance(3,mParamEntity));
        mFragments.add( FileTypeFragment.newInstance(4,mParamEntity));

        return mFragments;
    }

}
