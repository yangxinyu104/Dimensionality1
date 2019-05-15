package com.bw.movie.bean;

import java.util.List;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.5.14 11:49
 * @Description：YangXinYu
 */
public class ReviewBean {


    /**
     * result : [{"commentContent":"真鸡儿6","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","commentId":474,"commentTime":1555675723000,"commentUserId":12466,"commentUserName":"二愣子","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"真鸡儿6","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","commentId":473,"commentTime":1555675175000,"commentUserId":12466,"commentUserName":"二愣子","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"好看","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-04-07/20190407113559.jpg","commentId":327,"commentTime":1553915181000,"commentUserId":12050,"commentUserName":"爽歪歪","greatNum":1,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"好看","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-04-07/20190407113559.jpg","commentId":296,"commentTime":1553824715000,"commentUserId":12050,"commentUserName":"爽歪歪","greatNum":1,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"好看","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-04-07/20190407113559.jpg","commentId":295,"commentTime":1553824642000,"commentUserId":12050,"commentUserName":"爽歪歪","greatNum":1,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"好看","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-04-07/20190407113559.jpg","commentId":257,"commentTime":1553690310000,"commentUserId":12050,"commentUserName":"爽歪歪","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"好看","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-04-07/20190407113559.jpg","commentId":256,"commentTime":1553690297000,"commentUserId":12050,"commentUserName":"爽歪歪","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"好看","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-04-07/20190407113559.jpg","commentId":230,"commentTime":1553583116000,"commentUserId":12050,"commentUserName":"爽歪歪","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"好看","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-04-07/20190407113559.jpg","commentId":229,"commentTime":1553581008000,"commentUserId":12050,"commentUserName":"爽歪歪","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"好看","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-04-07/20190407113559.jpg","commentId":228,"commentTime":1553580910000,"commentUserId":12050,"commentUserName":"爽歪歪","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":0}]
     * message : 查询成功
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
         * commentContent : 真鸡儿6
         * commentHeadPic : http://172.17.8.100/images/movie/head_pic/bwjy.jpg
         * commentId : 474
         * commentTime : 1555675723000
         * commentUserId : 12466
         * commentUserName : 二愣子
         * greatNum : 0
         * hotComment : 0
         * isGreat : 0
         * replyNum : 0
         */

        private String commentContent;
        private String commentHeadPic;
        private int commentId;
        private long commentTime;
        private int commentUserId;
        private String commentUserName;
        private int greatNum;
        private int hotComment;
        private int isGreat;
        private int replyNum;
        public boolean flag;
        public String getCommentContent() {
            return commentContent;
        }

        public void setCommentContent(String commentContent) {
            this.commentContent = commentContent;
        }

        public String getCommentHeadPic() {
            return commentHeadPic;
        }

        public void setCommentHeadPic(String commentHeadPic) {
            this.commentHeadPic = commentHeadPic;
        }

        public int getCommentId() {
            return commentId;
        }

        public void setCommentId(int commentId) {
            this.commentId = commentId;
        }

        public long getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(long commentTime) {
            this.commentTime = commentTime;
        }

        public int getCommentUserId() {
            return commentUserId;
        }

        public void setCommentUserId(int commentUserId) {
            this.commentUserId = commentUserId;
        }

        public String getCommentUserName() {
            return commentUserName;
        }

        public void setCommentUserName(String commentUserName) {
            this.commentUserName = commentUserName;
        }

        public int getGreatNum() {
            return greatNum;
        }

        public void setGreatNum(int greatNum) {
            this.greatNum = greatNum;
        }

        public int getHotComment() {
            return hotComment;
        }

        public void setHotComment(int hotComment) {
            this.hotComment = hotComment;
        }

        public int getIsGreat() {
            return isGreat;
        }

        public void setIsGreat(int isGreat) {
            this.isGreat = isGreat;
        }

        public int getReplyNum() {
            return replyNum;
        }

        public void setReplyNum(int replyNum) {
            this.replyNum = replyNum;
        }
    }
}
