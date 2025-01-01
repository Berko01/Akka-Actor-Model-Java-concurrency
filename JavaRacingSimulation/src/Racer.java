import java.util.Map;
import java.util.Random;

public class Racer implements Runnable{

	private final int id;
	
	private final int raceLength;
	private final Map<Integer, Integer> currentPositions;
	private final Map<Integer, Long> results;

	private final double defaultAverageSpeed = 48.2;
	private final int averageSpeedAdjustmentFactor;
	private final Random random;
	
	private double currentSpeed = 0;
	private double currentPosition = 0;
	
	
	public Racer(int id, int raceLength, Map<Integer, Integer> currentPositions, Map<Integer, Long> results) {
		this.id = id;
		this.raceLength = raceLength;
		this.currentPositions = currentPositions;
		this.results = results;
		random = new Random();
		averageSpeedAdjustmentFactor = random.nextInt(30) - 10;
	}
	
	private double getMaxSpeed() {
		return defaultAverageSpeed * (1+((double)averageSpeedAdjustmentFactor / 100));
	}
		
	private double getDistanceMovedPerSecond() {
		return currentSpeed * 1000 / 3600;
	}
	
	private void determineNextSpeed() {
		if (currentPosition < ((double) raceLength / 4)) {
			currentSpeed = currentSpeed  + (((getMaxSpeed() - currentSpeed) / 10) * random.nextDouble());
		}
		else {
			currentSpeed = currentSpeed * (0.5 + random.nextDouble());
		}
	
		if (currentSpeed > getMaxSpeed()) 
			currentSpeed = getMaxSpeed();
		
		if (currentSpeed < 5)
			currentSpeed = 5;
		
		if (currentPosition > ((double) raceLength / 2) && currentSpeed < getMaxSpeed() / 2) {
			currentSpeed = getMaxSpeed() / 2;
		}
	}
		

	@Override
	public void run() {
		
		while (currentPosition < raceLength) {
			determineNextSpeed();
			currentPosition += getDistanceMovedPerSecond();
			if (currentPosition > raceLength )
				currentPosition  = raceLength;
			currentPositions.put(id, (int)currentPosition);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
		results.put(id, System.currentTimeMillis());
		
	}
	
}
