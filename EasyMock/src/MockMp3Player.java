import java.util.ArrayList;

public class MockMp3Player implements Mp3Player {
	private ArrayList<String> list = null;
	private double curPosition = 0.0;
	private int index = 0;
	private boolean flag = false;

	public void play(){
		if (list != null){
			flag = true;
			curPosition = 0.1;
		}
	}

	public void pause(){
		flag = false;
	}

	public void stop(){
		flag = false;
	}

	public double currentPosition(){
		return curPosition;
	}
	
	 public String currentSong(){
		if (list == null){
			return null;
		} else{
			return list.get(index);
		}
	}
	 
	public void next() {
		if (index != (list.size()-1)){
			index = index + 1;
		} 
	}

	public void prev(){
		if (index != 0){
			index = index - 1;
		}
	}
	public boolean isPlaying(){
		return flag;
	}
	public void loadSongs(ArrayList<String> names){
		list = names;
		curPosition = 0.0;
	}
	
}