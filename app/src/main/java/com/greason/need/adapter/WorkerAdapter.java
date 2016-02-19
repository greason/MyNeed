package com.greason.need.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.greason.need.R;
import com.greason.need.model.WorkerModel;
import com.greason.need.utils.BitmapFromAssets;

import java.io.File;
import java.util.List;

/**
 * Created by greason on 2015/7/29.
 */
public class WorkerAdapter extends BaseAdapter {

    public Context context;
    public List<WorkerModel> datas ;

    protected LayoutInflater inflater ;

    private String path = "worker";
    private String secondFolder ;
    private String perFix_backgroudImg = "hang";

    private BackgroundImgListener listener ;

    public WorkerAdapter(Context context,String secondFolder , List<WorkerModel> datas){
        super();
        this.context = context ;
        this.secondFolder = secondFolder;
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
    public WorkerModel getItem(int i) {
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
            convertView = inflater.inflate(R.layout.viewpager_worker_gv_item,null);
            holder.item_name = (TextView)convertView.findViewById(R.id.gv_name);
            holder.item_caption = (TextView)convertView.findViewById(R.id.gv_caption);

            holder.item_bgImg = (ImageView)convertView.findViewById(R.id.gv_img);

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
        holder.item_caption.setText(datas.get(position).getCaption());

        holder.item_bgImg.setImageBitmap(BitmapFromAssets.getBitmap(context , path + File.separator + secondFolder +
                File.separator + perFix_backgroudImg + (position + 1)));
        return  convertView;
    }

    public class ViewHolder{
        TextView item_name;
        TextView item_caption;
        ImageView item_bgImg;
    }

    /**
     * 背景图片监听事件
     */
    public interface BackgroundImgListener{
        public void click(int position);
    }
}
