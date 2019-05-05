package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

    import interfaces;

    /** FlyBois that can be collected and used to purchase skins and backgrounds in the store.
     * @author JavaBoiz
     * @version v0.1 on 05.05.2019
     */
    public class StoreFlyBoi extends CollectableBoi
    {
        // properties
        Vector2 sFBPosition;
        Texture image;

        // constructors
        public StoreFlyBoi( Vector2 position)
        {
            super();

//    image.setImage( "image_String.png");
            sFBPosition = position;
        }

        // methods
        public Vector2 getPosition()
        {
            return sFBPosition;
        }

        public void setPosition( Vector2 position)
        {
            sFBPosition = position;
        }

        @Override
        public void performInteraction()
        {
            // Increase the static property (in the game loop) countStoreFlyBoi by 1.
            this.dispose();
        }
    }

