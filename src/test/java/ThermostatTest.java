import org.example.models.Thermostat;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class ThermostatTest {
    private Thermostat thermostat;
    @Before
    public void before() {
        thermostat = new Thermostat(0,100);
    }

    @Test
    public void setTemperatureWithValidRangeSuccess(){
        Assert.assertEquals(thermostat.getMinTemperature(),thermostat.getTemperature());
        boolean changed = thermostat.setTemperature(thermostat.getMaxTemperature());

        Assert.assertTrue(changed);
        Assert.assertEquals(thermostat.getMaxTemperature(),thermostat.getTemperature());
    }
    @Test
    public void setTemperatureBelowMinFail(){
        boolean changed = thermostat.setTemperature(thermostat.getMinTemperature()-1);
        Assert.assertFalse(changed);
        Assert.assertEquals(thermostat.getMinTemperature(),thermostat.getTemperature());
    }
    @Test
    public void setTemperatureAboveMaxFail(){
        boolean changed = thermostat.setTemperature(thermostat.getMaxTemperature()+1);
        Assert.assertFalse(changed);
        Assert.assertEquals(thermostat.getMinTemperature(),thermostat.getTemperature());
    }
}
