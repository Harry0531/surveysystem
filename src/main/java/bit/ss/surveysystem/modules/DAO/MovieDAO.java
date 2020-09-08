package bit.ss.surveysystem.modules.DAO;

import bit.ss.surveysystem.modules.entity.MovieEntity;
import bit.ss.surveysystem.modules.entity.UserEntity;

import java.util.List;

public interface MovieDAO {
    List<MovieEntity> getDefaultMovie(int num);

    List<MovieEntity> getMoviesByName(MovieEntity movie);

    int getMoviesNumByName(MovieEntity movie);

    List<MovieEntity> getRecommendMovieById(UserEntity user);

    List<MovieEntity> getRelatedMovies(MovieEntity movie);

    List<MovieEntity> getRecentTopMovies();

    MovieEntity getMovieById(int id);
}
