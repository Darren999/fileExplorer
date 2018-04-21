package com.darren.fileexplorer;

import android.view.View;
import android.widget.AdapterView;

/**
 * Created by Darren.fan on 2018/4/21.
 */
public interface InnerItemOnclickListener {
    public void itemClick(AdapterView<?> parent, View view, int position, long id);
}
