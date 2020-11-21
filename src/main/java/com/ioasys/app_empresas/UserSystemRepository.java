package com.ioasys.app_empresas;

import com.ioasys.app_empresas.model.UserSystem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSystemRepository extends JpaRepository<UserSystem, Long> {

    UserSystem findByEmail(String email);

}
