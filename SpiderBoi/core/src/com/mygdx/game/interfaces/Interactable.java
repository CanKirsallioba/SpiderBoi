package com.mygdx.game.interfaces;


import com.mygdx.game.SpiderBoiGame;
import com.mygdx.game.boi.SpiderBoi;

/** Used for distinguishing objects that are interactable.
 * @author JavaBoiz
 * @version 0.1 on 05.05.2019
 */
public interface Interactable
{
    /**
     * This method performs the interaction of the desired
     * object.
     */
    public void performInteraction(SpiderBoiGame game);
}
