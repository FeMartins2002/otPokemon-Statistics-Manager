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
class AuthenticationWindowTest {
	private Robot robot;
	private FrameFixture window;

	@BeforeEach
	void setUp() {
		robot = BasicRobot.robotWithNewAwtHierarchy();
		robot.settings().delayBetweenEvents(200); // 80 or 200

		AuthenticationWindow frame = GuiActionRunner.execute(() -> new AuthenticationWindow("") {});
		window = new FrameFixture(robot, frame);
		window.show();
	}

	@Test
	void shouldBlockComponentsAfterThreeFailedAttempts() {
		window.button("sendResponseButton").click();

		JOptionPaneFixture optionPane = JOptionPaneFinder.findOptionPane().using(robot);
		optionPane.requireMessage("Fill in the field !");
		optionPane.requireTitle("Attention");
		optionPane.okButton().click();

		window.textBox("responseField").deleteText().enterText("Onix");
		window.button("sendResponseButton").click();

		optionPane = JOptionPaneFinder.findOptionPane().using(robot);
		optionPane.requireMessage("Incorrect answer !");
		optionPane.requireTitle("Error");
		optionPane.okButton().click();
	}

	@AfterEach
	void tearDown() {
		window.cleanUp();
		robot.cleanUp();
	}
}
