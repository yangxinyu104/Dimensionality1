package com.bw.movie.bean;

import org.greenrobot.greendao.annotation.Entity;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.5.12 20:10
 * @Description：YangXinYu
 */
@Entity
public class PopularMovieDao {

        private int followMovie;
        private int id;
        private String imageUrl;
        private String name;
        private int rank;
        private String summary;
        public boolean flag;

        @Generated(hash = 1542556156)
        public PopularMovieDao(int followMovie, int id, String imageUrl, String name,
                int rank, String summary, boolean flag) {
            this.followMovie = followMovie;
            this.id = id;
            this.imageUrl = imageUrl;
            this.name = name;
            this.rank = rank;
            this.summary = summary;
            this.flag = flag;
        }

        @Generated(hash = 1192079043)
        public PopularMovieDao() {
        }

        public int getFollowMovie() {
            return followMovie;
        }

        public void setFollowMovie(int followMovie) {
            this.followMovie = followMovie;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public boolean getFlag() {
            return this.flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }
}
