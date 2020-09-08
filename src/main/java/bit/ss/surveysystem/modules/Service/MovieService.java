package bit.ss.surveysystem.modules.Service;

import bit.ss.surveysystem.modules.DAO.MovieDAO;
import bit.ss.surveysystem.modules.DAO.UserDAO;
import bit.ss.surveysystem.modules.entity.MovieEntity;
import bit.ss.surveysystem.modules.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieDAO movieDAO;

    @Autowired
    private UserDAO userDAO;

    public List<MovieEntity> getHomepageMovies() {
        return movieDAO.getDefaultMovie(15);
    }

    public MovieEntity getMovieDetails(MovieEntity movie, String userName) {
        //todo wh:记录点击？
        return movieDAO.getMovieById(movie.getId());
    }

    public List<MovieEntity> getMoviesByName(MovieEntity movie) {
        return movieDAO.getMoviesByName(movie);
    }

    public int getMoviesNumByName(MovieEntity movie) {
        return movieDAO.getMoviesNumByName(movie);
    }

    public List<MovieEntity> getRecommendMovies(String userName) {
        UserEntity userEntity = userDAO.getUserInfoByUsername(userName);
        List<MovieEntity> list = movieDAO.getRecommendMovieById(userEntity);
        if (list == null || list.size() == 0) {//无论如何都会返回3条记录
            list = movieDAO.getDefaultMovie(3);
        }
        return list;
    }

    public List<MovieEntity> getRelatedMovies(MovieEntity movie) {
        return movieDAO.getRelatedMovies(movie);
    }

    public List<MovieEntity> getRecentTopMovies() {
        return movieDAO.getRecentTopMovies();
    }
}
