package com.ktplayer;
import org.junit.Test;
import org.testfx.util.WaitForAsyncUtils;

import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;

import static org.junit.Assert.assertEquals;

import java.io.File;   

public class ControllerTest extends TestBase{
	public Controller controller = new Controller();
	public TableView<Song>  songTable;
	String folderChooserId = "#folderChooser";
	String playButtonId = "#playButton";
	String pauseButtonId = "#pauseButton";
	String volumeValue = "#volumeValue";
	String volumeSlider = "#volumeSlider";
	String songSlider = "#songSlider";
	String nextSongButton = "#nextSongButton";
	String previousSongButton = "#previousSongButton";
	String muteIcon = "#muteIcon";
	String volumeIcon = "#volumeIcon";

	@Test
	public void playOrPauseSongTest() throws Exception {
		launchUInterfaceAndImportDirectory();
		updatePlayersMedia();
		songTable.getSelectionModel().select(0); 
		Song firstSong = songTable.getSelectionModel().getSelectedItem();
		Media media = controller.createMedia(firstSong.getUrl());
		updateControllerAttribute();
		controller.setCurrentlyPlayer(controller.players.get(1));
		controller.playPauseSong(firstSong);
		controller.volumeHandler();
		controller.volumeIconChanger();
		WaitForAsyncUtils.waitForFxEvents(1000);   
	}

//	@Test
//	public void importTest() throws Exception {
//		launchUInterfaceAndImportDirectory();
//		assertEquals(songTable.getItems().size(), 2);  	
//	}

	public void launchUInterfaceAndImportDirectory() {
		clickOn(folderChooserId);
		WaitForAsyncUtils.waitForFxEvents(300);
		songTable = (TableView<Song>) find("#songTable");
		//         C:\Users\Administrator\Desktop\ETS\AAH22\MGL846\code_source\ktPlayer-Music-Player\src\main\resources\music
	}

	public void updatePlayersMedia() {
		for (Song song : songTable.getSelectionModel().getTableView().getItems()) {
			File file = new File(song.getUrl());
			controller.players.add(controller.createPlayer(file.getAbsolutePath()));
		}
	}
	public void updateControllerAttribute() {
		controller.pauseButton = find(pauseButtonId);
		controller.playButton = find(playButtonId);
		controller.volumeSlider = find(volumeSlider);
		controller.volumeValue = find(volumeValue);
		controller.songSlider = find(songSlider);
		controller.nextSongButton = find(nextSongButton);
		controller.previousSongButton = find(previousSongButton);
		controller.muteIcon = find(muteIcon);
		controller.volumeIcon = find(volumeIcon);
		controller.albumName = new Label();
		controller.artistName= new Label();
		controller.songName= new Label();
		controller.currentDuration= new Label();
	}
}
