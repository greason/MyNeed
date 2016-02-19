package com.greason.need.database;

import android.os.Environment;

/**
 * Created by greason on 2015/7/17.
 */
public class DBConstants {

    //数据库路径
    public static final String BASE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/zgzs/";

    public static final String DATABASE_NAME = "zgzs.db";

    public static final String ZS_TABLENAME = "zhansun";
    public static final String ZG_TABLENAME = "zhanguo";
    public static final String SL_TABLENAME = "shili";

}
