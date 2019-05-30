package cn.bsd.learn.sql;

//规范所有的数据库操作
public interface IBaseDao<T> {
    //插入数据
    long insert(T bean);
}
