package com.yee.demo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author yee
 * 2024/6/26 11:31
 */
@Slf4j
public class EasyExcelUtils<T> {
    /**
     * 获取读取Excel的监听器对象
     * 为了解耦及减少每个数据模型bean都要创建一个监听器的臃肿, 使用泛型指定数据模型类型
     * 使用jdk8新特性中的函数式接口 Consumer
     * 可以实现任何数据模型bean的数据解析, 不用重复定义监听器
     * @param consumer 处理解析数据的函数, 一般可以是数据入库逻辑的函数
     * @param threshold 阈值,达到阈值就处理一次存储的数据
     * @param <T> 数据模型泛型
     * @return 返回监听器
     */
    public static <T> AnalysisEventListener<T> getReadListener(Consumer<List<T>> consumer, int threshold) {

        return new AnalysisEventListener<T>() {

            /**
             * 存储解析的数据 T t
             * ArrayList基于数组实现, 查询更快
             * LinkedList基于双向链表实现, 插入和删除更快
             */
            List<T> dataList = new LinkedList<>();

            /**
             * 每解析一行数据事件调度中心都会通知到这个方法, 订阅者1
             * @param data 解析的每行数据
             * @param context
             */
            @Override
            public void invoke(T data, AnalysisContext context) {
                log.info("订阅者1执行时间------{}----数据：{}", LocalDateTime.now(),data);
//                valid(data);
                dataList.add(data);
                // 达到阈值就处理一次存储的数据
                if (dataList.size() >= threshold) {
                    consumer.accept(dataList);
                    dataList.clear();
                }
            }

            /**
             * excel文件解析完成后,事件调度中心会通知到该方法, 订阅者2
             * @param context
             */
            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                log.info("订阅者2执行时间------{}----数据：{}", LocalDateTime.now(),dataList);
                // 最后阈值外的数据做处理
                if (dataList.size() > 0) {
                    consumer.accept(dataList);
                }
            }

            /**
             * 异常方法 (类型转换异常也会执行此方法)  （读取一行抛出异常也会执行此方法)
             */
            @Override
            public void onException(Exception exception, AnalysisContext context) {
                log.info("数据异常");
                // 如果是某一个单元格的转换异常 能获取到具体行号
                if (exception instanceof ExcelDataConvertException) {
                    ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException)exception;
                    log.error("第{}行，第{}列解析异常，数据为:{}", excelDataConvertException.getRowIndex(),
                            excelDataConvertException.getColumnIndex(), excelDataConvertException.getCellData());
                    throw new RuntimeException("第"+excelDataConvertException.getRowIndex()+"行" +
                            "，第" + (excelDataConvertException.getColumnIndex() + 1) + "列读取错误");
                }
                //抛出非空校验异常
                throw new RuntimeException(exception.getMessage());
            }
        };

    }

    /**
     * 获取读取Excel的监听器对象, 不指定阈值, 默认阈值为 2000
     * @param consumer
     * @param <T>
     * @return
     */
    public static <T> AnalysisEventListener<T> getReadListener(Consumer<List<T>> consumer) {
        return getReadListener(consumer, 2000);
    }

    /**
     * Excel导入字段校验
     * @param object 校验的JavaBean 其属性须有自定义注解
     */
//    public static void valid(Object object) {
//        Field[] fields = object.getClass().getDeclaredFields();
//        for (Field field : fields) {
//            //设置可访问
//            field.setAccessible(true);
//            //属性的值
//            Object fieldValue = null;
//            try {
//                fieldValue = field.get(object);
//            } catch (IllegalAccessException e) {
//                throw new RuntimeException("导入参数检查失败");
//            }
//            //是否包含必填校验注解
//            boolean isExcelValid = field.isAnnotationPresent(ExcelDataValid.class);
//            if (isExcelValid && Objects.isNull(fieldValue)) {
//                log.error(field.getAnnotation(ExcelDataValid.class).message());
//                throw new RuntimeException(field.getAnnotation(ExcelDataValid.class).message());
//            }
//        }
//    }
}

