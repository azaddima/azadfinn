import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.net.InetAddress;
import java.util.Observable;
import java.util.Observer;

public class GuiForm extends JFrame implements Observer {

    private JPanel panel1;
    private JButton addRectBtn;
    private JTabbedPane tabBar;
    private JButton stopPntBtn;
    private JSlider colorSliderRed;
    private JSlider colorSliderGreen;
    private JSlider colorSliderBlue;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JPanel colorCenter;
    private JPanel color;
    private JPanel workSpace;
    private JPanel menuPanel;
    private JPanel southPanel;
    private JPanel eastBar;
    private JLabel positionLabel;
    private JSpinner xSpinner;
    private JSpinner ySpinner;
    private JPanel positionPanel;
    private JPanel positionCenter;
    private JPanel dimensionPanel;
    private JPanel dimensionCenter;
    private JPanel networkPanel;
    private JTextField serverPortTextField;
    private JButton serverStartButton;
    private JTextField clientPortTextField;
    private JTextField clientIpAddressTextField;
    private JButton clientStartButton;
    private JSlider slider1;
    private JSpinner spinner1;
    private JSpinner spinner2;
    private JPanel serverPanel;
    private JLabel serverLabel;
    private JLabel portLabel;
    private JLabel serverIpAddressLabel;
    private JLabel serverIpLabel;
    private JPanel serverButtonPanel;
    private JPanel clientPanel;
    private JLabel clientLabel;
    private JLabel clientPortLabel;
    private JLabel clientIpAddressLabel;
    private JPanel clientButtonPanel;
    private JButton serverStopButton;
    private JButton clientStopButton;

    //Canvas
    private PaintArea paintArea = new PaintArea();

    //Menu
    private JMenuItem mntmNew;
    private JMenuItem mntmSave;
    private JMenuItem mntmOpen;


    public GuiForm(){


        setContentPane(panel1);

        JPanel menuPanel = new JPanel();
        menuPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        FlowLayout fl_menuPanel = (FlowLayout) menuPanel.getLayout();
        fl_menuPanel.setAlignment(FlowLayout.LEFT);
        getContentPane().add(menuPanel, BorderLayout.NORTH);

        //MenuBar
        JMenuBar menuBar = new JMenuBar();
        menuPanel.add(menuBar);

        //Canvas
        getContentPane().add(paintArea, BorderLayout.CENTER);


        // JMenu Bar
        JMenu mnFile = new JMenu("File");
        menuBar.add(mnFile);
        mntmNew = new JMenuItem("New");
        mnFile.add(mntmNew);
        mntmSave = new JMenuItem("Save");
        mnFile.add(mntmSave);
        mntmOpen = new JMenuItem("Open");
        mnFile.add(mntmOpen);

        JMenu mnEdit = new JMenu("Edit");
        menuBar.add(mnEdit);
        JMenuItem mntmUndo = new JMenuItem("Undo");
        mnEdit.add(mntmUndo);
        JMenuItem mntmRedo = new JMenuItem("Redo");
        mnEdit.add(mntmRedo);


        setVisible(true);
        setSize(1000,1000);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        new GuiForm();

    }

    @Override
    public void update(Observable o, Object arg) {


        if(arg instanceof Color) {

            // Color text Fields
            textField.setText(( (Color) arg).getRed() + "");
            textField_1.setText(((Color) arg).getGreen() + "");
            textField_2.setText(((Color) arg).getBlue() + "");

        }

        if(arg instanceof InetAddress) {
            System.out.println("hellou");
            serverIpLabel.setText(((((InetAddress) arg).getHostAddress())+ ""));
        }


    }

    public void updateWorkSpace(Object o){
        if(o instanceof MyRectangle){
            int red = ((MyRectangle) o).getR();
            int green = ((MyRectangle) o).getG();
            int blue = ((MyRectangle) o).getB();

            colorSliderRed.setValue(red);
            colorSliderGreen.setValue(green);
            colorSliderBlue.setValue(blue);

            textField.setText(red + "");
            textField_1.setText(green + "");
            textField_2.setText(blue + "");

            xSpinner.setValue(((MyRectangle) o).getX());
            ySpinner.setValue(((MyRectangle) o).getY());

            // Width, Height, and Sizefactor
            spinner2.setValue(((MyRectangle) o).getWidth());
            spinner1.setValue(((MyRectangle) o).getHeight());

        }
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }

    public JButton getAddRectBtn() {
        return addRectBtn;
    }

    public void setAddRectBtn(JButton addRectBtn) {
        this.addRectBtn = addRectBtn;
    }

    public JTabbedPane getTabBar() {
        return tabBar;
    }

    public void setTabBar(JTabbedPane tabBar) {
        this.tabBar = tabBar;
    }

    public JButton getStopPntBtn() {
        return stopPntBtn;
    }

    public void setStopPntBtn(JButton stopPntBtn) {
        this.stopPntBtn = stopPntBtn;
    }

    public JSlider getColorSliderRed() {
        return colorSliderRed;
    }

    public void setColorSliderRed(JSlider colorSliderRed) {
        this.colorSliderRed = colorSliderRed;
    }

    public JSlider getColorSliderGreen() {
        return colorSliderGreen;
    }

    public void setColorSliderGreen(JSlider colorSliderGreen) {
        this.colorSliderGreen = colorSliderGreen;
    }

    public JSlider getColorSliderBlue() {
        return colorSliderBlue;
    }

    public void setColorSliderBlue(JSlider colorSliderBlue) {
        this.colorSliderBlue = colorSliderBlue;
    }

    public JTextField getTextField() {
        return textField;
    }

    public void setTextField(JTextField textField) {
        this.textField = textField;
    }

    public JTextField getTextField_1() {
        return textField_1;
    }

    public void setTextField_1(JTextField textField_1) {
        this.textField_1 = textField_1;
    }

    public JTextField getTextField_2() {
        return textField_2;
    }

    public void setTextField_2(JTextField textField_2) {
        this.textField_2 = textField_2;
    }

    public JPanel getColorCenter() {
        return colorCenter;
    }

    public void setColorCenter(JPanel colorCenter) {
        this.colorCenter = colorCenter;
    }

    public JPanel getColor() {
        return color;
    }

    public void setColor(JPanel color) {
        this.color = color;
    }

    public JPanel getWorkSpace() {
        return workSpace;
    }

    public void setWorkSpace(JPanel workSpace) {
        this.workSpace = workSpace;
    }

    public JPanel getMenuPanel() {
        return menuPanel;
    }

    public void setMenuPanel(JPanel menuPanel) {
        this.menuPanel = menuPanel;
    }

    public JPanel getSouthPanel() {
        return southPanel;
    }

    public void setSouthPanel(JPanel southPanel) {
        this.southPanel = southPanel;
    }

    public PaintArea getPaintArea() {
        return paintArea;
    }

    public void setPaintArea(PaintArea paintArea) {
        this.paintArea = paintArea;
    }

    public JMenuItem getMntmNew() {
        return mntmNew;
    }

    public void setMntmNew(JMenuItem mntmNew) {
        this.mntmNew = mntmNew;
    }

    public JMenuItem getMntmSave() {
        return mntmSave;
    }

    public void setMntmSave(JMenuItem mntmSave) {
        this.mntmSave = mntmSave;
    }

    public JMenuItem getMntmOpen() {
        return mntmOpen;
    }

    public void setMntmOpen(JMenuItem mntmOpen) {
        this.mntmOpen = mntmOpen;
    }


    public JSpinner getxSpinner() {
        return xSpinner;
    }

    public void setxSpinner(JSpinner xSpinner) {
        this.xSpinner = xSpinner;
    }

    public JSpinner getySpinner() {
        return ySpinner;
    }

    public void setySpinner(JSpinner ySpinner) {
        this.ySpinner = ySpinner;
    }

    public JTextField getServerPortTextField() {
        return serverPortTextField;
    }

    public JButton getServerStartButton() {
        return serverStartButton;
    }

    public void setServerIpLabel(JLabel serverIpLabel) {
        this.serverIpLabel = serverIpLabel;
    }

    public JTextField getClientPortTextField() {
        return clientPortTextField;
    }

    public JTextField getClientIpAddressTextField() {
        return clientIpAddressTextField;
    }

    public JButton getClientStartButton() {
        return clientStartButton;
    }

    public JSlider getSlider1() {
        return slider1;
    }

    public void setSlider1(JSlider slider1) {
        this.slider1 = slider1;
    }

    public JSpinner getSpinner1() {
        return spinner1;
    }

    public void setSpinner1(JSpinner spinner1) {
        this.spinner1 = spinner1;
    }

    public JSpinner getSpinner2() {
        return spinner2;
    }

    public void setSpinner2(JSpinner spinner2) {
        this.spinner2 = spinner2;
    }

    public JButton getServerStopButton() {
        return serverStopButton;
    }

    public JButton getClientStopButton() {
        return clientStopButton;
    }
}
