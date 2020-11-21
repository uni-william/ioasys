package com.ioasys.app_empresas.service;

import com.ioasys.app_empresas.UserSystemRepository;
import com.ioasys.app_empresas.model.UserSystem;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserSystemService {

    @Autowired
    private UserSystemRepository userSystemRepository;

    public UserSystem save(UserSystem UserSystem) {
        return userSystemRepository.save(UserSystem);
    }

    public UserSystem update(Long id, UserSystem UserSystem) {
        UserSystem UserSystemSave = findById(id);
        BeanUtils.copyProperties(UserSystem, UserSystemSave, "id");
        return userSystemRepository.save(UserSystemSave);
    }

    private UserSystem findById(Long id) {
        UserSystem UserSystemSave = this.userSystemRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        return UserSystemSave;
    }

}
