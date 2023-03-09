package br.com.centerhelp;

import br.com.centerhelp.dominio.equipamento.model.Equipamento;
import br.com.centerhelp.dominio.equipamento.repository.EquipamentoRepository;
import br.com.centerhelp.dominio.equipamento.view.EquipamentoView;

public class Main {
    public static void main(String[] args) {
        novoEquipamento();
    }

    private static void novoEquipamento() {
        Equipamento e = EquipamentoView.showForm(null);
        EquipamentoRepository.save(e);
        System.out.println("Equipamento salvo com sucesso! " + e);
        EquipamentoRepository.manager.close();
        EquipamentoRepository.factory.close();
    }
}