package fr.noctu.haxx.proto.utils;

public class TimeHelper {
	
	private long lastMS = 0L;
	
	public boolean isDelayComplete(float f){
		if(System.currentTimeMillis() - this.lastMS >- f){
			return true;
		}
		return false;
	}
	
	public long getCurrentMS(){
		return System.nanoTime() / 1000000L;
	}
	
	public void setLastMS(long lastMS){
		this.lastMS = System.currentTimeMillis();
	}
	
	public int convertToMS(int perSecond){
		return 1000/perSecond;
	}
	
	public boolean hasReached(long milliseconds){
		return getCurrentMS() - lastMS >= milliseconds;
	}
	
	public void reset(){
		lastMS = getCurrentMS();
	}
	
	public void reset(long offset){
		lastMS = getCurrentMS() - offset;
	}
}
