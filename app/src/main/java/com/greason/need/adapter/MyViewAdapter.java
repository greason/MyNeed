package com.greason.need.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.greason.need.R;

import java.util.List;

/**
 * Created by greason on 2015/7/29.
 */
public class MyViewAdapter<T> extends BaseAdapter {

    public Context context;
    public List<T> datas ;

    protected LayoutInflater inflater ;

    public MyViewAdapter( Context context ,List<T> datas){
        super();
        this.context = context ;
        this.datas = datas;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public T getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_item_basic,null);
            holder.item_notice = (ImageView)convertView.findViewById(R.id.item_notice);
            holder.item_name = (TextView)convertView.findViewById(R.id.item_name);
            holder.item_time = (TextView)convertView.findViewById(R.id.item_time);
            holder.item_check = (CheckBox)convertView.findViewById(R.id.item_check);
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
        return  convertView;
    }

    public class ViewHolder{
        ImageView item_notice;
        TextView item_name;
        TextView item_time;
        CheckBox item_check;
    }
}
