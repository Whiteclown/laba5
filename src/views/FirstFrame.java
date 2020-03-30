package views;

import Bees.Bee;
import Bees.BeeBig;
import Bees.BeeWork;
import Habitat.Habitat;
import org.w3c.dom.DOMImplementation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FirstFrame extends JFrame implements KeyListener {
    VisualPanel visualPanel;
    ControlPanel controlPanel;
    Habitat habitat;
    JLabel timeLabel;
    JButton buttonBegin;
    JButton buttonStop;
    ButtonGroup buttonGroup;
    ButtonGroup buttonGroupMenu;
    JRadioButton jRadioButtonShowTime;
    JRadioButton jRadioButtonHideTime;
    JTextField jTextFieldN1;
    JTextField jTextFieldN2;
    JCheckBox jCheckBoxShowInfo;
    JCheckBoxMenuItem jCheckBoxMenuItemShowInfo;
    JMenuItem jMenuItemBegin;
    JMenuItem jMenuItemStop;
    JRadioButtonMenuItem jRadioButtonMenuItemShowTime;
    JRadioButtonMenuItem jRadioButtonMenuItemHideTime;
    boolean isContinue;
    JComboBox jComboBoxP;
    JComboBox jComboBoxK;
    String[] items = {
            "10%",
            "20%",
            "30%",
            "40%",
            "50%",
            "60%",
            "70%",
            "80%",
            "90%",
            "100%"
    };
    boolean timeVisible = true;
    int time;
    boolean isShowInfo = false;

    public FirstFrame(){
        habitat = new Habitat(5,3, 0.8, 0.5, this);
        visualPanel = new VisualPanel();
        controlPanel = new ControlPanel();
        BeginActionListener beginActionListener = new BeginActionListener();
        StopActionListener stopActionListener = new StopActionListener();
        ShowTimeActionListener showTimeActionListener = new ShowTimeActionListener();
        HideTimeActionListener hideTimeActionListener = new HideTimeActionListener();
        ShowInfoItemListener showInfoItemListener = new ShowInfoItemListener();

        setTitle("Bees");
        Dimension dimensionFirstFrame = new Dimension(habitat.getWIDTH(), habitat.getHEIGHT());
        setPreferredSize(dimensionFirstFrame);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Панель интерфейса
        Dimension controlSize = new Dimension(150, dimensionFirstFrame.height);
        Color colorControlPanel = new Color(97, 148, 222);
        controlPanel.setPreferredSize(controlSize);
        controlPanel.setLayout(new FlowLayout());
        controlPanel.setBackground(colorControlPanel);
        add(controlPanel, BorderLayout.EAST);
        buttonBegin = new JButton("Begin");
        buttonBegin.addActionListener(beginActionListener);
        buttonBegin.setFocusable(false);
        controlPanel.add(buttonBegin);
        buttonStop = new JButton("Stop");
        buttonStop.addActionListener(stopActionListener);
        buttonStop.setFocusable(false);
        buttonStop.setEnabled(false);
        controlPanel.add(buttonStop);
        jCheckBoxShowInfo = new JCheckBox("Итоговая информация");
        jCheckBoxShowInfo.setBackground(colorControlPanel);
        jCheckBoxShowInfo.setFocusable(false);
        jCheckBoxShowInfo.setForeground(Color.BLUE);
        jCheckBoxShowInfo.addItemListener(showInfoItemListener);
        controlPanel.add(jCheckBoxShowInfo);
        JLabel jLabelTime = new JLabel("Видимость времени:");
        controlPanel.add(jLabelTime);
        buttonGroup = new ButtonGroup();
        jRadioButtonShowTime = new JRadioButton("Да", true);
        jRadioButtonShowTime.setBackground(colorControlPanel);
        jRadioButtonShowTime.setFocusable(false);
        jRadioButtonShowTime.setForeground(Color.BLUE);
        jRadioButtonShowTime.addActionListener(showTimeActionListener);
        jRadioButtonHideTime = new JRadioButton("Нет", false);
        jRadioButtonHideTime.setBackground(colorControlPanel);
        jRadioButtonHideTime.setFocusable(false);
        jRadioButtonHideTime.setForeground(Color.BLUE);
        jRadioButtonHideTime.addActionListener(hideTimeActionListener);
        buttonGroup.add(jRadioButtonShowTime);
        buttonGroup.add(jRadioButtonHideTime);
        controlPanel.add(jRadioButtonShowTime);
        controlPanel.add(jRadioButtonHideTime);
        JLabel jLabelPeriods = new JLabel("Периоды рождения:");
        controlPanel.add(jLabelPeriods);
        JLabel jLabelN1 = new JLabel("Трутни");
        jLabelN1.setPreferredSize(new Dimension(50, 20));
        controlPanel.add(jLabelN1);
        jTextFieldN1 = new JTextField("5");
        jTextFieldN1.setPreferredSize(new Dimension(40, 20));
        controlPanel.add(jTextFieldN1);
        JLabel jLabelN2 = new JLabel("Рабочие");
        jLabelN2.setPreferredSize(new Dimension(50, 20));
        controlPanel.add(jLabelN2);
        jTextFieldN2 = new JTextField("3");
        jTextFieldN2.setPreferredSize(new Dimension(40, 20));
        controlPanel.add(jTextFieldN2);
        JLabel jLabelInfo = new JLabel("Прочая информация:");
        controlPanel.add(jLabelInfo);
        JLabel jLabelP = new JLabel("P рабочих");
        jLabelP.setPreferredSize(new Dimension(60, 20));
        controlPanel.add(jLabelP);
        jComboBoxP = new JComboBox(items);
        jComboBoxP.setPreferredSize(new Dimension(60, 20));
        controlPanel.add(jComboBoxP);
        JLabel jLabelK = new JLabel("% трутней");
        jLabelK.setPreferredSize(new Dimension(60, 20));
        controlPanel.add(jLabelK);
        jComboBoxK = new JComboBox(items);
        jComboBoxK.setPreferredSize(new Dimension(60, 20));
        controlPanel.add(jComboBoxK);

        //панель визуализации
        visualPanel.setPreferredSize(new Dimension(dimensionFirstFrame.width - controlSize.width, dimensionFirstFrame.height));
        visualPanel.setLayout(new FlowLayout());
        visualPanel.setBackground(new Color(148, 227, 36));
        add(visualPanel);

        //Меню
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(createMenuFile());
        jMenuBar.setSize(200, 200);
        jMenuBar.setVisible(true);
        setJMenuBar(jMenuBar);

        addKeyListener(this);
        jTextFieldN1.addKeyListener(this);
        jTextFieldN2.addKeyListener(this);
        jComboBoxP.addKeyListener(this);
        jComboBoxK.addKeyListener(this);

        timeLabel = new JLabel(" ", SwingConstants.CENTER);
        add(timeLabel, BorderLayout.NORTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
        setFocusable(true);
    }

    public void beesDraw(){
        visualPanel.paintBee();
    }

    public void updateTime(int time){
        this.time = time;
        timeLabel.setText(time / 60 + ":" + time % 60);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_B:
                jComboBoxP.setEnabled(false);
                buttonBegin.setEnabled(false);
                buttonStop.setEnabled(true);
                jTextFieldN1.setEditable(false);
                jTextFieldN2.setEditable(false);
                jComboBoxK.setEnabled(false);
                jMenuItemBegin.setEnabled(false);
                jMenuItemStop.setEnabled(true);
                habitat.startBorn();
                this.requestFocus();
                break;
            case KeyEvent.VK_E:
                if (isShowInfo){
                    habitat.pauseTimer();
                    createDialogWindow();
                    if (isContinue){
                        habitat.startBorn();
                        break;
                    }
                }
                habitat.stopBorn();
                jComboBoxP.setEnabled(true);
                buttonBegin.setEnabled(true);
                buttonStop.setEnabled(false);
                jTextFieldN1.setEditable(true);
                jTextFieldN2.setEditable(true);
                jComboBoxK.setEnabled(true);
                jMenuItemBegin.setEnabled(true);
                jMenuItemStop.setEnabled(false);
                BeeWork.countBeeWork = 0;
                BeeBig.countBeeBig = 0;
                Bee.countBees = 0;
                break;
            case KeyEvent.VK_T:
                timeVisible = !timeVisible;
                timeLabel.setVisible(timeVisible);
                buttonGroup.clearSelection();
                jRadioButtonShowTime.setSelected(timeVisible);
                jRadioButtonHideTime.setSelected(!timeVisible);
                buttonGroupMenu.clearSelection();
                jRadioButtonMenuItemShowTime.setSelected(timeVisible);
                jRadioButtonMenuItemHideTime.setSelected(!timeVisible);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void createDialogWindow(){
        JDialog jDialog = new JDialog(this, "Result", true);
        JPanel jPanelDialog = new JPanel();
        jPanelDialog.setLayout(new BoxLayout(jPanelDialog, BoxLayout.Y_AXIS));
        jDialog.setContentPane(jPanelDialog);

        Font font = new Font("Courier", Font.BOLD, 15);
        Font font1 = new Font("Courier", Font.ITALIC, 15);
        JLabel jLabelBees = new JLabel("Bees: " + Bee.countBees, SwingConstants.CENTER);
        jLabelBees.setFont(font);
        jPanelDialog.add(jLabelBees);
        JLabel jLabelBeeWork = new JLabel("Work bees: " + BeeWork.countBeeWork, SwingConstants.CENTER);
        jLabelBeeWork.setFont(font1);
        JLabel jLabelBeeBig = new JLabel("Big bees: " + BeeBig.countBeeBig, SwingConstants.CENTER);
        jLabelBeeBig.setForeground(Color.ORANGE);
        jPanelDialog.add(jLabelBeeWork);
        jPanelDialog.add(jLabelBeeBig);
        jPanelDialog.add(new JLabel("Time: " + time / 60 + ":" + time % 60));
        JButton jButtonOk = new JButton("ОК");
        jButtonOk.setFocusable(false);
        jButtonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isContinue = false;
                jDialog.setVisible(false);
                jDialog.dispose();
            }
        });
        jPanelDialog.add(jButtonOk);
        JButton jButtonCancel = new JButton("Отмена");
        jButtonCancel.setFocusable(false);
        jButtonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isContinue = true;
                jDialog.setVisible(false);
                jDialog.dispose();
            }
        });
        jPanelDialog.add(jButtonCancel);

        jDialog.setDefaultCloseOperation(HIDE_ON_CLOSE);
        jDialog.setPreferredSize(new Dimension(150, 170));
        jDialog.setResizable(false);
        jDialog.pack();
        jDialog.setLocationRelativeTo(this);
        jDialog.setVisible(true);
    }

    public int getVisualPanelWidth(){
        return visualPanel.getWidth();
    }

    public int getVisualPanelHeight(){
        return visualPanel.getHeight();
    }

    private JMenu createMenuFile(){
        JMenu file = new JMenu("Настройки");
        jMenuItemBegin = new JMenuItem("Начать");
        JMenuItem exit = new JMenuItem(new ExitAction());
        jMenuItemStop = new JMenuItem("Стоп");
        jMenuItemStop.setEnabled(false);
        jCheckBoxMenuItemShowInfo = new JCheckBoxMenuItem("Показ информации");
        buttonGroupMenu = new ButtonGroup();
        jRadioButtonMenuItemShowTime = new JRadioButtonMenuItem("Показать время");
        jRadioButtonMenuItemHideTime = new JRadioButtonMenuItem("Скрыть время");
        buttonGroupMenu.add(jRadioButtonMenuItemShowTime);
        buttonGroupMenu.add(jRadioButtonMenuItemHideTime);
        jRadioButtonMenuItemShowTime.setSelected(true);
        file.add(jMenuItemBegin);
        file.add(jMenuItemStop);
        file.add(jCheckBoxMenuItemShowInfo);
        file.add(jRadioButtonMenuItemShowTime);
        file.add(jRadioButtonMenuItemHideTime);
        file.add(exit);


        //Разобрать
        jMenuItemBegin.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                buttonBegin.setEnabled(false);
                buttonStop.setEnabled(true);
                jMenuItemBegin.setEnabled(false);
                jMenuItemStop.setEnabled(true);
                jTextFieldN1.setEditable(false);
                jTextFieldN2.setEditable(false);
                jComboBoxK.setEnabled(false);
                jComboBoxP.setEnabled(false);
                habitat.startBorn();
            }
        });

        jMenuItemStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isShowInfo){
                    habitat.pauseTimer();
                    createDialogWindow();
                    if (isContinue){
                        habitat.startBorn();
                        return;
                    }
                }
                habitat.stopBorn();
                buttonBegin.setEnabled(true);
                buttonStop.setEnabled(false);
                jMenuItemBegin.setEnabled(true);
                jMenuItemStop.setEnabled(false);
                jTextFieldN1.setEditable(true);
                jTextFieldN2.setEditable(true);
                jComboBoxK.setEnabled(true);
                jComboBoxP.setEnabled(true);
                BeeWork.countBeeWork = 0;
                BeeBig.countBeeBig = 0;
                Bee.countBees = 0;
            }
        });

        jCheckBoxMenuItemShowInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jCheckBoxShowInfo.setSelected(!isShowInfo);
            }
        });

        jRadioButtonMenuItemShowTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeVisible = !timeVisible;
                timeLabel.setVisible(timeVisible);
                buttonGroup.clearSelection();
                jRadioButtonShowTime.setSelected(timeVisible);
                jRadioButtonHideTime.setSelected(!timeVisible);
            }
        });

        jRadioButtonMenuItemHideTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeVisible = !timeVisible;
                timeLabel.setVisible(timeVisible);
                buttonGroup.clearSelection();
                jRadioButtonShowTime.setSelected(timeVisible);
                jRadioButtonHideTime.setSelected(!timeVisible);
            }
        });

        return file;
    }

    class ExitAction extends AbstractAction{
        ExitAction(){
            putValue(NAME, "Выход");
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    private JMenu createViewMenu(){
        JMenu viewMenu = new JMenu("Вид");
        return viewMenu;
    }

    class BeginActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            buttonBegin.setEnabled(false);
            buttonStop.setEnabled(true);
            jTextFieldN1.setEditable(false);
            jTextFieldN2.setEditable(false);
            jComboBoxK.setEnabled(false);
            jMenuItemBegin.setEnabled(false);
            jMenuItemStop.setEnabled(true);
            jComboBoxP.setEnabled(false);
            habitat.startBorn();
        }
    }

    class StopActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isShowInfo){
                habitat.pauseTimer();
                createDialogWindow();
                if (isContinue){
                    habitat.startBorn();
                    return;
                }
            }
            habitat.stopBorn();
            buttonBegin.setEnabled(true);
            buttonStop.setEnabled(false);
            jTextFieldN1.setEditable(true);
            jTextFieldN2.setEditable(true);
            jComboBoxK.setEnabled(true);
            jMenuItemBegin.setEnabled(true);
            jMenuItemStop.setEnabled(false);
            jComboBoxP.setEnabled(true);
            BeeWork.countBeeWork = 0;
            BeeBig.countBeeBig = 0;
            Bee.countBees = 0;
        }
    }

    class ShowTimeActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            timeVisible = !timeVisible;
            timeLabel.setVisible(timeVisible);
            buttonGroupMenu.clearSelection();
            jRadioButtonMenuItemShowTime.setSelected(timeVisible);
            jRadioButtonMenuItemHideTime.setSelected(!timeVisible);
        }
    }

    class HideTimeActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            timeVisible = !timeVisible;
            timeLabel.setVisible(timeVisible);
            buttonGroupMenu.clearSelection();
            jRadioButtonMenuItemShowTime.setSelected(timeVisible);
            jRadioButtonMenuItemHideTime.setSelected(!timeVisible);
        }
    }

    class ShowInfoItemListener implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent e) {
            isShowInfo = !isShowInfo;
            jCheckBoxMenuItemShowInfo.setState(isShowInfo);
        }
    }

    public int getN1(){
        try{
            return Integer.valueOf(jTextFieldN1.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            createError();
            jTextFieldN1.setText("5");
        }
        return Integer.valueOf(jTextFieldN1.getText());
    }

    public int getN2(){
        try{
            return Integer.valueOf(jTextFieldN2.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            createError();
            jTextFieldN2.setText("3");
        }
        return Integer.valueOf(jTextFieldN2.getText());
    }

    public double getP(){
        return ((double)(jComboBoxP.getSelectedIndex() + 1)) / 10;
    }

    public double getK(){
        return ((double)(jComboBoxK.getSelectedIndex() + 1)) / 10;
    }

    public void createError(){
        JDialog jDialogError = new JDialog(this, "Error!", true);
        JPanel jPanelError = new JPanel();
        jPanelError.setLayout(new BorderLayout());
        JLabel jLabelError = new JLabel("<html>Неправильный формат данных!<Br>Выставлены значения по умолчанию.</html>");
        jPanelError.add(jLabelError, BorderLayout.CENTER);
        jDialogError.add(jPanelError);

        jDialogError.setDefaultCloseOperation(HIDE_ON_CLOSE);
        jDialogError.setPreferredSize(new Dimension(300, 100));
        jDialogError.setResizable(false);
        jDialogError.pack();
        jDialogError.setLocationRelativeTo(this);
        jDialogError.setVisible(true);
    }
}