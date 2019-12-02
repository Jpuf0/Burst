package org.auxilor;

import javax.swing.*;

public interface Interface {
    JFrame frame = new JFrame("Burst Messenger");
    JTextPane textPane = new JTextPane();
    JScrollPane textScroll = new JScrollPane(textPane,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    JMenuBar menuBar = new JMenuBar();
    JMenu sessionMenu = new JMenu("Session");
    JMenu userMenu = new JMenu("User");
    JMenuItem changeUsername = new JMenuItem("Change Name");
    JMenuItem currentUsername = new JMenuItem("No Username");
    JMenuItem joinSession = new JMenuItem("Join Session");
    JMenuItem sessionID = new JMenuItem("ID: Public");
    JTextField textField = new JTextField(15);
}