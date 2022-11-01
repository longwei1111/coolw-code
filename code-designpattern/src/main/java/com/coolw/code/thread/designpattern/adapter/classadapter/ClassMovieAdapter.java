package com.coolw.code.thread.designpattern.adapter.classadapter;

/**
 * @Classname ClassMovieAdapter
 * @Description 电影适配器
 * @Author lw
 * @Date 2019-12-26 13:01
 */
public class ClassMovieAdapter extends KoreanDramasAdapteeImpl implements AmericanMovieTarget {

    @Override
    public void playAmericanMovie() {
        System.out.println("正在播放美剧。。。。。。");
        System.out.println("开始切换。。。。。。");
        playKoreanDramas();
    }

}
