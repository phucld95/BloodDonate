/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import activity.Action;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import object.Hospital;
import object.NhomMau;
import object.R;
import object.User;

/**
 *
 * @author TIENNH
 */
public class GUI extends javax.swing.JFrame {

    /**
     * Creates new form GUI
     */
    //String urlSystem = System.getProperty("user.dir", null);
    Action action = new Action();
    BufferedImage bufferedImage;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Hospital hospital;
    User user;
    DefaultTableModel tableModel = new DefaultTableModel();

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public GUI() {
        initComponents();
        initGUI();
        this.addComponentListener(new ComponentListener() {

            @Override
            public void componentResized(ComponentEvent e) {
                int width = getContentPane().getWidth();
                int height = getContentPane().getHeight();
                pnInfo.setSize(new Dimension(width, height));
            }

            @Override
            public void componentMoved(ComponentEvent e) {
            }

            @Override
            public void componentShown(ComponentEvent e) {
            }

            @Override
            public void componentHidden(ComponentEvent e) {
            }
        });

    }

    @SuppressWarnings("Convert2Lambda")
    public void initGUI() {
        pnInfo = new JPanel();
        pnTable = new JPanel();
        setTitle("Give BLOOD, Give LIFE!");
        pnInfo.setBackground(R.colorBackGround);
        double height = screenSize.getHeight();
        this.setSize(new Dimension((int) (screenSize.getWidth() * 3 / 8), (int) screenSize.getHeight() * 3 / 8));
        spInfo.setViewportView(pnInfo);
        pnInfo.setLayout(null);
        lbUser = new JLabel("User Name : ");
        lbPass = new JLabel("Pass Word : ");
        tfUser = new JTextField("noitiethospital@gmail.com");
        pfPass = new JPasswordField("noitiet");
        btSignIn = new JButton("Log In");
        lbUser.setBounds(R.boundsX, R.heightFrame, R.widthLabel, R.heightLabel);
        lbUser.setFont(new Font("Arial", Font.BOLD, 14));
        tfUser.setBounds(lbUser.getWidth() + R.boundsX, R.heightFrame, R.widthTextField, R.heightLabel);
        R.heightFrame += R.heightLabel + 10;
        lbPass.setBounds(R.boundsX, R.heightFrame, R.widthLabel, R.heightLabel);
        lbPass.setFont(new Font("Arial", Font.BOLD, 14));
        pfPass.setBounds(lbPass.getWidth() + R.boundsX, R.heightFrame, R.widthTextField, R.heightLabel);
        R.heightFrame += R.heightLabel + 10;
        btSignIn.setBounds(R.boundsX + lbPass.getWidth() + pfPass.getWidth() - R.widthButton, R.heightFrame, R.widthButton, R.heightLabel);
        btSignIn.setFont(new Font("Arial", Font.BOLD, 12));
        pnInfo.add(lbUser);
        pnInfo.add(tfUser);
        pnInfo.add(lbPass);
        pnInfo.add(pfPass);
        pnInfo.add(btSignIn);
        btSignIn.addActionListener(new ActionListener() {
            @Override
            @SuppressWarnings("ResultOfMethodCallIgnored")
            public void actionPerformed(ActionEvent e) {
                String request = R.urlCheckHospital + "userName=" + tfUser.getText() + "&passWord=" + pfPass.getText();
                request.replaceAll(" ", "%20");

                hospital = action.singIn(request);
                if (hospital == null) {
                    JOptionPane.showMessageDialog(null, "Log In Fail!");
                } else {
                    resetGUI();
                    controlGUI();

                }
            }
        });
        btSignIn.setBackground(R.color);
    }

    @SuppressWarnings("Convert2Lambda")
    public void controlGUI() {
        pnControl = new JPanel();
        pnControl.setLayout(null);
        pnControl.setBackground(R.colorBackGround);
        this.setSize(new Dimension((int) screenSize.getWidth() / 2, (int) screenSize.getHeight() / 4));
        R.heightFrame = 30;

        lbSelect = new JLabel("Select your type : ");
        lbSelect.setFont(new Font("Arial", Font.BOLD, 12));
        cbSelect = new JComboBox<>();
        cbSelect.addItem("Search");
        cbSelect.addItem("Update User");
        cbSelect.addItem("Update Info");
        cbSelect.setBackground(R.colorBackGround);
        btGo = new JButton("Go");
        btGo.setFont(new Font("Arial", Font.BOLD, 12));

        lbSelect.setBounds(R.boundsX, R.heightFrame, R.widthTextField / 2, R.heightLabel * 3 / 2);
        cbSelect.setBounds(lbSelect.getWidth() + R.boundsX, R.heightFrame + 5, R.widthTextField, R.heightLabel);
        btGo.setBounds(lbSelect.getWidth() + cbSelect.getWidth() + R.heightLabel, R.heightFrame + 5, R.widthButton, R.heightLabel);
        btGo.setBackground(R.color);
        R.heightFrame += lbSelect.getHeight() + 5;

        pnControl.add(lbSelect);
        pnControl.add(cbSelect);
        pnControl.add(btGo);
        spInfo.setViewportView(pnControl);

        btGo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (cbSelect.getSelectedItem().toString()) {
                    case "Search":
                        search();
                        break;
                    case "Update User":
                        setUpdateUser(0, 0);
                        break;
                    case "Update Info":
                        setUpdateHospital();
                        break;

                }
            }
        });

    }

    @SuppressWarnings("Convert2Lambda")
    public void search() {
        resetGUI();
        pnInfo.setLayout(null);
        this.setSize(new Dimension((int) screenSize.getWidth() * 2 / 3, (int) screenSize.getHeight() / 3));
        R.heightFrame = 30;

        lbSearch = new JLabel("Input blood type:");
        lbSearch.setFont(new Font("Arial", Font.BOLD, 12));
        tfSearch = new JTextField("");
        tfSearch.setToolTipText("Please input blood type");
        btCancel = new JButton("Cancel");
        btCancel.setFont(new Font("Arial", Font.BOLD, 12));
        btCancel.setBackground(R.color);
        btOK = new JButton("OK");
        btOK.setFont(new Font("Arial", Font.BOLD, 12));
        btOK.setBackground(R.color);
        lbSearch.setBounds(R.boundsX, R.heightFrame, R.widthTextField / 2, R.heightLabel * 3 / 2);
        tfSearch.setBounds(lbSearch.getWidth() + R.boundsX, R.heightFrame + 5, R.widthTextField * 2, R.heightLabel);
        R.heightFrame += lbSearch.getHeight() + 5;
        btOK.setBounds(lbSearch.getWidth() + tfSearch.getWidth() - R.widthButton, R.heightFrame, R.widthButton, R.heightLabel);
        btCancel.setBounds(lbSearch.getWidth() + tfSearch.getWidth() - R.widthButton - R.widthButton - 5, R.heightFrame, R.widthButton, R.heightLabel);

        tbSearch = new JTable();
        tbSearch.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (tbSearch.getSelectedRow() != -1) {
                    setUpdateUser(tbSearch.getSelectedRow(), 1);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        spTable = new JScrollPane();

        pnInfo.add(lbSearch);
        pnInfo.add(tfSearch);
        pnInfo.add(btOK);
        pnInfo.add(btCancel);
        spInfo.setViewportView(pnInfo);

        tfSearch.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            @SuppressWarnings("ResultOfMethodCallIgnored")
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    NhomMau nhomMau = action.convertStringToNhomMau(tfSearch.getText().toUpperCase());
                    String request = R.urlGetUserWidthBlood + "nhomMau=" + tfSearch.getText().toUpperCase();
                    request.replaceAll(" ", "%20");
                    R.arrayUser = new ArrayList<>();
                    tableModel = new DefaultTableModel();
                    R.arrayUser = action.getUser(request);
                    if (R.arrayUser == null) {
                        R.arrayUser = new ArrayList<>();
                    }
                    ArrayList<String> arr = nhomMau.getNhomMauTT();

                    int i;
                    for (i = 0; i < arr.size(); i++) {
                        @SuppressWarnings("UnusedAssignment")
                        ArrayList<User> arrUser = new ArrayList<>();
                        request = R.urlGetUserWidthBlood + "nhomMau=" + arr.get(i).toUpperCase();
                        request.replaceAll(" ", "%20");
                        arrUser = action.getUser(request);
                        if (arrUser != null) {
                            @SuppressWarnings("UnusedAssignment")
                            int j = 0;
                            for (j = 0; j < arrUser.size(); j++) {
                                R.arrayUser.add(arrUser.get(j));
                            }

                        }

                    }
                    if (R.arrayUser.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No result!");
                        return;
                    }
                    tableModel = getTableModel(R.arrayUser);
                    tbSearch.setModel(tableModel);
                    TableData tableData = new TableData(tableModel);
                    tableData.setVisible(true);

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        btOK.addActionListener(new ActionListener() {
            @Override
            @SuppressWarnings("ResultOfMethodCallIgnored")
            public void actionPerformed(ActionEvent e) {
                NhomMau nhomMau = action.convertStringToNhomMau(tfSearch.getText().toUpperCase());
                String request = R.urlGetUserWidthBlood + "nhomMau=" + tfSearch.getText().toUpperCase();
                request.replaceAll(" ", "%20");
                R.arrayUser = new ArrayList<>();
                tableModel = new DefaultTableModel();
                R.arrayUser = action.getUser(request);
                if (R.arrayUser == null) {
                    R.arrayUser = new ArrayList<>();
                }
                ArrayList<String> arr = nhomMau.getNhomMauTT();

                int i;
                for (i = 0; i < arr.size(); i++) {
                    @SuppressWarnings("UnusedAssignment")
                    ArrayList<User> arrUser = new ArrayList<>();
                    request = R.urlGetUserWidthBlood + "nhomMau=" + arr.get(i).toUpperCase();
                    request.replaceAll(" ", "%20");
                    arrUser = action.getUser(request);
                    if (arrUser != null) {
                        @SuppressWarnings("UnusedAssignment")
                        int j = 0;
                        for (j = 0; j < arrUser.size(); j++) {
                            R.arrayUser.add(arrUser.get(j));
                        }

                    }

                }
                if (R.arrayUser.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No result!");
                    return;
                }
                tableModel = getTableModel(R.arrayUser);
                tbSearch.setModel(tableModel);
                   TableData tableData = new TableData(tableModel);
                    tableData.setVisible(true);

            }
        });

        btCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGUI();
                setSize(new Dimension((int) screenSize.getWidth() / 2, (int) screenSize.getHeight() / 4));
                spInfo.setViewportView(pnControl);
            }
        });

    }

    public void resetGUI() {
        pnInfo.removeAll();
        pnInfo.revalidate();
        pnInfo.repaint();
        spInfo.setViewportView(pnInfo);

    }

    @SuppressWarnings("Convert2Lambda")
    public void setUpdateHospital() {
        pnUpdateInfo = new javax.swing.JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bufferedImage, 0, 0, pnUpdateInfo.getWidth(), pnUpdateInfo.getHeight(), null);
                repaint();
            }
        };

        pnUpdateInfo.setBackground(R.colorBackGround);

        pnUpdateInfo.setLayout(null);
        this.setSize(new Dimension((int) (screenSize.getWidth() * 3 / 8), (int) screenSize.getHeight() / 2));
        R.heightFrame = 10;
        lbUserName = new JLabel("User Name : ");
        lbPassWord = new JLabel("Pass Word :");
        lbName = new JLabel("Name : ");
        lbSDT = new JLabel("Telephone Number : ");
        lbDiaChi = new JLabel("Address : ");

        btUpdateInfo = new JButton("Update");
        btUpdateInfo.setFont(new Font("Arial", Font.BOLD, 12));
        btUpdateInfo.setBackground(R.color);
        btCancelInfo = new JButton("Cancel");
        btCancelInfo.setBackground(R.color);
        btCancelInfo.setFont(new Font("Arial", Font.BOLD, 12));

        tfUserName = new JTextField(hospital.getUserName());
        tfUserName.setEnabled(false);
        pfPassWord = new JPasswordField(hospital.getPassWord());
        tfName = new JTextField(hospital.getTen());
        tfSDT = new JTextField(hospital.getSdt());
        tfDiaChi = new JTextField(hospital.getDiaChi());

        lbUserName.setBounds(R.boundsX, R.heightFrame, R.widthLabel, R.heightLabel);
        tfUserName.setBounds(lbUserName.getWidth() + R.boundsX, R.heightFrame + 5, R.widthTextField, R.heightLabel);
        R.heightFrame += lbUserName.getHeight();
        lbPassWord.setBounds(R.boundsX, R.heightFrame, R.widthLabel, R.heightLabel);
        pfPassWord.setBounds(lbPassWord.getWidth() + R.boundsX, R.heightFrame + 5, R.widthTextField, R.heightLabel);
        R.heightFrame += lbPassWord.getHeight() + 5;
        lbName.setBounds(R.boundsX, R.heightFrame, R.widthLabel, R.heightLabel);
        tfName.setBounds(lbName.getWidth() + R.boundsX, R.heightFrame + 5, R.widthTextField, R.heightLabel);
        R.heightFrame += lbName.getHeight() + 5;
        lbSDT.setBounds(R.boundsX, R.heightFrame, R.widthLabel, R.heightLabel);
        tfSDT.setBounds(lbSDT.getWidth() + R.boundsX, R.heightFrame + 5, R.widthTextField, R.heightLabel);
        R.heightFrame += lbSDT.getHeight() + 5;
        lbDiaChi.setBounds(R.boundsX, R.heightFrame, R.widthLabel, R.heightLabel);
        tfDiaChi.setBounds(lbDiaChi.getWidth() + R.boundsX, R.heightFrame + 5, R.widthTextField, R.heightLabel);
        R.heightFrame += lbDiaChi.getHeight() + 5;
        btUpdateInfo.setBounds(lbDiaChi.getWidth() + tfDiaChi.getWidth() - R.widthButton + 10, R.heightFrame, R.widthButton, R.heightLabel);
        btCancelInfo.setBounds(lbDiaChi.getWidth() + tfDiaChi.getWidth() - R.widthButton + 10 - R.widthButton - 10, R.heightFrame, R.widthButton, R.heightLabel);

        pnUpdateInfo.add(lbUserName);
        pnUpdateInfo.add(lbPassWord);
        pnUpdateInfo.add(lbName);
        pnUpdateInfo.add(lbSDT);
        pnUpdateInfo.add(lbDiaChi);
        pnUpdateInfo.add(tfUserName);
        pnUpdateInfo.add(pfPassWord);
        pnUpdateInfo.add(tfName);
        pnUpdateInfo.add(tfSDT);
        pnUpdateInfo.add(tfDiaChi);

        pnUpdateInfo.add(btUpdateInfo);
        pnUpdateInfo.add(btCancelInfo);

        spInfo.setViewportView(pnUpdateInfo);

        btUpdateInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String request = R.urlUpdateHospital + "userName=" + tfUserName.getText() + "&passWord=" + pfPassWord.getText() + "&sdt=" + tfSDT.getText()
                        + "&diaChi=" + tfDiaChi.getText() + "&ten=" + tfName.getText();
                request = request.replaceAll(" ", "%20");
                if (action.updateHospital(request)) {
                    hospital.setPassWord(pfPassWord.getText());
                    hospital.setTen(tfName.getText());
                    hospital.setSdt(tfSDT.getText());
                    hospital.setDiaChi(tfDiaChi.getText());

                    JOptionPane.showMessageDialog(null, "Update successful!");
                } else {
                    JOptionPane.showMessageDialog(null, "Update Fail!");
                }
            }
        });

        btCancelInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSize(new Dimension((int) screenSize.getWidth() / 2, (int) screenSize.getHeight() / 4));
                spInfo.setViewportView(pnControl);
            }
        });

    }

    @SuppressWarnings("Convert2Lambda")
    public void setUpdateUser(int index, int ctrl) {
        R.heightFrame = 10;
        pnUpdateUser = new JPanel();
        if (ctrl == 0) {
            lbSearchName = new JLabel("Input Name : ");
            tfSearchName = new JTextField();
            lbSearchName.setBounds(R.boundsX, R.heightFrame, R.widthLabel, R.heightLabel);
            tfSearchName.setBounds(lbSearchName.getWidth() + R.boundsX, R.heightFrame, R.widthTextField, R.heightLabel);
            R.heightFrame += lbSearchName.getHeight() + 5;
            tfSearchName.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        String request = R.urlGetUser + "userName=" + tfSearchName.getText();
                        user = action.getUser(request).get(0);
                        if (user != null) {
                            tfUser.setText(user.getUserName());
                            tfNameUser.setText(user.getTen());
                            tfNhomMau.setText(user.getNhomMau().getTenNhomMau());
                            tfNgayHienMau.setText(user.getNgayHienMau().toString());
                            tfNgayXetNghiem.setText(user.getNgayXetNghiem().toString());
                            tfTinhTrang.setText(user.getTinhTrang());
                            tfDiemSucKhoe.setText(user.getDiemSucKhoe());
                        } else {
                            JOptionPane.showMessageDialog(null, "No result!");
                        }

                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                }
            });

            pnUpdateUser.setLayout(null);
            pnUpdateUser.setBackground(R.colorBackGround);
            setSize(new Dimension((int) (screenSize.getWidth() * 3 / 8), (int) screenSize.getHeight() * 2 / 3));
            lbNameUser = new JLabel("Name : ");
            lbNhomMau = new JLabel("Blood type : ");
            lbNgayHienMau = new JLabel("Give date : ");
            lbNgayXetNghiem = new JLabel("Test date : ");
            lbTinhTrang = new JLabel("Status : ");
            lbDiemSucKhoe = new JLabel("Core of hedlth : ");
            tfNameUser = new JTextField();

            tfNhomMau = new JTextField();
            tfNgayHienMau = new JTextField();
            tfNgayXetNghiem = new JTextField();
            tfTinhTrang = new JTextField();
            tfDiemSucKhoe = new JTextField();
            lbNameUser.setBounds(R.boundsX, R.heightFrame, R.widthLabel, R.heightLabel);
            tfNameUser.setBounds(lbNameUser.getWidth() + R.boundsX, R.heightFrame + 5, R.widthTextField, R.heightLabel);
            R.heightFrame += lbNameUser.getHeight() + 5;
            lbNhomMau.setBounds(R.boundsX, R.heightFrame, R.widthLabel, R.heightLabel);
            tfNhomMau.setBounds(lbNhomMau.getWidth() + R.boundsX, R.heightFrame + 5, R.widthTextField, R.heightLabel);
            R.heightFrame += lbNhomMau.getHeight() + 5;
            lbNgayHienMau.setBounds(R.boundsX, R.heightFrame, R.widthLabel, R.heightLabel);
            tfNgayHienMau.setBounds(lbNgayHienMau.getWidth() + R.boundsX, R.heightFrame + 5, R.widthTextField, R.heightLabel);
            R.heightFrame += lbNgayHienMau.getHeight() + 5;
            lbNgayXetNghiem.setBounds(R.boundsX, R.heightFrame, R.widthLabel, R.heightLabel);
            tfNgayXetNghiem.setBounds(lbNgayXetNghiem.getWidth() + R.boundsX, R.heightFrame + 5, R.widthTextField, R.heightLabel);
            R.heightFrame += lbNgayXetNghiem.getHeight() + 5;
            lbTinhTrang.setBounds(R.boundsX, R.heightFrame, R.widthLabel, R.heightLabel);
            tfTinhTrang.setBounds(lbTinhTrang.getWidth() + R.boundsX, R.heightFrame + 5, R.widthTextField, R.heightLabel);
            R.heightFrame += lbTinhTrang.getHeight() + 5;
            lbDiemSucKhoe.setBounds(R.boundsX, R.heightFrame, R.widthLabel, R.heightLabel);
            tfDiemSucKhoe.setBounds(lbDiemSucKhoe.getWidth() + R.boundsX, R.heightFrame + 5, R.widthTextField, R.heightLabel);
            R.heightFrame += lbDiemSucKhoe.getHeight() + 5;
            btUpdateUser = new JButton("Update");
            btCancelUser = new JButton("Cancel");
            btUpdateUser.setFont(new Font("Arial", Font.BOLD, 12));
            btCancelUser.setFont(new Font("Arial", Font.BOLD, 12));
            btUpdateUser.setBounds(lbDiemSucKhoe.getWidth() + tfDiemSucKhoe.getWidth() - R.widthButton + 10, R.heightFrame, R.widthButton, R.heightLabel);
            btUpdateUser.setBackground(R.color);
            btCancelUser.setBackground(R.color);
            btCancelUser.setBounds(lbDiemSucKhoe.getWidth() + tfDiemSucKhoe.getWidth() - R.widthButton - R.widthButton - 5, R.heightFrame, R.widthButton, R.heightLabel);
            pnUpdateUser.add(lbNameUser);
            pnUpdateUser.add(lbNhomMau);
            pnUpdateUser.add(lbNgayHienMau);
            pnUpdateUser.add(lbNgayXetNghiem);
            pnUpdateUser.add(lbTinhTrang);
            pnUpdateUser.add(lbDiemSucKhoe);
            pnUpdateUser.add(tfNameUser);
            pnUpdateUser.add(tfNhomMau);
            pnUpdateUser.add(tfNgayHienMau);
            pnUpdateUser.add(tfNgayXetNghiem);
            pnUpdateUser.add(tfTinhTrang);
            pnUpdateUser.add(tfDiemSucKhoe);
            pnUpdateUser.add(btUpdateUser);
            pnUpdateUser.add(btCancelUser);
            pnUpdateUser.add(lbSearchName);
            pnUpdateUser.add(tfSearchName);

            spInfo.setViewportView(pnUpdateUser);
            btCancelUser.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (ctrl == 0) {
                        setSize(new Dimension((int) screenSize.getWidth() / 2, (int) screenSize.getHeight() / 2));
                        spInfo.setViewportView(pnControl);
                    } else {
                        setSize(new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight()));
                        spInfo.setViewportView(tbSearch);
                    }
                }
            });
            btUpdateUser.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String request = R.urlUpdateUserWithHospital + "userName=" + user.getUserName() + "&nhomMau=" + tfNhomMau.getText() + "&ngayHiemMau=" + tfNgayHienMau.getText()
                            + "&ngayXetNghiem=" + tfNgayXetNghiem.getText() + "&tinhTrang=" + tfTinhTrang.getText() + "&diemSucKhoe=" + tfDiemSucKhoe.getText();
                    request = request.replaceAll(" ", "%20");
                    if (action.updateUserFromHospital(request)) {
                        JOptionPane.showMessageDialog(null, "Update Successful!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Update Fail!");
                    }
                }

                public void keyReleased(KeyEvent e) {
                }
            });

        } else {
            user = R.arrayUser.get(index);

            pnUpdateUser.setLayout(null);
            pnUpdateUser.setBackground(R.colorBackGround);
            this.setSize(new Dimension((int) (screenSize.getWidth() * 3 / 8), (int) screenSize.getHeight() / 2));
            lbNameUser = new JLabel("Name : ");
            lbNhomMau = new JLabel("Blood type : ");
            lbNgayHienMau = new JLabel("Give date : ");
            lbNgayXetNghiem = new JLabel("Test date : ");
            lbTinhTrang = new JLabel("Status : ");
            lbDiemSucKhoe = new JLabel("Core of hedlth : ");
            tfNameUser = new JTextField(user.getTen());
            tfNhomMau = new JTextField(user.getNhomMau().getTenNhomMau());
            tfNgayHienMau = new JTextField(user.getNgayHienMau().toString());
            tfNgayXetNghiem = new JTextField(user.getNgayXetNghiem().toString());
            tfTinhTrang = new JTextField(user.getTinhTrang());
            tfDiemSucKhoe = new JTextField(user.getDiemSucKhoe());
            lbNameUser.setBounds(R.boundsX, R.heightFrame, R.widthLabel, R.heightLabel);
            tfNameUser.setBounds(lbNameUser.getWidth() + R.boundsX, R.heightFrame + 5, R.widthTextField, R.heightLabel);
            R.heightFrame += lbNameUser.getHeight() + 5;
            lbNhomMau.setBounds(R.boundsX, R.heightFrame, R.widthLabel, R.heightLabel);
            tfNhomMau.setBounds(lbNhomMau.getWidth() + R.boundsX, R.heightFrame + 5, R.widthTextField, R.heightLabel);
            R.heightFrame += lbNhomMau.getHeight() + 5;
            lbNgayHienMau.setBounds(R.boundsX, R.heightFrame, R.widthLabel, R.heightLabel);
            tfNgayHienMau.setBounds(lbNgayHienMau.getWidth() + R.boundsX, R.heightFrame + 5, R.widthTextField, R.heightLabel);
            R.heightFrame += lbNgayHienMau.getHeight() + 5;
            lbNgayXetNghiem.setBounds(R.boundsX, R.heightFrame, R.widthLabel, R.heightLabel);
            tfNgayXetNghiem.setBounds(lbNgayXetNghiem.getWidth() + R.boundsX, R.heightFrame + 5, R.widthTextField, R.heightLabel);
            R.heightFrame += lbNgayXetNghiem.getHeight() + 5;
            lbTinhTrang.setBounds(R.boundsX, R.heightFrame, R.widthLabel, R.heightLabel);
            tfTinhTrang.setBounds(lbTinhTrang.getWidth() + R.boundsX, R.heightFrame + 5, R.widthTextField, R.heightLabel);
            R.heightFrame += lbTinhTrang.getHeight() + 5;
            lbDiemSucKhoe.setBounds(R.boundsX, R.heightFrame, R.widthLabel, R.heightLabel);
            tfDiemSucKhoe.setBounds(lbDiemSucKhoe.getWidth() + R.boundsX, R.heightFrame + 5, R.widthTextField, R.heightLabel);
            R.heightFrame += lbDiemSucKhoe.getHeight() + 5;
            btUpdateUser = new JButton("Update");
            btCancelUser = new JButton("Cancel");
            btUpdateUser.setFont(new Font("Arial", Font.BOLD, 12));
            btCancelUser.setFont(new Font("Arial", Font.BOLD, 12));
            btUpdateUser.setBounds(lbDiemSucKhoe.getWidth() + tfDiemSucKhoe.getWidth() - R.widthButton + 10, R.heightFrame, R.widthButton, R.heightLabel);
            btUpdateUser.setBackground(R.color);
            btCancelUser.setBackground(R.color);
            btCancelUser.setBounds(lbDiemSucKhoe.getWidth() + tfDiemSucKhoe.getWidth() - R.widthButton - R.widthButton - 5, R.heightFrame, R.widthButton, R.heightLabel);
            pnUpdateUser.add(lbNameUser);
            pnUpdateUser.add(lbNhomMau);
            pnUpdateUser.add(lbNgayHienMau);
            pnUpdateUser.add(lbNgayXetNghiem);
            pnUpdateUser.add(lbTinhTrang);
            pnUpdateUser.add(lbDiemSucKhoe);
            pnUpdateUser.add(tfNameUser);
            pnUpdateUser.add(tfNhomMau);
            pnUpdateUser.add(tfNgayHienMau);
            pnUpdateUser.add(tfNgayXetNghiem);
            pnUpdateUser.add(tfTinhTrang);
            pnUpdateUser.add(tfDiemSucKhoe);
            pnUpdateUser.add(btUpdateUser);
            pnUpdateUser.add(btCancelUser);
            spInfo.setViewportView(pnUpdateUser);

            btCancelUser.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (ctrl == 0) {
                        setSize(new Dimension((int) screenSize.getWidth() / 2, (int) screenSize.getHeight() / 2));
                        spInfo.setViewportView(pnControl);
                    } else {
                        setSize(new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight()));
                        spInfo.setViewportView(tbSearch);
                    }
                }
            });
            btUpdateUser.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String request = R.urlUpdateUserWithHospital + "userName=" + user.getUserName() + "&nhomMau=" + tfNhomMau.getText() + "&ngayHiemMau=" + tfNgayHienMau.getText()
                            + "&ngayXetNghiem=" + tfNgayXetNghiem.getText() + "&tinhTrang=" + tfTinhTrang.getText() + "&diemSucKhoe=" + tfDiemSucKhoe.getText();
                    request = request.replaceAll(" ", "%20");
                    if (action.updateUserFromHospital(request)) {
                        JOptionPane.showMessageDialog(null, "Update Successful!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Update Fail!");
                    }
                }
            });
        }
    }

    public DefaultTableModel getTableModel(ArrayList<User> arr) {
        @SuppressWarnings("Convert2Diamond")
        ArrayList<String> arrayColumns = new ArrayList<String>();
        arrayColumns.add("Number");
        arrayColumns.add("Name");
        arrayColumns.add("Blood type");
        arrayColumns.add("Telephone Numver");
        arrayColumns.add("Hospital");
        arrayColumns.add("Address");
        arrayColumns.add("Time free");
        arrayColumns.add("Health status");
        tableModel.setColumnIdentifiers(arrayColumns.toArray());
        @SuppressWarnings("Convert2Diamond")
        ArrayList<String> arrayRow = new ArrayList<String>();
        int i;
        for (i = 0; i < arr.size(); i++) {
            arrayRow.add(String.valueOf(i + 1));
            arrayRow.add(arr.get(i).getTen());
            arrayRow.add(arr.get(i).getNhomMau().getTenNhomMau());
            arrayRow.add(arr.get(i).getSdt());
            @SuppressWarnings("LocalVariableHidesMemberVariable")
            Hospital hospital = action.getHospital(R.urlGetHospital + "idBV=" + arr.get(i).getIdBV());
            arrayRow.add(hospital.getTen());
            arrayRow.add(arr.get(i).getDiaChi());
            arrayRow.add(arr.get(i).getThoiGianRanh());
            arrayRow.add(arr.get(i).getTinhTrang());
            tableModel.addRow(arrayRow.toArray());
            arrayRow.clear();
        }

        return tableModel;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        spInfo = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(spInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(spInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private JLabel lbSearchName;
    private JTextField tfSearchName;

    private JPanel pnControl;
    private JLabel lbSelect;
    private JComboBox<String> cbSelect;
    private JButton btGo;

    private JLabel lbUser;
    private JLabel lbPass;
    private JTextField tfUser;
    private JPasswordField pfPass;
    private JButton btSignIn;

    private JLabel lbSearch;
    private JTextField tfSearch;
    private JButton btCancel;
    private JButton btOK;

    private JScrollPane spTable;
    private JTable tbSearch;

    private JLabel lbUserName;
    private JLabel lbPassWord;
    private JLabel lbName;
    private JLabel lbSDT;
    private JLabel lbDiaChi;

    private JTextField tfUserName;
    private JPasswordField pfPassWord;
    private JTextField tfName;
    private JTextField tfSDT;
    private JTextField tfDiaChi;

    private JButton btUpdate;
    private JButton btUpdateInfo;
    private JButton btCancelInfo;
    private JPanel pnUpdateInfo;

    private JPanel pnUpdateUser;
    private JButton btUpdateUser;
    private JButton btCancelUser;

    private JLabel lbNameUser;
    private JLabel lbNhomMau;
    private JLabel lbNgayHienMau;
    private JLabel lbNgayXetNghiem;
    private JLabel lbTinhTrang;
    private JLabel lbDiemSucKhoe;

    private JTextField tfNameUser;
    private JTextField tfNhomMau;
    private JTextField tfNgayHienMau;
    private JTextField tfNgayXetNghiem;
    private JTextField tfTinhTrang;
    private JTextField tfDiemSucKhoe;
    private JPanel pnTable;

    private JPanel pnInfo;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane spInfo;
    // End of variables declaration//GEN-END:variables
}
