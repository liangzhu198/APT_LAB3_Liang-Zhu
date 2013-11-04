/**
 * Excerpted from the book, "Pragmatic Unit Testing"
 * ISBN 0-9745140-1-2
 * Copyright 2003 The Pragmatic Programmers, LLC.  All Rights Reserved.
 * Visit www.PragmaticProgrammer.com
 */

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.util.ArrayList;

import junit.framework.TestCase;

public class TestMp3Player1 extends TestCase {

  protected Mp3Player mp3;
  protected ArrayList<String> list  = new ArrayList<String>();;

  public void setUp() {
    mp3 = createMock(Mp3Player.class);
    
    list = new ArrayList<String>();
    list.add("Bill Chase -- Open Up Wide");
    list.add("Jethro Tull -- Locomotive Breath");
    list.add("The Boomtown Rats -- Monday");
    list.add("Carl Orff -- O Fortuna");
  }

  public void testPlay() {
	//set
	mp3.loadSongs(list);
	expectLastCall();
	mp3.play();
	expectLastCall();
    mp3.pause();	
	expectLastCall();
    mp3.stop();
	expectLastCall();
	expect(mp3.isPlaying()).andReturn(false);
	expect(mp3.isPlaying()).andReturn(true);
	expect(mp3.currentPosition()).andReturn(0.1);
    expect(mp3.currentPosition()).andReturn(0.1);
    expect(mp3.currentPosition()).andReturn(0.1);
	replay(mp3);

	//verify
	mp3.loadSongs(list);
    assertFalse(mp3.isPlaying());
	mp3.play();
    assertTrue(mp3.isPlaying());
    assertTrue(mp3.currentPosition() != 0.0);
    mp3.pause();
    assertTrue(mp3.currentPosition() != 0.0);
    mp3.stop();
    assertEquals(mp3.currentPosition(), 0.0, 0.1);
    
	verify(mp3);
  }

  public void testPlayNoList() {

    //set
	mp3.play();
	expectLastCall();
    mp3.pause();	
	expectLastCall();
    mp3.stop();
	expectLastCall();
	expect(mp3.isPlaying()).andReturn(false).times(2);
	expect(mp3.currentPosition()).andReturn(0.1);
	expect(mp3.currentPosition()).andReturn(0.1);
	expect(mp3.currentPosition()).andReturn(0.1);
	replay(mp3);

	//verify
    assertFalse(mp3.isPlaying());
    mp3.play();
    assertFalse(mp3.isPlaying());
    assertEquals(mp3.currentPosition(), 0.0, 0.1);
    mp3.pause();
    assertEquals(mp3.currentPosition(), 0.0, 0.1);
    mp3.stop();
    assertEquals(mp3.currentPosition(), 0.0, 0.1);
	verify(mp3);
  }

  public void testAdvance() {
	//set
	mp3.loadSongs(list);  
	expectLastCall();
    mp3.play();
	expectLastCall();
	mp3.prev();
	expectLastCall().times(2);
	mp3.next();
	expectLastCall().times(5);
	expect(mp3.isPlaying()).andReturn(true).times(3);
	expect(mp3.currentSong()).andReturn("Bill Chase -- Open Up Wide");
	expect(mp3.currentSong()).andReturn("Jethro Tull -- Locomotive Breath");
	expect(mp3.currentSong()).andReturn("The Boomtown Rats -- Monday");
	expect(mp3.currentSong()).andReturn("Jethro Tull -- Locomotive Breath");
	expect(mp3.currentSong()).andReturn("The Boomtown Rats -- Monday");
	expect(mp3.currentSong()).andReturn("Carl Orff -- O Fortuna");
	expect(mp3.currentSong()).andReturn("Carl Orff -- O Fortuna");
	replay(mp3);

	//verify
	mp3.loadSongs(list);

    mp3.play();

    assertTrue(mp3.isPlaying());

    mp3.prev();
    assertEquals(mp3.currentSong(), list.get(0));
    assertTrue(mp3.isPlaying());

    mp3.next();
    assertEquals(mp3.currentSong(), list.get(1));
    mp3.next();
    assertEquals(mp3.currentSong(), list.get(2));
    mp3.prev();

    assertEquals(mp3.currentSong(), list.get(1));
    mp3.next();
    assertEquals(mp3.currentSong(), list.get(2));
    mp3.next();
    assertEquals(mp3.currentSong(), list.get(3));
    mp3.next();
    assertEquals(mp3.currentSong(), list.get(3));
    assertTrue(mp3.isPlaying());
	verify(mp3);
  }

}