package com.hocode.chat.events;

import java.io.File;
import java.io.IOException;

import javax.annotation.Nonnull;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.jetbrains.annotations.NotNull;

import com.hocode.chat.Start;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class EventListener extends ListenerAdapter {
	
    @Override
    public void onReady(@NotNull ReadyEvent e){
        System.out.println("Welcome to KalinChat V" + Start.ver);
    }

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent e){
    	String getmsg = Start.textarea.getText();
        String msg = e.getMessage().getContentRaw();     
        Start.textarea.setText(getmsg + "\n" + msg);
        
        //sound & uvedomleniya
        if(msg != null) {
        	try {
        		File soundFile = new File("C:\\Users\\" + System.getProperty("user.name") + "\\AppData\\Roaming\\msg.wav"); //Звуковой файл
        		
        		//Получаем AudioInputStream
        		//Вот тут могут полететь IOException и UnsupportedAudioFileException
        		AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
        		
        		//Получаем реализацию интерфейса Clip
        		//Может выкинуть LineUnavailableException
        		Clip clip = AudioSystem.getClip();
        		
        		//Загружаем наш звуковой поток в Clip
        		//Может выкинуть IOException и LineUnavailableException
        		clip.open(ais);
        		
        		clip.setFramePosition(0); //устанавливаем указатель на старт
        		clip.start(); //Поехали!!!

        		//Если не запущено других потоков, то стоит подождать, пока клип не закончится
        	        //В GUI-приложениях следующие 3 строчки не понадобятся
        		Thread.sleep(clip.getMicrosecondLength()/1000);
        		clip.stop(); //Останавливаем
        		clip.close(); //Закрываем
        	} catch (IOException | UnsupportedAudioFileException | LineUnavailableException exc) {
        		exc.printStackTrace();
        	} catch (InterruptedException exc) {}
        }
    }
}