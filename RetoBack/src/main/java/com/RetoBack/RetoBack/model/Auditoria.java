package com.RetoBack.RetoBack.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class Auditoria {
    Date fechaRegistro;
    Date fechaModificacion;
}
