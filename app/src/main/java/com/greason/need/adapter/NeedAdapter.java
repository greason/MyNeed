package com.greason.need.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.greason.need.MainActivity;
import com.greason.need.NeedDetailActivity;
import com.greason.need.R;
import com.greason.need.model.NeedModel;
import com.greason.need.utils.BitmapFromAssets;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by greason on 2015/7/29.
 */
public class NeedAdapter extends BaseAdapter {

    public Context context;
    public List<NeedModel> datas ;

    protected LayoutInflater inflater ;

    private String needPath = "need";
    private String needPerFix_backgroudImg = "good";
    private String needPerFix_personImg = "tou";

    private BackgroundImgListener listener ;

    public NeedAdapter(Context context, List<NeedModel> datas){
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
            convertView = inflater.inflate(R.layout.viewpager_need_list_item,null);
            holder.item_name = (TextView)convertView.findViewById(R.id.viewpager_need_name);
            holder.item_title = (TextView)convertView.findViewById(R.id.viewpager_need_title);
            holder.item_caption = (TextView)convertView.findViewById(R.id.viewpager_need_caption);
            holder.item_backgroudImg = (ImageView)convertView.findViewById(R.id.viewpager_need_backgroudImg);
            holder.item_personImg = (ImageView)convertView.findViewById(R.id.viewpager_need_personImg);

            holder.item_backgroudImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.click(position);
                }
            });
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
        holder.item_name.setText(datas.get(position).getName());
        holder.item_title.setText(datas.get(position).getTitle());
        holder.item_caption.setText(datas.get(position).getCaption());
        holder.item_backgroudImg.setImageBitmap(BitmapFromAssets.getBitmap(context, needPath + File.separator + needPerFix_backgroudImg + (position + 1)));
        holder.item_personImg.setImageBitmap(BitmapFromAssets.getBitmap(context, needPath + File.separator + needPerFix_personImg + (position + 1)));
        return  convertView;
    }

    public class ViewHolder{
        ImageView item_backgroudImg;
        ImageView item_personImg;
        TextView item_title;
        TextView item_name;
        TextView item_caption;
    }

    /**
     * 背景图片监听事件
     */
    public interface BackgroundImgListener{
        public void click(int position);
    }
}
