package cn.edu.seu.core_java_volume;

import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.swing.*;

public class ImageViewer {
    public static final int START_X = 600;
    public static final int START_Y = 300;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new ImageViewerFrame();

            frame.setTitle("ImageViewer");
            frame.setBounds(START_X, START_Y, ImageViewerFrame.DEFAULT_WIDTH, ImageViewerFrame.DEFAULT_HEIGHT);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class ImageViewerFrame extends JFrame {
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 400;

    private int scrollX = 0;
    private int scrollY = 0;
    private Image image;
    private final Panel imagePanel;

    public static String getPathOfJFileChooser(JFileChooser chooser) {
        // 重构不建议这样使用，因为 chooser 是 inst，getSelectedFile 返回值也是 inst... 这样太让人困惑？
        return chooser.getSelectedFile().getPath();
    }


    public ImageViewerFrame() throws HeadlessException {
        // Java 中，这个 class 似乎可以做到无成本移出去！但是不知道本质是否一致
        class Local {
            public void initMenuBar(JMenuBar menuBar, List<JMenu> menus) {
                setJMenuBar(menuBar);
                menus.forEach(menuBar::add);
            }

            public void initFileMenu(JMenu fileMenu) {
                var testLabel = new JLabel();
                add(testLabel);

                // 文件选择器
                var chooser = new JFileChooser();
                chooser.setCurrentDirectory(new File("./src/main/resources"));

                var openItem = new JMenuItem("Open");
                fileMenu.add(openItem);
                openItem.addActionListener(event -> {
                    int res = chooser.showOpenDialog(null);
                    if (res == JFileChooser.APPROVE_OPTION) {
                        String name = getPathOfJFileChooser(chooser);
                        image = Toolkit.getDefaultToolkit().getImage(name);
                        testLabel.setIcon(new ImageIcon(image));
                        imagePanel.repaint();
                        // int offsetX = 10, offsetY = 30;
                        // setSize(image.getWidth(ImageViewerFrame.this) + offsetX,
                        //         image.getHeight(ImageViewerFrame.this) + offsetY);
                    }
                });

                var exitItem = new JMenuItem("Exit");
                fileMenu.add(exitItem);
                exitItem.addActionListener(event -> System.exit(0));
            }

            public void addScrollBar() {
                var screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                var hScrollbar = new Scrollbar(Scrollbar.HORIZONTAL, 0, 50, 0, screenSize.width);
                var vScrollbar = new Scrollbar(Scrollbar.VERTICAL, 0, 50, 0, screenSize.height);

                // 滚动条事件监听
                hScrollbar.addAdjustmentListener(e -> {
                    scrollX = hScrollbar.getValue();
                    imagePanel.repaint();
                });

                vScrollbar.addAdjustmentListener(e -> {
                    scrollY = vScrollbar.getValue();
                    imagePanel.repaint();
                });

                add(hScrollbar);
                add(vScrollbar);
            }
        }

        var local = new Local();


        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        var menuBar = new JMenuBar();
        var fileMenu = new JMenu("File");
        local.initMenuBar(menuBar, List.of(fileMenu));
        local.initFileMenu(fileMenu);

        // 主区域添加 layout
        // setLayout(new FlowLayout());

        imagePanel = new Panel() {
            @Override
            public void paint(Graphics g) {
                // Optional<Image> imageOpt = Optional.ofNullable(image);
                // imageOpt.ifPresent(e -> System.out.println(e.getSource()));
                // 2024-11-02：不知为何，此处的代码未能显示
                g.drawImage(image, ImageViewer.START_X, ImageViewer.START_Y, this);
            }
        };
        // add(imagePanel);

        // local.addScrollBar();
    }
}