package ro.sapientia.furniture.service;

import org.springframework.stereotype.Service;
import ro.sapientia.furniture.error.FurnitureItemNotFoundException;
import ro.sapientia.furniture.error.JoineryNotFoundException;
import ro.sapientia.furniture.model.Joinery;

import java.util.List;
import java.util.Optional;

public interface JoinerySevice {

    public List<Joinery> findAllJoinery();

    public Joinery create(Joinery joinery);

    public Optional<Joinery> findJoineryById(Long id) throws JoineryNotFoundException;

    Joinery update(Long id, Joinery joinery);

    void delete(Long id);
}
