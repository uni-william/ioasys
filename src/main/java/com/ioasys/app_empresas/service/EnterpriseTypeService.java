package com.ioasys.app_empresas.service;

import com.ioasys.app_empresas.model.EnterpriseType;
import com.ioasys.app_empresas.repository.EnterpriseTypeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class EnterpriseTypeService {

    @Autowired
    private EnterpriseTypeRepository enterpriseTypeRepository;

    public EnterpriseType save(EnterpriseType enterpriseType) {
        return enterpriseTypeRepository.save(enterpriseType);
    }

    public EnterpriseType update(Long code, EnterpriseType enterpriseType) {
        EnterpriseType enterpriseTypeSave = findByCode(code);
        BeanUtils.copyProperties(enterpriseType, enterpriseTypeSave, "code");
        return enterpriseTypeRepository.save(enterpriseTypeSave);
    }

    private EnterpriseType findByCode(Long id) {
        EnterpriseType enterpriseTypeSave = this.enterpriseTypeRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        return enterpriseTypeSave;
    }

}
