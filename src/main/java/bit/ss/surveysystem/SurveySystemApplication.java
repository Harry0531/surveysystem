package bit.ss.surveysystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("bit.ss.surveysystem")
public class SurveySystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SurveySystemApplication.class, args);
    }

}
