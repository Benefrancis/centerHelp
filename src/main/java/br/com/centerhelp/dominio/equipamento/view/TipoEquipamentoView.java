package br.com.centerhelp.dominio.equipamento.view;

import br.com.centerhelp.dominio.equipamento.model.TipoEquipamento;
import br.com.centerhelp.dominio.equipamento.repository.TipoEquipamentoRepository;

import javax.swing.*;

public class TipoEquipamentoView {

    static TipoEquipamentoRepository repository = new TipoEquipamentoRepository();

    public static TipoEquipamento showForm(TipoEquipamento tp) {
        String nome = JOptionPane.showInputDialog("Tipo de Equipamento", tp == null ? "" : tp.getNome());
        var tipo = new TipoEquipamento();
        tipo.setNome(nome.toUpperCase());
        tipo.setId(tp == null ? null : tp.getId());
        return tipo;
    }


    public static TipoEquipamento select(TipoEquipamento tipo) {

        var tipos = repository.findAll();

        if(tipos.isEmpty()) return showForm(null);

        TipoEquipamento ret = (TipoEquipamento) JOptionPane.showInputDialog(
                null, // componente pai. Como não temos será null
                "Tipo de Equipamento",
                "Tipo",
                JOptionPane.QUESTION_MESSAGE,
                null, // icone
                tipos.toArray(), // Número da opção
                tipo == null ? 1 : tipo);
        return ret;
    }

}
