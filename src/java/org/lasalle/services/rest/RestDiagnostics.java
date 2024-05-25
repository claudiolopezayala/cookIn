/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.services.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author Claudio
 */
@Path("diagnostics")
public class RestDiagnostics {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("greeting")
    public Response saludar(){
        String out = """
                     {"response":"Up and running"}
                     """;
        return Response.ok(out).build();
    }
}
