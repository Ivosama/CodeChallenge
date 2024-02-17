package com.idts.codechallenge.api;

import com.idts.codechallenge.api.constants.Game;
import com.idts.codechallenge.api.model.Player;
import jakarta.validation.constraints.AssertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class CodeChallengeApplicationTests {
	private Player player;

	@Test
	void contextLoads() {
	}

	@Test
	public void testIfGameStringConstructorWorks() {
		Game game = Game.DIABLO;

//		Game game2 = Game.findByName(game.getName());
//		Assert.isTrue(game2.getDeclaringClass().isMemberClass(), "This test is useless.");

	}

	@Test
	public void testPlayerCanBeAdded() {

	}


}
