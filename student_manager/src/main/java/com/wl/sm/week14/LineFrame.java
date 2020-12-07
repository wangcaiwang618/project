package com.wl.sm.week14;

import javax.swing.*;
import java.awt.*;

/**
 * @ClassName LineFrame
 * @Description 绘制线段的窗体
 * @Author YPH
 * @Date 2020/12/7
 **/

public class LineFrame extends JFrame {
    public LineFrame(){
        init();
        //将窗体内容面板背景设置为黑色
        getContentPane().setBackground(new Color(0,0,0));
        setTitle("Line frame");
        setSize(1024,768);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void init(){
        LineThread lt = new LineThread();
        lt.setFrame(this);
        new Thread(lt).start();
    }

    public static void main(String[] args) {
        new LineFrame();
    }
}
