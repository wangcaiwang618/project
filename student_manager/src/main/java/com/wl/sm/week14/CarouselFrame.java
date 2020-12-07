package com.wl.sm.week14;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

/**
 * @ClassName CarouselFrame
 * @Description 图片轮播的窗体
 * @Author YPH
 * @Date 2020/12/7
 **/

public class CarouselFrame  extends JFrame {
    private JLabel bglabel;
    private JLabel timeLabel;
    private JLabel textLabel;
    public CarouselFrame(){
        init();
        setTitle("CarouselFrame");
        setSize(500,600);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void init(){
        bglabel = new JLabel();
        CarouselThread ct = new CarouselThread();
        ct.setBgLabel(bglabel);
        new Thread(ct).start();
        this.getContentPane().add(bglabel,BorderLayout.CENTER);
        //时间线程和标签
        timeLabel = new JLabel(LocalDateTime.now().toString());
        timeLabel.setFont(new Font("微软雅黑",Font.BOLD,24));
        this.getContentPane().add(timeLabel,BorderLayout.SOUTH);
        TimeThread tt = new TimeThread();
        tt.setTimeLabel(timeLabel);
        new Thread(tt).start();
        //文本
        textLabel = new JLabel();
        textLabel.setFont(new Font("微软雅黑",Font.BOLD,28));
        this.getContentPane().add(textLabel,BorderLayout.NORTH);
        TextThread tet = new TextThread();
        tet.setTextLabel(textLabel);
        new Thread(tet).start();

    }

    public static void main(String[] args) {
        new CarouselFrame();
    }
}
