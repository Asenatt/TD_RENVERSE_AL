package domain;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import factory.BotFactory;
import factory.DirectWinBotFactory;
import factory.HumanBotFactory;
import ui.TextBuilder;

class P4Tests {
	static Puissance4 board;
	static BotFactory directWinBotFactory; 
	static BotFactory humanBotFactory; 
	static P4Player p1;
	static P4Player p2;
	static TextBuilder bld;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		board = Puissance4Impl.getInstance();
		directWinBotFactory = new DirectWinBotFactory();
		humanBotFactory = new HumanBotFactory();
		p1 = humanBotFactory.createBot(board);
		
		p2 = directWinBotFactory.createBot(board);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	/*
	 * Tests if game is instantiated correctly
	 * */
	@Test
	void testGameLaunching() {
		assert(board != null);
	}
	
	/*
	 * Tests if game initialization is correct
	 * */
	@Test
	void testGameInit() {
		board.init(p1, p2);
		bld = new TextBuilder();
		board.buildPuissance4(bld);
		assertEquals(bld.getPuissance4(), "***************\n" + 
				"| | | | | | | |\n" + 
				"| | | | | | | |\n" + 
				"| | | | | | | |\n" + 
				"| | | | | | | |\n" + 
				"| | | | | | | |\n" + 
				"| | | | | | | |\n" + 
				"| | | | | | | |\n" + 
				"***************\n");
	}
	/*
	 * Tests if move is played correctly
	 * */
	@Test
	void testMove() {
		board.init(p1, p2);
		bld = new TextBuilder();
		board.play(0);
		board.play(0);
		board.play(5);
		board.buildPuissance4(bld);
		assertEquals(bld.getPuissance4(), "***************\n" + 
				"| | | | | | | |\n" + 
				"| | | | | | | |\n" + 
				"| | | | | | | |\n" + 
				"| | | | | | | |\n" + 
				"| | | | | | | |\n" + 
				"|O| | | | | | |\n" + 
				"|X| | | | |X| |\n" + 
				"***************\n");
		
	}
	
	/*
	 * Tests if column is winning
	 * */
	@Test 
	void testGameOverColumn() {
		board.init(p1, p2);
		for(int i = 0; i <= 2; ++i) {
			board.play(0);
			board.play(1);
		}
		board.play(0);
		assert(board.isFinished());
	}
	
	/*
	 * Tests if line is winning
	 * */
	@Test
	void testGameOverLine() {
		board.init(p1, p2);
		for(int i = 0; i <= 2; ++i) {
			board.play(i);
			board.play(0);
		}
		board.play(3);
		assert(board.isFinished());
	}
	
	/*
	 * Tests if diagonal is winning
	 * */
	@Test 
	void testGameOverDiagonal1() {
		board.init(p1, p2);
		board.play(0);//human
		board.play(1);
		
		board.play(1);//human
		board.play(2);
		
		board.play(2);//human
		board.play(3);
		
		board.play(2);//human
		board.play(3);
		
		board.play(0);//human
		board.play(3);
		
		board.play(3);//human
		
		assert(board.isFinished());
	}
	@Test
	void testGameOverDiagonal2() {
		board.init(p1, p2);
		board.play(6);//human
		board.play(5);
		
		board.play(5);//human
		board.play(4);
		
		board.play(4);//human
		board.play(3);
		
		board.play(4);//human
		board.play(3);
		
		board.play(6);//human
		board.play(3);
		
		board.play(3);//human
		
		assert(board.isFinished());
	}

}
