package br.com.centerhelp;

import br.com.centerhelp.dominio.equipamento.model.Equipamento;
import br.com.centerhelp.dominio.equipamento.repository.EquipamentoRepository;
import br.com.centerhelp.dominio.equipamento.repository.TipoEquipamentoRepository;
import br.com.centerhelp.dominio.equipamento.view.EquipamentoView;
import br.com.centerhelp.dominio.equipamento.view.TipoEquipamentoView;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.swing.*;
import java.io.IOException;
import java.net.URI;

public class Main {


    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8080/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in br.com.fiap package
        final ResourceConfig rc = new ResourceConfig().packages("br.com.centerhelp.resources");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }


    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with endpoints available at %s%nHit Ctrl-C to stop it...", BASE_URI));
        System.in.read();
        server.stop();
    }

    public static void viewJoptionPane() {

        int opcao = montaMenu();

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
        var tipo = TipoEquipamentoView.showForm(null);
        tipo = TipoEquipamentoRepository.save(tipo);

        if (tipo != null) {
            JOptionPane.showMessageDialog(null, "Tipo de Equipamento (" + tipo.getNome() + ") foi salvo com sucesso! ");
            System.out.println(tipo);
        }

    }

    private static void novoEquipamento() {
        Equipamento e = EquipamentoView.showForm(null);
        EquipamentoRepository.save(e);
        System.out.println("Equipamento salvo com sucesso! " + e);
        EquipamentoRepository.manager.close();
        EquipamentoRepository.factory.close();
    }
}