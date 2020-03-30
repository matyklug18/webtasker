package matyk.webtasker;

import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTController {

    @RequestMapping("/login/{username}/{password}")
    public String getUserToken(@PathVariable String username, @PathVariable String password) {
        PrivateAuth auth = WebtaskerApplication.users.get(username).auth;
        if(Hasher.matchPasswd(password, HasherUtils.combineHash(auth.passwdSalt, auth.passwdHash))) {
            String token = Hasher.generateNewToken();
            WebtaskerApplication.tokens.put(token, username);
            return token;
        }
        return "invalid";
    }

    @RequestMapping("/api/{token}/{admin}/{field}")
    public String getApiField(@PathVariable String token, @PathVariable String admin, @PathVariable String field) {
        if(WebtaskerApplication.tokens.containsKey(token))
            if(Boolean.parseBoolean(admin) && WebtaskerApplication.users.get(WebtaskerApplication.tokens.get(token)).isAdmin)
                return WebtaskerApplication.adminFields.get(field);
            else if(!Boolean.parseBoolean(admin))
                return WebtaskerApplication.userFields.get(field);
            else if(!Boolean.parseBoolean(admin) && !WebtaskerApplication.users.get(WebtaskerApplication.tokens.get(token)).isAdmin)
                return "invalid";
        return "invalid";
    }

    @RequestMapping("/api/token/{token}/{field}")
    public String getTokenInfo(@PathVariable String token, @PathVariable String field) {
        if(field.equals("admin"))
            return String.valueOf(WebtaskerApplication.users.get(WebtaskerApplication.tokens.get(token)).isAdmin);
        else
            return "invalid";
    }
}