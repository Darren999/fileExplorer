package com.darren.fileexplorer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Darren.fan on 2018/4/21.
 */
public class ListItemAdapter extends BaseAdapter implements OnItemClickListener{
    private Context mContext;
    private File[] currentFiles;
    private InnerItemOnclickListener mListener;

    public ListItemAdapter(Context mConext, File[] currentFiles) {
        super();
        this.mContext = mConext;
        this.currentFiles = currentFiles;
    }

    @Override
    public int getCount() {
        return currentFiles.length;
    }

    @Override
    public Object getItem(int position) {
        return currentFiles[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layout = LayoutInflater.from(mContext);
        ViewHolder holder = new ViewHolder();
        if(convertView == null) {
            convertView = (View)layout.inflate(R.layout.file_list,parent,false);
            holder.imageView =(ImageView)convertView.findViewById(R.id.icon);
            holder.tx_name = (TextView)convertView.findViewById(R.id.file_name);
            holder.tx_date = (TextView)convertView.findViewById(R.id.file_date);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        if(currentFiles[position].isDirectory()){
            holder.imageView.setImageResource(R.drawable.folder);
        } else {
            holder.imageView.setImageResource(R.drawable.file);
        }

        holder.tx_name.setText(currentFiles[position].getName());

        long modTime = currentFiles[position].lastModified();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        holder.tx_date.setText(dateFormat.format(new Date(modTime)));

        return convertView;
    }

    public void setOnInnerItemOnClickListener(InnerItemOnclickListener listener) {
        mListener = listener;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mListener.itemClick(parent,view,position,id);
    }

    private class ViewHolder {
        private ImageView imageView;
        private TextView tx_name;
        private TextView tx_date;

    }
}
