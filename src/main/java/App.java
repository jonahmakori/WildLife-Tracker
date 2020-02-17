import jdk.jfr.Timestamp;
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

        Map<String,Object> model = new HashMap<String,Object>();


        get("/", (req,res) -> {
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animals",(req,res) ->{
            model.put("animals",NonEndangeredAnimal.all());
            return new ModelAndView(model, "animal.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animal/form",(req,res) -> {
            return new ModelAndView(model, "animalform.hbs");
        }, new HandlebarsTemplateEngine());


        post("/animal/new",(req,res) -> {
            String name = req.queryParams("animalName");
            NonEndangeredAnimal animal= new NonEndangeredAnimal(name);
            animal.save();
            return new ModelAndView(model, "animalSuccess.hbs");
        }, new HandlebarsTemplateEngine());

        get("/endangered",(req,res) ->{
            model.put("endangeredAnimals", EndangeredAnimals.all());
            return new ModelAndView(model, "endangeredanimal.hbs");
        }, new HandlebarsTemplateEngine());


        get("/endangered/form",(req,res)->{
            return new ModelAndView(model, "endangeredform.hbs");
        }, new HandlebarsTemplateEngine());

        post("/endangered/new",(req,res) ->{
            String name = req.queryParams("name");
            String health = req.queryParams("health");
            String age = req.queryParams("age");
            EndangeredAnimals animal= new EndangeredAnimals(name,health,age);
            animal.save();
            return new ModelAndView(model, "endangeredSuccess.hbs");
        }, new HandlebarsTemplateEngine());


        get("/sighting",(req,res)->{
            model.put("sighting",Sighting.all());
            return new ModelAndView(model, "sightings.hbs");
        }, new HandlebarsTemplateEngine());

        post("/sighting",(req,res) ->{
            int animal_Id = Integer.parseInt(req.queryParams("animal_Id"));
            String location = req.queryParams("location");
            String ranger_name = req.queryParams("ranger_name");
            Timestamp view_time = req.queryParams("view_time");
            Sighting sighting= new Sighting(animal_Id,location,view_time,ranger_name);
            sighting.save();
            return new ModelAndView(model, "sighting.hbs");
        }, new HandlebarsTemplateEngine());



        get("/sightings/form",(req,res)->{
            return new ModelAndView(model, "sightingform.hbs");
        }, new HandlebarsTemplateEngine());

        post("/sightings/new",(req,res)->{
            int animal_Id = Integer.parseInt(req.queryParams("animal_Id"));
            String ranger_name = req.queryParams("ranger_name");
            String location = req.queryParams("location");
            Sighting sighting = new Sighting(animal_Id,location,ranger_name);
            sighting.save();
            return new ModelAndView(model, "sightingSuccess.hbs");
        }, new HandlebarsTemplateEngine());



    }
}