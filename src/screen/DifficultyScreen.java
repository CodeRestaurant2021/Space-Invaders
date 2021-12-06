package screen;

import java.awt.event.KeyEvent;

import engine.*;

/**
 * Implements the difficulty screen.
 *
 *
 */
public class DifficultyScreen extends Screen{

    /** Milliseconds between changes in user selection. */
    private static final int SELECTION_TIME = 200;

    /** Time between changes in user selection. */
    private Cooldown selectionCooldown;

    /** Status code for select difficulty */
    private int difficultyCode;

    /**
     * Constructor, establishes the properties of the screen.
     *
     * @param width
     *
     *            Screen width.
     * @param height
     *            Screen height.
     * @param fps
     *            Frames per second, frame rate at which the game is run.
     */
    public DifficultyScreen(final int width, final int height, final int fps) {
        super(width, height, fps);

        // Defaults to play.
        this.difficultyCode = 1;
        this.selectionCooldown = Core.getCooldown(SELECTION_TIME);
        this.selectionCooldown.reset();
        this.returnCode = 5;
    }

    /**
     * Starts the action.
     *
     * @return Next screen code.
     */
    public final int run() {
        super.run();

        return this.returnCode;
    }

    /**
     * Updates the elements on screen and checks for events.
     */
    protected final void update() {
        super.update();

        draw();
        if (this.selectionCooldown.checkFinished()
                && this.inputDelay.checkFinished()) {
            if (inputManager.isKeyDown(KeyEvent.VK_UP)
                    || inputManager.isKeyDown(KeyEvent.VK_W)) {
                previousMenuItem();
                this.selectionCooldown.reset();
            }
            if (inputManager.isKeyDown(KeyEvent.VK_DOWN)
                    || inputManager.isKeyDown(KeyEvent.VK_S)) {
                nextMenuItem();
                this.selectionCooldown.reset();
            }
            if (inputManager.isKeyDown(KeyEvent.VK_SPACE))
                this.isRunning = false;
        }
    }
    /**
     * Shifts the focus to the next menu item.
     */
    private void nextMenuItem() {
        if (this.difficultyCode == 3)
            this.difficultyCode = 1;
        else
            this.difficultyCode++;
    }

    /**
     * Shifts the focus to the previous menu item.
     */
    private void previousMenuItem() {
        if (this.difficultyCode == 1)
            this.difficultyCode = 3;
        else
            this.difficultyCode--;
    }

    public int getDifficultyCode(){
        return this.difficultyCode;
    }

    /**
     * Draws the elements associated with the screen.
     */
    private void draw() {
        drawManager.initDrawing(this);

        drawManager.drawDifficultyTitle(this);
        drawManager.drawDifficultyOption(this, this.difficultyCode);

        drawManager.completeDrawing(this);
    }
}
