package br.com.centerhelp.resources;

import br.com.centerhelp.dominio.equipamento.model.TipoEquipamento;
import br.com.centerhelp.dominio.equipamento.repository.TipoEquipamentoRepository;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/")
public class IndexResource {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response findAll() {
        return Response.ok().entity("Seja Bem Vindo ao <strong>Center Help</strong>").build();
    }

}
