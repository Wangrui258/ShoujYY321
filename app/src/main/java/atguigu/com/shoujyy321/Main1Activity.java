package atguigu.com.shoujyy321;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import java.util.ArrayList;

import atguigu.com.shoujyy321.base.BaseFragment;
import atguigu.com.shoujyy321.fragment.LocalAudioFragment;
import atguigu.com.shoujyy321.fragment.LocalVideoFragment;
import atguigu.com.shoujyy321.fragment.NetAudioFragment;
import atguigu.com.shoujyy321.fragment.NutVideoFragment;
import atguigu.com.shoujyy321.fragment.RecyclerviewFragment;

public class Main1Activity extends AppCompatActivity {
    private RadioGroup fg_main;
    private ArrayList<BaseFragment> fragemnts;
    private int position;
    //缓冲的
    private Fragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        fg_main = (RadioGroup) findViewById(R.id.fg_main);
        ininFragment();
        initListener();
    }
    private void initListener() {
        fg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_local_video:
                        position = 0;
                        break;
                    case R.id.rb_local_audio:
                        position = 1;
                        break;
                    case R.id.rb_net_audio:
                        position = 2;
                        break;
                    case R.id.rb_net_video:
                        position = 3;
                        break;
                    case R.id.rb_Recyclerview:
                        position = 4;
                        break;
                }
                //当前的
                Fragment currentFragment = fragemnts.get(position);
                switchFragment(currentFragment);
            }
        });
        fg_main.check(R.id.rb_local_video);
    }
    private void switchFragment(Fragment currentFragment) {

        if(tempFragment != currentFragment) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if(currentFragment != null) {
                if(!currentFragment.isAdded()) {
                    if(tempFragment != null) {
                        ft.hide(tempFragment);
                    }
                    ft.add(R.id.fl_mainc_content,currentFragment);

                } else {
                    if(tempFragment != null) {
                        ft.hide(tempFragment);
                    }
                    ft.show(currentFragment);
                }
                ft.commit();
            }
            tempFragment = currentFragment;
        }
    }
    private void ininFragment() {
        fragemnts = new ArrayList<>();
        fragemnts.add(new LocalVideoFragment());
        fragemnts.add(new LocalAudioFragment());
        fragemnts.add(new NetAudioFragment());
        fragemnts.add(new NutVideoFragment());
        fragemnts.add(new RecyclerviewFragment());
    }
}
