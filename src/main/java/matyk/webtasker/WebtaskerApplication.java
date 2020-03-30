package matyk.webtasker;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.HashMap;

@SpringBootApplication
public class WebtaskerApplication {

	public static HashMap<String, UserInfo> users = new HashMap<>();
	public static HashMap<String, String> tokens = new HashMap<>();
	public static HashMap<String, String> userFields = new HashMap<>();
	public static HashMap<String, String> adminFields = new HashMap<>();

	public static void main(String[] args) throws ParseException {

		JSONObject object = (JSONObject) new JSONParser().parse(StringLoader.loadResourceAsString("users.json"));

		//System.out.println(Hasher.hash("Mikes18"));

		object.forEach((k, v) -> {

			JSONObject user = (JSONObject) v;
			String hash = (String) user.get("hash");
			String salt = (String) user.get("salt");
			boolean isAdmin = (boolean) user.get("admin");

			users.put((String) k, new UserInfo(new PrivateAuth(hash, salt), isAdmin));
		});

		SpringApplication.run(WebtaskerApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}

		};
	}
}