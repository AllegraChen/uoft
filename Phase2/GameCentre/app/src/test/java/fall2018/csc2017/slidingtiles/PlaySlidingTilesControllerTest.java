package fall2018.csc2017.slidingtiles;

import android.widget.Button;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class PlaySlidingTilesControllerTest {

    SlidingTilesManager slidingTilesManager;
    User user;
    ArrayList<Button> tileButtons;

    PlaySlidingTilesController playSlidingTilesController = new PlaySlidingTilesController();

    /**
     * Make a set of tiles that are in order.
     * @return a set of tiles that are in order
     */
    private List<SlidingTile> makeTiles() {
        List<SlidingTile> tiles = new ArrayList<>();
        final int numTiles = SlidingTilesBoard.NUM_ROWS * SlidingTilesBoard.NUM_COLS;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            if (tileNum == numTiles - 1) {
                tiles.add(new SlidingTile(tileNum, tileNum));
            } else {
                tiles.add(new SlidingTile(tileNum));
            }
        }

        return tiles;
    }


    private void setUpCorrect() {
        user = new User("Test", "Test");

        SlidingTilesBoard.setDimensions(4);
        List<SlidingTile> tiles = makeTiles();
        SlidingTilesBoard newBoard = new SlidingTilesBoard(tiles);
        slidingTilesManager = new SlidingTilesManager();
        slidingTilesManager.setBoard(newBoard);
        SetUpActivity.undoLimit = 3;
        user.setRecentManagerOfBoard(SlidingTilesManager.GAME_NAME, slidingTilesManager);

        GameLauncher.setCurrentUser(user);


    }

    @Test
    public void TestCreateAndUpdateTileButtons() {
        setUpCorrect();
    }

    @Test
    public void TestUsedNumberOfUndos() {
        setUpCorrect();

        //no moves have been completed so no undos can be performed
        assertEquals("NoUndoText", playSlidingTilesController.usedNumberOfUndos());

        //max amount of undos have been performed
        GameLauncher.getCurrentUser().setNumOfUndos(SlidingTilesManager.GAME_NAME, 5);
        assertEquals("UndoLimitText", playSlidingTilesController.usedNumberOfUndos());

        //has done a move and not reached limit of undos so can perform undo
//        slidingTilesManager.touchMove(14);
//        System.out.println(user.getStackOfGameStates(SlidingTilesManager.GAME_NAME));
//        assertEquals("NoUndoText", playSlidingTilesController.usedNumberOfUndos());
    }

    @Test
    public void TestEndOfGame() {
        setUpCorrect();
        ArrayList arrayList = new ArrayList();
        arrayList.add(true);
        arrayList.add(true);
        assertEquals(arrayList, playSlidingTilesController.endOfGame(slidingTilesManager));
    }

    @Test
    public void TestIncrementAndGetNumberOfMoves() {
        setUpCorrect();
        PlaySlidingTilesController.incrementNumberOfMoves();
        assertEquals(1, playSlidingTilesController.getNumberOfMoves());
    }

    @Test
    public void TestGetNumberOfUndos() {
        setUpCorrect();
        assertEquals(5, playSlidingTilesController.getNumberOfUndos());
    }
}
