package br.com.view;

import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.finder.JOptionPaneFinder;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JOptionPaneFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
class ChangePasswordWindowTest {
	private Robot robot;
	private FrameFixture window;

	@BeforeEach
	void setUp() {
		robot = BasicRobot.robotWithNewAwtHierarchy();
		robot.settings().delayBetweenEvents(200); // 80 or 200

		ChangePasswordWindow frame = GuiActionRunner.execute(() -> new ChangePasswordWindow() {});
		window = new FrameFixture(robot, frame);
		window.show();
	}

	@Test
	void shouldBlockComponentsAfterThreeFailedAttempts() {
		window.button("changePasswordButton").click();
		JOptionPaneFixture optionPane = JOptionPaneFinder.findOptionPane().using(robot);
		optionPane.requireMessage("Fill in all fields !");
		optionPane.requireTitle("Attention");
		optionPane.okButton().click();

		window.textBox("passwordField").deleteText().enterText("123");
		window.textBox("repeatField").deleteText().enterText("321");
		window.checkBox("showPassword").click();
		window.button("changePasswordButton").click();

		optionPane = JOptionPaneFinder.findOptionPane().using(robot);
		optionPane.requireMessage("Passwords do not match !");
		optionPane.requireTitle("Attention");
		optionPane.okButton().click();
	}

	@AfterEach
	void tearDown() {
		window.cleanUp();
		robot.cleanUp();
	}
}
