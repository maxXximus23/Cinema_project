
import com.dut.CinemaProject.dao.domain.Hall;
import com.dut.CinemaProject.dao.repos.HallRepository;
import com.dut.CinemaProject.dto.Hall.HallDto;
import com.dut.CinemaProject.dto.Hall.NewHall;
import com.dut.CinemaProject.dto.Hall.UpdateHallData;
import com.dut.CinemaProject.exceptions.BadRequestException;
import com.dut.CinemaProject.exceptions.ItemNotFoundException;
import com.dut.CinemaProject.services.interfaces.IHallService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HallService implements IHallService {

    private final HallRepository hallRepository;

    @Override
    public Long createHall(NewHall newHall) {
        if(newHall.getName().isBlank() || newHall.getPlaces() < 1 ||newHall.getRowsAmount() < 1)
            throw new BadRequestException("No information");

        Hall hall = new Hall();
        hall.setName(newHall.getName());
        hall.setRowsAmount(hall.getRowsAmount());
        hall.setPlaces(newHall.getPlaces());

        return hallRepository.save(hall).getId();
    }

    @Override
    public void deleteHall(Long id) {
        Hall hall = hallRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Hall is not found"));
        hallRepository.delete(hall);

    }

    @Override
    public HallDto updateHall(UpdateHallData hall) {
        Hall updateHall = hallRepository.findById(hall.getId()).orElseThrow(() -> new ItemNotFoundException("Hall is not found"));

        updateHall.setName(hall.getName());
        updateHall.setRowsAmount(hall.getRowsAmount());
        updateHall.setPlaces(hall.getPlaces());

        return new HallDto(hallRepository.save(updateHall));
    }

    @Override
    public HallDto getHallById(Long id) {
        return new HallDto(hallRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Hall is not found")));
    }
}
