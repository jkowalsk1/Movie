package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by oracle on 8/14/17.
 */
@Controller
public class HomeController {


    @Autowired
    ActorRepository actorRepository;

    @RequestMapping("/")
    public String index(Model model){
        // First let's create an actor
        Actor actor = new Actor();
        actor.setName("Sandra Bullock");
        actor.setRealname("Sandra Mae Bullowski");

        //Now Lets create a movie
        Movie movie = new Movie();
        movie.setTitle("Emoji Movie");
        movie.setYear(2017);
        movie.setDescription("About Emojis...");

        //Add the movie to an empty list
        Set<Movie> movies = new HashSet<Movie>();
        movies.add(movie);

        //Add the list of movies to the actors movie list
        actor.setMovies(movies);

        //Save the actor to the database
        actorRepository.save(actor);
        //Grab all the actors from the database and send them to the
        // template
        model.addAttribute("actors", actorRepository.findAll());
        return "index";
    }
}
