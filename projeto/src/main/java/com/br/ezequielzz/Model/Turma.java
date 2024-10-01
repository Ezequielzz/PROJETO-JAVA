package com.br.ezequielzz.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Turma {
    private int turmaId;
    private String serie;
    private String anoLetivo;
    private String turno;
    private String sala;

    public Turma(int turmaId, String serie, String anoLetivo, String turno, String sala) {
        this.turmaId = turmaId;
        this.serie = serie;
        this.anoLetivo = anoLetivo;
        this.turno = turno;
        this.sala = sala;
    }
}
