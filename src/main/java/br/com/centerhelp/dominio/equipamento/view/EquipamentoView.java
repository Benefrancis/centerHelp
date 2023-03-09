package br.com.centerhelp.dominio.equipamento.view;

import br.com.centerhelp.dominio.equipamento.model.Equipamento;

import javax.swing.*;

public abstract class EquipamentoView {

    public static Equipamento showForm(Equipamento e) {

        var tipo = TipoEquipamentoView.select(e == null ? null : e.getTipo());

        String nome = JOptionPane.showInputDialog("Nome do " + tipo.getNome(), e == null ? "" : e.getNome());
        String numeroDeSerie = JOptionPane.showInputDialog("Número de Série do " + tipo.getNome(), e == null ? "" : e.getNumeroDeSerie());
        var equipamento = new Equipamento();
        equipamento.setNome(nome).setNumeroDeSerie(numeroDeSerie).setTipo(tipo).setId(e == null ? null : e.getId());
        return equipamento;
    }


}
