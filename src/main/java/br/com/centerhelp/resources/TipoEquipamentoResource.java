package br.com.centerhelp.resources;

import br.com.centerhelp.dominio.equipamento.model.TipoEquipamento;
import br.com.centerhelp.dominio.equipamento.repository.TipoEquipamentoRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import java.net.URI;


@Path("/tipo-equipamento")
public class TipoEquipamentoResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.ok(TipoEquipamentoRepository.findAll()).build();
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        var tipo = TipoEquipamentoRepository.findById(id);
        if (tipo != null) {
            return Response.ok(tipo).build();
        } else {
            return Response.status(404).build();
        }
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(TipoEquipamento tipo) {

        var resp = TipoEquipamentoRepository.save(tipo);

        if(resp!=null) {

            final URI tipoUri = UriBuilder
                    .fromResource(TipoEquipamentoResource.class)
                    .path("/tipo-equipamento/{id}")
                    .build(resp.getId());

            return Response.created(tipoUri).entity(resp).build();

        }else{
            return Response.status(400).entity("Já existe").build();
        }
    }

}
