package com.wl.sm.frame;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.wl.sm.entity.Clazz;
import com.wl.sm.entity.Department;
import com.wl.sm.factory.ServiceFactory;
import com.wl.sm.utils.AliOSSUtil;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.net.URL;
import java.util.List;

/**
 * @ClassName MainFrame
 * @Description
 * @Author WL
 * @Date 2020/11/15
 **/

public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel centerPanel;
    private JButton 院系管理Button;
    private JButton 班级管理Button;
    private JButton 学生管理Button;
    private JButton 奖惩管理Button;
    private JPanel departmentPanel;
    private JPanel classPanel;
    private JPanel studentPanel;
    private JPanel rewardPanel;
    private JPanel toolBarPanel;
    private JButton 新增院系Button;
    private JButton 切换显示Button;
    private JPanel contentPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel bottomPanel;
    private JPanel addDepPanel;
    private JTextField depNameField;
    private JButton 选择图片Button;
    private JLabel logoLabel;
    private JButton addDepartmentbutton;
    private JPanel classTopPanel;
    private JButton 新增班级Button;
    private JTextField classAddTextField;
    private JComboBox depComboBox;
    private JPanel treePanel;
    private JSplitPane classSplitPanel;
    private JPanel classCententPanel;
    private final CardLayout c;
    private String uploadFileUrl;
    private File file;

    public MainFrame() {
        init();
        c = new CardLayout();
        centerPanel.setLayout(c);
        centerPanel.add("1", departmentPanel);
        centerPanel.add("2", classPanel);
        centerPanel.add("3", studentPanel);
        centerPanel.add("4", rewardPanel);

        院系管理Button.addActionListener(e -> {
            c.show(centerPanel, "1");
        });
        班级管理Button.addActionListener(e -> {
            c.show(centerPanel, "2");
        });
        学生管理Button.addActionListener(e -> {
            c.show(centerPanel, "3");
        });
        奖惩管理Button.addActionListener(e -> {
            c.show(centerPanel, "4");
        });
        showDepartments();
        /**
         * 首页布局调整和删除院系功能11.20
         */
        新增院系Button.addActionListener(e -> {
            //获取左侧面板的可见性
            boolean visible = addDepPanel.isVisible();
            //不可见
            if (!visible) {
                //向右侧展开、背景色变化、可见
                leftPanel.setPreferredSize(new Dimension(180, this.getHeight() - 100));
                addDepPanel.setVisible(true);
            } else {
                //向左侧展开、背景色变化、可见
                leftPanel.setPreferredSize(new Dimension(60, this.getHeight() - 100));
                addDepPanel.setVisible(false);
            }

            leftPanel.revalidate();
        });

        addDepartmentbutton.addActionListener(e -> {
            //上传文件到OSS并返回url
            uploadFileUrl = AliOSSUtil.ossUpload(file);
            //创建Department对象，并设置相应得属性
            Department department = new Department();
            department.setDepartmentName(depNameField.getText().trim());
            department.setLogo(uploadFileUrl);
            //调用service实现新增功能
            int n = ServiceFactory.getDepartmentServiceInstance().addDepartment(department);
            if (n == 1) {
                JOptionPane.showMessageDialog(centerPanel, "添加院系成功！");
                //新增院系成功后，将侧边栏隐藏
                leftPanel.setPreferredSize(new Dimension(60, this.getHeight() - 100));
                addDepPanel.setVisible(false);
                //刷新界面数据
                showDepartments();
                //清空文本框
                depNameField.setText("");
                logoLabel.setIcon(null);
            } else {
                JOptionPane.showMessageDialog(centerPanel, "添加院系失败");
            }

        });
        //院系名称文本框焦点监听
        depNameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                depNameField.setText("");
            }
        });
        选择图片Button.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            //默认打开路径
            fileChooser.setCurrentDirectory(new File("E:\\img\\"));
            //对话框显示的范围在centerPanel内
            int result = fileChooser.showOpenDialog(centerPanel);
            if (result == JFileChooser.APPROVE_OPTION) {
                //选中文件
                file = fileChooser.getSelectedFile();
                String name = file.getAbsolutePath();
                //创建icon对象
                URL url;
                ImageIcon icon = new ImageIcon(name);
                logoLabel.setPreferredSize(new Dimension(150, 150));
                //图片强制缩放为JLabel一样大小
                icon = new ImageIcon(icon.getImage().getScaledInstance(logoLabel.getWidth(), logoLabel.getHeight(), Image.SCALE_DEFAULT));
                logoLabel.setIcon(icon);
            }
        });
        班级管理Button.addActionListener(e -> {
            c.show(centerPanel, "2");
            showClazz();
        });
    }

    /**
     * 显示所有院系
     */
    private void showDepartments() {
        //移除原有数据
        contentPanel.removeAll();
        //从service层获取到多有院系列表
        List<Department> departmentList = ServiceFactory.getDepartmentServiceInstance().selectAll();
        //获取总数
        int len = departmentList.size();
        //根据院系总数算出行数（每行放4个）
        int row = len % 4 == 0 ? len / 4 : len / 4 + 1;
        //创建一个网格布局，每行4列，指定水平和垂直间距
        GridLayout gridLayout = new GridLayout(row, 4, 15, 15);
        contentPanel.setLayout(gridLayout);
        for (Department department : departmentList) {
            //给每个院系对象创建一个面板
            JPanel depPanel = new JPanel();
            depPanel.setPreferredSize(new Dimension(400, 400));
            depPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
            JLabel nameLabel = new JLabel(department.getDepartmentName());
            depPanel.setBorder(BorderFactory.createTitledBorder(department.getDepartmentName()));
            //新建一个JLabel标签，用来放置院系Logo，并设置大小
            JLabel logoLabel = new JLabel("<html><img src= '" + department.getLogo() + "'width='200' height='200' /></html>");
            //占位空白标签
            JLabel blankLabel = new JLabel();
            blankLabel.setPreferredSize(new Dimension(200, 30));
            //删除按钮
            JButton delBtn = new JButton("删除");
            depPanel.add(logoLabel);

            //院系面板加入主题内容面板
            contentPanel.add(depPanel);
            //按钮加入院系面板

            depPanel.add(delBtn);
            //删除院系
            delBtn.addActionListener(e -> {
                int n = ServiceFactory.getDepartmentServiceInstance().delDepartment(department, department.getId());
                if (n == 1) {
                    JOptionPane.showMessageDialog(centerPanel, "删除院系成功！");
                    showDepartments();
                } else {
                    JOptionPane.showMessageDialog(centerPanel, "删除院系失败！");
                }
            });
            //刷新主体内容面板
            contentPanel.revalidate();
        }
    }

    /**
     * 显示院系班级
     */
    private void showClazz() {
        List<Department> departments = ServiceFactory.getDepartmentServiceInstance().selectAll();
        showCombobox(departments);
        showTree(departments);
        showClazz(departments);
    }

    private void showTree(List<Department> departments) {
        treePanel.removeAll();
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("南京工业职业技术大学");
        for (Department department : departments) {
            DefaultMutableTreeNode group = new DefaultMutableTreeNode(department.getDepartmentName());
            root.add(group);
            List<Clazz> clazzList = ServiceFactory.getClazzServiceInstance().getClazzByDepId(department.getId());
            for (Clazz clazz : clazzList) {
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(clazz.getClassName());
                group.add(node);
            }
        }
        JTree tree = new JTree(root);
        tree.setRowHeight(30);
        tree.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        treePanel.add(tree, BorderLayout.CENTER);
        treePanel.revalidate();
    }

    private void showCombobox(List<Department> departments) {
        for (Department department : departments) {
            depComboBox.addItem(department);
        }
    }

    private void showClazz(List<Department> departments) {
        classCententPanel.removeAll();
        classCententPanel.setLayout(new GridLayout(0, 5, 15, 15));
        Font titleFont = new Font("微软雅黑", Font.PLAIN, 16);
        for (Department department : departments) {
            JPanel depPanel = new JPanel();
            depPanel.setPreferredSize(new Dimension(120, 150));
            depPanel.setBackground(new Color(63, 98, 131));
            depPanel.setLayout(new BorderLayout());
            JLabel depNameLabel = new JLabel(department.getDepartmentName());
            depNameLabel.setFont(titleFont);
            depNameLabel.setForeground(new Color(255, 255, 255));
            depPanel.add(depNameLabel, BorderLayout.NORTH);
            List<Clazz> clazzList = ServiceFactory.getClazzServiceInstance().getClazzByDepId(department.getId());
            DefaultListModel<Clazz> listModel = new DefaultListModel<>();
            for (Clazz clazz : clazzList) {
                listModel.addElement(clazz);
            }
            JList<Clazz> jList = new JList<>(listModel);
            jList.setBackground(new Color(101, 134, 184));
            JScrollPane scrollPane = new JScrollPane(jList);
            depPanel.add(scrollPane, BorderLayout.CENTER);
            classCententPanel.add(depPanel);
        }
    }

    private void init() {
        this.setTitle("MainFrame");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        topPanel = new JPanel();
        topPanel.setLayout(new GridLayoutManager(1, 6, new Insets(0, 0, 0, 0), 30, 30));
        mainPanel.add(topPanel, BorderLayout.NORTH);
        院系管理Button = new JButton();
        院系管理Button.setText("院系管理");
        topPanel.add(院系管理Button, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        topPanel.add(spacer1, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        班级管理Button = new JButton();
        班级管理Button.setText("班级管理");
        topPanel.add(班级管理Button, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        学生管理Button = new JButton();
        学生管理Button.setText("学生管理");
        topPanel.add(学生管理Button, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        奖惩管理Button = new JButton();
        奖惩管理Button.setText("奖惩管理");
        topPanel.add(奖惩管理Button, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        topPanel.add(spacer2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        centerPanel = new JPanel();
        centerPanel.setLayout(new CardLayout(0, 0));
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        departmentPanel = new JPanel();
        departmentPanel.setLayout(new BorderLayout(0, 0));
        departmentPanel.setBackground(new Color(-5705106));
        centerPanel.add(departmentPanel, "Card5");
        toolBarPanel = new JPanel();
        toolBarPanel.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        departmentPanel.add(toolBarPanel, BorderLayout.NORTH);
        新增院系Button = new JButton();
        新增院系Button.setText("新增院系");
        toolBarPanel.add(新增院系Button, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        toolBarPanel.add(spacer3, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        切换显示Button = new JButton();
        切换显示Button.setText("切换显示");
        toolBarPanel.add(切换显示Button, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setHorizontalScrollBarPolicy(31);
        departmentPanel.add(scrollPane1, BorderLayout.CENTER);
        contentPanel = new JPanel();
        contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        contentPanel.setMaximumSize(new Dimension(-1, -1));
        scrollPane1.setViewportView(contentPanel);
        classPanel = new JPanel();
        classPanel.setLayout(new BorderLayout(0, 0));
        classPanel.setBackground(new Color(-1002766));
        centerPanel.add(classPanel, "Card2");
        classTopPanel = new JPanel();
        classTopPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        classPanel.add(classTopPanel, BorderLayout.NORTH);
        depComboBox = new JComboBox();
        classTopPanel.add(depComboBox);
        classAddTextField = new JTextField();
        classAddTextField.setMinimumSize(new Dimension(60, 30));
        classAddTextField.setPreferredSize(new Dimension(200, 30));
        classTopPanel.add(classAddTextField);
        新增班级Button = new JButton();
        新增班级Button.setText("新增班级");
        classTopPanel.add(新增班级Button);
        classSplitPanel = new JSplitPane();
        classPanel.add(classSplitPanel, BorderLayout.CENTER);
        treePanel = new JPanel();
        treePanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        classSplitPanel.setLeftComponent(treePanel);
        classCententPanel = new JPanel();
        classCententPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        classSplitPanel.setRightComponent(classCententPanel);
        studentPanel = new JPanel();
        studentPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        studentPanel.setBackground(new Color(-4533518));
        centerPanel.add(studentPanel, "Card3");
        rewardPanel = new JPanel();
        rewardPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        rewardPanel.setBackground(new Color(-862024));
        centerPanel.add(rewardPanel, "Card4");
        leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout(0, 0));
        leftPanel.setVisible(true);
        mainPanel.add(leftPanel, BorderLayout.WEST);
        addDepPanel = new JPanel();
        addDepPanel.setLayout(new GridLayoutManager(7, 1, new Insets(0, 0, 0, 0), -1, -1));
        addDepPanel.setVisible(false);
        leftPanel.add(addDepPanel, BorderLayout.CENTER);
        depNameField = new JTextField();
        addDepPanel.add(depNameField, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        选择图片Button = new JButton();
        选择图片Button.setText("选择图片");
        addDepPanel.add(选择图片Button, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        logoLabel = new JLabel();
        logoLabel.setText("图片预览");
        logoLabel.setVerticalAlignment(0);
        addDepPanel.add(logoLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 150), new Dimension(150, 150), 0, false));
        final Spacer spacer4 = new Spacer();
        addDepPanel.add(spacer4, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        addDepartmentbutton = new JButton();
        addDepartmentbutton.setText("新增院系");
        addDepPanel.add(addDepartmentbutton, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        addDepPanel.add(spacer5, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(rightPanel, BorderLayout.EAST);
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
