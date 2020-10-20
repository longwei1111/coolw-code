package com.coolw.designpattern.adapter.interfaceadpter;

/**
 * @Classname InterfaceMovieAdapter
 * @Description 电影适配器
 * @Author lw
 * @Date 2019-12-26 13:21
 */
public class InterfaceMovieAdapter extends AbstractMovieAdaptee {

    @Override
    public void playAmericanMovie() {
        System.out.println("正在播放美剧。。。。。。");
    }

    @Override
    public void playKoreanDramas() {
        System.out.println("开始切换。。。。。。");
        System.out.println("正在播放韩剧。。。。。。");
    }

}
