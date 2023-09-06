package com.monochrome.dynamicdatasource.config;

/**
 * 使用ThreadLocal存储切换数据源后的KEY
 * @author monochrome
 * @date 2023/8/29
 */
public class DataSourceHolder {
    //线程 本地环境
    private static final ThreadLocal<String> dataSources = new InheritableThreadLocal();

    //设置数据源
    public static void setDataSource(String datasource) {
        dataSources.set(datasource);
    }
    //获取数据源
    public static String getDataSource() {
        return dataSources.get();
    }
    //清除数据源
    public static void clearDataSource() {
        dataSources.remove();
    }
}
