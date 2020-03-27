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
    JRadioButton jRadioButtonShowTime;
    JRadioButton jRadioButtonHideTime;
    JTextField jTextFieldN1;
    JTextField jTextFieldN2;
    JTextField jTextFieldP;
    JTextField jTextFieldK;
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
        JCheckBox jCheckBoxShowInfo = new JCheckBox("Итоговая информация");
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
        jTextFieldP = new JTextField("0.8");
        jTextFieldP.setPreferredSize(new Dimension(40, 20));
        controlPanel.add(jTextFieldP);
        JLabel jLabelK = new JLabel("% трутней");
        jLabelK.setPreferredSize(new Dimension(60, 20));
        controlPanel.add(jLabelK);
        jTextFieldK = new JTextField("0.1");
        jTextFieldK.setPreferredSize(new Dimension(40, 20));
        controlPanel.add(jTextFieldK);


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
        jTextFieldP.addKeyListener(this);
        jTextFieldK.addKeyListener(this);

        timeLabel = new JLabel(" ", SwingConstants.CENTER);
        add(timeLabel, BorderLayout.NORTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
        setFocusable(true);
    }

    public void beesDraw(ArrayList<Bee> bees){
        visualPanel.paintBee(bees);
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
                habitat.startBorn();
                buttonBegin.setEnabled(false);
                buttonStop.setEnabled(true);
                jTextFieldN1.setEditable(false);
                jTextFieldN2.setEditable(false);
                jTextFieldP.setEditable(false);
                jTextFieldK.setEditable(false);
                this.requestFocus();
                break;
            case KeyEvent.VK_E:
                habitat.stopBorn();
                buttonBegin.setEnabled(true);
                buttonStop.setEnabled(false);
                jTextFieldN1.setEditable(true);
                jTextFieldN2.setEditable(true);
                jTextFieldP.setEditable(true);
                jTextFieldK.setEditable(true);
                if (isShowInfo){
                    createDialogWindow();
                }
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

        jDialog.setDefaultCloseOperation(HIDE_ON_CLOSE);
        jDialog.setPreferredSize(new Dimension(150, 150));
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
        JMenu file = new JMenu("Файл");
        JMenuItem open = new JMenuItem("Открыть");
        JMenuItem exit = new JMenuItem(new ExitAction());
        file.add(open);
        file.addSeparator();
        file.add(exit);


        //Разобрать
        open.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println ("ActionListener.actionPerformed : open");
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
            habitat.startBorn();
            buttonBegin.setEnabled(false);
            buttonStop.setEnabled(true);
            jTextFieldN1.setEditable(false);
            jTextFieldN2.setEditable(false);
            jTextFieldP.setEditable(false);
            jTextFieldK.setEditable(false);
        }
    }

    class StopActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            habitat.stopBorn();
            buttonBegin.setEnabled(true);
            buttonStop.setEnabled(false);
            jTextFieldN1.setEditable(true);
            jTextFieldN2.setEditable(true);
            jTextFieldP.setEditable(true);
            jTextFieldK.setEditable(true);
            if (isShowInfo){
                createDialogWindow();
            }
            BeeWork.countBeeWork = 0;
            BeeBig.countBeeBig = 0;
            Bee.countBees = 0;
        }
    }

    class ShowTimeActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            timeLabel.setVisible(true);
        }
    }

    class HideTimeActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            timeLabel.setVisible(false);

        }
    }

    class ShowInfoItemListener implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent e) {
            isShowInfo = !isShowInfo;
        }
    }

    public int getN1(){
        return Integer.valueOf(jTextFieldN1.getText());
    }

    public int getN2(){
        return Integer.valueOf(jTextFieldN2.getText());
    }

    public double getP(){
        return Double.valueOf(jTextFieldP.getText());
    }

    public double getK(){
        return Double.valueOf(jTextFieldK.getText());
    }
}
