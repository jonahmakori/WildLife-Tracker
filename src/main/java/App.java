import models.EndangeredAnimals;
import models.NonEndangeredAnimal;
import models.Sighting;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
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

        get("/animals/form",(req,res)->{
            return new ModelAndView(model, "animalform.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animals/form",(req,res) -> {
            String name = req.queryParams("Name");
            NonEndangeredAnimal animal= new NonEndangeredAnimal(name);
            animal.save();
            return new ModelAndView(model, "animalform.hbs");
        }, new HandlebarsTemplateEngine());

        post("/animals/form",(req,res) -> {
            String name = req.queryParams("Name");
            NonEndangeredAnimal animal= new NonEndangeredAnimal(name);
            animal.save();
            return new ModelAndView(model, "animalSuccess.hbs");
        }, new HandlebarsTemplateEngine());


        get("/animals/new",(req,res) -> {
            String name = req.queryParams("Name");
            NonEndangeredAnimal animal= new NonEndangeredAnimal(name);
            animal.save();
            return new ModelAndView(model, "animalSuccess.hbs");
        }, new HandlebarsTemplateEngine());



        post("/animals/new",(req,res) -> {
            String name = req.queryParams("Name");
            NonEndangeredAnimal animal= new NonEndangeredAnimal(name);
            animal.save();
            return new ModelAndView(model, "animalSuccess.hbs");
        }, new HandlebarsTemplateEngine());

        get("/endangered",(req,res) ->{
            model.put("endangered", EndangeredAnimals.all());
            return new ModelAndView(model, "endangeredanimal.hbs");
        }, new HandlebarsTemplateEngine());

        get("/endangered/form",(req,res)->{
            return new ModelAndView(model, "endangeredform.hbs");
        }, new HandlebarsTemplateEngine());

        get("/endangered/form",(req,res)->{
            String name = req.queryParams("name");
            String health = req.queryParams("health");
            String age = req.queryParams("age");
            EndangeredAnimals animal= new EndangeredAnimals(name,health,age);
            animal.save();
            return new ModelAndView(model, "endangeredform.hbs");
        }, new HandlebarsTemplateEngine());

        post("/endangered/form",(req,res) ->{
            String name = req.queryParams("name");
            String health = req.queryParams("health");
            String age = req.queryParams("age");
            EndangeredAnimals animal= new EndangeredAnimals(name,health,age);
            animal.save();
            return new ModelAndView(model, "endangeredSuccess.hbs");
        }, new HandlebarsTemplateEngine());


        get("/endangered/new",(req,res)->{
            String name = req.queryParams("Name");
            String health = req.queryParams("Health");
            String age = req.queryParams("Age");
            EndangeredAnimals animal= new EndangeredAnimals(name,health,age);
            animal.save();
            return new ModelAndView(model, "endangeredSuccess.hbs");
        }, new HandlebarsTemplateEngine());

        post("/endangered/new",(req,res) ->{
            String name = req.queryParams("name");
            String health = req.queryParams("health");
            String age = req.queryParams("age");
            EndangeredAnimals animal= new EndangeredAnimals(name,health,age);
            animal.save();
            return new ModelAndView(model, "endangeredSuccess.hbs");
        }, new HandlebarsTemplateEngine());



        get("/sightings",(req,res)->{
            List<Sighting> sightings= Sighting.getAll();
            model.put("sightings",sightings);
            return new ModelAndView(model, "sightings.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sightings/form",(req,res)->{
            List<EndangeredAnimals> allEndangered = EndangeredAnimals.all();
            List<NonEndangeredAnimal> nonEndangeredAnimal= NonEndangeredAnimal.all();
            model.put("allEndangered", allEndangered);
            model.put("nonEndangeredAnimal",nonEndangeredAnimal);
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