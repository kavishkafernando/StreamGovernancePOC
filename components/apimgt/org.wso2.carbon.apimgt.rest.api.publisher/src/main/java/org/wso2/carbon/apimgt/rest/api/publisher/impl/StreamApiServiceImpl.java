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


package org.wso2.carbon.apimgt.rest.api.publisher.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.carbon.apimgt.core.api.APIPublisher;
import org.wso2.carbon.apimgt.core.exception.APIManagementException;
import org.wso2.carbon.apimgt.core.exception.APIMgtResourceNotFoundException;
import org.wso2.carbon.apimgt.core.exception.ExceptionCodes;
import org.wso2.carbon.apimgt.core.streams.EventStream;
import org.wso2.carbon.apimgt.core.util.APIMgtConstants;
import org.wso2.carbon.apimgt.rest.api.common.util.RestApiUtil;
import org.wso2.carbon.apimgt.rest.api.publisher.*;
import org.wso2.carbon.apimgt.rest.api.publisher.NotFoundException;
import org.wso2.carbon.apimgt.rest.api.common.dto.ErrorDTO;
import org.wso2.carbon.apimgt.rest.api.publisher.dto.StreamListDTO;
import org.wso2.carbon.apimgt.rest.api.publisher.utils.MappingUtil;
import org.wso2.carbon.apimgt.rest.api.publisher.utils.RestAPIPublisherUtil;
import org.wso2.msf4j.Request;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.HashMap;

/**
 * Implementation of Stream related endpoints.
 *
 */
public class StreamApiServiceImpl extends StreamApiService {

    private static final Logger log = LoggerFactory.getLogger(StreamApiServiceImpl.class);
    /**
     * Retrives all Streams that qualifies for the given fitering attributes
     *
     * @param limit       maximum Streams to return
     * @param offset      starting position of the pagination
     * @param query       search query
     * @param ifNoneMatch If-None-Match header value
     * @param request     msf4j request object
     * @return a list of qualifying APIs
     * @throws NotFoundException When the particular resource does not exist in the system
     */
    @Override
    public Response streamGet(Integer limit, Integer offset, String query, String ifNoneMatch, Request request) throws NotFoundException {
        String username = RestApiUtil.getLoggedInUsername(request);
        StreamListDTO streamListDTO = null;
        try {
            streamListDTO = MappingUtil.toStreamListDTO(RestAPIPublisherUtil.getApiPublisher(username).searchStreams(limit, offset, query));
            return Response.ok().entity(streamListDTO).build();
        } catch (APIManagementException e) {
            String errorMessage = "Error while retrieving Streams";
            HashMap<String, String> paramList = new HashMap<String, String>();
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(e.getErrorHandler(), paramList, e);
            log.error(errorMessage, e);
            return Response.status(e.getErrorHandler().getHttpStatusCode()).entity(errorDTO).build();
        }
    }

    /**
     * Creates a new Stream
     *
     * @param stream    EventStream model including the Stream details
     * @param request msf4j request object
     * @return Newly created Stream
     * @throws NotFoundException When the particular resource does not exist in the system
     */
    @Override
    public Response streamPost(EventStream stream, Request request) throws NotFoundException {
        System.out.println("Stream API is working");
        String username = RestApiUtil.getLoggedInUsername(request);
        EventStream.StreamBuilder streamBuilder = MappingUtil.toEventStream(stream);
        System.out.println(stream.toString());
        try {
            APIPublisher apiPublisher = RestAPIPublisherUtil.getApiPublisher(username);
            apiPublisher.addEventStream(streamBuilder);
            EventStream returnStream = apiPublisher.getStreambyUUID(streamBuilder.getId());
            return Response.status(Response.Status.CREATED).entity(MappingUtil.toEventStream(returnStream)).build();

        } catch (APIManagementException e) {
            String errorMessage = "Error while adding new Stream : " +  stream.getName() + "-" + stream.getVersion();
            HashMap<String, String> paramList = new HashMap<String, String>();
            paramList.put(APIMgtConstants.StreamExceptionsConstants.STREAM_NAME, stream.getName());
            paramList.put(APIMgtConstants.StreamExceptionsConstants.STREAM_VERSION, stream.getVersion());
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(e.getErrorHandler(), paramList);
            log.error(errorMessage, e);
            return Response.status(e.getErrorHandler().getHttpStatusCode()).entity(errorDTO).build();
        }
    }
    /**
     * Retrives an Stream by UUID
     *
     * @param streamId           UUID of Stream
     * @param ifNoneMatch     If-None-Match header value
     * @param ifModifiedSince If-Modified-Since header value
     * @param request         msf4j request object
     * @return Stream which is identified by the given UUID
     * @throws NotFoundException When the particular resource does not exist in the system
     */
    @Override
    public Response streamStreamIdGet(String streamId, String ifNoneMatch, String ifModifiedSince, Request request) throws NotFoundException {
        String username = RestApiUtil.getLoggedInUsername(request);

        try {
            if (!RestAPIPublisherUtil.getApiPublisher(username).isStreamExists(streamId)){
                String errorMessage = "Stream not found : " + streamId;
                APIMgtResourceNotFoundException e = new APIMgtResourceNotFoundException(errorMessage,
                        ExceptionCodes.STREAM_NOT_FOUND);
                HashMap<String, String> paramList = new HashMap<String, String>();
                paramList.put(APIMgtConstants.StreamExceptionsConstants.STREAM_ID, streamId);
                ErrorDTO errorDTO = RestApiUtil.getErrorDTO(e.getErrorHandler(), paramList);
                log.error(errorMessage, e);
                return Response.status(e.getErrorHandler().getHttpStatusCode()).entity(errorDTO).build();
            }
            EventStream stream = MappingUtil.toEventStream(RestAPIPublisherUtil.getApiPublisher(username).getStreambyUUID(streamId)).build();
            return Response.ok().header(HttpHeaders.ETAG,  "\"").entity(stream).build();

        } catch (APIManagementException e) {
            String errorMessage = "Error while retrieving Stream : " + streamId;
            HashMap<String, String> paramList = new HashMap<String, String>();
            paramList.put(APIMgtConstants.StreamExceptionsConstants.STREAM_ID, streamId);
            ErrorDTO errorDTO = RestApiUtil.getErrorDTO(e.getErrorHandler(), paramList);
            log.error(errorMessage, e);
            return Response.status(e.getErrorHandler().getHttpStatusCode()).entity(errorDTO).build();
        }
    }
}
