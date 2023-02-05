package ro.sapientia.furniture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.sapientia.furniture.error.JoineryNotFoundException;
import ro.sapientia.furniture.model.Joinery;
import ro.sapientia.furniture.service.JoinerySevice;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/furniture-joinery")
public class JoineryController {

    @Autowired
    JoinerySevice joinerySevice;

    @GetMapping("/all")
    public List<Joinery> getAllJoinery() {
        return joinerySevice.findAllJoinery();
    }

    @PostMapping("/add")
    public Joinery create(@RequestBody Joinery joinery) {
        return joinerySevice.create(joinery);
    }

    @GetMapping("/find/{id}")
    public Optional<Joinery> getById(@PathVariable Long id) throws JoineryNotFoundException {
        return joinerySevice.findJoineryById(id);
    }

    @PutMapping("/update/{id}")
    public Joinery update(@PathVariable Long id, @RequestBody Joinery joinery) {
        return joinerySevice.update(id, joinery);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        joinerySevice.delete(id);
    }
}
