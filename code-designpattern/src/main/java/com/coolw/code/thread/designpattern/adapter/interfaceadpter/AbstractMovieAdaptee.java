package com.coolw.code.thread.designpattern.adapter.interfaceadpter;

/**
 * @Classname AbstractMovieAdaptee
 * @Description 电影适配者抽象类
 * @Author lw
 * @Date 2019-12-26 13:19
 */
public abstract class AbstractMovieAdaptee implements MovieTarget {

    @Override
    public void playAmericanMovie() {
    }

    @Override
    public void playKoreanDramas() {
    }

}
