package com.khoubyari.example.api.rest;

import com.khoubyari.example.api.view.HotelView;
import com.khoubyari.example.api.view.PersonView;
import com.khoubyari.example.dao.jpa.PersonRepository;
import com.khoubyari.example.domain.Hotel;
import com.khoubyari.example.domain.Person;
import com.khoubyari.example.service.HotelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Demonstrates how to set up RESTful API endpoints using Spring MVC
 */

@RestController
@RequestMapping(value = "/example/v1/person")
@Api(tags = {"hotels"})
public class CorruptedController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private HotelController hotelController;


    @RequestMapping(value = "/hotel",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a hotel resource.", notes = "Returns the URL of the new resource in the Location header.")
    public HotelView createHotel(@RequestBody Hotel hotel,
                                 HttpServletRequest request, HttpServletResponse response) {
        Hotel createdHotel = this.hotelService.createHotel(hotel);
        response.setHeader("Location", request.getRequestURL().append("/").append(createdHotel.getId()).toString());

        HotelView newHotel = new HotelView();
        newHotel.setName(hotel.getName());
        newHotel.setDescription(hotel.getDescription());
        newHotel.setCity(hotel.getCity());
        newHotel.setRating(hotel.getRating());

        return newHotel;
    }


    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a hotel resource.", notes = "Returns the URL of the new resource in the Location header.")
    public PersonView createPerson(@RequestBody Person person,
                                   HttpServletRequest request, HttpServletResponse response) {
        Person createdPerson = this.personRepository.save(person);
        response.setHeader("Location", request.getRequestURL().append("/").append(createdPerson.getId()).toString());

        PersonView newPerson = new PersonView();
        newPerson.setName(person.getName());
        newPerson.setSurname(person.getSurname());

        return newPerson;

    }
}
