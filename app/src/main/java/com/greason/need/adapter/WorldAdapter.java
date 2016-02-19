package com.greason.need.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.greason.need.R;
import com.greason.need.model.NeedModel;
import com.greason.need.model.WorldModel;
import com.greason.need.utils.BitmapFromAssets;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by greason on 2015/7/29.
 */
public class WorldAdapter extends BaseAdapter {

    public Context context;
    public List<WorldModel> datas ;

    protected LayoutInflater inflater ;

    private String path = "world";
    private String perFix_backgroudImg = "shijian";

    private BackgroundImgListener listener ;

    public WorldAdapter(Context context, List<WorldModel> datas){
        super();
        this.context = context ;
        this.datas = datas;
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
    public WorldModel getItem(int i) {
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
            convertView = inflater.inflate(R.layout.viewpager_world_list_item,null);
            holder.item_title = (TextView)convertView.findViewById(R.id.world_title);
            holder.item_captionOne = (TextView)convertView.findViewById(R.id.world_captionOne);
            holder.item_captionTwo = (TextView)convertView.findViewById(R.id.world_captionTwo);
            holder.item_captionThree = (TextView)convertView.findViewById(R.id.world_captionThree);
            holder.item_captionFour = (TextView)convertView.findViewById(R.id.world_captionFour);

            holder.item_captionOne_bg = (ImageView)convertView.findViewById(R.id.world_captionOne_bg);
            holder.item_captionTwo_bg = (ImageView)convertView.findViewById(R.id.world_captionTwo_bg);
            holder.item_captionThree_bg = (ImageView)convertView.findViewById(R.id.world_captionThree_bg);
            holder.item_captionFour_bg = (ImageView)convertView.findViewById(R.id.world_captionFour_bg);

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

        holder.item_captionOne.setText(datas.get(position).getCaptionOne());
        holder.item_captionTwo.setText(datas.get(position).getCaptionTwo());
        holder.item_captionThree.setText(datas.get(position).getCaptionThree());
        holder.item_captionFour.setText(datas.get(position).getCaptionFour());

        holder.item_captionOne_bg.setImageBitmap(BitmapFromAssets.getBitmap(context , path + File.separator + perFix_backgroudImg +
                (position + 1) + "_" + 1));
        holder.item_captionTwo_bg.setImageBitmap(BitmapFromAssets.getBitmap(context , path + File.separator + perFix_backgroudImg +
                (position + 1) + "_" + 2));
        holder.item_captionThree_bg.setImageBitmap(BitmapFromAssets.getBitmap(context , path + File.separator + perFix_backgroudImg +
                (position + 1) + "_" + 3));
        holder.item_captionFour_bg.setImageBitmap(BitmapFromAssets.getBitmap(context , path + File.separator + perFix_backgroudImg +
                (position + 1) + "_" + 4));
        return  convertView;
    }

    public class ViewHolder{
        TextView item_title;
        TextView item_captionOne;
        TextView item_captionTwo;
        TextView item_captionThree;
        TextView item_captionFour;

        ImageView item_captionOne_bg;
        ImageView item_captionTwo_bg;
        ImageView item_captionThree_bg;
        ImageView item_captionFour_bg;
    }

    /**
     * 背景图片监听事件
     */
    public interface BackgroundImgListener{
        public void click(int position);
    }
}
