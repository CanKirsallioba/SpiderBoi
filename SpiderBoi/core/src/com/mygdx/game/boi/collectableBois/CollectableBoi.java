package com.mygdx.game.boi.collectableBois;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.boi.Boi;
import com.mygdx.game.interfaces.Interactable;

/** Super class of non-moving and collectable FlyBois.
 * @author JavaBoiz
 * @version 0.1 on 05.05.2019
 */

public class CollectableBoi extends Boi implements Interactable
{
    // properties
    SpriteBatch cBSpriteBatch;

    // constructors
    public CollectableBoi()
    {
        super();
        cBSpriteBatch = new SpriteBatch();
    }

    // methods
    public void drawCharacter()
    {
        cBSpriteBatch.begin();
        cBSpriteBatch.draw( image, 0, 0);
        cBSpriteBatch.end();
    }

    public void performInteraction() {}
}


