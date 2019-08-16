package pl.tomala.demo.device;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.tomala.demo.Category;

@Controller
@RequestMapping("devices")
public class DeviceController {

    @Autowired
    DeviceRepository deviceRepository;

    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return "devices";
    }

    @RequestMapping("/add")
    @ResponseBody
    public String add() {
        Device device = new Device();
        device.setCategory(Category.LODÓWKA);
        deviceRepository.save(device);
        return device.toString();
    }
}
