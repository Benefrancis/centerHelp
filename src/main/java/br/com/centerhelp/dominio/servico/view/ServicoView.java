package br.com.centerhelp.dominio.servico.view;

import br.com.centerhelp.dominio.servico.model.Servico;

import javax.swing.*;
import java.time.LocalDateTime;

public abstract class ServicoView {

    public static Servico showForm(Servico s) {
        var tipo = TipoServicoView.select(s == null ? null : s.getTipo());

        String nome = JOptionPane.showInputDialog("Nome do Serviço",s == null ? null : s.getNome() );
        String descricao = JOptionPane.showInputDialog("Descricao do Serviço",s == null ? null : s.getDescricao() );

        LocalDateTime abertura = null;
        if(s.getAbertura()==null) abertura = LocalDateTime.now();

        var servico = new Servico();
        servico.setTipo(tipo).setNome(nome).setDescricao(descricao).setAbertura(abertura).setId(s == null ? null : s.getId());

        return servico;

    }
}
