package br.com.centerhelp.dominio.servico.view;

import br.com.centerhelp.dominio.servico.model.TipoServico;
import br.com.centerhelp.dominio.servico.repository.TipoServicoRepository;

import javax.swing.*;

public abstract class TipoServicoView {

    static TipoServicoRepository repo = new TipoServicoRepository();

    public static TipoServico showForm(TipoServico tipo) {

        var tp = new TipoServico();

        String nome = JOptionPane.showInputDialog("Tipo Serviço", tipo == null ? "" : tipo.getNome());
        Long id = tipo == null ? null : tipo.getId();

        tp.setNome(nome).setId(id);

        return tp;
    }


    public static TipoServico select(TipoServico tp) {
        var tipos = repo.findAll();

        if (tipos.isEmpty()) return showForm(null);

        TipoServico ret = (TipoServico) JOptionPane.showInputDialog(
                null,
                "Tipo Serviço",
                "Tipo Serviço",
                JOptionPane.QUESTION_MESSAGE,
                null,
                tipos.toArray(),
                tp == null ? 1 : tp
        );
        return ret;
    }


}
