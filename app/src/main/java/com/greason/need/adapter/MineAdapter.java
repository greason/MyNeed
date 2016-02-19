package com.greason.need.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.greason.need.R;
import com.greason.need.model.NeedModel;
import com.greason.need.utils.BitmapFromAssets;

import java.io.File;
import java.util.List;

/**
 * Created by greason on 2015/7/29.
 */
public class MineAdapter extends BaseAdapter {

    public Context context;
    public List<NeedModel> datas ;

    protected LayoutInflater inflater ;

    private String needPath = "mine";
    private String needPerFix_backgroudImg = "me";
    private String secondFolder ;

    private BackgroundImgListener listener ;

    public MineAdapter(Context context, String secondFolder ,List<NeedModel> datas){
        super();
        this.context = context ;
        this.datas = datas;
        this.secondFolder = secondFolder;
        inflater = LayoutInflater.from(context);
    }

    public void setOnBackgroundImgListener(BackgroundImgListener listener){
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public NeedModel getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.viewpager_mine_gv_item,null);
            holder.item_backgroudImg = (ImageView)convertView.findViewById(R.id.gv_img);
            holder.item_title = (TextView)convertView.findViewById(R.id.gv_title);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        convertView = getConvertView(holder ,position, convertView, viewGroup);

        return convertView;
    }

    /**
     * 外部从写接口
     * @param position
     * @param convertView
     * @param viewGroup
     */
    public View getConvertView(ViewHolder holder ,int position, View convertView, ViewGroup viewGroup){
        holder.item_title.setText(datas.get(position).getTitle());
        holder.item_backgroudImg.setImageBitmap(BitmapFromAssets.getBitmap(context, needPath + File.separator +
                secondFolder + File.separator + needPerFix_backgroudImg + (position + 1)));
        return  convertView;
    }

    public class ViewHolder{
        ImageView item_backgroudImg;
        TextView item_title;
    }

    /**
     * 背景图片监听事件
     */
    public interface BackgroundImgListener{
        public void click(int position);
    }
}
