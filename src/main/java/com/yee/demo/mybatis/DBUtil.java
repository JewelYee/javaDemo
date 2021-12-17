package com.yee.demo.mybatis;



/**
 * db 相关操作 工具类
 *
 * @Author yee
 * @Date 2020
 */
public class DBUtil {


    /**
     * 判断异常是否为数据插入重复异常
     *
     * @param e
     * @return true:重复异常 false:其他异常
     */
    public static boolean isDuplicateEntry(Exception e) {
        try {
            return e.getMessage().contains(DataBaseConstant.DB_EXCEPTION_DUPLICATE);
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * 判断数据更新是否成功（受影响行数是否大于0）
     *
     * @param rows 受影响行数
     * @return true:成功
     */
    public static boolean isUpdateSuccess(int rows) {
        return DataBaseConstant.UPDATE_ROWS != rows;
    }
}
