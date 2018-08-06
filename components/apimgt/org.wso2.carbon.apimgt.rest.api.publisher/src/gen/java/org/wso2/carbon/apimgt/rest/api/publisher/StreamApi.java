package org.wso2.carbon.apimgt.rest.api.publisher;


import io.swagger.annotations.ApiParam;
import org.wso2.carbon.apimgt.core.streams.EventStream;
import org.wso2.carbon.apimgt.rest.api.publisher.factories.StreamApiServiceFactory;
import org.wso2.msf4j.Microservice;
import org.wso2.msf4j.Request;
import org.osgi.service.component.annotations.Component;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Component(
    name = "org.wso2.carbon.apimgt.rest.api.publisher.StreamApi",
    service = Microservice.class,
    immediate = true
)
@Path("/api/am/publisher/v1.[\\d]+/stream")
@Consumes({ "application/json", "application/x-www-form-urlencoded", "multipart/form-data" })
@Produces({ "application/json" })
@ApplicationPath("/stream")
@io.swagger.annotations.Api(description = "the stream API")
public class StreamApi implements Microservice  {
   private final StreamApiService delegate = StreamApiServiceFactory.getStreamApi();

    @POST
    
    @Consumes({ "application/json", "application/x-www-form-urlencoded", "multipart/form-data" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Create a new Stream", notes = "This operation can be used to create a new Stream specifying the details of the Stream in the payload. The new Stream will be in `CREATED` state.  There is a special capability for a user who has `APIM Admin` permission such that he can create Streams on behalf of other users. For that he can to specify `\"provider\" : \"some_other_user\"` in the payload so that the API's creator will be shown as `some_other_user` in the UI. ", response = EventStream.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "OAuth2Security", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "apim:stream_create", description = "Create Stream")
        })
    }, tags={ "Stream (Collection)", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Created. Successful response with the newly created object as entity in the body. Location header contains URL of newly created entity. ", response = EventStream.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request. Invalid request or validation error. ", response = EventStream.class),
        
        @io.swagger.annotations.ApiResponse(code = 415, message = "Unsupported Media Type. The entity of the request was in a not supported format. ", response = EventStream.class) })
    public Response streamPost(@ApiParam(value = "Stream object that needs to be added " , required = true) EventStream stream
 , @Context Request request)
    throws NotFoundException {
        
        return delegate.streamPost(stream, request);
    }
}
