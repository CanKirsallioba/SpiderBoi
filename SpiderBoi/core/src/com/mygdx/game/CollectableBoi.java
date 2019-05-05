package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CollectableBoi {
    import interfaces;

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
            spriteBatch.begin();
            spriteBatch.draw( image, 0, 0);
            spriteBatch.end();
        }

        public void performInteraction() {}
    }

}
