package com.itz.base.use_cases.aaa_module.auth;

import com.itz.base.persistence.models.aaa_module.OtpCheck;
import com.itz.base.persistence.models.aaa_module.UserMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<UserMaster, Long> {
    //    @Query(value = "select TOP 1 * " +
//            "from userInfo where userId = :username",nativeQuery = true)
    UserMaster findByUserId(String username);

    UserMaster findByUserIdAndIsActive(String usrId, boolean b);
}

@Repository
interface OtpCheckRepository extends JpaRepository<OtpCheck, Long> {

    OtpCheck findByUserId(String pk0);
}