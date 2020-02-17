import models.EndangeredAnimals;
import models.NonEndangeredAnimal;
import models.Sighting;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;


public class App {

    public static void main(String[] args) {
        staticFileLocation("/public");
        String layout = "templates/layout.hbs";

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //CREATING OBJECTS

        //retrieving new animal form
        get("/animals/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model,"animal-form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animals", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model,"animal-form.hbs");
        }, new HandlebarsTemplateEngine());

        // posting animals form details
        post("/animals", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("content");
            try {
                NonEndangeredAnimal animal = new NonEndangeredAnimal(name);
                animal.save();
            } catch (IllegalArgumentException exception) {
                System.out.println("Please enter an animal name.");
            }
            response.redirect("/animals");
            return new ModelAndView(model, "animalEdit-form.hbs");
        }, new HandlebarsTemplateEngine());

        //retrieving all animals
        get("/animals", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("animals", NonEndangeredAnimal.all());
            return new ModelAndView(model,"animals.hbs" );
        }, new HandlebarsTemplateEngine());

        //retrieving  endangered animal form
        get("/endangered/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "endangeredAnimal-form.hbs");
        }, new HandlebarsTemplateEngine());

        //posting endangered animal form details
        post("/endangered/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            String health = request.queryParams("health");
            String age = request.queryParams("age");
            try {
                EndangeredAnimals endangered = new EndangeredAnimals(name, health, age);
                endangered.save();
            } catch (IllegalArgumentException exception) {
                System.out.println("Please enter all input fields.");
            }
            return new ModelAndView(model, "animalEdit-form.hbs");
        }, new HandlebarsTemplateEngine());

        //retrieving sighting form
        get("/sightings/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("animals", NonEndangeredAnimal.all());
            return new ModelAndView(model, "sighting-form.hbs");
        }, new HandlebarsTemplateEngine());

        //posting a sighting form details
        post("/sightings", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int animal_id = Integer.parseInt(request.queryParams("animal"));
            String location = request.queryParams("location");
            String ranger_name = request.queryParams("rangerName");


            try {
                Sighting sighting = new Sighting(animal_id, 1, location, ranger_name);
            } catch (IllegalArgumentException exception) {
                System.out.println("Please enter Ranger name.");
            }
            response.redirect("/sightings");
            return new ModelAndView(model, layout);
        }, new HandlebarsTemplateEngine());

        //retrieving all sightings
        get("/sightings", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("sightings", Sighting.all());
            model.put("Animal", NonEndangeredAnimal.class);
            return new ModelAndView(model, "sighting.hbs");
        }, new HandlebarsTemplateEngine());

        //retrieving animals by id
        get("/animals/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("animal", NonEndangeredAnimal.find(Integer.parseInt(request.params(":id"))));
            model.put("endangered", EndangeredAnimals.find(Integer.parseInt(request.params(":id"))));
            model.put("Sighting", Sighting.class);
            return new ModelAndView(model, "animal.hbs");
        }, new HandlebarsTemplateEngine());

        //UPDATING OBJECTS
        //retrieving animals edit form
        get("/animals/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("animal", NonEndangeredAnimal.find(Integer.parseInt(request.params(":id"))));
            model.put("endangered", EndangeredAnimals.find(Integer.parseInt(request.params(":id"))));
            return new ModelAndView(model, "animalEdit-form.hbs");
        }, new HandlebarsTemplateEngine());

        //posting animal edit form details
        post("/animals/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int id = Integer.parseInt(request.params(":id"));
            String name = request.queryParams("name");
            String health = request.queryParams("health");
            String age = request.queryParams("age");
            NonEndangeredAnimal animal = NonEndangeredAnimal.find(id);
            animal.update();
            if(animal.getType().equals("Endangered")) {
                EndangeredAnimals endangered = EndangeredAnimals.find(id);
                endangered.setHealth(health);
                endangered.setAge(age);
                endangered.update();
            }
            response.redirect("/animals/" + id);
            return new ModelAndView(model, "animalEdit-form.hbs");
        }, new HandlebarsTemplateEngine());

        //retrieving edit  sightings form
        get("/sightings/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("sighting", Sighting.find(Integer.parseInt(request.params(":id"))));
            model.put("Animal", NonEndangeredAnimal.class);
            return new ModelAndView(model,"sighting-form.hbs" );
        }, new HandlebarsTemplateEngine());

        //updating a sighting instance
        post("/sightings/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int id = Integer.parseInt(request.params(":id"));
            String location = request.queryParams("location");
            String rangerName = request.queryParams("rangerName");
            Sighting sighting = Sighting.find(id);
            sighting.setLocation(location);
            sighting.setRangerName(rangerName);
            sighting.update();
            response.redirect("/sightings");
            return new ModelAndView(model, "sightingEditing-form.hbs");
        }, new HandlebarsTemplateEngine());

        //deleting animal object
        get("/animals/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            NonEndangeredAnimal.find(Integer.parseInt(request.params(":id"))).delete();
            response.redirect("/animals");
            return new ModelAndView(model, "animal.hbs");
        }, new HandlebarsTemplateEngine());
        //deleting a sighting
        get("/sightings/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Sighting.find(Integer.parseInt(request.params(":id"))).delete();
            response.redirect("/sightings");
            return new ModelAndView(model, "sighting.hbs");
        }, new HandlebarsTemplateEngine());
    }
}

