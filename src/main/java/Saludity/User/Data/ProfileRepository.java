package Saludity.User.Data;

import Saludity.User.Model.Pojo.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    List<Profile> getProfiles(String name, String lastname, String phoneNumber, LocalDate datebirth);
    Profile getProfileById(Long id);

}
