package matyk.webtasker;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RequestController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/page")
    public String page() {
        return "page.html";
    }
}