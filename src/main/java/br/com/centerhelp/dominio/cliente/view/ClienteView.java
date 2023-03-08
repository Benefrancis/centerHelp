package br.com.centerhelp.dominio.cliente.view;

import br.com.centerhelp.dominio.cliente.model.Cliente;

import javax.swing.*;
import java.time.LocalDate;

public abstract class ClienteView {

    public static Cliente showForm(Cliente c) {

        var tipo = TipoClienteView.select(c == null ? null : c.getTipo());
        var nome = JOptionPane.showInputDialog("Nome do Cliente", c == null ? "" : c.getNome());
        var email = JOptionPane.showInputDialog("email do cliente", c == null ? "" : c.getEmail());


        var dt = JOptionPane.showInputDialog("Data de Nascimento DD/MM/YYYY");
        var dia = Integer.parseInt(dt.substring(0, 2));
        var mes = Integer.parseInt(dt.substring(3, 5));
        var ano = Integer.parseInt(dt.substring(6, 10));


        var nascimento = LocalDate.of(ano, mes, dia);

        return c;
    }

}
