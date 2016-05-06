package com.nucleus.Model;

import java.util.List;

public interface ILevel {

    List<INucleon> getAirborneNucleons();
    void addAirborneNucleon(INucleon nucleon);
    INucleonGun getNucleonGun();
    void outOfBoundsCheck();
    void update(float delta);


}