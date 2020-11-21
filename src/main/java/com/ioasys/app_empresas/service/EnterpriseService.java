package com.ioasys.app_empresas.service;

import com.ioasys.app_empresas.model.Enterprise;
import com.ioasys.app_empresas.repository.EnterpriseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class EnterpriseService {

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    public Enterprise save(Enterprise enterprise) {
        return enterpriseRepository.save(enterprise);
    }

    public Enterprise update(Long code, Enterprise enterprise) {
        Enterprise enterpriseSave = findByCode(code);
        BeanUtils.copyProperties(enterprise, enterpriseSave, "code");
        return enterpriseRepository.save(enterpriseSave);
    }

    private Enterprise findByCode(Long id) {
        Enterprise enterpriseSave = this.enterpriseRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        return enterpriseSave;
    }

}
