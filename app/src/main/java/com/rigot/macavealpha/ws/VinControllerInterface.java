package com.rigot.macavealpha.ws;

import com.rigot.macavealpha.metier.Cave;
import com.rigot.macavealpha.metier.Vin;

import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;

/**
 * Auteur : Seb
 * Date de creation : 27/09/2015
 */
public interface VinControllerInterface {


    @Post("json")
    ResultWS createVin(String vin);

    @Put("json")
    ResultWS updateVin(Vin vin);

    @Delete("json")
    ResultWS deleteVin(Vin vin);

//	@Delete("json")
//	void deleteCave(Cave cave);

//    @Get("json")
//    Vin getVinByID(Long id);

    @Get("json")
    Cave getAllVins();
}
