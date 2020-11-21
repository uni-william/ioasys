package com.ioasys.app_empresas.repository.enterprise;

import com.ioasys.app_empresas.model.Enterprise;
import com.ioasys.app_empresas.repository.filter.EnterpriseFilter;

import java.util.List;

public interface EnterpriseRepositoryQuery {

    public List<Enterprise> filter(EnterpriseFilter filter);
}
