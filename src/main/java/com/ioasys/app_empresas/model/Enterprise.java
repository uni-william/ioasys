package com.ioasys.app_empresas.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "enterprises")
public class Enterprise implements Serializable {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enterprise_code")
    private Long code;

    @NotNull
    @Column(length = 150, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "enterprise_type", referencedColumnName = "enterprise_type_code")
    private EnterpriseType enterpriseType;
}
