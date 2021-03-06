package com.tenniscourts.guests;

import com.tenniscourts.config.BaseRestController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping( "/guests" )
public class GuestController extends BaseRestController {

  @Autowired
  private final GuestService guestService;

  @PostMapping
  @ApiOperation( "Creates a new guest" )
  @ApiResponses( value = { @ApiResponse( code = 201, message = "Guest created with success" ) } )
  public ResponseEntity< Void > addGuest( @Valid @RequestBody GuestDTO guestDTO ) {
    return ResponseEntity
            .created( locationByEntity( guestService.addGuest( guestDTO ).getId() ) )
            .build();
  }

  @GetMapping( "/{id}" )
  @ApiOperation( "Find Guest by id" )
  @ApiResponses( value = { @ApiResponse( code = 200, message = "Ok" ) } )
  public ResponseEntity< GuestDTO > findGuestById( @PathVariable( "id" ) Long guestId ) {
    return ResponseEntity.ok( guestService.findGuestById( guestId ) );
  }

  @GetMapping( "named/{name}" )
  @ApiOperation( "Find Guest by name" )
  @ApiResponses( value = { @ApiResponse( code = 200, message = "Ok" ) } )
  public ResponseEntity< List<GuestDTO> > findGuestByName( @PathVariable( "name" ) String guestName ) {
    return ResponseEntity.ok( guestService.findGuestByName( guestName ) );
  }

  @DeleteMapping("/{id}")
  @ApiOperation( "Delete Guest by id" )
  @ApiResponses( value = { @ApiResponse( code = 200, message = "Ok" ) } )
  public void delete(@PathVariable("id") Long id) {
    this.guestService.deleteById(id);
  }

  @GetMapping
  @ApiOperation( "Find all Guests" )
  @ApiResponses( value = { @ApiResponse( code = 200, message = "Ok" ) } )
  public Iterable<GuestDTO> findAll( Pageable pageable) {
    return guestService.findAll(pageable);
  }
}