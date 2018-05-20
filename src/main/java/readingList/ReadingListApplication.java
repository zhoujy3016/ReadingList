package readingList;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication()
@EnableCaching
public class ReadingListApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReadingListApplication.class, args);
	}
}
