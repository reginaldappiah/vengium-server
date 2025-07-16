package com.rs;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.rs.game.player.Player;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;


public class Panel extends JPanel {
	
		
	JFrame frame = new JFrame("Storage");
    JList<PlayerList> list = new JList<>();
    DefaultListModel<PlayerList> model = new DefaultListModel<>();

    JLabel label = new JLabel();
    JPanel panel = new JPanel();
    JSplitPane splitPane = new JSplitPane();
    
   
        public Panel() {
                super(new GridLayout(1,1));
                

                JTabbedPane tabbedPane = new JTabbedPane();
                JComponent panel1 = makeTextPanel("Panel #1");
                tabbedPane.addTab("Server", panel1);
                panel1.setPreferredSize(new Dimension(300, 200));
                JComponent panel2 = makeTextPanel("Panel #2");
                tabbedPane.addTab("Player", panel2);
                JComponent panel3 = makeTextPanel("Panel #3");
                tabbedPane.addTab("Console", panel3);
                JComponent panel4= makeTextPanel("Panel #3");
                tabbedPane.addTab("NPC", panel4);
                add(tabbedPane);
                tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);        
                
                JTabbedPane tabbedPane2 = new JTabbedPane();
                JList<PlayerList> list = new JList<>();
                DefaultListModel<PlayerList> model = new DefaultListModel<>();
                
                JLabel label = new JLabel();
                JPanel panel = new JPanel();
                JSplitPane splitPane = new JSplitPane();
        
        
        }

        protected JComponent makeTextPanel (String text) {
                JPanel panel = new JPanel(false);
                JLabel filler = new JLabel(text);
                filler.setHorizontalAlignment(JLabel.CENTER);
                panel.setLayout (new GridLayout(1,1));
                panel.add(filler);
                return panel;
        }

        private static void createAndShowGUI() {
        	
        	
        	
            JFrame frame = new JFrame ("Vengium Control Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.add(new Panel(), BorderLayout.CENTER);

            frame.pack();
            frame.setVisible(true);
        }

        private class PlayerList {
        	String name;
        	String rank;
			/**
			 * @param name
			 * @param rank
			 */
			@SuppressWarnings("unused")
			public PlayerList(String name, String rank) {
				super();
				this.name = name;
				this.rank = rank;
			}
			/**
			 * @return the name
			 */
			public String getName() {
				return name;
			}
			/**
			 * @param name the name to set
			 */
			public void setName(String name) {
				this.name = name;
			}
			/**
			 * @return the rank
			 */
			public String getRank() {
				return rank;
			}
			/**
			 * @param rank the rank to set
			 */
			public void setRank(String rank) {
				this.rank = rank;
			}
        	
        	
        }
        public static void main(String[] args) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        UIManager.put("swing.boldmetal", Boolean.FALSE);
                        createAndShowGUI();
                    }

                });
        }
}