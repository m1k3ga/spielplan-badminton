package org.m1k3ga.badminton.spielplan.metrics;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MetricsTest {

	@Test
	public void gamesPlayedSeasonBeforeFirstGame() {
		Metrics metrics = new Metrics();
		assertEquals(0,metrics.getGamesPlayedSeason());
	}


	@Test
	public void gamesPlayedTodayBeforeFirstGame() {
		Metrics metrics = new Metrics();
		assertEquals(0,metrics.getGamesPlayedToday());
	}

	@Test
	public void ScoreBeforeFirstGame() {
		Metrics metrics = new Metrics();
		assertEquals(0,metrics.getScore());
	}
	
	@Test
	public void playOneGame() {
		// Given
		Metrics metrics = new Metrics();
		int gamesPlayedToday = metrics.getGamesPlayedToday();
		final int gamesPlayedSeason = metrics.getGamesPlayedSeason();

		// When
		metrics.gamePlayed();
		
		// Then
		assertEquals(gamesPlayedToday+1,metrics.getGamesPlayedToday());
		assertEquals(gamesPlayedSeason+1,metrics.getGamesPlayedToday());
	}

	@Test
	public void playTwoGames() {
		// Given
		Metrics metrics = new Metrics();
		int gamesPlayedToday = metrics.getGamesPlayedToday();
		final int gamesPlayedSeason = metrics.getGamesPlayedSeason();

		// When
		metrics.gamePlayed();
		metrics.gamePlayed();
		
		// Then
		assertEquals(gamesPlayedToday+2,metrics.getGamesPlayedToday());
		assertEquals(gamesPlayedSeason+2,metrics.getGamesPlayedToday());
	}

}