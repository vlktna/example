package com.khoubyari.example.api.rest;

import com.khoubyari.example.api.view.HotelView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.khoubyari.example.domain.Hotel;
import com.khoubyari.example.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Demonstrates how to set up RESTful API endpoints using Spring MVC
 */

@RestController
@RequestMapping(value = "/example/v1/hotels")
@Api(tags = {"hotels"})
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a hotel resource.", notes = "Returns the URL of the new resource in the Location header.")
    public void createHotel(@RequestBody HotelView hotel,
                            HttpServletRequest request, HttpServletResponse response) {
        Hotel newHotel = new Hotel();
        newHotel.setName(hotel.getName());
        newHotel.setDescription(hotel.getDescription());
        newHotel.setCity(hotel.getCity());
        newHotel.setRating(hotel.getRating());

        Hotel createdHotel = this.hotelService.createHotel(newHotel);
        response.setHeader("Location", request.getRequestURL().append("/").append(createdHotel.getId()).toString());
    }

}
