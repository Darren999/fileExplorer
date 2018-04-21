package com.darren.fileexplorer.model;

import java.util.ArrayList;

/**
 * Created by Darren.fan on 2018/4/21.
 */
public interface IFileBrowser  {
    public ArrayList<String> LookForAll();
    public ArrayList<String> LookFor(String post);
    public void gotoParentDir();
    public void gotoChildDir();
}
