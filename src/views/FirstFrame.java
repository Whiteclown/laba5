package views;

import Bees.Bee;
import Bees.BeeBig;
import Bees.BeeWork;
import Habitat.Habitat;
import Habitat.SingletonTimeBorn;
import Habitat.SingletonID;
import Habitat.SingletonObjects;
import Habitat.BeeWorkAI;
import Habitat.BeeBigAI;
import Habitat.BaseAI;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Map;
import java.util.Set;

public class FirstFrame extends JFrame implements KeyListener {
    VisualPanel visualPanel;
    BeeWorkAI beeWorkAI;
    BeeBigAI beeBigAI;
    ControlPanel controlPanel;
    Habitat habitat;
    JLabel timeLabel;
    JButton buttonBegin;
    JButton buttonStop;
    JButton buttonConsole;
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
    JTextField jTextFieldTimeOfLifeWork;
    JTextField jTextFieldTimeOfLifeBig;
    JButton buttonCurrentObjects;
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
    boolean threadWorkTurningOn = true;
    boolean threadBigTurningOn = true;
    JCheckBox checkThreadWork, checkThreadBig;
    JLabel priorityWorkThread, priorityBigThread;
    JComboBox inputPriorityWorkThread, inputPriorityBigThread;
    JMenuItem saveMenuItem, loadMenuItem;
    private JFileChooser fileChooser;

    public FirstFrame(){
        habitat = new Habitat(5,3, 0.8, 0.5, this);
        visualPanel = new VisualPanel();
        controlPanel = new ControlPanel();
        beeWorkAI = new BeeWorkAI("Workers", visualPanel);
        beeBigAI = new BeeBigAI("Bigers", visualPanel);
        BeginActionListener beginActionListener = new BeginActionListener();
        StopActionListener stopActionListener = new StopActionListener();
        ShowTimeActionListener showTimeActionListener = new ShowTimeActionListener();
        HideTimeActionListener hideTimeActionListener = new HideTimeActionListener();
        ShowInfoItemListener showInfoItemListener = new ShowInfoItemListener();
        CurrentObjectsActionListener currentObjectsActionListener = new CurrentObjectsActionListener();
        CheckThreadWorkListener checkThreadWorkListener = new CheckThreadWorkListener();
        CheckThreadBigListener checkThreadBigListener = new CheckThreadBigListener();
        CreateConsoleListener createConsoleListener = new CreateConsoleListener();

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
        jTextFieldN1 = new JTextField("2");
        jTextFieldN1.setPreferredSize(new Dimension(40, 20));
        controlPanel.add(jTextFieldN1);
        JLabel jLabelN2 = new JLabel("Рабочие");
        jLabelN2.setPreferredSize(new Dimension(50, 20));
        controlPanel.add(jLabelN2);
        jTextFieldN2 = new JTextField("2");
        jTextFieldN2.setPreferredSize(new Dimension(40, 20));
        controlPanel.add(jTextFieldN2);
        JLabel jLabelInfo = new JLabel("Прочая информация:");
        controlPanel.add(jLabelInfo);
        JLabel jLabelP = new JLabel("P рабочих");
        jLabelP.setPreferredSize(new Dimension(60, 20));
        controlPanel.add(jLabelP);
        jComboBoxP = new JComboBox(items);
        jComboBoxP.setSelectedIndex(7);
        jComboBoxP.setPreferredSize(new Dimension(60, 20));
        controlPanel.add(jComboBoxP);
        JLabel jLabelK = new JLabel("% трутней");
        jLabelK.setPreferredSize(new Dimension(60, 20));
        controlPanel.add(jLabelK);
        jComboBoxK = new JComboBox(items);
        jComboBoxK.setSelectedIndex(4);
        jComboBoxK.setPreferredSize(new Dimension(60, 20));
        controlPanel.add(jComboBoxK);
        //добавил
        JLabel jLabelTimeOfLife = new JLabel("Время жизни:");
        jLabelTimeOfLife.setPreferredSize(new Dimension(100, 20));
        controlPanel.add(jLabelTimeOfLife);
        JLabel jLabelTimeOfLifeWork = new JLabel("Рабочих:");
        jLabelTimeOfLifeWork.setPreferredSize(new Dimension(60, 20));
        controlPanel.add(jLabelTimeOfLifeWork);
        jTextFieldTimeOfLifeWork = new JTextField("6");
        jTextFieldTimeOfLifeWork.setPreferredSize(new Dimension(40,20));
        controlPanel.add(jTextFieldTimeOfLifeWork);
        JLabel jLabelTimeOfLifeBig = new JLabel("Трутней:");
        jLabelTimeOfLifeBig.setPreferredSize(new Dimension(60, 20));
        controlPanel.add(jLabelTimeOfLifeBig);
        jTextFieldTimeOfLifeBig = new JTextField("5");
        jTextFieldTimeOfLifeBig.setPreferredSize(new Dimension(40,20));
        controlPanel.add(jTextFieldTimeOfLifeBig);
        //knopka
        buttonCurrentObjects = new JButton("Текущие объекты");
        buttonCurrentObjects.addActionListener(currentObjectsActionListener);
        buttonCurrentObjects.setFocusable(false);
        buttonCurrentObjects.setEnabled(false);
        controlPanel.add(buttonCurrentObjects);
        //threads
        checkThreadWork = new JCheckBox("Рабочие", true);
        checkThreadWork.setFocusable(false);
        checkThreadWork.addActionListener(checkThreadWorkListener);
        checkThreadBig = new JCheckBox("Трутни", true);
        checkThreadBig.setFocusable(false);
        checkThreadBig.addActionListener(checkThreadBigListener);
        controlPanel.add(checkThreadWork);
        controlPanel.add(checkThreadBig);
        priorityWorkThread = new JLabel("Приоритет Р.");
        priorityWorkThread.setFocusable(false);
        inputPriorityWorkThread = new JComboBox(items);
        inputPriorityWorkThread.setFocusable(false);
        inputPriorityWorkThread.setSelectedIndex(4);
        controlPanel.add(priorityWorkThread);
        controlPanel.add(inputPriorityWorkThread);
        priorityBigThread = new JLabel("Приоритет Т.");
        priorityBigThread.setFocusable(false);
        inputPriorityBigThread = new JComboBox(items);
        inputPriorityBigThread.setFocusable(false);
        inputPriorityBigThread.setSelectedIndex(4);
        controlPanel.add(priorityBigThread);
        controlPanel.add(inputPriorityBigThread);
        buttonConsole = new JButton("Консоль");
        buttonConsole.setFocusable(false);
        buttonConsole.setEnabled(true);
        buttonConsole.addActionListener(createConsoleListener);
        controlPanel.add(buttonConsole);

        //панель визуализации
        visualPanel.setPreferredSize(new Dimension(dimensionFirstFrame.width - controlSize.width, dimensionFirstFrame.height));
        visualPanel.setLayout(new FlowLayout());
        visualPanel.setBackground(new Color(148, 227, 36));
        add(visualPanel);

        //Меню
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(createMenuFile());
        jMenuBar.add(createFileMenu());
        jMenuBar.setSize(200, 200);
        jMenuBar.setVisible(true);
        setJMenuBar(jMenuBar);

        try {
            FileReader fileReader = new FileReader("src/Properties.txt");
            BufferedReader reader = new BufferedReader(fileReader);

            jTextFieldN1 = new JTextField("" + Integer.parseInt(reader.readLine()));
            jTextFieldN2 = new JTextField("" + Integer.parseInt(reader.readLine()));
            jTextFieldTimeOfLifeWork = new JTextField("" + Integer.parseInt(reader.readLine()));
            jTextFieldTimeOfLifeBig = new JTextField("" + Integer.parseInt(reader.readLine()));
            jComboBoxP.setSelectedIndex(Integer.parseInt(reader.readLine()) / 10);
            jComboBoxK.setSelectedIndex(Integer.parseInt(reader.readLine()) / 10);

            reader.close();
        }
        catch (IOException e1) { e1.printStackTrace(); }

        addKeyListener(this);
        jTextFieldN1.addKeyListener(this);
        jTextFieldN2.addKeyListener(this);
        jTextFieldTimeOfLifeWork.addKeyListener(this);
        jTextFieldTimeOfLifeBig.addKeyListener(this);
        jComboBoxP.addKeyListener(this);
        jComboBoxK.addKeyListener(this);

        inputPriorityWorkThread.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                beeWorkAI.setPriority(inputPriorityWorkThread.getSelectedIndex());
            }
        });
        inputPriorityBigThread.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                beeBigAI.setPriority(inputPriorityBigThread.getSelectedIndex());
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                BufferedWriter writer = null;
                try {
                    writer = new BufferedWriter(new FileWriter("src/Properties.txt"));
                    writer.write("" + jTextFieldN1.getText());
                    writer.newLine();
                    writer.write("" + jTextFieldN2.getText());
                    writer.newLine();
                    writer.write("" + jTextFieldTimeOfLifeWork.getText());
                    writer.newLine();
                    writer.write("" + jTextFieldTimeOfLifeBig.getText());
                    writer.newLine();
                    writer.write("" + jComboBoxP.getSelectedIndex() * 10);
                    writer.newLine();
                    writer.write("" + jComboBoxK.getSelectedIndex() * 10);
                    writer.newLine();
                    writer.flush();
                    writer.close();
                }
                catch (IOException ex) { ex.printStackTrace(); }
                System.exit(0);
            }
        });

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
                jTextFieldTimeOfLifeWork.setEditable(false);
                jTextFieldTimeOfLifeBig.setEditable(false);
                jComboBoxK.setEnabled(false);
                jMenuItemBegin.setEnabled(false);
                jMenuItemStop.setEnabled(true);
                buttonCurrentObjects.setEnabled(true);
                BaseAI.movement = true;
                beeWorkAI = new BeeWorkAI("Workers", visualPanel);
                beeBigAI = new BeeBigAI("Bigers", visualPanel);
                habitat.startBorn();
                this.requestFocus();
                break;
            case KeyEvent.VK_E:
                if (isShowInfo){
                    BaseAI.movement = false;
                    habitat.pauseTimer();
                    createDialogWindow();
                    if (isContinue){
                        BaseAI.movement = true;
                        beeWorkAI = new BeeWorkAI("Workers", visualPanel);
                        beeBigAI = new BeeBigAI("Bigers", visualPanel);
                        habitat.startBorn();
                        break;
                    }
                }
                BaseAI.movement = false;
                habitat.stopBorn();
                jComboBoxP.setEnabled(true);
                buttonBegin.setEnabled(true);
                buttonStop.setEnabled(false);
                jTextFieldN1.setEditable(true);
                jTextFieldN2.setEditable(true);
                jTextFieldTimeOfLifeWork.setEditable(true);
                jTextFieldTimeOfLifeBig.setEditable(true);
                jComboBoxK.setEnabled(true);
                jMenuItemBegin.setEnabled(true);
                jMenuItemStop.setEnabled(false);
                buttonCurrentObjects.setEnabled(false);
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
                jTextFieldTimeOfLifeWork.setEditable(false);
                jTextFieldTimeOfLifeBig.setEditable(false);
                jComboBoxK.setEnabled(false);
                jComboBoxP.setEnabled(false);
                buttonCurrentObjects.setEnabled(true);
                BaseAI.movement = true;
                beeWorkAI = new BeeWorkAI("Workers", visualPanel);
                beeBigAI = new BeeBigAI("Bigers", visualPanel);
                habitat.startBorn();
            }
        });

        jMenuItemStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isShowInfo){
                    BaseAI.movement = false;
                    habitat.pauseTimer();
                    createDialogWindow();
                    if (isContinue){
                        BaseAI.movement = true;
                        beeWorkAI = new BeeWorkAI("Workers", visualPanel);
                        beeBigAI = new BeeBigAI("Bigers", visualPanel);
                        habitat.startBorn();
                        return;
                    }
                }
                BaseAI.movement = false;
                habitat.stopBorn();
                buttonBegin.setEnabled(true);
                buttonStop.setEnabled(false);
                jMenuItemBegin.setEnabled(true);
                jMenuItemStop.setEnabled(false);
                jTextFieldN1.setEditable(true);
                jTextFieldN2.setEditable(true);
                jTextFieldTimeOfLifeWork.setEditable(true);
                jTextFieldTimeOfLifeBig.setEditable(true);
                jComboBoxK.setEnabled(true);
                jComboBoxP.setEnabled(true);
                buttonCurrentObjects.setEnabled(false);
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
            jTextFieldTimeOfLifeWork.setEditable(false);
            jTextFieldTimeOfLifeBig.setEditable(false);
            jComboBoxK.setEnabled(false);
            jMenuItemBegin.setEnabled(false);
            jMenuItemStop.setEnabled(true);
            jComboBoxP.setEnabled(false);
            buttonCurrentObjects.setEnabled(true);
            BaseAI.movement = true;
            beeWorkAI = new BeeWorkAI("Workers", visualPanel);
            beeBigAI = new BeeBigAI("Bigers", visualPanel);
            habitat.startBorn();
        }
    }

    class CreateConsoleListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            createConsole();
        }
    }

    class CheckThreadWorkListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            checkThreadWork.setSelected(threadWorkTurningOn = !threadWorkTurningOn);
            if(threadWorkTurningOn) {
                BeeWorkAI.waiting = false;
                beeWorkAI.continueThread();
            }
            else BeeWorkAI.waiting = true;
        }
    }

    class CheckThreadBigListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            checkThreadBig.setSelected(threadBigTurningOn = !threadBigTurningOn);
            if(threadBigTurningOn) {
                BeeBigAI.waiting = false;
                beeBigAI.continueThread();
            }
            else BeeBigAI.waiting = true;
        }
    }

    class StopActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isShowInfo){
                BaseAI.movement = false;
                habitat.pauseTimer();
                createDialogWindow();
                if (isContinue){
                    BaseAI.movement = true;
                    beeWorkAI = new BeeWorkAI("Workers", visualPanel);
                    beeBigAI = new BeeBigAI("Bigers", visualPanel);
                    habitat.startBorn();
                    return;
                }
            }
            BaseAI.movement = false;
            habitat.stopBorn();
            buttonBegin.setEnabled(true);
            buttonStop.setEnabled(false);
            jTextFieldN1.setEditable(true);
            jTextFieldN2.setEditable(true);
            jTextFieldTimeOfLifeBig.setEditable(true);
            jTextFieldTimeOfLifeWork.setEditable(true);
            jComboBoxK.setEnabled(true);
            jMenuItemBegin.setEnabled(true);
            jMenuItemStop.setEnabled(false);
            jComboBoxP.setEnabled(true);
            buttonCurrentObjects.setEnabled(false);
            BeeWork.countBeeWork = 0;
            BeeBig.countBeeBig = 0;
            Bee.countBees = 0;
        }
    }

    class CurrentObjectsActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            habitat.pauseTimer();
            createCurrentObjectsDialog();
            habitat.startBorn();
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

    public int getTimeOfLifeWork(){
        try{
            return Integer.valueOf(jTextFieldTimeOfLifeWork.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            createError();
            jTextFieldTimeOfLifeWork.setText("3");
        }
        return Integer.valueOf(jTextFieldTimeOfLifeWork.getText());
    }

    public int getTimeOfLifeBig(){
        try{
            return Integer.valueOf(jTextFieldTimeOfLifeBig.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            createError();
            jTextFieldTimeOfLifeBig.setText("5");
        }
        return Integer.valueOf(jTextFieldTimeOfLifeBig.getText());
    }

    public void createCurrentObjectsDialog() {
        String text = "";
        JDialog jDialog = new JDialog(this, "Текущие объекты", true);
        jDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        jDialog.setLayout(new FlowLayout());
        jDialog.setPreferredSize(new Dimension(350, 200));
        jDialog.setResizable(false);

        JTextPane jTextPane = new JTextPane();
        jTextPane.setFocusable(false);
        jTextPane.setEditable(false);
        jTextPane.setBackground(getContentPane().getBackground());
        jTextPane.setFont(new Font("TimesRoman", Font.PLAIN, 14));

        if (SingletonTimeBorn.beesMap.isEmpty()) {
            jTextPane.setText("\n\n\nНет ни одного объекта");
        }
        else {
            Set set = SingletonTimeBorn.beesMap.entrySet();
            for (Object o : set) {
                Map.Entry e = (Map.Entry) o;
                text = text + "ID: " + e.getKey() + " Время рождения: " + e.getValue() + " ";
            }
            jTextPane.setText(text);
        }

        JScrollPane jScrollPane = new JScrollPane(jTextPane);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setPreferredSize(new Dimension(310, 150));
        jScrollPane.setBorder(BorderFactory.createLineBorder(getBackground()));

        jDialog.add(jScrollPane);
        jDialog.pack();
        jDialog.setLocationRelativeTo(this);
        jDialog.setVisible(true);
    }

    public void createConsole(){
        JDialog jDialogConsole = new JDialog(this, "Console", true);
        JPanel jPanelConsole = new JPanel();
        jPanelConsole.setLayout(new BorderLayout());
        JTextArea jTextAreaConsole = new JTextArea();
        jTextAreaConsole.setBackground(Color.BLACK);
        jTextAreaConsole.setForeground(Color.WHITE);
        jTextAreaConsole.setPreferredSize(new Dimension(300, 300));
        jPanelConsole.add(jTextAreaConsole);
        jDialogConsole.add(jPanelConsole);
        jTextAreaConsole.addKeyListener(new KeyAdapter() {
            @Override
            synchronized public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    try {
                    PipedOutputStream pipedOutputStream = new PipedOutputStream();
                    PipedInputStream pipedInputStream = new PipedInputStream(pipedOutputStream);
                    Runnable runOut = new Runnable() {
                        @Override
                        public void run() {
                            int offset = 0;
                            try {
                                offset = jTextAreaConsole.getLineOfOffset(jTextAreaConsole.getCaretPosition());
                                int start = jTextAreaConsole.getLineStartOffset(offset);
                                int end = jTextAreaConsole.getLineEndOffset(offset);
                                String command = jTextAreaConsole.getText(start, end - start);
                                pipedOutputStream.write(command.getBytes());
                            }
                            catch (BadLocationException ex) { ex.printStackTrace(); }
                            catch (IOException ex) { ex.printStackTrace(); }
                        }
                    };
                    Thread outThread = new Thread(runOut);
                    outThread.start();

                    Runnable runIn = new Runnable() {
                        @Override
                        public void run() {
                            byte[] bytes = new byte[100];
                            try { pipedInputStream.read(bytes); }
                            catch (IOException ex) { ex.printStackTrace(); }

                            String command = new String(bytes);
                            System.out.println(command);
                            String returnCount = "Количество";
                            String parWork = "рабочих";
                            String parBig = "трутней";
                            if (command.contains(returnCount) && (command.contains(parWork))) {
                                jTextAreaConsole.append("\n" + "Work bees: " + BeeWork.countBeeWork);
                            }
                            else if (command.contains(returnCount) && (command.contains(parBig))) {
                                jTextAreaConsole.append("\n" + "Big bees: " + BeeBig.countBeeBig);
                            }
                            else { jTextAreaConsole.append("\nДанной команды не существует. Повторите ввод!"); }

                            jTextAreaConsole.setCaretPosition(jTextAreaConsole.getText().length());
                        }
                    };
                    Thread inThread = new Thread(runIn);
                    inThread.start();

                    try {
                        Thread.sleep(50);
                        pipedOutputStream.close();
                        pipedInputStream.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }
        }
    });

        jDialogConsole.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        jDialogConsole.setPreferredSize(new Dimension(300, 300));
        jDialogConsole.setResizable(false);
        jDialogConsole.pack();
        jDialogConsole.setLocationRelativeTo(this);
        jDialogConsole.setVisible(true);
    }

    private JMenu createFileMenu() {
        JMenu jMenu = new JMenu("Файл");
        saveMenuItem = new JMenuItem("Сохранить");
        loadMenuItem = new JMenuItem("Загрузить");

        saveMenuItem.setEnabled(true);
        loadMenuItem.setEnabled(true);

        saveMenuItem.addActionListener(e -> {
            BeeWorkAI.waiting = true;
            BeeBigAI.waiting = true;
            habitat.pauseTimer();

            fileChooser = new JFileChooser("src");
            fileChooser.setDialogTitle("Сохранение файла");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if(fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){
                habitat.saveFile(fileChooser.getSelectedFile());
            }
            habitat.stopBorn();

            BeeWorkAI.waiting = false;
            BeeBigAI.waiting = false;

            buttonBegin.setEnabled(true);
            buttonStop.setEnabled(false);
            jTextFieldN1.setEditable(true);
            jTextFieldN2.setEditable(true);
            jTextFieldTimeOfLifeBig.setEditable(true);
            jTextFieldTimeOfLifeWork.setEditable(true);
            jComboBoxK.setEnabled(true);
            jMenuItemBegin.setEnabled(true);
            jMenuItemStop.setEnabled(false);
            jComboBoxP.setEnabled(true);
            buttonCurrentObjects.setEnabled(false);
        });
        loadMenuItem.addActionListener(e -> {
            if(time != 0) {
                BeeWorkAI.waiting = true;
                BeeBigAI.waiting = true;
                habitat.stopBorn();

                fileChooser = new JFileChooser("src");
                fileChooser.setDialogTitle("Загрузка файла");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                if(fileChooser.showDialog(this, "Load") == JFileChooser.APPROVE_OPTION) {
                    habitat.loadFile(fileChooser.getSelectedFile());
                }

                beeWorkAI.waiting = false;
                beeBigAI.waiting = false;

                buttonBegin.setEnabled(true);
                buttonStop.setEnabled(false);
                jTextFieldN1.setEditable(true);
                jTextFieldN2.setEditable(true);
                jTextFieldTimeOfLifeBig.setEditable(true);
                jTextFieldTimeOfLifeWork.setEditable(true);
                jComboBoxK.setEnabled(true);
                jMenuItemBegin.setEnabled(true);
                jMenuItemStop.setEnabled(false);
                jComboBoxP.setEnabled(true);
                buttonCurrentObjects.setEnabled(false);
            }
            else {
                fileChooser = new JFileChooser("src");
                fileChooser.setDialogTitle("Загрузка файла");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                if(fileChooser.showDialog(this, "Load") == JFileChooser.APPROVE_OPTION){
                    habitat.loadFile(fileChooser.getSelectedFile());
                }
            }
        });

        jMenu.add(saveMenuItem);
        jMenu.add(loadMenuItem);
        return jMenu;
    }

}