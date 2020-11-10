package org.example;

import org.example.HomeSystem;
import org.example.core.Template;
import org.example.models.Light;
import org.example.models.Thermostat;
import org.example.models.Thing;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThingController {
    private final HomeSystem homeSystem;

    public ThingController(HomeSystem homeSystem) {
        this.homeSystem = homeSystem;
    }

    public String detail(Request request, Response response){
        //récupération de l'id de la thing entré dans le lien lors de la requête get
        //FIXME Protect request param for invalid values
        String idParam = request.params(":id");
        int id = Integer.parseInt(idParam);
        int index = id - 1; //-1 car le velocity démarre de 1 mais nous on préfère avoir un index

        List<Thing> things = homeSystem.getThings();
        Thing thing = things.get(index);

        // CODE SMELL => Attention à ne pas trop avoir de things sinon ce controller sera trop grand et il vaudra mieux alors avoir une autre solution
        if (thing instanceof  Light) {
            return detailLight(request,response,id,(Light)thing);
        } else if (thing instanceof Thermostat) {
            return detailThermostat(request,response,id,(Thermostat)thing);
        }

        return "";
    }

    private String detailThermostat(Request request, Response response, int id, Thermostat thermostat) {
        String action = request.queryParamOrDefault("action","");
        String value = request.queryParamOrDefault("value","");
        String message = "";

        if (action.equals("set_temperature")) {
            int temperature = Integer.parseInt(value);
            //on essaye de modifier la température et ensuite si on voit que ça ne l'a pas modifié
            if (!thermostat.setTemperature(temperature)) {
                message = "Desire temperature="+temperature+" is outside of valid range";
            }
        }

        Map<String, Object> model = new HashMap<>();
        model.put("id",id);
        model.put("thermostat",thermostat);
        model.put("message",message);
        return Template.render("thing_thermostat.html", model);
    }

    private String detailLight(Request request, Response response, int id, Light light) {
        //Handle actions
        //paramètre action avec par défaut une chaîne vide (si param non renseignée)
        String action = request.queryParamOrDefault("action","");

        if (action.equals("toggle")) {
            light.setLightOn(!light.isLightOn());
            System.out.println("TOGGLED LIGHT : "+light.isLightOn());
        }

        Map<String, Object> model = new HashMap<>();
        model.put("id",id);
        model.put("light",light);
        return Template.render("thing_light.html", model);
    }
}
