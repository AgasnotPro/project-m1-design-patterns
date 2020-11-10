import org.example.HomeSystem;
import org.example.SystemLogger;
import org.example.models.Light;
import org.example.models.Thing;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class HomeSystemTest {
    private SystemLogger logger;
    private HomeSystem homeSystem;

    @Before
    public void before(){
        logger = Mockito.mock(SystemLogger.class);
        homeSystem = new HomeSystem(logger);
    }

    @Test
    public void thingsEmptyAtInitial() {
        Assert.assertTrue(homeSystem.getThings().isEmpty());
    }
    @Test
    public void addLightSuccess() {
        homeSystem.addThing(new Light());
        Assert.assertEquals(1, homeSystem.getThings().size());
    }

    @Test
    public void toggleLightsWhenHomeSystemIsOnSuccess() {
        homeSystem.addThing(new Light());
        homeSystem.addThing(new Light());

        homeSystem.toggleAllLights(true);
        for (Light l:homeSystem.getLights()) {
            Assert.assertTrue(l.isLightOn());
        }
    }

    @Test
    public void toggleLightsWenHomeSystemIsOffFail() {
        homeSystem.addThing(new Light());
        homeSystem.addThing(new Light());
        homeSystem.setState(HomeSystem.State.OFF);

        homeSystem.toggleAllLights(true);

        for (Light l:homeSystem.getLights()) {
            Assert.assertFalse(l.isLightOn());
        }
    }
    @Test
    public void toggleLightTriggersHomeSystemAddLogSuccess() {
        Light light = new Light();
        light.setLightChangedListener(homeSystem);

        light.setLightOn(true);

        //vérif  qu'un log est ajouté dans le syst.
        /*En fait ce qu'on veut vérifier c'est juste que la méthode
        addLog a été appelée et cela est possible grâce au Mockito*/
        Mockito.verify(logger).addLog(Mockito.anyString()); /*
        Mockito.anyString() équivaut à entrer n'importe quelle
        chaine de caractère car on se fiche de savoir quel message on entre,
        on veut juste savoir si ça a bien été appelé*/
    }

}
