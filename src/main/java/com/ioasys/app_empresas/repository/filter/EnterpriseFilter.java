package com.ioasys.app_empresas.repository.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnterpriseFilter {

    private String name;
    private Long typeCode;
}
