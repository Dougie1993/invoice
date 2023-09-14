package za.co.douglasmedia.profile.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import za.co.douglasmedia.profile.Entities.Profile;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    @Query("SELECT p FROM Profile p WHERE  p.profileId = ?1 AND i.deactivated = false")
    Optional<Profile> findProfileByIdAndNotDeactivated(long profileId);

    @Query("SELECT p FROM Profile p WHERE p.userId = ?1 AND i.deactivated = false")
    Optional<Profile> findProfileByUserIdAndNotDeactivated(String userId);

    @Modifying
    @Query("UPDATE Profile p SET p.businessName = ?1, p.address = ?2, p.phone = ?3, " +
            "p.mobile = ?4, p.logoString = ?5, p.deactivated = ?6 WHERE  p.userId = ?7 ")
    void updateProfileByUserId(String businessName, String address, String phone,
                               String mobile, String logoString, boolean deactivated,
                               String userId);


}
