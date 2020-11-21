package com.ioasys.app_empresas.repository;

import com.ioasys.app_empresas.model.Enterprise;
import com.ioasys.app_empresas.repository.enterprise.EnterpriseRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnterpriseRepository extends JpaRepository<Enterprise, Long>, EnterpriseRepositoryQuery {

}
