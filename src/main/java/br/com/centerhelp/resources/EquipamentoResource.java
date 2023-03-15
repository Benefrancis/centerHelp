package br.com.centerhelp.resources;

import br.com.centerhelp.dominio.equipamento.model.Equipamento;
import br.com.centerhelp.dominio.equipamento.repository.EquipamentoRepository;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Collection;

@Path("/equipamento")
public class EquipamentoResource {

    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Equipamento> getEquipamentos() {
        return EquipamentoRepository.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {

        Equipamento e = EquipamentoRepository.findById(id);

        if (e != null) {
            Response.ResponseBuilder response = Response.ok();
            response.entity(e);
            return response.build();
        } else {
            Response.ResponseBuilder response = Response.status(404);
            return response.build();
        }
    }

}
