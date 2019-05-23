package com.bw.movie.bean;

import java.util.List;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.5.21 9:09
 * @Description：YangXinYu
 */
public class RecordBean {


    /**
     * result : [{"amount":1,"beginTime":"19:20:00","cinemaName":"青春光线电影院","createTime":1558060263000,"endTime":"21:18:00","id":12223,"movieName":"西虹市首富","orderId":"20190517103103074","price":0.13,"screeningHall":"2号厅","status":2,"userId":1288},{"amount":1,"beginTime":"19:20:00","cinemaName":"青春光线电影院","createTime":1558059007000,"endTime":"21:18:00","id":12220,"movieName":"西虹市首富","orderId":"20190517101007848","price":0.13,"screeningHall":"2号厅","status":2,"userId":1288},{"amount":1,"beginTime":"19:20:00","cinemaName":"青春光线电影院","createTime":1558057414000,"endTime":"21:18:00","id":12216,"movieName":"西虹市首富","orderId":"20190517094334356","price":0.13,"screeningHall":"2号厅","status":2,"userId":1288},{"amount":1,"beginTime":"19:20:00","cinemaName":"青春光线电影院","createTime":1558057330000,"endTime":"21:18:00","id":12215,"movieName":"西虹市首富","orderId":"20190517094210700","price":0.13,"screeningHall":"2号厅","status":2,"userId":1288},{"amount":1,"beginTime":"20:50:00","cinemaName":"保利国际影城北京龙旗广场店","createTime":1558028632000,"endTime":"22:10:00","id":12180,"movieName":"碟中谍6：全面瓦解","orderId":"20190517014352723","price":0.2,"screeningHall":"情侣厅","status":2,"userId":1288}]
     * message : 请求成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * amount : 1
         * beginTime : 19:20:00
         * cinemaName : 青春光线电影院
         * createTime : 1558060263000
         * endTime : 21:18:00
         * id : 12223
         * movieName : 西虹市首富
         * orderId : 20190517103103074
         * price : 0.13
         * screeningHall : 2号厅
         * status : 2
         * userId : 1288
         */

        private int amount;
        private String beginTime;
        private String cinemaName;
        private long createTime;
        private String endTime;
        private int id;
        private String movieName;
        private String orderId;
        private double price;
        private String screeningHall;
        private int status;
        private int userId;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public String getCinemaName() {
            return cinemaName;
        }

        public void setCinemaName(String cinemaName) {
            this.cinemaName = cinemaName;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMovieName() {
            return movieName;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getScreeningHall() {
            return screeningHall;
        }

        public void setScreeningHall(String screeningHall) {
            this.screeningHall = screeningHall;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
