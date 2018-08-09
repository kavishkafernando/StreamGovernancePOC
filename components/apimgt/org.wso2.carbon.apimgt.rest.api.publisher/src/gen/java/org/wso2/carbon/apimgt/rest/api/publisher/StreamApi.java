/*
 *
 *   Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *   WSO2 Inc. licenses this file to you under the Apache License,
 *   Version 2.0 (the "License"); you may not use this file except
 *   in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */
package org.wso2.carbon.apimgt.rest.api.publisher;

import io.swagger.annotations.ApiParam;
import org.wso2.carbon.apimgt.core.streams.EventStream;
import org.wso2.carbon.apimgt.rest.api.publisher.dto.StreamListDTO;
import org.wso2.carbon.apimgt.rest.api.publisher.factories.StreamApiServiceFactory;
import org.wso2.msf4j.Microservice;
import org.wso2.msf4j.Request;
import org.osgi.service.component.annotations.Component;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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

    @OPTIONS
    @GET
    
    @Consumes({ "application/json", "application/x-www-form-urlencoded", "multipart/form-data" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Retrieve/Search Streams ", notes = "This operation provides you a list of available Streams qualifying under a given search condition.  Each retrieved Stream is represented with a minimal amount of attributes. ", response = StreamListDTO.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "OAuth2Security", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "apim:api_view", description = "View API")
        })
    }, tags={ "Stream (Collection)", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK. List of qualifying APIs is returned. ", response = StreamListDTO.class),
        
        @io.swagger.annotations.ApiResponse(code = 304, message = "Not Modified. Empty body because the client has already the latest version of the requested resource (Will be supported in future). ", response = StreamListDTO.class),
        
        @io.swagger.annotations.ApiResponse(code = 406, message = "Not Acceptable. The requested media type is not supported ", response = StreamListDTO.class) })
    public Response streamGet(@ApiParam(value = "Maximum size of resource array to return. ", defaultValue= "25") @DefaultValue("25") @QueryParam("limit") Integer limit
, @ApiParam(value = "Starting point within the complete list of items qualified. ", defaultValue= "0") @DefaultValue("0") @QueryParam("offset") Integer offset
, @ApiParam(value = "**Search condition**.  You can search in attributes by using an **\"<attribute>:\"** modifier.  Eg. \"provider:wso2\" will match an Stream if the provider of the Stream is exactly \"wso2\".  Additionally you can use wildcards.  Eg. \"provider:wso2*\" will match an Stream if the provider of the Stream starts with \"wso2\".  Supported attribute modifiers are [**version, context, lifeCycleStatus, description, subcontext, doc, provider**]  If no advanced attribute modifier has been specified, search will match the given query string against API Name. ") @QueryParam("query") String query
, @ApiParam(value = "Validator for conditional requests; based on the ETag of the formerly retrieved variant of the resourec. " )@HeaderParam("If-None-Match") String ifNoneMatch
 , @Context Request request)
    throws NotFoundException {
        limit = limit == null ? Integer.valueOf("25"): limit;
        offset= offset == null? Integer.valueOf("0"): offset;
        
        return delegate.streamGet(limit, offset, query, ifNoneMatch, request);
    }

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
    @OPTIONS
    @GET
    @Path("/{streamId}")
    @Consumes({ "application/json", "application/x-www-form-urlencoded", "multipart/form-data" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Get details of a Stream", notes = "Using this operation, you can retrieve complete details of a single Stream. You need to provide the Id of the Stream to retrive it. ", response = EventStream.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "OAuth2Security", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "apim:api_view", description = "View API")
        })
    }, tags={ "API (Individual)", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK. Requested Stream is returned ", response = EventStream.class),
        
        @io.swagger.annotations.ApiResponse(code = 304, message = "Not Modified. Empty body because the client has already the latest version of the requested resource (Will be supported in future). ", response = EventStream.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Not Found. Requested Stream does not exist. ", response = EventStream.class),
        
        @io.swagger.annotations.ApiResponse(code = 406, message = "Not Acceptable. The requested media type is not supported ", response = EventStream.class) })
    public Response streamStreamIdGet(@ApiParam(value = "**Stream ID** consisting of the **UUID** of the Stream. The combination of the provider of the API, name of the Stream and the version is also accepted as a valid Stream ID. Should be formatted as **provider-name-version**. ",required=true) @PathParam("streamId") String streamId
,@ApiParam(value = "Validator for conditional requests; based on the ETag of the formerly retrieved variant of the resourec. " )@HeaderParam("If-None-Match") String ifNoneMatch
,@ApiParam(value = "Validator for conditional requests; based on Last Modified header of the formerly retrieved variant of the resource. " )@HeaderParam("If-Modified-Since") String ifModifiedSince
 ,@Context Request request)
    throws NotFoundException {
        
        return delegate.streamStreamIdGet(streamId,ifNoneMatch,ifModifiedSince,request);
    }
}
