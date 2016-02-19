package com.greason.need;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.greason.need.adapter.MineAdapter;
import com.greason.need.adapter.MyViewPagerAdapter;
import com.greason.need.adapter.NeedAdapter;
import com.greason.need.adapter.WorkerAdapter;
import com.greason.need.adapter.WorldAdapter;
import com.greason.need.model.NeedModel;
import com.greason.need.model.WorkerModel;
import com.greason.need.model.WorldModel;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements View.OnClickListener {

    private ViewPager viewPager , workerViewpager , mineViewpager;
    private ImageButton viewpager_need , viewpager_world , viewpager_worker ,viewpager_mine ;
    private List<View> pagerList ;

    private TextView main_title ;

    private ListView viewpager_need_list ,viewpager_world_list ;
    private List<NeedModel> need_datas ;
    private List<WorldModel> world_datas ;
    private List<WorkerModel> worker_datas ;

    private TextView worker_viewpager_meishi , worker_viewpager_shenghuo , worker_viewpager_lvxing ,worker_viewpager_meiren;

    private TextView mine_viewpager_need , mine_viewpager_have ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViewPager(getLayoutInflater());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void initTextView() {
        /**
         * 初始化Tab
         */
        viewpager_need = (ImageButton) findViewById(R.id.viewpager_need);
        viewpager_world = (ImageButton) findViewById(R.id.viewpager_world);
        viewpager_worker = (ImageButton) findViewById(R.id.viewpager_worker);
        viewpager_mine = (ImageButton) findViewById(R.id.viewpager_mine);

        viewpager_need.setOnClickListener(new MyOnClickListener(0));
        viewpager_world.setOnClickListener(new MyOnClickListener(1));
        viewpager_worker.setOnClickListener(new MyOnClickListener(2));
        viewpager_mine.setOnClickListener(new MyOnClickListener(3));

        main_title = (TextView)findViewById(R.id.main_title);

    }

    /**
     * 初始化 viewPager
     * @param inflater
     */
    private void initViewPager( final LayoutInflater inflater) {

        initTextView();

        viewPager = (ViewPager) findViewById(R.id.main_viewpager);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setPageMargin(getResources().getDimensionPixelOffset(R.dimen.viewpager_between));

        pagerList = new ArrayList<View>();
        pagerList.add(inflater.inflate(R.layout.viewpager_need, null));
        pagerList.add(inflater.inflate(R.layout.viewpager_world, null));
        pagerList.add(inflater.inflate(R.layout.viewpager_worker, null));
        pagerList.add(inflater.inflate(R.layout.viewpager_mine, null));

        viewPager.setAdapter(new MyViewPagerAdapter(pagerList));
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());

        need_datas = createNeeds();

        NeedAdapter adapter = new NeedAdapter(this, need_datas);
        adapter.setOnBackgroundImgListener(new NeedAdapter.BackgroundImgListener() {
            @Override
            public void click(int position) {
                Intent intent = new Intent(MainActivity.this, NeedDetailActivity.class);
                intent.putExtra("title", need_datas.get(position).getTitle());
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
        viewpager_need_list = (ListView)pagerList.get(0).findViewById(R.id.viewpager_need_list);
        viewpager_need_list.setAdapter(adapter);
        viewpager_need_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int position, long id) {

            }
        });


        world_datas = createWorld();
        viewpager_world_list = (ListView)pagerList.get(1).findViewById(R.id.viewpager_world_list);
        viewpager_world_list.setAdapter(new WorldAdapter(this, world_datas));



        //行家
        workerViewpager = (ViewPager)pagerList.get(2).findViewById(R.id.worker_viewpager);
        workerViewpager.setOffscreenPageLimit(2);
        workerViewpager.setPageMargin(getResources().getDimensionPixelOffset(R.dimen.viewpager_between));
        List<View> pagerList2 = new ArrayList<View>();
        pagerList2.add(inflater.inflate(R.layout.viewpager_worker_gv, null));
        pagerList2.add(inflater.inflate(R.layout.viewpager_worker_gv, null));
        pagerList2.add(inflater.inflate(R.layout.viewpager_worker_gv, null));
        pagerList2.add(inflater.inflate(R.layout.viewpager_worker_gv, null));
        workerViewpager.setAdapter(new MyViewPagerAdapter(pagerList2));
        workerViewpager.setOnPageChangeListener(new MyWorkerOnPageChangeListener());

        worker_viewpager_meishi = (TextView)pagerList.get(2).findViewById(R.id.worker_viewpager_meishi);
        worker_viewpager_shenghuo = (TextView)pagerList.get(2).findViewById(R.id.worker_viewpager_shenghuo);
        worker_viewpager_lvxing = (TextView)pagerList.get(2).findViewById(R.id.worker_viewpager_lvxing);
        worker_viewpager_meiren = (TextView)pagerList.get(2).findViewById(R.id.worker_viewpager_meiren);
        worker_viewpager_meishi.setOnClickListener(new MyWorkerOnClickListener(0));
        worker_viewpager_shenghuo.setOnClickListener(new MyWorkerOnClickListener(1));
        worker_viewpager_lvxing.setOnClickListener(new MyWorkerOnClickListener(2));
        worker_viewpager_meiren.setOnClickListener(new MyWorkerOnClickListener(3));
        workerViewpager.setCurrentItem(0);

        GridView gv_meishi = (GridView)pagerList2.get(0).findViewById(R.id.gv_meishi);
        worker_datas = createWorker();
        gv_meishi.setAdapter(new WorkerAdapter(this,  "meishi" ,worker_datas));

        GridView gv_shenghuo = (GridView)pagerList2.get(1).findViewById(R.id.gv_meishi);
        worker_datas = createWorker();
        gv_shenghuo.setAdapter(new WorkerAdapter(this , "shenghuo" , worker_datas));

        GridView gv_lvxing = (GridView)pagerList2.get(2).findViewById(R.id.gv_meishi);
        worker_datas = createWorker();
        gv_lvxing.setAdapter(new WorkerAdapter(this , "lvxing" , worker_datas));

        GridView gv_meiren = (GridView)pagerList2.get(3).findViewById(R.id.gv_meishi);
        worker_datas = createWorker();
        gv_meiren.setAdapter(new WorkerAdapter(this , "meiren" , worker_datas));

        //我的
        mineViewpager = (ViewPager)pagerList.get(3).findViewById(R.id.mine_viewpager);
        mineViewpager.setOffscreenPageLimit(1);
        mineViewpager.setPageMargin(getResources().getDimensionPixelOffset(R.dimen.viewpager_between));
        List<View> pagerList3 = new ArrayList<View>();
        pagerList3.add(inflater.inflate(R.layout.viewpager_mine_gv, null));
        pagerList3.add(inflater.inflate(R.layout.viewpager_mine_gv, null));
        mineViewpager.setAdapter(new MyViewPagerAdapter(pagerList3));
        mineViewpager.setOnPageChangeListener(new MyMineOnPageChangeListener());

        mine_viewpager_need = (TextView)pagerList.get(3).findViewById(R.id.mine_viewpager_need);
        mine_viewpager_have = (TextView)pagerList.get(3).findViewById(R.id.mine_viewpager_have);
        mine_viewpager_need.setOnClickListener(new MyMineOnClickListener(0));
        mine_viewpager_have.setOnClickListener(new MyMineOnClickListener(1));
        mineViewpager.setCurrentItem(0);

        GridView gv_need = (GridView)pagerList3.get(0).findViewById(R.id.gv_need);
        gv_need.setAdapter(new MineAdapter(this, "need" ,need_datas));

        GridView gv_have = (GridView)pagerList3.get(1).findViewById(R.id.gv_need);
        gv_have.setAdapter(new MineAdapter(this , "have" ,need_datas));


        viewPager.setCurrentItem(0);
        main_title.setText(getResources().getString(R.string.viewpager_need));
        viewpager_need.setImageDrawable(getResources().getDrawable(R.mipmap.zhu1r));
    }

    /**
     *  Tab 栏菜单监听器
     */
    private class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        public void onClick(View v) {
            viewPager.setCurrentItem(index);
            checkViewpagerImg();
        }
    }

    /**
     * Tab监听器
     */
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        public MyOnPageChangeListener() {
            //初始加载 viewPagerOne
        }

        public void onPageScrollStateChanged(int arg0) {

        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        public void onPageSelected(int item) {
            checkViewpagerImg();

        }
    }

    /**
     *  Tab 栏菜单监听器
     */
    private class MyWorkerOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyWorkerOnClickListener(int i) {
            index = i;
        }

        public void onClick(View v) {
            workerViewpager.setCurrentItem(index);
            checkWorkerViewpagerColor();
        }
    }

    /**
     * Tab监听器
     */
    public class MyWorkerOnPageChangeListener implements ViewPager.OnPageChangeListener {

        public MyWorkerOnPageChangeListener() {
            //初始加载 viewPagerOne
        }

        public void onPageScrollStateChanged(int arg0) {

        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        public void onPageSelected(int item) {
            checkWorkerViewpagerColor();

        }
    }

    /**
     *  Tab 栏菜单监听器
     */
    private class MyMineOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyMineOnClickListener(int i) {
            index = i;
        }

        public void onClick(View v) {
            mineViewpager.setCurrentItem(index);
            checkMineViewpagerColor();
        }
    }

    /**
     * Tab监听器
     */
    public class MyMineOnPageChangeListener implements ViewPager.OnPageChangeListener {

        public MyMineOnPageChangeListener() {
            //初始加载 viewPagerOne
        }

        public void onPageScrollStateChanged(int arg0) {

        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        public void onPageSelected(int item) {
            checkMineViewpagerColor();

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            default:
                break;
        }
        return ;
    }

    /**
     * 伪造数据
     * @return
     */
    private List<NeedModel> createNeeds(){
        List<NeedModel> models = new ArrayList<NeedModel>();

        String names[] = getResources().getStringArray(R.array.need_names);
        String titles[] = getResources().getStringArray(R.array.need_titles);
        String captions[] = getResources().getStringArray(R.array.need_captions);
        for (int i = 0 ; i <  names.length ; i++){
            NeedModel model = new NeedModel();
            model.setName(names[i]);
            model.setTitle(titles[i]);
            model.setCaption(captions[i]);

            models.add(model);
        }

        return models;
    }

    private List<WorldModel> createWorld(){
        List<WorldModel> models = new ArrayList<WorldModel>();

        String titles[] = getResources().getStringArray(R.array.world_titles);
        String captions[] = getResources().getStringArray(R.array.world_captions);
        for (int i = 0 ; i <  titles.length ; i++){
            WorldModel model = new WorldModel();
            model.setTitle(titles[i]);
            model.setCaptionOne(captions[4 * i]);
            model.setCaptionTwo(captions[4 * i + 1]);
            model.setCaptionThree(captions[4 * i + 2]);
            model.setCaptionFour(captions[4 * i + 3]);

            models.add(model);
        }

        return models;
    }

    private List<WorkerModel> createWorker(){
        List<WorkerModel> models = new ArrayList<WorkerModel>();

        String names[] = getResources().getStringArray(R.array.worker_names);
        String captions[] = getResources().getStringArray(R.array.need_captions);
        for (int i = 0 ; i <  names.length ; i++){
            WorkerModel model = new WorkerModel();
            model.setName(names[i]);
            model.setCaption(captions[i]);

            models.add(model);
        }

        return models;
    }

    /**
     * 显示资源切换
     */
    private void checkViewpagerImg(){

        viewpager_need.setImageDrawable(getResources().getDrawable(R.mipmap.zhu1));
        viewpager_world.setImageDrawable(getResources().getDrawable(R.mipmap.zhu2));
        viewpager_worker.setImageDrawable(getResources().getDrawable(R.mipmap.zhu3));
        viewpager_mine.setImageDrawable(getResources().getDrawable(R.mipmap.zhu4));

        if (viewPager.getCurrentItem() == 0) {
            main_title.setText( getString(R.string.viewpager_need) );
            viewpager_need.setImageDrawable(getResources().getDrawable(R.mipmap.zhu1r));

        } else if (viewPager.getCurrentItem() == 1) {
            main_title.setText( getString(R.string.viewpager_world) );
            viewpager_world.setImageDrawable(getResources().getDrawable(R.mipmap.zhu2r));

        } else if (viewPager.getCurrentItem() == 2){
            main_title.setText(getString(R.string.viewpager_worker));
            viewpager_worker.setImageDrawable(getResources().getDrawable(R.mipmap.zhu3r));

        } else {
            main_title.setText( getString(R.string.viewpager_mine) );
            viewpager_mine.setImageDrawable(getResources().getDrawable(R.mipmap.zhu4r));
        }
    }

    /**
     * 改变显示字体颜色
     */
    private void checkWorkerViewpagerColor(){

        worker_viewpager_meishi.setTextColor(getResources().getColor(R.color.black));
        worker_viewpager_shenghuo.setTextColor(getResources().getColor(R.color.black));
        worker_viewpager_lvxing.setTextColor(getResources().getColor(R.color.black));
        worker_viewpager_meiren.setTextColor(getResources().getColor(R.color.black));

        if (workerViewpager.getCurrentItem() == 0) {
            worker_viewpager_meishi.setTextColor(getResources().getColor(R.color.red));
        } else if (workerViewpager.getCurrentItem() == 1) {
            worker_viewpager_shenghuo.setTextColor(getResources().getColor(R.color.red));
        } else if (workerViewpager.getCurrentItem() == 2){
            worker_viewpager_lvxing.setTextColor(getResources().getColor(R.color.red));
        } else {
            worker_viewpager_meiren.setTextColor(getResources().getColor(R.color.red));
        }
    }

    /**
     * 改变显示字体颜色
     */
    private void checkMineViewpagerColor(){

        mine_viewpager_need.setTextColor(getResources().getColor(R.color.black));
        mine_viewpager_have.setTextColor(getResources().getColor(R.color.black));

        if (mineViewpager.getCurrentItem() == 0) {
            mine_viewpager_need.setTextColor(getResources().getColor(R.color.red));
        } else {
            mine_viewpager_have.setTextColor(getResources().getColor(R.color.red));
        }
    }

}

