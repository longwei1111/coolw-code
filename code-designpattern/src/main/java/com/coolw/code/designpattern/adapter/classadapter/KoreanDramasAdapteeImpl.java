package com.coolw.code.designpattern.adapter.classadapter;

/**
 * @Classname KoreanDramasAdapteeImpl
 * @Description 韩剧适配者接口实现
 * @Author lw
 * @Date 2019-12-26 13:03
 */
public class KoreanDramasAdapteeImpl implements KoreanDramasAdaptee {

    @Override
    public void playKoreanDramas() {
        System.out.println("正在播放韩剧。。。。。。");
    }

}
