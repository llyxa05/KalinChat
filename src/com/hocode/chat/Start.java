package com.hocode.chat;

import com.hocode.chat.main.Main;

import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Border;
import ua.ilyxa05.Config;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

public class Start {
	
    public static String name;
    public static String ver = "0.2 BETA";
    
    public static void main(String[] args){
    	if(Config.Debug == false) {
    		//ver check
    		try {
                HttpsURLConnection httpsClient = (HttpsURLConnection) new URL("https://kalincdn.ilyxa05.moe/ver.txt").openConnection();
                httpsClient.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.157 Safari/537.36");
                BufferedReader buffer = new BufferedReader(new InputStreamReader(httpsClient.getInputStream()));
                String readLine = buffer.readLine();
                if (readLine.equals(ver)) {
                	System.out.println("Current ver: " + ver + "\nReaded into the site: " + readLine);
                } else {
                	System.out.println("Current ver: " + ver + "\nReaded into the site: " + readLine);
                	JOptionPane.showMessageDialog(null, "Привет! Вышла новая версия KalinChat!\nОбновись, ведь без этого ты многое теряешь!");
                	System.exit(0);
                } 
            } catch (Exception e) {
            	System.out.println("Error getting version into the site");
            	System.exit(0);
            }
    		
    		//download sound
    		try {
        		String url = ("https://kalincdn.ilyxa05.moe/msg.wav");
            	String fileName = "C:\\Users\\" + System.getProperty("user.name") + "\\AppData\\Roaming\\msg.wav";
            	
            	FileOutputStream fout = null;
            	BufferedInputStream in = null;
            	try {
            		in = new BufferedInputStream(new URL(url).openStream());
            		fout = new FileOutputStream(fileName);
            		byte data[] = new byte[1024];
            		int count;
            		while((count = in.read(data, 0, 1024)) != -1) {
            			fout.write(data, 0, count);
            			fout.flush();
            		}
            	} catch (MalformedURLException e) {} catch (IOException e) {} finally{
            		try {
            			in.close();
            			fout.close();
            		} catch(IOException e) {} }}catch(Exception e){}
    		if(name == null) {
                JPanel panel = new JPanel();
                panel.setBackground(new Color(102, 205, 170));
                panel.setSize(new Dimension(200, 64));
                panel.setLayout(null);
                
                JLabel label = new JLabel("Type your nickname:");
                label.setBounds(0, 0, 200, 64);
                label.setFont(new Font("Arial", Font.BOLD, 11));
                label.setHorizontalAlignment(SwingConstants.CENTER);
                panel.add(label);
                
                JTextField nickField = new JTextField(10);
                nickField.setBounds(170, 15, 100, 30);
                nickField.setFont(new Font("Arial", Font.BOLD, 11));
                nickField.setHorizontalAlignment(SwingConstants.CENTER);
                panel.add(nickField);

                UIManager.put("OptionPane.minimumSize",new Dimension(300, 120));        
                JOptionPane.showMessageDialog(null, panel, "Welcome to KalinChat V" + ver, JOptionPane.PLAIN_MESSAGE);
                
                name = nickField.getText();
                int count = 0;
                for(int i = 0; i<name.length(); i++) {
                   count++;
                }
                if(count > 3) {
                	gui();
            		new Main();
                } else {
                	JOptionPane.showMessageDialog(null, "Your nickname is less than 3 characters!");
                }
        	}
    	} else {
    		name = "dev";
    		gui();
    		new Main();
    	}
    }
    
    //gui
    public static JTextField textField = new JTextField(10);
    public static boolean sended = false;
    public static JTextArea textarea = new JTextArea();
    public static JScrollPane scroll = new JScrollPane(textarea,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    
    public static void gui() {   
        Font font = new Font("Century Gothic", Font.BOLD, 14);
        Font font1 = new Font("Century Gothic", Font.PLAIN, 13);
        
    	JFrame frame = new JFrame("KalinChat V" + ver);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(Config.SCREEN_SIZEX, Config.SCREEN_SIZEY);
        frame.setResizable(false);
        frame.setVisible(true); 

        JPanel panel = new JPanel();
        JLabel labelEnter = new JLabel("Enter");
        JButton send = new JButton("Send");
        
        send.addActionListener(event -> {
        	sended = true;
        });
        
        //textField.
        textField.setFont(font1);
        
        panel.add(send);
        panel.add(labelEnter);
        panel.add(textField);
        panel.add(send);
        
        textarea.setFont(font);
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);
        textarea.setEditable(false);
        textarea.setText("Welcome to KalinChat V" + ver + "\nConnecting to Discrod API...");
        
        frame.getContentPane().add(BorderLayout.CENTER, scroll);
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.setVisible(true);
    }
}
