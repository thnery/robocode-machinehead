package com.thnery;
import robocode.*;
import java.awt.Color;

/**
 * MachineHead - a robot by thnery
 */
public class MachineHead extends Robot {
	boolean peek;
	double distance;

	public void run() {
		// Set Machine Head War Paint
		setColors(Color.white, Color.red, Color.black);

		// Set distance to move
		distance = Math.max(getBattleFieldWidth(), getBattleFieldHeight());
		peek = false;

		// Go to the first wall
		turnLeft(getHeading() % 90);
		ahead(distance);

		// Prepare guns
		peek = true;
		turnGunRight(0);
		turnLeft(90);

		// Time to kill
		while(true) {
			peek = true;
			ahead(distance);
			peek = false;

			turnLeft(90);
		}
	}

	public void onHitRobot(HitRobotEvent event) {
		if (event.getBearing() > -90 && event.getBearing() < 90) {
			back(100);
		} else {
			ahead(100);
		}
	}

	public void onScannedRobot(ScannedRobotEvent event) {
		fire(6);
		if (peek) { scan(); }
	}
}