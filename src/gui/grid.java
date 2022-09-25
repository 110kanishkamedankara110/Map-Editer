/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.JPanel;
import theams.MyTheam;

/**
 *
 * @author Kanishka
 */
public class grid extends javax.swing.JFrame {

    int gridX = 15, gridY = 20;
    int tiles = gridX * gridY;
    int z = 10;
    int selLay = 0;
    JButton b[][][] = new JButton[gridX][gridY][z];
    boolean focous = true;
    ImageIcon i;
    int tilesX = ((new ImageIcon(new ImageIcon(getClass().getResource("../tiles/Overworld.png")).getImage()).getIconWidth() / 16));
    int tilesY = ((new ImageIcon(new ImageIcon(getClass().getResource("../tiles/Overworld.png")).getImage()).getIconHeight() / 16));
    int noL = 1;

    boolean clear_tiles = false;

    public grid() {

        try {
            initComponents();

            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            this.setLocationRelativeTo(null);
            loadTiles();
            loadLayers();

        } catch (Exception ex) {

        }

    }

    private final void loadLayers() {

        jPanel3.setLayout(new GridLayout(z, 1));
        jPanel3.removeAll();
        for (int f = 0; f < noL; f++) {
            int g = f;
            JLabel ljb = new JLabel();
            ljb.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent arg0) {
                    selLay = g;
                }

                @Override
                public void mousePressed(MouseEvent arg0) {

                }

                @Override
                public void mouseReleased(MouseEvent arg0) {

                }

                @Override
                public void mouseEntered(MouseEvent arg0) {

                }

                @Override
                public void mouseExited(MouseEvent arg0) {

                }

            });

            ljb.setText("layer" + f);
            jPanel3.add(ljb);
        }

    }

    private final void loadTiles() {
        new Thread(() -> {
            int lo = 0;

            for (int k = 0; k < tilesX; k++) {
                for (int l = 0; l < tilesY; l++) {

                    try {

                        JButton jbut = new JButton();
                        jbut.setSize(32, 32);

                        BufferedImage bf = ImageIO.read(new File(getClass().getResource("../tiles/Overworld.png").getPath()));
                        int gapX;

                        if (k == 0 || l == 0) {
                            gapX = 16;
                        } else {
                            gapX = 16;
                        }
                        final int kf = k;
                        final int lf = l;
                        jbut.addMouseListener(new MouseListener() {
                            @Override
                            public void mouseClicked(MouseEvent arg0) {
                                i = new ImageIcon(bf.getSubimage(gapX * lf, gapX * kf, 16, 16).getScaledInstance(32, 32, Image.SCALE_SMOOTH));
                            }

                            @Override
                            public void mousePressed(MouseEvent arg0) {

                            }

                            @Override
                            public void mouseReleased(MouseEvent arg0) {

                            }

                            @Override
                            public void mouseEntered(MouseEvent arg0) {

                            }

                            @Override
                            public void mouseExited(MouseEvent arg0) {

                            }

                        });

                        jbut.setIcon(new ImageIcon(bf.getSubimage(gapX * l, gapX * k, 16, 16).getScaledInstance(32, 32, Image.SCALE_SMOOTH)));

                        jPanel1.add(jbut);
                        lo = ((lo + 1));
                        int c = tilesX * tilesY;
                        jPanel1.revalidate();
                        jScrollPane1.revalidate();
                    } catch (IOException ex) {

                    }
                }

            }

            clear_tiles = true;
        }).start();
    }

    private final void drowGrid(JPanel jpan, int val) {
        new Thread(() -> {
            for (int x = 0; x < gridX; x++) {
                for (int y = 0; y < gridY; y++) {
                    jPanel2.add(jpan);
                    jPanel2.setComponentZOrder(jpan, val);
                    jpan.setOpaque(false);
                    jpan.setLayout(new GridLayout(gridX, gridY));
                    jpan.grabFocus();

                    JButton jb = new JButton();

                    jb.setBorderPainted(false);
                    jb.setBackground(Color.white);
                    jb.setOpaque(false);
                    jb.setContentAreaFilled(false);
                    jb.addFocusListener(new FocusListener() {

                        ImageIcon bg;

                        @Override
                        public void focusGained(FocusEvent arg0) {
//                        bg = (ImageIcon) b[Integer.parseInt(jTextField1.getText())][Integer.parseInt(jTextField2.getText())][Integer.parseInt(jTextField3.getText())].getIcon();
//                        if (focous) {
//                            b[Integer.parseInt(jTextField1.getText())][Integer.parseInt(jTextField2.getText())][selLay].setIcon(i);
//
//                        }
                        }

                        @Override
                        public void focusLost(FocusEvent arg0) {
//                        if (focous) {
//                            JButton jb = b[Integer.parseInt(jTextField1.getText())][Integer.parseInt(jTextField2.getText())][selLay];
//                            jb.setIcon(bg);
//                        }
                        }

                    });

                    b[x][y][selLay] = jb;
                    jb.setSize(32, 32);
                    jb.setName(x + "," + y + "," + selLay);

                    int ix = x;
                    int iy = y;
                    jb.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent arg0) {
                            focous = false;
                            b[Integer.parseInt(jTextField1.getText())][Integer.parseInt(jTextField2.getText())][Integer.parseInt(jTextField3.getText())].setIcon(i);
                        }

                        @Override
                        public void mousePressed(MouseEvent arg0) {

                        }

                        @Override
                        public void mouseReleased(MouseEvent arg0) {
                            focous = true;
                        }

                        @Override
                        public void mouseEntered(MouseEvent arg0) {
                            jTextField1.setText(String.valueOf(ix));
                            jTextField2.setText(String.valueOf(iy));
                            jTextField3.setText(String.valueOf(selLay));
                            jb.grabFocus();

                        }

                        @Override
                        public void mouseExited(MouseEvent arg0) {

                        }

                    });
                    jb.addMouseMotionListener(new MouseMotionListener() {
                        @Override
                        public void mouseDragged(MouseEvent arg0) {
                            focous = false;
                            b[Integer.parseInt(jTextField1.getText())][Integer.parseInt(jTextField2.getText())][Integer.parseInt(jTextField3.getText())].setIcon(i);
                        }

                        @Override
                        public void mouseMoved(MouseEvent arg0) {

                        }

                    });

                    jpan.add(jb);

                }

            }

        }).start();
        jPanel2.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jButton1 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField1.setEditable(false);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTextField1PropertyChange(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jTextField2.setEditable(false);

        jScrollPane1.setMaximumSize(new java.awt.Dimension(250, 400));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(250, 400));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(250, 400));
        jScrollPane1.setVerifyInputWhenFocusTarget(false);

        jPanel1.setMaximumSize(new java.awt.Dimension(1152, 1280));
        jPanel1.setMinimumSize(new java.awt.Dimension(1152, 1280));
        jPanel1.setPreferredSize(new java.awt.Dimension(1152, 1280));
        jPanel1.setLayout(new java.awt.GridLayout(40, 36));
        jScrollPane1.setViewportView(jPanel1);

        jButton1.setText("Add Layer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField3.setEditable(false);
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField3KeyTyped(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMaximumSize(new java.awt.Dimension(640, 480));
        jPanel2.setMinimumSize(new java.awt.Dimension(640, 480));
        jPanel2.setPreferredSize(new java.awt.Dimension(640, 480));
        jPanel2.setLayout(new javax.swing.OverlayLayout(jPanel2));

        java.awt.GridBagLayout jPanel3Layout = new java.awt.GridBagLayout();
        jPanel3Layout.columnWidths = new int[] {0};
        jPanel3Layout.rowWeights = new double[] {100.0};
        jPanel3.setLayout(jPanel3Layout);
        jScrollPane2.setViewportView(jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased


    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTextField1PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1PropertyChange

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        selLay = selLay + 1;
        noL = noL + 1;
        drowGrid(new JPanel(), 0);
        loadLayers();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
        if (jTextField3.getText().isEmpty()) {
            jTextField3.setText("0");
        } else {
            selLay = Integer.parseInt(jTextField3.getText());
        }
    }//GEN-LAST:event_jTextField3KeyReleased

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
//        System.out.println(evt.getKeyChar());

//        if (!Pattern.compile("[0-" + noL + "]").matcher(jTextField3.getText()+evt.getKeyChar()).matches()) {
//            evt.consume();
//            jTextField3.setText(String.valueOf(noL));
//        }

    }//GEN-LAST:event_jTextField3KeyTyped

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        MyTheam.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            grid gr = new grid();
            gr.selLay = 0;
            JPanel newJp = new JPanel();
           
            gr.drowGrid(newJp, 0);

            new Thread(() -> {

                Loading l = new Loading(gr,true);
                
                l.setVisible(true);
                

            }).start();
            gr.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
