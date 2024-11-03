package cn.edu.seu.core_java_volume;

import java.awt.*;
import java.awt.event.*;

public class ImageScrollExample extends Frame {
    private Image image;
    private int imageWidth;
    private int imageHeight;
    private int scrollX = 0;
    private int scrollY = 0;

    public ImageScrollExample(String imagePath) {
        // 加载图片
        image = Toolkit.getDefaultToolkit().getImage(imagePath);

        // 获取图片的宽度和高度
        MediaTracker tracker = new MediaTracker(this);
        tracker.addImage(image, 0);
        try {
            tracker.waitForAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        imageWidth = image.getWidth(this);
        imageHeight = image.getHeight(this);

        // 设置窗口属性
        setTitle("Image Scroll Example");
        setSize(600, 400);
        setLayout(new BorderLayout());

        // 创建一个面板用于显示图片
        Panel imagePanel = new Panel() {
            @Override
            public void paint(Graphics g) {
                g.drawImage(image, -scrollX, -scrollY, this);
            }
        };

        // 创建滚动条
        Scrollbar hScrollbar = new Scrollbar(Scrollbar.HORIZONTAL, 0, 50, 0, imageWidth);
        Scrollbar vScrollbar = new Scrollbar(Scrollbar.VERTICAL, 0, 50, 0, imageHeight);

        // 滚动条事件监听
        hScrollbar.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent e) {
                scrollX = hScrollbar.getValue();
                imagePanel.repaint();
            }
        });

        vScrollbar.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent e) {
                scrollY = vScrollbar.getValue();
                imagePanel.repaint();
            }
        });

        // 将组件添加到框架
        add(imagePanel, BorderLayout.CENTER);
        add(hScrollbar, BorderLayout.SOUTH);
        add(vScrollbar, BorderLayout.EAST);

        // 关闭窗口事件
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        // 替换为你自己的图片路径
        new ImageScrollExample("./src/main/resources/img.png");
        // new ImageScrollExample("E:\\ProgrammingProjects\\IDEAProjects\\Lua\\java_start_project\\src\\main\\resources\\img.png");
    }
}

