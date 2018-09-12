package com.bw.movie.ui.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.MessageEvent;
import com.bw.movie.ui.fragment.CinemaFragment;
import com.bw.movie.ui.fragment.MemberFragment;
import com.bw.movie.ui.fragment.MovieFragment;
import com.bw.movie.ui.fragment.SettingFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

import static com.ashokvarma.bottomnavigation.BottomNavigationBar.BACKGROUND_STYLE_STATIC;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.fl_main)
    FrameLayout flMain;
    @BindView(R.id.bnb_main)
    BottomNavigationBar bnbMain;
    private MovieFragment home;
    private CinemaFragment movie;
    private SettingFragment show;
    private MemberFragment mine;
    private FragmentManager supportFragmentManager;
    @Override
    public int bindLayout() {
        EventBus.getDefault().register(this);
        return R.layout.activity_main;
    }

    /**
     * @param message
     */
    //得到值
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void Event(MessageEvent message){
       if(message.getTag() == 0){
           bnbMain.selectTab(0);
       }
    }

    @Override
    public void initData() {


        bnbMain.setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BACKGROUND_STYLE_STATIC)
                .setActiveColor("#3086BE") //选中颜色
                .setInActiveColor("#FF888485") //未选中颜色
                .setBarBackgroundColor("#FFF4F1F2");//导航栏背景色
        bnbMain.addItem(new BottomNavigationItem(R.drawable.dianying, "影片"))
                .addItem(new BottomNavigationItem(R.drawable.yingyuan, "影院"))
                .addItem(new BottomNavigationItem(R.drawable.wode, "会员"))
                .addItem(new BottomNavigationItem(R.drawable.settings, "设置"))
                .initialise();
        supportFragmentManager = getSupportFragmentManager();
        home = new MovieFragment();
        supportFragmentManager.beginTransaction().add(R.id.fl_main, home, null).commit();
        bnbMain.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
                if (home != null) {
                    fragmentTransaction.hide(home);
                }
                if (movie != null) {
                    fragmentTransaction.hide(movie);
                }
                if (show != null) {
                    fragmentTransaction.hide(show);
                }
                if (mine != null) {
                    fragmentTransaction.hide(mine);
                }
                switch (position) {
                    case 0:
                        if (home == null) {
                            home = new MovieFragment();
                            fragmentTransaction.add(R.id.fl_main, home);
                        } else {
                            fragmentTransaction.show(home);
                        }
                        break;
                    case 1:
                        if (movie == null) {
                            movie = new CinemaFragment();
                            fragmentTransaction.add(R.id.fl_main, movie);
                        } else {
                            fragmentTransaction.show(movie);
                        }
                        break;

                    case 2:
                        if (mine == null) {
                            mine = new MemberFragment();
                            fragmentTransaction.add(R.id.fl_main, mine);
                        } else {
                            fragmentTransaction.show(mine);
                        }
                        break;
                    case 3:
                        if (show == null) {
                            show = new SettingFragment();
                            fragmentTransaction.add(R.id.fl_main, show);
                        } else {
                            fragmentTransaction.show(show);
                        }
                        break;
                }
                fragmentTransaction.commit();
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public BasePresenter providePresenter() {
        return null;
    }

}
