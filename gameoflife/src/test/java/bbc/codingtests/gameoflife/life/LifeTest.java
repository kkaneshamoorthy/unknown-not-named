package bbc.codingtests.gameoflife.life;

import bbc.codingtests.gameoflife.gamestate.GameState;
import bbc.codingtests.gameoflife.gamestate.GameStateImpl;
import bbc.codingtests.gameoflife.life.Life;
import bbc.codingtests.gameoflife.life.LifeImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class LifeTest {

	private String input;
	private String expectedOutput;

	public LifeTest(String input, String expectedOutput) {
		this.input = input;
		this.expectedOutput = expectedOutput;
	}

	@Parameterized.Parameters
	public static Collection inputData() {
		return Arrays.asList( new Object[][] {
				{		"....*....\n" +
						"....*....\n" +
						"....*....\n" +
						"....*....\n" +
						"....*....\n" +
						"....*....\n" +
						"....*....\n" +
						"....*....",

						".........\n" +
						"...***...\n" +
						"...***...\n" +
						"...***...\n" +
						"...***...\n" +
						"...***...\n" +
						"...***...\n" +
						"........."
				},
				{
						"...\n" +
						"***\n" +
						"...",

						".*.\n" +
						".*.\n" +
						".*."
				},
				{
						"..........\n" +
						"..........\n" +
						"****......\n" +
						"...*......\n" +
						"...*......",

						"..........\n" +
						".**.......\n" +
						".***......\n" +
						".*.**.....\n" +
						".........."
				}
		});
	}

	@Test
	public void testParameterizedInputs() {
		Life testLife = new LifeImpl();
		GameState gameState = new GameStateImpl(this.input);
		GameState evolvedState = testLife.evolve(gameState);
		assertEquals("The game state after evolve should be different", this.expectedOutput, evolvedState.toString());
	}

	@Test
	public void testEmptyGrid() {
		String emptyStateInput = "...\n...\n...";

		Life testLife = new LifeImpl();
		GameState emptyState = new GameStateImpl(emptyStateInput);
		assertEquals("An empty grid should stay the same", emptyStateInput, testLife.evolve(emptyState).toString());
	}

	@Test
	public void testScenario0_noLiveCell_returnsNoLiveCells() {
		String deadcells = "...\n...\n...";
		String expectedOutput = "...\n...\n...";

		Life testLife = new LifeImpl();
		GameState deadCellState = new GameStateImpl(deadcells);
		assertEquals("No live cells should evolves to a game state with no live cells", expectedOutput, testLife.evolve(deadCellState).toString());
	}

	@Test
	public void testScenario1_underpopulation() {
		String underPopulationInput = "*.*\n...\n...";
		String expectedOutput = "...\n...\n...";

		Life testLife = new LifeImpl();
		GameState underPopulationState = new GameStateImpl(underPopulationInput);
		assertEquals("Underpopulation grid should lead to dead cell", expectedOutput, testLife.evolve(underPopulationState).toString());
	}

	@Test
	public void testScenario6_withTwoEvolveCall() {
		String input = "...\n***\n...";
		String expectedOutput = ".*.\n.*.\n.*.";

		Life testLife = new LifeImpl();
		GameState gameState = new GameStateImpl(input);
		GameState evolvedState = testLife.evolve(gameState);
		assertEquals("The game state after evolve should be different", expectedOutput, evolvedState.toString());
		assertEquals("The game state after two evolve should be same as input", input, testLife.evolve(evolvedState).toString());
	}

	@Test
	public void testNullGameStateImpl() {
		Life testLife = new LifeImpl();
		GameState nullState = null;
		assertEquals("Over Population grid should lead to dead cell", null, testLife.evolve(nullState));
	}

	@Test
	public void testShouldAliveCellLive_withLive3Neighbours() {
		Life life = new LifeImpl();

	}
}
