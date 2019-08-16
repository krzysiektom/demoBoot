package pl.tomala.demo.warehouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.tomala.demo.State;
import pl.tomala.demo.device.DeviceRepository;

import java.util.Arrays;

@Controller
@RequestMapping("warehouse")
public class WarehouseController {

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return "warehouse";
    }

    @RequestMapping("/add/{deviceId}")
    @ResponseBody
    public String add(@PathVariable("deviceId") Long deviceId) {
        Warehouse warehouse = new Warehouse();
        warehouse.setDevice(deviceRepository.getOne(deviceId));
        warehouse.setState(State.ZEPSUTY);
        warehouseRepository.save(warehouse);
        return warehouse.toString();
    }

    @RequestMapping("/all")
    @ResponseBody
    public String all() {
        return Arrays.toString(warehouseRepository.findAll().stream().filter(warehouse -> warehouse.getState()!=State.WYDANY).toArray());
    }

    @RequestMapping("/repair/{id}")
    @ResponseBody
    public String repair(@PathVariable("id") Long id) {
        Warehouse warehouse =warehouseRepository.getOne(id);
        warehouse.setState(State.SPRAWNY);
        warehouseRepository.save(warehouse);
        return warehouse.toString();
    }

    @RequestMapping("/issue/{id}")
    @ResponseBody
    public String issue(@PathVariable("id") Long id) {
        Warehouse warehouse =warehouseRepository.getOne(id);
        if(warehouse.getState()==State.SPRAWNY){
            warehouse.setState(State.WYDANY);
        }
        warehouseRepository.save(warehouse);
        return warehouse.toString();
    }
}
