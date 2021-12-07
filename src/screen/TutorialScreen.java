package screen;

import engine.Cooldown;
import engine.Core;

import java.awt.event.KeyEvent;

/**
 * Implements the manual screen.
 *
 *
 */

public class TutorialScreen extends Screen{

    /** Milliseconds between changes in user selection. */
    private static final int SELECTION_TIME = 200;

    /** Time between changes in user selection. */
    private Cooldown selectionCooldown;


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
    public TutorialScreen(final int width, final int height, final int fps) {
        super(width, height, fps);

        // Defaults to play.
        this.selectionCooldown = Core.getCooldown(SELECTION_TIME);
        this.selectionCooldown.reset();
        this.returnCode = 1;
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
            if (inputManager.isKeyDown(KeyEvent.VK_ESCAPE))
                this.isRunning = false;
        }
    }
    /**
     * Shifts the focus to the next menu item.
     */

    /**
     * Draws the elements associated with the screen.
     */
    protected void draw() {
        drawManager.initDrawing(this);

        drawManager.drawTutorialTitle(this);
        drawManager.drawTutorialMenu(this);

        drawManager.completeDrawing(this);
    }
}
