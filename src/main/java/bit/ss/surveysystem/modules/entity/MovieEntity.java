package bit.ss.surveysystem.modules.entity;

import bit.ss.surveysystem.common.persistence.DataEntity;
import lombok.Data;

@Data
public class MovieEntity extends DataEntity<MovieEntity> {
    int id;
    String movieName;
    String showDate;
    String director;
    String actors;
    String picture;
    float rating;
    String description;
    String types;
    String bigPicture;
}
