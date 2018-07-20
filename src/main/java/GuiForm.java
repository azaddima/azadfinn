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
    private JTextField redValue;
    private JTextField greenValue;
    private JTextField blueValue;
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
    private JSlider sizeSlider;
    private JSpinner heightSpinner;
    private JSpinner widthSpinner;
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
    private JButton circleBtn;
    private JButton serverStopButton;
    private JButton clientStopButton;
    private JPanel animationPanel;
    private JPanel animationCenter;
    private JLabel randomColorLabel;
    private JLabel sinusLabel;
    private JCheckBox sinusCheckBox;
    private JCheckBox randomColorCheckBox;
    private JButton deleteBtn;

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
            redValue.setText(( (Color) arg).getRed() + "");
            greenValue.setText(((Color) arg).getGreen() + "");
            blueValue.setText(((Color) arg).getBlue() + "");

        }

        //Inet adress at beginning
        if(arg instanceof InetAddress) {
            System.out.println("Trying to set Inetadress to GUI");
            serverIpLabel.setText(((((InetAddress) arg).getHostAddress())+ ""));
        }

        //Set spinner if form is resized
        if(arg instanceof String){
            String str = (String)arg;

            if(str.contains("width")){

                str = str.replaceAll("\\D+","");

                widthSpinner.setValue(Integer.parseInt(str));

            } else if(str.contains("height")){

                str = str.replaceAll("\\D+","");
                heightSpinner.setValue(Integer.parseInt(str));
            }


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

            redValue.setText(red + "");
            greenValue.setText(green + "");
            blueValue.setText(blue + "");

            xSpinner.setValue(((MyRectangle) o).getX());
            ySpinner.setValue(((MyRectangle) o).getY());

            // Width, Height, and Sizefactor
            widthSpinner.setValue(((MyRectangle) o).getWidth());
            heightSpinner.setValue(((MyRectangle) o).getHeight());
            sizeSlider.setValue(((MyRectangle) o).getSize());

            //Animation
            sinusCheckBox.setSelected(((MyRectangle) o).isSinus());
            randomColorCheckBox.setSelected(((MyRectangle) o).isRandomClr());

        }

        if(o instanceof MyCircle){
            int red = ((MyCircle) o).getR();
            int green = ((MyCircle) o).getG();
            int blue = ((MyCircle) o).getB();

            colorSliderRed.setValue(red);
            colorSliderGreen.setValue(green);
            colorSliderBlue.setValue(blue);

            redValue.setText(red + "");
            greenValue.setText(green + "");
            blueValue.setText(blue + "");

            xSpinner.setValue(((MyCircle) o).getX());
            ySpinner.setValue(((MyCircle) o).getY());

            // Width, Height, and Sizefactor
            widthSpinner.setValue(((MyCircle) o).getWidth());
            heightSpinner.setValue(((MyCircle) o).getHeight());
            sizeSlider.setValue(((MyCircle) o).getSize());

            //Animation
            sinusCheckBox.setSelected(((MyCircle) o).isSinus());
            randomColorCheckBox.setSelected(((MyCircle) o).isRandomClr());
        }


    }

    public void disableWorkSpace(){
        colorSliderRed.setEnabled(false);
        colorSliderGreen.setEnabled(false);
        colorSliderBlue.setEnabled(false);
        redValue.setEnabled(false);
        greenValue.setEnabled(false);
        blueValue.setEnabled(false);
        xSpinner.setEnabled(false);
        ySpinner.setEnabled(false);
        widthSpinner.setEnabled(false);
        heightSpinner.setEnabled(false);
        sizeSlider.setEnabled(false);
        sinusCheckBox.setEnabled(false);
        randomColorCheckBox.setEnabled(false);
    }

    public void enableWorkSpace(){
        colorSliderRed.setEnabled(true);
        colorSliderGreen.setEnabled(true);
        colorSliderBlue.setEnabled(true);
        redValue.setEnabled(true);
        greenValue.setEnabled(true);
        blueValue.setEnabled(true);
        xSpinner.setEnabled(true);
        ySpinner.setEnabled(true);
        widthSpinner.setEnabled(true);
        heightSpinner.setEnabled(true);
        sizeSlider.setEnabled(true);
        sinusCheckBox.setEnabled(true);
        randomColorCheckBox.setEnabled(true);
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

    public JTextField getRedValue() {
        return redValue;
    }

    public void setRedValue(JTextField redValue) {
        this.redValue = redValue;
    }

    public JTextField getGreenValue() {
        return greenValue;
    }

    public void setGreenValue(JTextField greenValue) {
        this.greenValue = greenValue;
    }

    public JTextField getBlueValue() {
        return blueValue;
    }

    public void setBlueValue(JTextField blueValue) {
        this.blueValue = blueValue;
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

    public JSlider getSizeSlider() {
        return sizeSlider;
    }

    public void setSizeSlider(JSlider sizeSlider) {
        this.sizeSlider = sizeSlider;
    }

    public JSpinner getHeightSpinner() {
        return heightSpinner;
    }

    public void setHeightSpinner(JSpinner heightSpinner) {
        this.heightSpinner = heightSpinner;
    }

    public JSpinner getWidthSpinner() {
        return widthSpinner;
    }

    public void setWidthSpinner(JSpinner widthSpinner) {
        this.widthSpinner = widthSpinner;
    }

    public JButton getCircleBtn() {
        return circleBtn;
    }

    public void setCircleBtn(JButton circleBtn) {
        this.circleBtn = circleBtn;
    }
    public JButton getServerStopButton() {
        return serverStopButton;
    }

    public JButton getClientStopButton() {
        return clientStopButton;
    }

    public JCheckBox getSinusCheckBox() {
        return sinusCheckBox;
    }

    public void setSinusCheckBox(JCheckBox sinusCheckBox) {
        this.sinusCheckBox = sinusCheckBox;
    }

    public JCheckBox getRandomColorCheckBox() {
        return randomColorCheckBox;
    }

    public void setRandomColorCheckBox(JCheckBox randomColorCheckBox) {
        this.randomColorCheckBox = randomColorCheckBox;
    }

    public JButton getDeleteBtn() {
        return deleteBtn;
    }

    public void setDeleteBtn(JButton deleteBtn) {
        this.deleteBtn = deleteBtn;
    }

    public JPanel getPositionPanel() {
        return positionPanel;
    }

    public JPanel getDimensionPanel() {
        return dimensionPanel;
    }

    public JPanel getPositionCenter() {
        return positionCenter;
    }

    public JPanel getDimensionCenter() {
        return dimensionCenter;
    }

    public void disableForClient() {
        getClientStartButton().setEnabled(false);
        getClientStopButton().setEnabled(true);
        getClientPortTextField().setEnabled(false);
        getColorSliderBlue().setEnabled(false);
        getColorSliderGreen().setEnabled(false);
        getColorSliderRed().setEnabled(false);
        getRedValue().setEnabled(false);
        getGreenValue().setEnabled(false);
        getBlueValue().setEnabled(false);
        getxSpinner().setEnabled(false);
        getySpinner().setEnabled(false);
        getSizeSlider().setEnabled(false);
        getHeightSpinner().setEnabled(false);
        getWidthSpinner().setEnabled(false);
        getAddRectBtn().setEnabled(false);
        getCircleBtn().setEnabled(false);
        getServerStartButton().setEnabled(false);
        getServerPortTextField().setEnabled(false);
        sinusCheckBox.setEnabled(false);
        randomColorCheckBox.setEnabled(false);
    }

    public void enableForClient() {
        getClientStartButton().setEnabled(true);
        getClientStopButton().setEnabled(false);
        getClientPortTextField().setEnabled(true);
        getColorSliderBlue().setEnabled(true);
        getColorSliderGreen().setEnabled(true);
        getColorSliderRed().setEnabled(true);
        getRedValue().setEnabled(false);
        getGreenValue().setEnabled(false);
        getBlueValue().setEnabled(false);
        getxSpinner().setEnabled(true);
        getySpinner().setEnabled(true);
        getSizeSlider().setEnabled(false);
        getHeightSpinner().setEnabled(false);
        getWidthSpinner().setEnabled(false);
        getAddRectBtn().setEnabled(true);
        getCircleBtn().setEnabled(true);
        getServerStartButton().setEnabled(true);
        getServerPortTextField().setEnabled(true);
        sinusCheckBox.setEnabled(true);
        randomColorCheckBox.setEnabled(true);
    }

    public void disableForServer() {
        getServerStartButton().setEnabled(false);
        getServerStopButton().setEnabled(true);
        getServerPortTextField().setEnabled(false);
        getClientPortTextField().setEnabled(false);
        getClientIpAddressTextField().setEnabled(false);
        getClientStartButton().setEnabled(false);
        getClientStopButton().setEnabled(false);
    }
    public void enableForServer() {
        getServerStartButton().setEnabled(true);
        getServerStopButton().setEnabled(false);
        getServerPortTextField().setEnabled(true);
        getClientPortTextField().setEnabled(true);
        getClientIpAddressTextField().setEnabled(true);
        getClientStartButton().setEnabled(true);
        getClientStopButton().setEnabled(true);
    }



}
