package br.com.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
class LoginWindowTest {
	private Robot robot;
	private FrameFixture window;

	@BeforeEach
	void setUp() {
		robot = BasicRobot.robotWithNewAwtHierarchy();
		robot.settings().delayBetweenEvents(80); // 80 or 200

		LoginWindow frame = GuiActionRunner.execute(() -> new LoginWindow() {});
		window = new FrameFixture(robot, frame);
		window.show();
	}

	@Test
	void shouldBlockComponentsAfterThreeFailedAttempts() {
		for (int i = 0; i < 3; i++) {
			window.textBox("userField").deleteText().enterText("admin");
			window.textBox("passwordField").deleteText().enterText("123");
			window.button("loginButton").click();

			JOptionPaneFixture optionPane = JOptionPaneFinder.findOptionPane().using(robot);
			optionPane.requireMessage("Incorrect username or password !");
			optionPane.requireTitle("Error");
			optionPane.okButton().click();
		}

		window.textBox("userField").requireDisabled();
		window.textBox("passwordField").requireDisabled();
		window.button("loginButton").requireDisabled();
		window.button("forgotPasswordButton").requireDisabled();
		assertEquals("Blocked", window.label("blockingNoticeLabel").text());
	}

	@AfterEach
	void tearDown() {
		window.cleanUp();
		robot.cleanUp();
	}
}
