package ro.sapientia.furniture.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sapientia.furniture.model.Joinery;
import ro.sapientia.furniture.repository.JoineryRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class JoineryServiceImpl implements JoinerySevice{

    private final JoineryRepository joineryRepository;

    @Override
    public List<Joinery> findAllJoinery() {
        return joineryRepository.findAll();
    }

    @Override
    public Joinery create(Joinery joinery) {
        return joineryRepository.save(joinery);
    }

    @Override
    public Optional<Joinery> findJoineryById(Long id) {
        return joineryRepository.findById(id);
    }

    @Override
    public Joinery update(Long id, Joinery joinery) {
        Joinery joinery1 = joineryRepository.findById(id).orElseThrow();

        joinery1.setPrice(joinery.getPrice());
        joinery1.setType(joinery.getType());

        return joineryRepository.save(joinery1);
    }

    @Override
    public void delete(Long id) {
        Joinery joinery = joineryRepository.findById(id).orElseThrow();
        joineryRepository.delete(joinery);
    }

}
