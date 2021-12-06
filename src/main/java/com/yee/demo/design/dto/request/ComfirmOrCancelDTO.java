package com.yee.demo.design.dto.request;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2021/9/2 8:39 PM
 */
@Data
public class ComfirmOrCancelDTO implements Serializable {
    /**
     * 确认：confirm 取消：cancel
     */
    private String type;

    /**
     * 交易单号
     */
    private String tradeNo;

    /**
     * 账户id
     */
    private Long accountId;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 操作时间
     */
    private Date tradeTime;

    private String requestNo;


    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"type\":\"")
                .append(type).append('\"');
        sb.append(",\"tradeNo\":\"")
                .append(tradeNo).append('\"');
        sb.append(",\"accountId\":")
                .append(accountId);
        sb.append(",\"batchNo\":\"")
                .append(batchNo).append('\"');
        sb.append(",\"tradeTime\":\"")
                .append(tradeTime).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
