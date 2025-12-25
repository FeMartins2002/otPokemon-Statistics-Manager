package br.com.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoginAttemptControllerTest {
	public static LoginAttemptController controller;

	@BeforeEach
	void initializeAttemptsController() {
		controller = new LoginAttemptController(3);
	}

	@Test
	void registerFailedAttemptTest() {
		int attempts = controller.getRemainingAttempts();
		controller.registerFailedAttempt();
		assertEquals(attempts - 1, controller.getRemainingAttempts());
	}

	@Test
	void registerFailedAttemptShouldReturnFalseAtZero() {
		controller.registerFailedAttempt();
		controller.registerFailedAttempt();
		boolean result = controller.registerFailedAttempt();
		assertFalse(result);
	}

	@Test
	void resetAttemptTest() {
		controller.registerFailedAttempt();
		controller.registerFailedAttempt();
		controller.resetAttempts();
		assertEquals(3, controller.getRemainingAttempts());
		assertFalse(controller.isBlocked());
	}

	@Test
	void isBlockedTest() {
		controller.registerFailedAttempt();
		controller.registerFailedAttempt();
		controller.registerFailedAttempt();
		assertTrue(controller.isBlocked());
	}
}
