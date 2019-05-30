package cn.bsd.learn.sql;

import android.database.sqlite.SQLiteDatabase;

import java.io.File;

//提供给其它用户（coder）调用的类
public class BaseDaoFactory {
    private static final BaseDaoFactory instance = new BaseDaoFactory();
    private SQLiteDatabase sqLiteDatabase;

    //数据库的存储位置
    private String path;
    public static BaseDaoFactory getInstance(){
        return instance;
    }

    private BaseDaoFactory(){
        path = "data/data/cn.bsd.learn.sql/database/learn.db";
        //判断路径是都存在
        File file = new File(path);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(path,null);
    }

    //对外提供一个API
    public <T> BaseDao getBaseDao(Class<T> beanClazz){
        BaseDao baseDao = null;
        try {
            baseDao = BaseDao.class.newInstance();
            baseDao.init(sqLiteDatabase,beanClazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baseDao;
    }
}
