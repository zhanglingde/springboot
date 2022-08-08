package com.limg.mongodb.mongospring.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "orders")
public class Order {
    /**
     * 省
     */
    private String province;
    /**
     * 店铺名称
     */
    private String shopName;
    /**
     * 电话号码
     */
    private String phone;
    /**
     * 下单日期
     */
    // @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date orderDate;
    /**
     * 订单状态
     */
    private String status;
    /**
     * 运单号码
     */
    private String waybillNo;
    /**
     * 总运费
     */
    private Integer shippingFee;

    /**
     * 总费用
     */
    private Integer total;

    /**
     * 订单明细列表
     */
    private List<OrderDetail> orderDetailList;


    private Bill bill;
}
