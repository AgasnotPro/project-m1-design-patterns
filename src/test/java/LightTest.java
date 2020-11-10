import org.example.models.Light;
import org.example.models.Thing;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class LightTest {
    Light light;

    @Before
    public void before() {
        light = new Light();
    }

    @Test
    public void setLightOnReachableStateSuccess() {
        Assert.assertFalse(light.isLightOn());
        Assert.assertEquals(Thing.State.REACHABLE, light.getState());

        light.setLightOn(true);

        Assert.assertTrue(light.isLightOn());
    }

    @Test
    public void setLightOnUnreachableStateSuccess() {
        light.setState(Thing.State.UNREACHABLE);

        light.setLightOn(true);

        Assert.assertFalse(light.isLightOn());
    }

    @Test
    public void setLightShouldTriggerListener() {
        Light.OnLightChangedListener listener = Mockito.mock(Light.OnLightChangedListener.class);

        light.setLightChangedListener(listener);
        light.setLightOn(true);

        Mockito.verify(listener).onLightChanged(light);
    }

}
