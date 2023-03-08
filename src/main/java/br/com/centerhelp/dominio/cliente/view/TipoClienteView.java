package br.com.centerhelp.dominio.cliente.view;

import br.com.centerhelp.dominio.cliente.model.TipoCliente;
import br.com.centerhelp.dominio.cliente.repository.TipoClienteRepository;

import javax.swing.*;

public abstract class TipoClienteView {

    public static TipoCliente showForm(TipoCliente t) {
        var nome = JOptionPane.showInputDialog("Tipo de Cliente", t == null ? "" : t.getNome());
        TipoCliente tipo = new TipoCliente();
        tipo.setNome(nome).setId(t == null ? null : t.getId());
        return tipo;
    }

    public static TipoCliente select(TipoCliente t) {

        var tipos = TipoClienteRepository.findAll();

        if (tipos.isEmpty()) return showForm(null);

        TipoCliente ret = (TipoCliente) JOptionPane.showInputDialog(
                null,
                "Tipo Cliente",
                "Tipo Cliente",
                JOptionPane.QUESTION_MESSAGE,
                null,
                tipos.toArray(),
                t == null ? 1 : t
        );
        return ret;
    }


}
