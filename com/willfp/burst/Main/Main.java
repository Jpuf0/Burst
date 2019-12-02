package org.auxilor;

import java.awt.*;
import java.io.IOException;
import javax.swing.*;
import javax.swing.text.DefaultCaret;

public class Main implements Interface {
	private Communicate communicate = new Communicate();
	private String session = "Public";
	private String username = null;

    public static void main(String[] args) {
		Main main = new Main();
		main.initUI();
		main.mainReceive();
    }

    void mainReceive() {
    	while(true) {
			try {
				communicate.receive(session);
			} catch (IOException ignored) {}
		}
	}

    void initUI(){
		menuBar.add(sessionMenu);
		menuBar.add(userMenu);

		sessionMenu.add(joinSession);
		sessionMenu.addSeparator();
		sessionMenu.add(sessionID);

		userMenu.add(changeUsername);
		userMenu.addSeparator();
		userMenu.add(currentUsername);

		sessionID.setEnabled(false);
		currentUsername.setEnabled(false);

		frame.add(menuBar);
		frame.add(textScroll);
		frame.add(textField);

		textField.addActionListener(e -> {
			try {communicate.send(username + ": " + textField.getText(), session);} catch (IOException ignored) {}
		});

		joinSession.addActionListener(e -> {
			session = JOptionPane.showInputDialog(null, "Session ID: ", "Join Session", JOptionPane.PLAIN_MESSAGE);
			try {
				communicate.send(username + " joined the session", session);
			} catch (IOException ignored) {}
			sessionID.setText("ID: " + session);
		});

		changeUsername.addActionListener(e -> {
			String prevUsername = username;
			username = JOptionPane.showInputDialog(null, "Choose a username : ", "Change Username", JOptionPane.PLAIN_MESSAGE);
			if(username==null || username.equals("") || username.isBlank() || username.isEmpty()){
				username = prevUsername;
				return;
			}
			try {
				communicate.send(prevUsername + " changed their name to " + username, session);
			} catch (IOException ignored) {}
			currentUsername.setText("Name: " + username);
		});

		textPane.setEditable(false);

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(300,300);

		frame.getContentPane().add(BorderLayout.CENTER, textScroll);
		frame.getContentPane().add(BorderLayout.NORTH, menuBar);
		frame.getContentPane().add(BorderLayout.SOUTH, textField);

		frame.setVisible(true);

		while(username==null){
			username = JOptionPane.showInputDialog(null, "Choose a username : ", "Username", JOptionPane.PLAIN_MESSAGE);
			currentUsername.setText("Name: " + username);
		}

		try {
			communicate.send(username + " joined the session", session);
		} catch (IOException ignored) {}
	}
}
