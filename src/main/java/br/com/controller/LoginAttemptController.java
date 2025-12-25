package br.com.controller;

public class LoginAttemptController {
	private int attempts;
	private final int maxAttempts;

	public LoginAttemptController(int maxAttempts) {
		this.maxAttempts = maxAttempts;
		this.attempts = maxAttempts;
	}

	public boolean registerFailedAttempt() {
		attempts--;
		return attempts > 0;
	}

	public int getRemainingAttempts() {
		return attempts;
	}

	public void resetAttempts() {
		attempts = maxAttempts;
	}

	public boolean isBlocked() {
		return attempts <= 0;
	}
}
