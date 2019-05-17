package com.bw.movie.bean;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.5.16 9:49
 * @Description：YangXinYu
 */
public class BuyBean {


    /**
     * orderId : 20180807084055347
     * message : 下单成功
     * status : 0000
     */

    private String orderId;
    private String message;
    private String status;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
