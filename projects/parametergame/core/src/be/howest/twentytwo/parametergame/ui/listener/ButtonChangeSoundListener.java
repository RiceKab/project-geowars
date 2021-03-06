/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.twentytwo.parametergame.ui.listener;

import be.howest.twentytwo.parametergame.audio.SoundSequencer;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 *
 * @author Floris
 */
public class ButtonChangeSoundListener extends ChangeListener {
	private SoundSequencer sounds;

	public ButtonChangeSoundListener(SoundSequencer soundSequencer) {
		this.sounds = soundSequencer;
	}

	@Override
	public void changed(ChangeEvent event, Actor actor) {
		sounds.addSound("sound/button_click.wav");
	}

}
