package inventory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InventoryController {

    @RequestMapping(value = "/web")
    public String inventory(){
        return "inventory";
    }
}
