package com.nucleus.views.libGDXGraphics.viewables;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nucleus.model.particles.INucleon;
import com.nucleus.assetHandler.NAssetsData;
import com.nucleus.model.particles.Proton;
import com.nucleus.model.collision.Vector;
import com.nucleus.assetHandler.Assets;

import java.util.List;

/**
 * Created by erik on 28/04/16.
 */
public class NucleonViewable implements IViewable {

    private List<INucleon> nucleons;
    private Texture proton, neutron, protonLargeTrail, neutronLargeTrail;
    private TextureRegion protonLargeTrailRegion, neutronLargeTrailRegion;

    public NucleonViewable(List<INucleon> nucleons){

        this.nucleons = nucleons;

        //Adding textures
        proton = Assets.getTexture(NAssetsData.PROTON);//Assets.getTexture("proton.png");
        neutron = Assets.getTexture(NAssetsData.NEUTRON);//Assets.getTexture("neutron.png");
        protonLargeTrail = Assets.getTexture(NAssetsData.TAILPROTON2) ;//Assets.getTexture("tailProton2.png");
        neutronLargeTrail = Assets.getTexture(NAssetsData.TAILNEUTRON2);//Assets.getTexture("tailNeutron2.png");

        //Adding filters
        protonLargeTrail.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.MipMapLinearNearest);
        neutronLargeTrail.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.MipMapLinearNearest);
        proton.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        neutron.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        //Fitting rotated textures into TextureRegions
        protonLargeTrailRegion = new TextureRegion(protonLargeTrail, protonLargeTrail.getWidth(), protonLargeTrail.getHeight());
        neutronLargeTrailRegion = new TextureRegion(neutronLargeTrail, neutronLargeTrail.getWidth(), neutronLargeTrail.getHeight());

    }

    private int vectorToRadians(Vector v){

        double angle = Math.atan2(v.getY(), v.getX());
        double degrees = 180*angle/Math.PI;
        return (int) (90+Math.round(degrees))%360;

    }


    @Override
    public void render(SpriteBatch batch){

        batch.begin();
        batch.enableBlending();

        for(INucleon nucleon : nucleons){
            if (nucleon.getClass() == Proton.class){
                float x = nucleon.getPosition().getX();
                float y = nucleon.getPosition().getY();
                batch.draw(proton, x  - 2*nucleon.getRadius(),y  - 2*nucleon.getRadius());
                batch.draw(protonLargeTrailRegion,
                        //TODO fix 2px offset
                        x - nucleon.getRadius()-1,
                        y - 1,
                        (float) (nucleon.getRadius()),
                        0,
                        protonLargeTrail.getWidth(),
                        protonLargeTrail.getHeight(),
                        1.0f,
                        1.0f,
                       vectorToRadians(nucleon.getVelocity()));

            }
            else{
                float x = nucleon.getPosition().getX();
                float y = nucleon.getPosition().getY();
                batch.draw(neutron, x - 2*nucleon.getRadius(),  y - 2*nucleon.getRadius());
                batch.draw(neutronLargeTrailRegion,
                        //TODO fix 2px offset
                        x - nucleon.getRadius(),
                        y - 1,
                        (float) (nucleon.getRadius()),
                        0,
                        neutronLargeTrail.getWidth(),
                        neutronLargeTrail.getHeight(),
                        1.0f,
                        1.0f,
                       // 90);
                       vectorToRadians(nucleon.getVelocity()));
            }
        }
        batch.end();
    }


}