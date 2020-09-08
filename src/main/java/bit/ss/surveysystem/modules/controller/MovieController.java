package bit.ss.surveysystem.modules.controller;

import bit.ss.surveysystem.common.persistence.Page;
import bit.ss.surveysystem.common.web.BaseApi;
import bit.ss.surveysystem.common.web.MsgType;
import bit.ss.surveysystem.modules.Service.MovieService;
import bit.ss.surveysystem.modules.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/movie")
public class MovieController extends BaseApi {
    @Autowired
    private MovieService movieService;

    @RequestMapping(value = "getHomepageMovies", method = RequestMethod.POST)
    @ResponseBody
    public Object getHomepageMovies() {
        try {
            Page<MovieEntity> page = new Page<>();
            page.setResultList(movieService.getHomepageMovies());
            return retMsg.Set(MsgType.SUCCESS, page);
        } catch (Exception e) {
            e.printStackTrace();
            return retMsg.Set(MsgType.ERROR, e.toString());
        }
    }

    @RequestMapping(value = "getMoviesByName", method = RequestMethod.POST)
    @ResponseBody
    public Object getMoviesByName(@RequestBody MovieEntity movie) {
        try {
            Page<MovieEntity> page = new Page<>();
            page.setResultList(movieService.getMoviesByName(movie));
            page.setTotal(movieService.getMoviesNumByName(movie));
            return retMsg.Set(MsgType.SUCCESS, page);
        } catch (Exception e) {
            e.printStackTrace();
            return retMsg.Set(MsgType.ERROR, e.toString());
        }
    }

    @RequestMapping(value = "getRecommendMovies", method = RequestMethod.POST)
    @ResponseBody
    public Object getRecommendMovies(@RequestParam("userName") String userName) {
        try {
            Page<MovieEntity> page = new Page<>();
            page.setResultList(movieService.getRecommendMovies(userName));
            return retMsg.Set(MsgType.SUCCESS, page);
        } catch (Exception e) {
            e.printStackTrace();
            return retMsg.Set(MsgType.ERROR, e.toString());
        }
    }

    @RequestMapping(value = "getRelatedMovies", method = RequestMethod.POST)
    @ResponseBody
    public Object getRelatedMovies(@RequestBody MovieEntity movie) {
        try {
            Page<MovieEntity> page = new Page<>();
            page.setResultList(movieService.getRelatedMovies(movie));
            return retMsg.Set(MsgType.SUCCESS, page);
        } catch (Exception e) {
            e.printStackTrace();
            return retMsg.Set(MsgType.ERROR, e.toString());
        }
    }

    @RequestMapping(value = "getRecentTopMovies", method = RequestMethod.POST)
    @ResponseBody
    public Object getRecentTopMovies() {
        try {
            Page<MovieEntity> page = new Page<>();
            page.setResultList(movieService.getRecentTopMovies());
            return retMsg.Set(MsgType.SUCCESS, page);
        } catch (Exception e) {
            e.printStackTrace();
            return retMsg.Set(MsgType.ERROR, e.toString());
        }
    }

    @Deprecated
    @RequestMapping(value = "getMovieDetails", method = RequestMethod.POST)
    @ResponseBody
    public Object getMovieDetails(@RequestParam String userName, @RequestBody MovieEntity movie) {
        try {
            return movieService.getMovieDetails(movie, userName);
        } catch (Exception e) {
            e.printStackTrace();
            return retMsg.Set(MsgType.ERROR, e.toString());
        }
    }
}
