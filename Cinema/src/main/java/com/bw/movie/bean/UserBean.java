package com.bw.movie.bean;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.5.17 15:04
 * @Description：YangXinYu
 */
public class UserBean {


    /**
     * result : {"email":"123@qq.com","id":1288,"nickName":"1111111","sex":1}
     * message : 修改成功
     * status : 0000
     */

    private ResultBean result;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
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

    public static class ResultBean {
        /**
         * email : 123@qq.com
         * id : 1288
         * nickName : 1111111
         * sex : 1
         */

        private String email;
        private int id;
        private String nickName;
        private int sex;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }
    }
}
