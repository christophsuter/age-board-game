package ch.hslu.testing.boundry;

/**
 * Created by Christoph on 04.04.2017.
 */
public class Ninja {

    private final String name;
    private final String preferedWeapon;

    public Ninja(String name, String preferedWeapon) {
        this.name = name;
        this.preferedWeapon = preferedWeapon;
    }

    public String getName() {
        return name;
    }

    public String getPreferedWeapon() {
        return preferedWeapon;
    }
}
