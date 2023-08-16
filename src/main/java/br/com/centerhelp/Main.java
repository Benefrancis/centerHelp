package br.com.centerhelp;

import br.com.centerhelp.dominio.equipamento.model.Equipamento;
import br.com.centerhelp.dominio.equipamento.repository.EquipamentoRepository;
import br.com.centerhelp.dominio.equipamento.repository.TipoEquipamentoRepository;
import br.com.centerhelp.dominio.equipamento.view.EquipamentoView;
import br.com.centerhelp.dominio.equipamento.view.TipoEquipamentoView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        var opcao = montaMenu();

        while (opcao != 0) {
            switch (opcao) {
                case 1 -> novoEquipamento();
                case 2 -> novoTipoDeEquipamento();
            }
            opcao = montaMenu();
        }
        JOptionPane.showMessageDialog(null, "Muito obrigado por usar o nosso sistema");
    }

    private static int montaMenu() {
        String mensagemMenu = """
                Bem-vindo ao Center Help
                                
                Digite:
                                
                -------    Equipamento   -------------------------
                1 - Cadastramento de Equipamento
                2 - Cadatramento de Tipo de Equipamento
                                
                --------   Serviço    ----------------------------
                3 - Abertura de Serviço
                4 - Cadastramento de Tipo de Serviço
                --------------------------------------------------
                                
                0 - Sair
                """;

        int opcao = Integer.parseInt(JOptionPane.showInputDialog(mensagemMenu));

        return opcao;
    }

    private static void novoTipoDeEquipamento() {
        TipoEquipamentoRepository repo = new TipoEquipamentoRepository();
        var tipo = TipoEquipamentoView.showForm(null);
        tipo = repo.persist(tipo);
        if (tipo != null) {
            JOptionPane.showMessageDialog(null, "Tipo de Equipamento (" + tipo.getNome() + ") foi salvo com sucesso! ");
            System.out.println(tipo);
        }

    }

    private static void novoEquipamento() {
        Equipamento e = EquipamentoView.showForm(null);
        EquipamentoRepository repo = new EquipamentoRepository();
        repo.persist(e);
        System.out.println("Equipamento salvo com sucesso! " + e);
        EquipamentoRepository.manager.close();
        EquipamentoRepository.factory.close();
    }
}