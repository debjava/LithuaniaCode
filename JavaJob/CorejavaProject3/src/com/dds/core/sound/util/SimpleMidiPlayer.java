package com.dds.core.sound.util;
import java.io.File;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Receiver;
import javax.sound.midi.Transmitter;

public class SimpleMidiPlayer 
{
	private static Sequencer	sm_sequencer = null;
	private static Synthesizer	sm_synthesizer = null;

	public void playSound( String filePath )
	{
		File	midiFile = new File( filePath );
		Sequence	sequence = null;
		try
		{
			sequence = MidiSystem.getSequence(midiFile);
		}
		catch (InvalidMidiDataException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.exit(1);
		}

		try
		{
			sm_sequencer = MidiSystem.getSequencer();
		}
		catch (MidiUnavailableException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		if (sm_sequencer == null)
		{
			System.exit(1);
		}

		
		try
		{
			sm_sequencer.open();
		}
		catch (MidiUnavailableException e)
		{
			e.printStackTrace();
			System.exit(1);
		}

		try
		{
			sm_sequencer.setSequence(sequence);
		}
		catch (InvalidMidiDataException e)
		{
			e.printStackTrace();
			System.exit(1);
		}

		
		if (! (sm_sequencer instanceof Synthesizer))
		{
			
			try
			{
				sm_synthesizer = MidiSystem.getSynthesizer();
				sm_synthesizer.open();
				Receiver	synthReceiver = sm_synthesizer.getReceiver();
				Transmitter	seqTransmitter = sm_sequencer.getTransmitter();
				seqTransmitter.setReceiver(synthReceiver);
			}
			catch (MidiUnavailableException e)
			{
				e.printStackTrace();
			}
		}

		sm_sequencer.start();
	}

	public static void main(String[] args)
	{
		
		String	strFilename = "C:/All wav audio files/DingDong.wav";//"C:/town.mid";
//		File	midiFile = new File(strFilename);
		new SimpleMidiPlayer().playSound( strFilename );

		

}
	
}
