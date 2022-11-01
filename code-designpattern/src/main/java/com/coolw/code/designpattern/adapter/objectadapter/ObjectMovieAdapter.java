package com.coolw.code.designpattern.adapter.objectadapter;

import com.coolw.code.designpattern.adapter.classadapter.AmericanMovieTarget;
import com.coolw.code.designpattern.adapter.classadapter.KoreanDramasAdaptee;

/**
 * @Classname ObjectMovieAdapter
 * @Description 电影适配器
 * @Author lw
 * @Date 2019-12-26 13:01
 */
public class ObjectMovieAdapter implements AmericanMovieTarget {

    private KoreanDramasAdaptee koreanDramas;

    public ObjectMovieAdapter(KoreanDramasAdaptee koreanDramas) {
        this.koreanDramas = koreanDramas;
    }

    @Override
    public void playAmericanMovie() {
        System.out.println("正在播放美剧。。。。。。");
        System.out.println("开始切换。。。。。。");
        koreanDramas.playKoreanDramas();
    }

}
