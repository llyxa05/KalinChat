package com.hocode.chat.main;

import com.hocode.chat.Start;
import com.hocode.chat.events.EventListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static JDA jda;
    public Main(){
        try{
            String tokenjsk = "twoitokenotbota";
            jda = JDABuilder.createDefault(tokenjsk)
                    .disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE)
                    .setBulkDeleteSplittingEnabled(false)
                    .setCompression(Compression.NONE)
                    .setStatus(OnlineStatus.ONLINE)
                    .build();
            jda.addEventListener(new EventListener());
           
            try {
				jda.awaitReady().getCategoryById("1069259127634792568").getTextChannels().get(0)
				.sendMessage(Start.name + "* зашёл в чат!")
				.timeout(5, TimeUnit.SECONDS)
				.submit();
			} catch (InterruptedException e) {}
            
            Scanner scanner = new Scanner(System.in);
            while (true) {
            	String msg = Start.textField.getText();
            	String pole = Start.textField.getText();
            	
            	int count = 0;
                for(int i = 0; i<pole.length(); i++) {
                   count++;
                }
                for (TextChannel channel : jda.getTextChannels()) {
                	if(Start.sended == true) {
                		if(Start.sended = true) {
                        	Start.textField.setText("");
                        }
                		if(count > 0) {
                			channel.sendMessage(Start.name+": "+msg).queue();
                    		Start.sended = false;
                		}
                	}
                } 
            }
        }catch (LoginException le){ }
    }
}
