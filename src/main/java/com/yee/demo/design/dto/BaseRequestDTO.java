package com.yee.demo.design.dto;

import com.yee.demo.design.enums.ScenesItemEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @Author yee
 * @Date 2020/9/7
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseRequestDTO implements Serializable {




    private String requestNo;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 交易单号
     */
    private String tradeNo;

    /**
     * 备注
     */
    private String remark;

    /**
     * 场景
     */
    private ScenesItemEnum scenesItemEnum;

    /**
     * 交易时间
     */
    private Date tradeTime;

    /**
     * B端业务账单此参数必传。因为和其他处理场景不同
     *
     */
    private Boolean redPush;

    /**
     * 账户id 非必传
     */
    private Long accountId;


}
