package com.bw.movie.ghb;

/**
 * Project name：Dimensionality1
 * Time: 2019/5/14 8:49
 * Author: 高海波
 */
public class BasePresenter<V> {

    private V view;

    public V getView() {
        return view;
    }

    public void setView(V view) {
        this.view = view;
    }

    public void detachView(){
        this.view = null;
    }

}
