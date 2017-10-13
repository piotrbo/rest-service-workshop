package contract;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.*;

@RestController
public class ItemsController {

    private static final List<Item> items = new ArrayList<Item>();
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/items", method = RequestMethod.POST)
    public long postItems(@RequestParam(value = "item", defaultValue = "ITEM") String name) {
        Item item = new Item(counter.getAndIncrement(), name);
        items.add(item);
        return item.getId();
    }

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public List<Item> getItems() {
        return items;
    }

    @RequestMapping(value = "/items/{id}", method = RequestMethod.DELETE)
    public String deleteItem(@PathVariable(value = "id") long id) {
        int all = items.size();
        items.removeIf(item -> item.getId() == id);
        return items.size() < all ? "deleted" : "no such id";
    }
}
