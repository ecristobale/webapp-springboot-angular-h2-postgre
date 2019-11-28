package com.ecristobale.microserv.restapi.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecristobale.microserv.restapi.models.MyEntity;
import com.ecristobale.microserv.restapi.services.IMyService;

@RestController @CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/restapi/")
public class RestApiResource {
	
	@Autowired
	IMyService myService;

	@RequestMapping(method = RequestMethod.GET, value = "entidad/{idRequest}"
			, produces = "application/json")
	public ResponseEntity<?> getEntidadById(@RequestHeader("Authorization") String authToken, 
			@PathVariable(value="idRequest") long id){
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add(authToken, "getEntidadById");
		MyEntity entity = myService.getEntidadById(id);
		if(entity == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		ResponseEntity<MyEntity> responseEntity = new ResponseEntity<MyEntity>(entity, responseHeaders, HttpStatus.OK);
		return responseEntity;
	}

	@RequestMapping(method = RequestMethod.GET, value = "entidad"
			, produces = "application/json")
	public ResponseEntity<?> getAllEntidad(@RequestHeader("Authorization") String authToken){
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add(authToken, "getAllEntidad");
		List<MyEntity> entities = myService.getAllEntidad();
		ResponseEntity<List<MyEntity>> responseEntity = new ResponseEntity<List<MyEntity>>(entities, responseHeaders, HttpStatus.OK);
		return responseEntity;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "entidad")
    public ResponseEntity<?> create(@Valid @RequestBody MyEntity myEntity) {
        return ResponseEntity.ok(myService.createMyEntity(myEntity));
    }
	
	@RequestMapping(method = RequestMethod.DELETE, value = "entidad/{idRequest}")
    public ResponseEntity<?> deleteById(@PathVariable("idRequest") long id,
    		@RequestHeader("Authorization") String authToken) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add(authToken, "deleteById");
		boolean deleted = myService.deleteMyEntity(id);
        return new ResponseEntity<>(deleted, responseHeaders, deleted ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
	
	@RequestMapping(method = RequestMethod.PUT, value = "entidad/{idRequest}")
    public ResponseEntity<?> updateEntityById(@PathVariable("idRequest") Long id, @RequestHeader("Authorization") String authToken,
    		@Valid @RequestBody MyEntity myEntity) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add(authToken, "updateEntityById");
        if (myService.getEntidadById(id) == null) {
            return new ResponseEntity<>(responseHeaders, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(myService.updateMyEntity(myEntity, id), responseHeaders, HttpStatus.OK);
    }

	public IMyService getMyService() {
		return myService;
	}
	public void setMyService(IMyService myService) {
		this.myService = myService;
	}
}
